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
        if(this.endTime.isAfter(this.familyAFirstPeriodEndTime)) {
            return (int) this.startTime.until(this.familyAFirstPeriodEndTime, ChronoUnit.HOURS);
        } else {
            return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
        }
    }

    protected int getHoursInSecondRatePeriod() {
        return 3;
    }
}
