package softuni.exam.models.dto;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityImportDTO {
    @NotNull
    @Size(min = 2, max = 60)
    private String cityName;

    @Size(min = 2)
    private String description;

    @NotNull
    @Min(500)
    private Integer population;

    @NotNull
    private Long country;

    public CityImportDTO() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
