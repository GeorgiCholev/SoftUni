package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.taskImports.TaskImportDTO;
import softuni.exam.models.dto.taskImports.TaskImportRoot;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.MechanicService;
import softuni.exam.service.PartService;
import softuni.exam.service.TaskService;
import softuni.exam.util.CarType;

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
public class TaskServiceImpl implements TaskService {


    private static final File TASKS_XML_FILE =
            new File(String.format(BASIC_PATH_FORMAT, "xml", "tasks.xml"));

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final Unmarshaller unmarshaller;

    private final MechanicService mechanicService;

    private final CarService carService;

    private final PartService partService;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, Validator validator,
                           @Qualifier(TASK_UNMARSHALLER) Unmarshaller unmarshaller,
                           MechanicService mechanicService, CarService carService, PartService partService) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.unmarshaller = unmarshaller;
        this.mechanicService = mechanicService;
        this.carService = carService;
        this.partService = partService;
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(TASKS_XML_FILE.toPath());
    }

    @Override
    public String importTasks() throws JAXBException {
        TaskImportRoot taskImports = (TaskImportRoot) this.unmarshaller.unmarshal(TASKS_XML_FILE);

        return taskImports.getTasks().stream()
                .map(this::importTask)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importTask(TaskImportDTO dto) {
        Set<ConstraintViolation<TaskImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Mechanic> optMechanic = this.mechanicService.findByFirstName(dto.getMechanic().getFirstName());
            Optional<Car> optCar = this.carService.findById(dto.getCar().getId());

            if (optMechanic.isPresent() && optCar.isPresent()) {
                Mechanic mechanic = optMechanic.get();
                Car car = optCar.get();
                Part part = this.partService.findById(dto.getPart().getId()).get();

                Task mappedTask = this.modelMapper.map(dto, Task.class);
                mappedTask.setMechanic(mechanic);
                mappedTask.setCar(car);
                mappedTask.setPart(part);

                this.taskRepository.save(mappedTask);

                return String.format(CORRECT_DATA_FORMAT, mappedTask);
            }
        }

        return String.format(INCORRECT_DATA_FORMAT, "task");
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return this.taskRepository
                .findAllByCar_CarTypeOrderByPriceDesc(CarType.coupe)
                .stream().map(Task::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
