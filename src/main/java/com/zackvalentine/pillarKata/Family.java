package com.zackvalentine.pillarKata;

import java.io.IOException;
import java.time.LocalDateTime;

public enum Family {

    FAMILYA("A", LocalDateTime.of(2019, 1, 1, 23, 0), LocalDateTime.of(2019, 1, 2, 4, 0), 15, 20),
    FAMILYB("B", LocalDateTime.of(2019, 1, 1, 22, 0), LocalDateTime.of(2019, 1, 2, 0, 0), LocalDateTime.of(2019, 1, 2, 4, 0), 12, 8, 16),
    FAMILYC("C", LocalDateTime.of(2019, 1, 1, 21, 0), LocalDateTime.of(2019, 1, 2, 4, 0), 21, 15);

    private String value;
    private LocalDateTime firstPeriodStartTime;
    private LocalDateTime firstPeriodEndTime;
    private LocalDateTime secondPeriodStartTime;
    private LocalDateTime secondPeriodEndTime;
    private LocalDateTime thirdPeriodStartTime;
    private LocalDateTime thirdPeriodEndTime;
    private int firstPeriodRate;
    private int secondPeriodRate;
    private int thirdPeriodRate;

    Family(String value, LocalDateTime firstPeriodEndTime, LocalDateTime secondPeriodEndTime,
                  int firstPeriodRate, int secondPeriodRate) {
        this.value = value;
        this.firstPeriodStartTime = LocalDateTime.of(2019, 1, 1, 17, 0);
        this.firstPeriodEndTime = firstPeriodEndTime;
        this.secondPeriodStartTime = firstPeriodEndTime;
        this.secondPeriodEndTime = secondPeriodEndTime;
        this.firstPeriodRate = firstPeriodRate;
        this.secondPeriodRate = secondPeriodRate;
        this.thirdPeriodStartTime = null;
        this.thirdPeriodEndTime = null;
        this.thirdPeriodRate = 0;
    }

    Family(String value, LocalDateTime firstPeriodEndTime, LocalDateTime secondPeriodEndTime,
           LocalDateTime thirdPeriodEndTime, int firstPeriodRate, int secondPeriodRate, int thirdPeriodRate) {
        this.value = value;
        this.firstPeriodStartTime = LocalDateTime.of(2019, 1, 1, 17, 0);
        this.firstPeriodEndTime = firstPeriodEndTime;
        this.secondPeriodStartTime = firstPeriodEndTime;
        this.secondPeriodEndTime = secondPeriodEndTime;
        this.thirdPeriodStartTime = secondPeriodEndTime;
        this.thirdPeriodEndTime = thirdPeriodEndTime;
        this.firstPeriodRate = firstPeriodRate;
        this.secondPeriodRate = secondPeriodRate;
        this.thirdPeriodRate = thirdPeriodRate;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getFirstPeriodStartTime() {
        return firstPeriodStartTime;
    }

    public LocalDateTime getFirstPeriodEndTime() {
        return firstPeriodEndTime;
    }

    public LocalDateTime getSecondPeriodStartTime() {
        return secondPeriodStartTime;
    }

    public LocalDateTime getSecondPeriodEndTime() {
        return secondPeriodEndTime;
    }

    public LocalDateTime getThirdPeriodStartTime() {
        return thirdPeriodStartTime;
    }

    public LocalDateTime getThirdPeriodEndTime() {
        return thirdPeriodEndTime;
    }

    public int getFirstPeriodRate() {
        return firstPeriodRate;
    }

    public int getSecondPeriodRate() {
        return secondPeriodRate;
    }

    public int getThirdPeriodRate() {
        return thirdPeriodRate;
    }

    public boolean isThirdRatePeriodAllowed() {
        return thirdPeriodStartTime != null;
    }

    public static Family toType(String letter) {
        switch (letter) {
            case "A":
                return FAMILYA;
            case "B":
                return FAMILYB;
            case "C":
                return FAMILYC;
            default:
                return null;
        }
    }
}
