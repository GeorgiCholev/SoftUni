package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentImportDTO;
import softuni.exam.models.dto.ApartmentImportWrapper;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
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
public class ApartmentServiceImpl implements ApartmentService {

    private static final File APARTMENT_XML_FILE =
            new File(String.format(BASIC_PATH, "xml", "apartments.xml"));

    private final ApartmentRepository apartmentRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final Unmarshaller unmarshaller;

    private final TownRepository townRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, ModelMapper modelMapper, Validator validator,
                                @Qualifier(APARTMENT_UNMARSHALLER) Unmarshaller unmarshaller,
                                TownRepository townRepository) {
        this.apartmentRepository = apartmentRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.unmarshaller = unmarshaller;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(APARTMENT_XML_FILE.toPath());
    }

    @Override
    public String importApartments() throws JAXBException {
        ApartmentImportWrapper apartmentsWrapper = (ApartmentImportWrapper) unmarshaller.unmarshal(APARTMENT_XML_FILE);

        return apartmentsWrapper.getApartments()
                .stream()
                .map(this::importApartment)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importApartment(ApartmentImportDTO dto) {
        Set<ConstraintViolation<ApartmentImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Apartment> optApartment =
                    this.apartmentRepository.findByTown_TownNameAndArea(dto.getTownName(), dto.getArea());

            if (optApartment.isEmpty()) {

                Apartment mappedApartment = this.modelMapper.map(dto, Apartment.class);

                Town town = this.townRepository.findByTownName(dto.getTownName()).get();
                mappedApartment.setTown(town);

                this.apartmentRepository.save(mappedApartment);

                return String.format(CORRECT_DATA, mappedApartment);
            }
        }

        return String.format(INCORRECT_DATA, "apartment");
    }
}
