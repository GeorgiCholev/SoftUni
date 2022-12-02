package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.forecastsDTOs.ForecastImportDTO;
import softuni.exam.models.dto.forecastsDTOs.ForecastImportRoot;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.DayOfWeek;

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
public class ForecastServiceImpl implements ForecastService {

    private static final File FORECASTS_XML_FILE =
            new File(String.format(BASIC_PATH_FORMAT, "xml", "forecasts.xml"));
    private final ForecastRepository forecastRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final Unmarshaller unmarshaller;

    private final CityService cityService;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper modelMapper,
                               Validator validator, Unmarshaller unmarshaller, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.unmarshaller = unmarshaller;
        this.cityService = cityService;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(FORECASTS_XML_FILE.toPath());
    }

    @Override
    public String importForecasts() throws JAXBException {

        ForecastImportRoot forecastImports = (ForecastImportRoot) this.unmarshaller.unmarshal(FORECASTS_XML_FILE);

        return forecastImports.getForecasts()
                .stream()
                .map(this::importForecast)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importForecast(ForecastImportDTO dto) {

        Set<ConstraintViolation<ForecastImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<City> optCity = this.cityService.findById(dto.getCityId());

            if (optCity.isPresent()) {

                City city = optCity.get();
                DayOfWeek dayOfWeek = dto.getDayOfWeek();
                Optional<Forecast> optForecast = this.forecastRepository.findByDayOfWeekAndCity(dayOfWeek, city);

                if (optForecast.isEmpty()) {

                    Forecast mappedForecast = this.modelMapper.map(dto, Forecast.class);
                    mappedForecast.setCity(city);

                    this.forecastRepository.save(mappedForecast);

                    return String.format(CORRECT_DATA_FORMAT, mappedForecast);
                }

            }

        }

        return String.format(INCORRECT_DATA_FORMAT, "forecast");
    }

    @Override
    public String exportForecasts() {
        return this.forecastRepository
                .findAllByDayOfWeekAndCityPopulationJPQL(DayOfWeek.SUNDAY, 150_000)
                .stream()
                .map(Forecast::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
