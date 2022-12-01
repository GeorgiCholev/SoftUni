package softuni.exam.models.entity;

import softuni.exam.util.ApartmentType;

import javax.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity {

    @Column(name = "apartment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApartmentType apartmentType;

    @Column(nullable = false)
    private Double area;

    @ManyToOne(optional = false)
    private Town town;

    public Apartment() {
        super();
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "apartment " + this.apartmentType + " - " + String.format("%.2f", this.area);
    }
}
