package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Job;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc
            (BigDecimal salary, BigDecimal hoursAWeek);
}
