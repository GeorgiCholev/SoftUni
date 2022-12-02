package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.util.DayOfWeek;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    Optional<Forecast> findByDayOfWeekAndCity(DayOfWeek dayOfWeek, City city);

    @Query("SELECT f FROM Forecast f " +
            "JOIN f.city c " +
            "WHERE f.dayOfWeek = :dayOfWeek AND c.population < :population " +
            "ORDER BY f.maxTemperature DESC, f.id ASC")
    List<Forecast> findAllByDayOfWeekAndCityPopulationJPQL(DayOfWeek dayOfWeek, Integer population);
}
