package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicImportDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;

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
public class MechanicServiceImpl implements MechanicService {


    private static final Path MECHANICS_JSON_PATH =
            new File(String.format(BASIC_PATH_FORMAT, "json", "mechanics.json")).toPath();

    private final MechanicRepository mechanicRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;

    private final Validator validator;

    @Autowired
    public MechanicServiceImpl(MechanicRepository mechanicRepository, ModelMapper modelMapper,
                               Gson gson, Validator validator) {
        this.mechanicRepository = mechanicRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public Optional<Mechanic> findByFirstName(String firstName) {
        return this.mechanicRepository.findByFirstName(firstName);
    }

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(MECHANICS_JSON_PATH);
    }

    @Override
    public String importMechanics() throws IOException {

        String json = this.readMechanicsFromFile();
        MechanicImportDTO[] mechanicImports = this.gson.fromJson(json, MechanicImportDTO[].class);

        return Arrays.stream(mechanicImports)
                .map(this::importMechanic)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importMechanic(MechanicImportDTO dto) {

        Set<ConstraintViolation<MechanicImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Mechanic> optMechanic = this.mechanicRepository
                    .findByFirstNameOrEmailOrPhone(dto.getFirstName(), dto.getEmail(), dto.getPhone());

            if (optMechanic.isEmpty()) {

                Mechanic mappedMechanic = this.modelMapper.map(dto, Mechanic.class);

                this.mechanicRepository.save(mappedMechanic);

                return String.format(CORRECT_DATA_FORMAT, mappedMechanic);
            }
        }

        return String.format(INCORRECT_DATA_FORMAT, "mechanic");
    }
}
