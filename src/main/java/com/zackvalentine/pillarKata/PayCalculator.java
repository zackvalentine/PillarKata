package com.zackvalentine.pillarKata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

class PayCalculator {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Family family;

    private final LocalDate firstDay = LocalDate.of(2019, 1, 1);
    private final LocalDate secondDay = firstDay.plusDays(1);

    PayCalculator(int startHour, int endHour, String familyLetter) throws IllegalArgumentException {
        if(isTimeOutOfBounds(startHour)) {
            throw new IllegalArgumentException("Invalid start time");
        }
        if(isTimeOutOfBounds(endHour)) {
            throw new IllegalArgumentException("Invalid end time");
        }
        this.startTime = buildLocalDateTime(startHour);
        this.endTime = buildLocalDateTime(endHour);
        if(!this.endTime.isAfter(this.startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        this.family = Family.toType(familyLetter);
    }

    int getTotalPay() {
        return getPayForFirstRatePeriod()
                + getPayForSecondRatePeriod()
                + getPayForThirdRatePeriod();
    }

    LocalDateTime getStartTime() {
        return startTime;
    }

    LocalDateTime getEndTime() {
        return endTime;
    }

    private LocalDateTime buildLocalDateTime(int hour) {
        if(hour >= 17) {
            return LocalDateTime.of(firstDay, LocalTime.of(hour, 0));
        } else {
            return LocalDateTime.of(secondDay, LocalTime.of(hour, 0));
        }
    }

    int getShiftDuration() {
        return (int)(this.startTime.until(this.endTime, ChronoUnit.HOURS));
    }

    private boolean isTimeOutOfBounds(int hour) {
        return hour > 4 && hour < 17;
    }

    int getHoursInFirstRatePeriod() {
        if(isShiftOutsideRatePeriod(this.family.getFirstPeriodStartTime(), this.family.getFirstPeriodEndTime())) {
            return 0;
        }
        if(this.endTime.isAfter(this.family.getFirstPeriodEndTime())) {
            return (int) this.startTime.until(this.family.getFirstPeriodEndTime(), ChronoUnit.HOURS);
        } else {
            return (int) this.startTime.until(this.endTime, ChronoUnit.HOURS);
        }
    }

    int getHoursInSecondRatePeriod() {
        if(isShiftOutsideRatePeriod(this.family.getSecondPeriodStartTime(), this.family.getSecondPeriodEndTime())) {
            return 0;
        }
        return (int) getLaterTime(this.startTime, this.family.getSecondPeriodStartTime()).until(getEarlierTime(this.endTime, this.family.getSecondPeriodEndTime()), ChronoUnit.HOURS);
    }

    boolean isShiftOutsideRatePeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        if((this.startTime.isAfter(periodEnd))
                || this.startTime.isEqual(periodEnd)) {
            return true;
        } else return (this.endTime.isBefore(periodStart))
                || this.endTime.isEqual(periodStart);
    }

    boolean isShiftStartTimeAtOrAfterPeriodEnd(LocalDateTime periodEnd) {
        return this.startTime.isAfter(periodEnd) || this.startTime.isEqual(periodEnd);
    }

    boolean isShiftEndTimeAtOrBeforePeriodStart(LocalDateTime periodStart) {
        return this.endTime.isBefore(periodStart) || this.endTime.isEqual(periodStart);
    }

    int getHoursInThirdRatePeriod() {
        if(!this.family.isThirdRatePeriodAllowed()) {
            return 0;
        } else if(isShiftOutsideRatePeriod(this.family.getThirdPeriodStartTime(), this.family.getThirdPeriodEndTime())) {
            return 0;
        } else {
            return (int) getLaterTime(this.startTime, this.family.getThirdPeriodStartTime()).until(
                    getEarlierTime(this.endTime, this.family.getThirdPeriodEndTime()), ChronoUnit.HOURS);
        }
    }

    int getPayForFirstRatePeriod() {
        return this.family.getFirstPeriodRate() * this.getHoursInFirstRatePeriod();
    }

    int getPayForSecondRatePeriod() {
        return this.family.getSecondPeriodRate() * this.getHoursInSecondRatePeriod();
    }

    int getPayForThirdRatePeriod() {
        return this.family.getThirdPeriodRate() * this.getHoursInThirdRatePeriod();
    }

    LocalDateTime getEarlierTime(LocalDateTime firstTime, LocalDateTime secondTime) {
        return firstTime.isBefore(secondTime) || firstTime.isEqual(secondTime) ? firstTime : secondTime;
    }

    LocalDateTime getLaterTime(LocalDateTime firstTime, LocalDateTime secondTime) {
        return firstTime.isAfter(secondTime) || firstTime.isEqual(secondTime) ? firstTime : secondTime;
    }
}
