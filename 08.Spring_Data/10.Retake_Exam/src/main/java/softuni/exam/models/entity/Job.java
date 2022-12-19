package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "double")
    private BigDecimal salary;

    @Column(name = "hoursaweek", nullable = false, columnDefinition = "double")
    private BigDecimal hoursAWeek;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @ManyToOne(optional = false)
    private Company company;

    public Job() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(BigDecimal hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "job " + this.title;
    }

    public String getInfo() {
        return "Job title: " + this.title +
                System.lineSeparator() +
                "-Salary: " + String.format("%.2f", this.salary) + "$" +
                System.lineSeparator() +
                "--Hours a week: " + String.format("%.2f", this.hoursAWeek) + "h."
                + System.lineSeparator();
    }
}
