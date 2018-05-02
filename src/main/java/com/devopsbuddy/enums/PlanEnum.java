package com.devopsbuddy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlanEnum {

    BASIC(1, "Basic"),
    PRO(2, "Pro");


    @Getter
    private final int id;
    @Getter
    private final String planName;



}
