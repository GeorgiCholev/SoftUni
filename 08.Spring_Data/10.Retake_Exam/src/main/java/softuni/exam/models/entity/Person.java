package softuni.exam.models.entity;

import softuni.exam.util.StatusType;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person extends BaseEntity {

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_type", nullable = false)
    private StatusType statusType;

    @ManyToOne(optional = false)
    private Country country;

    public Person() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "person " + this.getFullName();
    }
}