package softuni.exam.models.entity;

import softuni.exam.util.DayOfWeek;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity {

    @Column(name = "day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "max_temperature", nullable = false)
    private Double maxTemperature;

    @Column(name = "min_temperature", nullable = false)
    private Double minTemperature;

    @Column(nullable = false)
    private Time sunrise;

    @Column(nullable = false)
    private Time sunset;

    @ManyToOne(optional = false)
    private City city;

    public Forecast() {
        super();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Time getSunrise() {
        return sunrise;
    }

    public void setSunrise(Time sunrise) {
        this.sunrise = sunrise;
    }

    public Time getSunset() {
        return sunset;
    }

    public void setSunset(Time sunset) {
        this.sunset = sunset;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "forecast " + this.dayOfWeek + " - " + String.format("%.2f", this.maxTemperature);
    }

    public String getInfo() {
        return "City: " + this.city.getCityName() + ":" +
                System.lineSeparator() +
                "-min temperature: " + String.format("%.2f", this.minTemperature) +
                System.lineSeparator() +
                "--max temperature: " + String.format("%.2f", this.maxTemperature) +
                System.lineSeparator() +
                "---sunrise: " + this.sunrise +
                System.lineSeparator() +
                "----sunset: " + this.sunset;
    }
}
