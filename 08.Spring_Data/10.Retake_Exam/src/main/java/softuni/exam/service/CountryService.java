package softuni.exam.service;


import softuni.exam.models.entity.Country;

import java.io.IOException;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);
    boolean areImported();

    String readCountriesFileContent() throws IOException;

    String importCountries() throws IOException;
}
