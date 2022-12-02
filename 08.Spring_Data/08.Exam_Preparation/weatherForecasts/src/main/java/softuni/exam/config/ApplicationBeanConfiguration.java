package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import softuni.exam.models.dto.forecastsDTOs.ForecastImportRoot;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

    @Bean
    public Unmarshaller createUnmarshaller() throws JAXBException {
        return JAXBContext.newInstance(ForecastImportRoot.class).createUnmarshaller();
    }
}
