package softuni.exam.service;

import softuni.exam.models.entity.Part;

import java.io.IOException;
import java.util.Optional;

public interface PartService {

    Optional<Part> findById(long id);

    boolean areImported();

    String readPartsFileContent() throws IOException;

    String importParts() throws IOException;
}
