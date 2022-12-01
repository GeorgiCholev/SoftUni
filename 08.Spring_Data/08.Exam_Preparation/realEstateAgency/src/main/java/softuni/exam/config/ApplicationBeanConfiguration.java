package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import softuni.exam.models.dto.ApartmentImportWrapper;
import softuni.exam.models.dto.OfferImportWrapper;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static softuni.exam.util.Constants.*;

@Component
public class ApplicationBeanConfiguration {

    @Bean
    public Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator createValidation() {
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Bean(name = APARTMENT_UNMARSHALLER)
    public Unmarshaller createApartmentUnmarshaller() throws JAXBException {
         return JAXBContext.newInstance(ApartmentImportWrapper.class).createUnmarshaller();
    }

    @Bean(name = OFFER_UNMARSHALLER)
    public Unmarshaller createOfferUnmarshaller() throws JAXBException {
        return JAXBContext.newInstance(OfferImportWrapper.class).createUnmarshaller();
    }

}
