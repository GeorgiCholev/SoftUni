package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface TaskService {

    boolean areImported();

    String readTasksFileContent() throws IOException;

    String importTasks() throws JAXBException;

    String getCoupeCarTasksOrderByPrice();
}
