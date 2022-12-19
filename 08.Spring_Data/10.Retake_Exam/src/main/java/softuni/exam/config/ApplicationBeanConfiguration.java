package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import softuni.exam.models.dto.companyImports.CompanyImportRoot;
import softuni.exam.models.dto.jobImports.JobImportRoot;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static softuni.exam.util.Constants.COMPANY_UNMARSHALLER;
import static softuni.exam.util.Constants.JOB_UNMARSHALLER;

@Component
public class ApplicationBeanConfiguration {

    @Bean
    public Gson createGson() {
        return new GsonBuilder().create();
    }

    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator createValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean(name = COMPANY_UNMARSHALLER)
    public Unmarshaller createCompanyUnmarshaller() throws JAXBException {
        return JAXBContext.newInstance(CompanyImportRoot.class).createUnmarshaller();
    }

    @Bean(name = JOB_UNMARSHALLER)
    public Unmarshaller createJobUnmarshaller() throws JAXBException {
        return JAXBContext.newInstance(JobImportRoot.class).createUnmarshaller();
    }
}
