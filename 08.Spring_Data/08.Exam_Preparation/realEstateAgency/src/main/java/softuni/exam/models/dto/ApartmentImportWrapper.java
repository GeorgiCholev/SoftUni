package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentImportWrapper {

    @XmlElement(name = "apartment")
    private List<ApartmentImportDTO> apartments;

    public ApartmentImportWrapper() {
        this.apartments = new ArrayList<>();
    }

    public List<ApartmentImportDTO> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentImportDTO> apartments) {
        this.apartments = apartments;
    }
}
