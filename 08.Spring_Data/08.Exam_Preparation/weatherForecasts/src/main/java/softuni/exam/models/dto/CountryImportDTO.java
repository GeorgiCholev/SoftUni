package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryImportDTO {

    @NotNull
    @Size(min = 2, max = 60)
    private String countryName;

    @NotNull
    @Size(min = 2, max = 60)
    private String currency;

    public CountryImportDTO() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
