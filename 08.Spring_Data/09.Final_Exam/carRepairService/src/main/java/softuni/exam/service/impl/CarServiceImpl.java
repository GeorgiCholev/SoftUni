package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.carImports.CarImportDTO;
import softuni.exam.models.dto.carImports.CarImportRoot;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

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
public class CarServiceImpl implements CarService {

    private static final File CARS_XML_FILE =
            new File(String.format(BASIC_PATH_FORMAT, "xml", "cars.xml"));

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final Unmarshaller unmarshaller;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Validator validator,
                          @Qualifier(CAR_UNMARSHALLER) Unmarshaller unmarshaller) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public Optional<Car> findById(long id) {
        return this.carRepository.findById(id);
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(CARS_XML_FILE.toPath());
    }

    @Override
    public String importCars() throws JAXBException {
        CarImportRoot carImports = (CarImportRoot) this.unmarshaller.unmarshal(CARS_XML_FILE);

        return carImports.getCars().stream()
                .map(this::importCar)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importCar(CarImportDTO dto) {
        Set<ConstraintViolation<CarImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Car> optCar = this.carRepository.findByPlateNumber(dto.getPlateNumber());

            if (optCar.isEmpty()) {

                Car mappedCar = this.modelMapper.map(dto, Car.class);

                this.carRepository.save(mappedCar);

                return String.format(CORRECT_DATA_FORMAT,mappedCar);
            }
        }
        return String.format(INCORRECT_DATA_FORMAT, "car");
    }
}
