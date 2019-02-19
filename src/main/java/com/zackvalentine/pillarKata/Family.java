package com.zackvalentine.pillarKata;

import java.time.LocalDateTime;

public enum Family {

    FAMILYA("A", LocalDateTime.of(2019, 1, 1, 23, 0), LocalDateTime.of(2019, 1, 2, 4, 0), 15, 20),
    FAMILYB("B", LocalDateTime.of(2019, 1, 1, 22, 0), LocalDateTime.of(2019, 1, 2, 0, 0), 12, 8);

    private String value;
    private LocalDateTime firstPeriodEndTime;
    private LocalDateTime secondPeriodEndTime;
    private int firstPeriodRate;
    private int secondPeriodRate;

    Family(String value, LocalDateTime firstPeriodEndTime, LocalDateTime secondPeriodEndTime,
                  int firstPeriodRate, int secondPeriodRate) {
        this.value = value;
        this.firstPeriodEndTime = firstPeriodEndTime;
        this.secondPeriodEndTime = secondPeriodEndTime;
        this.firstPeriodRate = firstPeriodRate;
        this.secondPeriodRate = secondPeriodRate;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getFirstPeriodEndTime() {
        return firstPeriodEndTime;
    }

    public LocalDateTime getSecondPeriodEndTime() {
        return secondPeriodEndTime;
    }

    public int getFirstPeriodRate() {
        return firstPeriodRate;
    }

    public int getSecondPeriodRate() {
        return secondPeriodRate;
    }
}
