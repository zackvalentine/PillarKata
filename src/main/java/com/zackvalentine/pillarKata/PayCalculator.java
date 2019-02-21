package com.zackvalentine.pillarKata;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class PayCalculator {
    LocalDateTime startTime;
    LocalDateTime endTime;
    String familyLetter;
    Family family;

    private final LocalDate firstDay = LocalDate.of(2019, 1, 1);
    private final LocalDate secondDay = firstDay.plusDays(1);

    public PayCalculator(int startHour, int endHour, String familyLetter) throws IOException {
        if(isTimeOutOfBounds(startHour)) {
            throw new IOException("Invalid start time");
        }
        if(isTimeOutOfBounds(endHour)) {
            throw new IOException("Invalid end time");
        }
        this.startTime = buildLocalDateTime(startHour);
        this.endTime = buildLocalDateTime(endHour);
        this.familyLetter = familyLetter;
        if(this.familyLetter.equals("A")) {
            this.family = Family.FAMILYA;
        } else if(this.familyLetter.equals("B")) {
            this.family = Family.FAMILYB;
        } else if(this.familyLetter.equals("C")) {
            this.family = Family.FAMILYC;
        } else {
            throw new IOException("Invalid family");
        }
        if(!this.endTime.isAfter(this.startTime)) {
            throw new IOException("End time must be after start time");
        }
    }

    public int getTotalPay() {
        return getPayForFirstRatePeriod()
                + getPayForSecondRatePeriod()
                + getPayForThirdRatePeriod();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    protected LocalDateTime buildLocalDateTime(int hour) {
        if(hour >= 17) {
            return LocalDateTime.of(firstDay, LocalTime.of(hour, 0));
        } else {
            return LocalDateTime.of(secondDay, LocalTime.of(hour, 0));
        }
    }

    protected int getShiftDuration() {
        return (int)(this.startTime.until(this.endTime, ChronoUnit.HOURS));
    }

    protected boolean isTimeOutOfBounds(int hour) {
        return hour > 4 && hour < 17;
    }

    protected int getHoursInFirstRatePeriod() {
        if(this.startTime.isAfter(this.family.getFirstPeriodEndTime())
            || this.startTime.isEqual(this.family.getFirstPeriodEndTime())) {
            return 0;
        }
        if(this.endTime.isAfter(this.family.getFirstPeriodEndTime())) {
            return (int) this.startTime.until(this.family.getFirstPeriodEndTime(), ChronoUnit.HOURS);
        } else {
            return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
        }
    }

    protected int getHoursInSecondRatePeriod() {
        if(this.endTime.isBefore(this.family.getFirstPeriodEndTime())
            || this.endTime.isEqual(this.family.getFirstPeriodEndTime())) {
            return 0;
        }
        if(this.startTime.isAfter(this.family.getSecondPeriodEndTime())
            || this.startTime.isEqual(this.family.getSecondPeriodEndTime())) {
            return 0;
        }
        if(this.startTime.isAfter(this.family.getFirstPeriodEndTime())) {
            return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
        } else if(this.endTime.isAfter(this.family.getSecondPeriodEndTime())) {
            return (int) this.family.getFirstPeriodEndTime().until(this.family.getSecondPeriodEndTime(), ChronoUnit.HOURS);
        } else {
            return (int) this.family.getFirstPeriodEndTime().until(this.endTime, ChronoUnit.HOURS);
        }
    }

    protected boolean isShiftOutsideRatePeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        if((this.startTime.isAfter(periodEnd))
                || this.startTime.isEqual(periodEnd)) {
            return true;
        } else if((this.endTime.isBefore(periodStart))
            || this.endTime.isEqual(periodStart)) {
            return true;
        }
        return false;
    }

    protected int getHoursInThirdRatePeriod() {
        if(!this.endTime.isAfter(this.family.getSecondPeriodEndTime())) {
            return 0;
        }
        if(this.startTime.isAfter(this.family.getSecondPeriodEndTime())) {
            return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
        } else {
            return (int) this.family.getSecondPeriodEndTime().until(this.endTime, ChronoUnit.HOURS);
        }
    }

    protected int getPayForFirstRatePeriod() {
        return this.family.getFirstPeriodRate() * this.getHoursInFirstRatePeriod();
    }

    protected int getPayForSecondRatePeriod() {
        return this.family.getSecondPeriodRate() * this.getHoursInSecondRatePeriod();
    }

    protected int getPayForThirdRatePeriod() {
        return this.family.getThirdPeriodRate() * this.getHoursInThirdRatePeriod();
    }
}
