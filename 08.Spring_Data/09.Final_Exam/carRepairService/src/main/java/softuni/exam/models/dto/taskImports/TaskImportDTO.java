package softuni.exam.models.dto.taskImports;

import softuni.exam.util.xmlAdapters.BigDecimalFormatXmlAdapter;
import softuni.exam.util.xmlAdapters.LocalDateTimeXmlAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDTO {

    @NotNull
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime date;

    @NotNull
    @Positive
    @XmlJavaTypeAdapter(BigDecimalFormatXmlAdapter.class)
    private BigDecimal price;

    @NotNull
    private CarIdDTO car;

    @NotNull
    private MechanicFirstNameDTO mechanic;

    @NotNull
    private PartIdDTO part;

    public TaskImportDTO() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarIdDTO getCar() {
        return car;
    }

    public void setCar(CarIdDTO car) {
        this.car = car;
    }

    public MechanicFirstNameDTO getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicFirstNameDTO mechanic) {
        this.mechanic = mechanic;
    }

    public PartIdDTO getPart() {
        return part;
    }

    public void setPart(PartIdDTO part) {
        this.part = part;
    }
}
