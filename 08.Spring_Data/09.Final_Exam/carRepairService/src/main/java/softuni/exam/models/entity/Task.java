package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    private Mechanic mechanic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "parts_id")
    private Part part;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cars_id")
    private Car car;

    public Task() {
        super();
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

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "task " + this.price;
    }

    public String getInfo() {
        return "Car " + this.car.getCarMakeAndModel() + " with " + this.car.getKilometers() + "km" +
                System.lineSeparator() +
                "-Mechanic: " + this.mechanic.getFullName() + " - task №" + super.getId() + ":" +
                System.lineSeparator() +
                "--Engine: " + this.car.getEngine() +
                System.lineSeparator() +
                "---Price: " + this.price + "$";
    }
}
