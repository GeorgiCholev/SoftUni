package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jobImports.JobImportDTO;
import softuni.exam.models.dto.jobImports.JobImportRoot;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.service.JobService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.util.Constants.*;

@Service
public class JobServiceImpl implements JobService {

    private static final File JOBS_XML_FILE =
            new File(String.format(BASIC_PATH_FORMAT, "xml", "jobs.xml"));

    private final JobRepository jobRepository;

    private final CompanyService companyService;

    private final Unmarshaller unmarshaller;

    private final Validator validator;

    private final ModelMapper modelMapper;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, CompanyService companyService,
                          @Qualifier(JOB_UNMARSHALLER) Unmarshaller unmarshaller,
                          Validator validator, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
        this.unmarshaller = unmarshaller;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(JOBS_XML_FILE.toPath());
    }

    @Override
    public String importJobs() throws IOException, JAXBException {

        JobImportRoot jobImports = (JobImportRoot) this.unmarshaller.unmarshal(JOBS_XML_FILE);

        return jobImports.getJobs().stream()
                .map(this::importJob)
                .collect(Collectors.joining(System.lineSeparator()));
    }


    private String importJob(JobImportDTO dto) {

        Set<ConstraintViolation<JobImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Company> optCompany = this.companyService.findById(dto.getCompany());

            if (optCompany.isPresent()) {

                Job mappedJob = this.modelMapper.map(dto, Job.class);
                mappedJob.setCompany(optCompany.get());
                this.jobRepository.save(mappedJob);

                return String.format(CORRECT_DATA_FORMAT, mappedJob);
            }
        }


        return String.format(INCORRECT_DATA_FORMAT, "job");
    }

    @Override
    public String getBestJobs() {

        return this.jobRepository
                .findAllBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc
                        (BigDecimal.valueOf(5000.00), BigDecimal.valueOf(30.00))
                .stream()
                .map(Job::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
