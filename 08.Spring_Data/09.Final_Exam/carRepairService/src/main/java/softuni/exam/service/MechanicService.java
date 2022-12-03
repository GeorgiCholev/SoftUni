package softuni.exam.service;


import softuni.exam.models.entity.Mechanic;

import java.io.IOException;
import java.util.Optional;

public interface MechanicService {

    Optional<Mechanic> findByFirstName(String firstName);

    boolean areImported();

    String readMechanicsFromFile() throws IOException;

    String importMechanics() throws IOException;
}
