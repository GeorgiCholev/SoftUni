package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.countryImports.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.util.Constants.*;

@Service
public class CountryServiceImpl implements CountryService {


    private static final Path COUNTRIES_JSON_PATH =
            new File(String.format(BASIC_PATH_FORMAT, "json", "countries.json")).toPath();

    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper modelMapper,
                              Validator validator) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }


    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFileContent() throws IOException {
        return Files.readString(COUNTRIES_JSON_PATH);
    }

    @Override
    public String importCountries() throws IOException {

        String json = this.readCountriesFileContent();

        CountryImportDTO[] countryImports = this.gson.fromJson(json, CountryImportDTO[].class);

        return Arrays.stream(countryImports)
                .map(this::importCountry)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importCountry(CountryImportDTO dto) {

        Set<ConstraintViolation<CountryImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Country> optCountry = this.countryRepository.findByNameOrCode(dto.getName(), dto.getCode());

            if (optCountry.isEmpty()) {

                Country mappedCountry = this.modelMapper.map(dto, Country.class);
                this.countryRepository.save(mappedCountry);

                return String.format(CORRECT_DATA_FORMAT, mappedCountry);
            }

        }

        return String.format(INCORRECT_DATA_FORMAT, "country");
    }
}
