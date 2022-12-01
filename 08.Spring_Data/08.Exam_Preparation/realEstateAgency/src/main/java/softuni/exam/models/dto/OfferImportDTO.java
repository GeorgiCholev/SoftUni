package softuni.exam.models.dto;

import softuni.exam.util.xmlAdapters.LocalDateXmlAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDTO {

    @NotNull
    @Positive
    private BigDecimal price;

    @XmlElement(name = "agent")
    @NotNull
    private AgentNameDTO agentName;

    @XmlElement(name = "apartment")
    @NotNull
    private ApartmentIdDTO apartmentId;

    @XmlJavaTypeAdapter(value = LocalDateXmlAdapter.class)
    @NotNull
    private LocalDate publishedOn;

    public OfferImportDTO() {}

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AgentNameDTO getAgentName() {
        return agentName;
    }

    public void setAgentName(AgentNameDTO name) {
        this.agentName = name;
    }

    public ApartmentIdDTO getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(ApartmentIdDTO apartmentId) {
        this.apartmentId = apartmentId;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }
}
