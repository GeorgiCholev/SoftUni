package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column(name = "part_name", nullable = false, unique = true)
    private String partName;

    @Column(nullable = false, columnDefinition = "double")
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    public Part() {
        super();
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "part " + this.partName + " - " + String.format("%.2f", this.price);
    }
}
