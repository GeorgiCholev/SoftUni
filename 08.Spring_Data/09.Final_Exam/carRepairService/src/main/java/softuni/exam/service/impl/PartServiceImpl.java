package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartImportDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;

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
public class PartServiceImpl implements PartService {

    private static final Path PARTS_JSON_PATH =
            new File(String.format(BASIC_PATH_FORMAT, "json", "parts.json")).toPath();

    private final PartRepository partRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;

    private final Validator validator;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, Gson gson, Validator validator) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public Optional<Part> findById(long id) {
        return this.partRepository.findById(id);
    }

    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(PARTS_JSON_PATH);
    }

    @Override
    public String importParts() throws IOException {

        String json = this.readPartsFileContent();

        PartImportDTO[] partImports = this.gson.fromJson(json, PartImportDTO[].class);

        return Arrays.stream(partImports)
                .map(this::importPart)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importPart(PartImportDTO dto) {
        Set<ConstraintViolation<PartImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Part> optPartName = this.partRepository.findByPartName(dto.getPartName());

            if (optPartName.isEmpty()) {

                Part mappedPart = this.modelMapper.map(dto, Part.class);

                this.partRepository.save(mappedPart);

                return String.format(CORRECT_DATA_FORMAT, mappedPart);
            }
        }

        return String.format(INCORRECT_DATA_FORMAT, "part");
    }
}
