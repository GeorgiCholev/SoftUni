package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.companyImports.CompanyImportDTO;
import softuni.exam.models.dto.companyImports.CompanyImportRoot;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.service.CountryService;
import softuni.exam.util.Constants;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.util.Constants.*;

@Service
public class CompanyServiceImpl implements CompanyService {


    private static final File COMPANIES_XML_FILE =
            new File(String.format(BASIC_PATH_FORMAT, "xml", "companies.xml"));

    private final CompanyRepository companyRepository;

    private final CountryService countryService;

    private final Unmarshaller unmarshaller;

    private final Validator validator;

    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CountryService countryService,
                              @Qualifier(COMPANY_UNMARSHALLER) Unmarshaller unmarshaller,
                              Validator validator, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.countryService = countryService;
        this.unmarshaller = unmarshaller;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id);
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(COMPANIES_XML_FILE.toPath());
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {

        CompanyImportRoot companyImports = (CompanyImportRoot) this.unmarshaller.unmarshal(COMPANIES_XML_FILE);

        return companyImports.getCompanies().stream()
                .map(this::importCompany)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importCompany(CompanyImportDTO dto) {

        Set<ConstraintViolation<CompanyImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Company> optCompany = this.companyRepository.findByName(dto.getName());
            Optional<Country> optCountry = this.countryService.findById(dto.getCountry());

            if (optCompany.isEmpty() && optCountry.isPresent()) {

                Company mappedCompany = this.modelMapper.map(dto, Company.class);
                mappedCompany.setCountry(optCountry.get());
                this.companyRepository.save(mappedCompany);

                return String.format(CORRECT_DATA_FORMAT, mappedCompany);
            }
        }
        return String.format(INCORRECT_DATA_FORMAT, "company");
    }
}
