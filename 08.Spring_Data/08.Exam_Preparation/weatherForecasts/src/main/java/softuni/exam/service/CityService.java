package softuni.exam.service;

import softuni.exam.models.entity.City;

import java.io.IOException;
import java.util.Optional;

public interface CityService {

    Optional<City> findById(long id);

    boolean areImported();

    String readCitiesFileContent() throws IOException;
	
	String importCities() throws IOException;
}
