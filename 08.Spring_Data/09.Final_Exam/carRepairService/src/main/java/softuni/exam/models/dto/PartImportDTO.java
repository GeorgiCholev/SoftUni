package softuni.exam.models.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PartImportDTO {

    @NotNull
    @Size(min = 2, max = 19)
    private String partName;

    @NotNull
    @Min(10) @Max(2_000)
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer quantity;

    public PartImportDTO() {
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
}
