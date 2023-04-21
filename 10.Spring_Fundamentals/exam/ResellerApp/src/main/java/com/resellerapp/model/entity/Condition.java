package com.resellerapp.model.entity;

import com.resellerapp.model.entity.enums.ConditionName;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "condition_name", unique = true, nullable = false)
    private ConditionName conditionName;

    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public Condition(ConditionName conditionName) {
        this.conditionName = conditionName;
        this.description = conditionName.getDescription();
    }


    public Long getId() {
        return id;
    }

    public ConditionName getConditionName() {
        return conditionName;
    }

    public String getDescription() {
        return description;
    }
}
