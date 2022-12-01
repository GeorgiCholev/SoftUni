package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferImportDTO;
import softuni.exam.models.dto.OfferImportWrapper;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ApartmentType;

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
public class OfferServiceImpl implements OfferService {

    private static final File OFFER_XML_FILE =
            new File(String.format(BASIC_PATH, "xml", "offers.xml"));

    private final OfferRepository offerRepository;

    private final AgentRepository agentRepository;

    private final ApartmentRepository apartmentRepository;

    private final Unmarshaller unmarshaller;

    private final ModelMapper modelMapper;

    private final Validator validator;

    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository,
                            ApartmentRepository apartmentRepository,
                            @Qualifier(OFFER_UNMARSHALLER) Unmarshaller unmarshaller,
                            ModelMapper modelMapper, Validator validator) {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
        this.unmarshaller = unmarshaller;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(OFFER_XML_FILE.toPath());
    }

    @Override
    public String importOffers() throws JAXBException {

        OfferImportWrapper offersWrapper = (OfferImportWrapper) unmarshaller.unmarshal(OFFER_XML_FILE);
        return offersWrapper.getOffers()
                .stream()
                .map(this::importOffer)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importOffer(OfferImportDTO dto) {

        Set<ConstraintViolation<OfferImportDTO>> violations = this.validator.validate(dto);

        if (violations.isEmpty()) {

            Optional<Agent> optAgent = this.agentRepository.findByFirstName(dto.getAgentName().getName());

            if (optAgent.isPresent()) {

                Apartment apartment = this.apartmentRepository.findById(dto.getApartmentId().getId()).get();

                Offer mappedOffer = this.modelMapper.map(dto, Offer.class);
                mappedOffer.setAgent(optAgent.get());
                mappedOffer.setApartment(apartment);

                this.offerRepository.save(mappedOffer);

                return String.format(CORRECT_DATA, mappedOffer);
            }
        }

        return String.format(INCORRECT_DATA, "offer");
    }


    @Override
    public String exportOffers() {
        ApartmentType apartmentType = ApartmentType.three_rooms;
        return this.offerRepository
                .findAllByApartment_ApartmentTypeOrderByApartment_AreaDescPriceAsc(apartmentType)
                .stream()
                .map(Offer::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
