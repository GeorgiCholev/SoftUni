package com.resellerapp.repository;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Optional<Condition> findByConditionName(ConditionName conditionName);
}
