package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static softuni.exam.util.Constants.*;

@Service
public class TownServiceImpl implements TownService {

    private final Path TOWN_JSON_PATH = new File(String.format(BASIC_PATH, "json", "towns.json")).toPath();
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, Validator validator) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(TOWN_JSON_PATH);
    }

    @Override
    public String importTowns() throws IOException {

        String townsJson = this.readTownsFileContent();

        TownImportDTO[] townImports = gson.fromJson(townsJson, TownImportDTO[].class);

        List<String> result = new ArrayList<>();

        for (TownImportDTO townImport : townImports) {
            Set<ConstraintViolation<TownImportDTO>> violations = validator.validate(townImport);

            if (violations.isEmpty()) {

                Optional<Town> optTown = this.townRepository.findByTownName(townImport.getTownName());
                if (optTown.isEmpty()) {

                    Town mappedTown = modelMapper.map(townImport, Town.class);
                    this.townRepository.save(mappedTown);
                    result.add(String.format(CORRECT_DATA, mappedTown));

                    continue;
                }

            }

            result.add(String.format(INCORRECT_DATA, "town"));
        }

        return String.join(System.lineSeparator(), result);
    }

}
