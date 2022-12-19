package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.personImports.PersonImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Person;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.CountryService;
import softuni.exam.service.PersonService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
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
public class PersonServiceImpl implements PersonService {

    private static final Path PEOPLE_JSON_PATH =
            new File(String.format(BASIC_PATH_FORMAT, "json", "people.json")).toPath();

    private final PersonRepository personRepository;

    private final CountryService countryService;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, CountryService countryService, Gson gson,
                             ModelMapper modelMapper, Validator validator) {
        this.personRepository = personRepository;
        this.countryService = countryService;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return this.personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files.readString(PEOPLE_JSON_PATH);
    }

    @Override
    public String importPeople() throws IOException, JAXBException {

        String json = this.readPeopleFromFile();
        PersonImportDTO[] peopleImports = this.gson.fromJson(json, PersonImportDTO[].class);

        return Arrays.stream(peopleImports)
                .map(this::importPerson)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importPerson(PersonImportDTO dto) {

        Set<ConstraintViolation<PersonImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Person> optPerson = this.personRepository
                    .findByFirstNameOrEmailOrPhone(dto.getFirstName(), dto.getEmail(), dto.getPhone());

            Optional<Country> optCountry = this.countryService.findById(dto.getCountry());

            if (optPerson.isEmpty() && optCountry.isPresent()) {

                Person mappedPerson = this.modelMapper.map(dto, Person.class);
                mappedPerson.setCountry(optCountry.get());
                this.personRepository.save(mappedPerson);

                return String.format(CORRECT_DATA_FORMAT, mappedPerson);
            }

        }

        return String.format(INCORRECT_DATA_FORMAT, "person");
    }
}
