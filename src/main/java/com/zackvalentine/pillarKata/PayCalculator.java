package com.zackvalentine.pillarKata;

import java.time.LocalDateTime;

public class PayCalculator {
    LocalDateTime startTime;
    LocalDateTime endTime;

    public PayCalculator(int startHour, int endHour) {
        this.startTime = LocalDateTime.of(2019, 1, 1, startHour, 0);
        this.endTime = LocalDateTime.of(2019, 1, 1, endHour, 0);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
