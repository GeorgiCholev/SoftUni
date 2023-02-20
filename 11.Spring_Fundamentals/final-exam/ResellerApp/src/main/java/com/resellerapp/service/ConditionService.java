package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public void addAll(Collection<Condition> conditions) {
        conditionRepository.saveAll(conditions);
    }

    public boolean conditionsExist() {
        return conditionRepository.count() != 0;
    }

    public Condition getConditionWithName(ConditionName conditionName) {
        return conditionRepository.findByConditionName(conditionName).orElse(null);
    }
}
