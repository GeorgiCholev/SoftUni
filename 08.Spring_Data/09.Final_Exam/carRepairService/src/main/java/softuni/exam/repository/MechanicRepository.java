package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Mechanic;

import java.util.Optional;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

    Optional<Mechanic> findByFirstNameOrEmailOrPhone(String firstName, String email, String phone);

    Optional<Mechanic> findByFirstName(String firstName);
}
