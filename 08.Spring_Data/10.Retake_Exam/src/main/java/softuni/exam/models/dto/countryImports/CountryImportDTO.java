package softuni.exam.models.dto.countryImports;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryImportDTO {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @SerializedName("countryCode")
    @NotNull
    @Size(min = 2, max = 19)
    private String code;

    @NotNull
    @Size(min = 2, max = 19)
    private String currency;

    public CountryImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
