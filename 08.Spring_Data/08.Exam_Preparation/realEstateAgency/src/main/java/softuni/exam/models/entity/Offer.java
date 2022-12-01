package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "published_on", nullable = false)
    private LocalDate publishedOn;

    @ManyToOne(optional = false)
    private Apartment apartment;

    @ManyToOne(optional = false)
    private Agent agent;

    public Offer() {
        super();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return String.format("offer %.2f", this.price);
    }

    public String getInfo() {
        return "Agent " + this.agent.getFullName() + " with offer №" + super.getId() + ":"
                + System.lineSeparator() +
                "\t\t-Apartment area: " + String.format("%.2f", this.apartment.getArea())
                + System.lineSeparator() +
                "   \t\t--Town: " + this.apartment.getTown().getTownName()
                + System.lineSeparator() +
                "   \t\t---Price: " + String.format("%.2f", this.price) + "$";
    }
}
