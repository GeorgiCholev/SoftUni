package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionName;
import com.resellerapp.service.ConditionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ConditionInitialSeed implements CommandLineRunner {

    private final ConditionService conditionService;

    public ConditionInitialSeed(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (conditionService.conditionsExist()) {
            return;
        }

        conditionService.addAll(
                Arrays.stream(ConditionName.values())
                        .map(Condition::new)
                        .collect(Collectors.toList())
        );
    }
}
