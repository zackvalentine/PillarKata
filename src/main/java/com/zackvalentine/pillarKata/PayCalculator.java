package com.zackvalentine.pillarKata;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PayCalculator {
    LocalDateTime startTime;
    LocalDateTime endTime;
    private final LocalDate firstDay = LocalDate.of(2019, 1, 1);
    private final LocalDate secondDay = firstDay.plusDays(1);

    public PayCalculator(int startHour, int endHour) throws IOException {
        if(startHour > 4 && startHour < 17) {
            throw new IOException("Invalid start time");
        }
        if(endHour > 4 && endHour < 17) {
            throw new IOException("Invalid end time");
        }
        this.startTime = buildLocalDateTime(startHour);
        this.endTime = buildLocalDateTime(endHour);
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
}
