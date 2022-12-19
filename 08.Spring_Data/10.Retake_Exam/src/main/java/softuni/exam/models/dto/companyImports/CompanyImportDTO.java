package softuni.exam.models.dto.companyImports;

import softuni.exam.util.LocalDateXmlAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyImportDTO {

    @XmlElement(name = "companyName")
    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    @NotNull
    private LocalDate dateEstablished;

    @NotNull
    @Size(min = 2, max = 30)
    private String website;

    @XmlElement(name = "countryId")
    @NotNull
    private Long country;

    public CompanyImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(LocalDate dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
