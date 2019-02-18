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

    private final LocalDate firstDay = LocalDate.of(2019, 1, 1);
    private final LocalDate secondDay = firstDay.plusDays(1);
    private final LocalDateTime familyAFirstPeriodEndTime = LocalDateTime.of(firstDay, LocalTime.of(23, 0));
    private final LocalDateTime familyASecondPeriodEndTime = LocalDateTime.of(secondDay, LocalTime.of(4, 0));
    private final int familyAFirstPeriodRate = 11;
    private final int familyASecondPeriodRate = 20;
    private final LocalDateTime familyBFirstPeriodEndTime = LocalDateTime.of(firstDay, LocalTime.of(22, 0));

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
        if(!this.endTime.isAfter(this.startTime)) {
            throw new IOException("End time must be after start time");
        }
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
        if(this.familyLetter.equals("A")) {
            if (this.endTime.isAfter(this.familyAFirstPeriodEndTime)) {
                return (int) this.startTime.until(this.familyAFirstPeriodEndTime, ChronoUnit.HOURS);
            } else {
                return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
            }
        } else if(this.familyLetter.equals("B")) {
            if (this.endTime.isAfter(this.familyBFirstPeriodEndTime)) {
                return (int) this.startTime.until(this.familyBFirstPeriodEndTime, ChronoUnit.HOURS);
            } else {
                return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
            }
        }
        return 0;
    }

    protected int getHoursInSecondRatePeriod() {
        if(this.startTime.isAfter(this.familyAFirstPeriodEndTime)) {
            return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
        } else {
            return (int) this.familyAFirstPeriodEndTime.until(this.endTime, ChronoUnit.HOURS);
        }
    }

    protected int getPayForFirstRatePeriod() {
        return this.familyAFirstPeriodRate * this.getHoursInFirstRatePeriod();
    }

    protected int getPayForSecondRatePeriod() {
        return this.familyASecondPeriodRate * this.getHoursInSecondRatePeriod();
    }
}
