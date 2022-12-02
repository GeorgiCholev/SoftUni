package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
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
public class CityServiceImpl implements CityService {

    private static final Path CITIES_JSON_PATH =
            new File(String.format(BASIC_PATH_FORMAT, "json", "cities.json")).toPath();

    private final CityRepository cityRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final Gson gson;

    private final CountryService countryService;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper, Validator validator,
                           Gson gson, CountryService countryService) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
        this.countryService = countryService;
    }


    @Override
    public Optional<City> findById(long id) {
        return this.cityRepository.findById(id);
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(CITIES_JSON_PATH);
    }

    @Override
    public String importCities() throws IOException {

        String json = this.readCitiesFileContent();
        CityImportDTO[] cityImports = this.gson.fromJson(json, CityImportDTO[].class);
        return Arrays.stream(cityImports)
                .map(this::importCity)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importCity(CityImportDTO dto) {
        Set<ConstraintViolation<CityImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<City> optCity = this.cityRepository.findByCityName(dto.getCityName());
            Optional<Country> optCountry = this.countryService.findById(dto.getCountry());

            if (optCity.isEmpty() && optCountry.isPresent()) {
                Country country = optCountry.get();

                City mappedCity = this.modelMapper.map(dto, City.class);
                mappedCity.setCountry(country);

                this.cityRepository.save(mappedCity);

                return String.format(CORRECT_DATA_FORMAT, mappedCity);
            }
        }

        return String.format(INCORRECT_DATA_FORMAT, "city");
    }
}
