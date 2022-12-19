package softuni.exam.models.dto.jobImports;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JobImportDTO {

    @XmlElement(name = "jobTitle")
    @NotNull
    @Size(min = 2, max = 40)
    private String title;

    @NotNull
    @Min(10)
    private BigDecimal hoursAWeek;

    @NotNull
    @Min(300)
    private BigDecimal salary;

    @NotNull
    @Size(min = 5)
    private String description;

    @XmlElement(name = "companyId")
    @NotNull
    private Long company;

    public JobImportDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(BigDecimal hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }
}
