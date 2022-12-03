package softuni.exam.service;


import softuni.exam.models.entity.Car;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

public interface CarService {

    Optional<Car> findById(long id);

    boolean areImported();

    String readCarsFromFile() throws IOException;

    String importCars() throws JAXBException;
}
