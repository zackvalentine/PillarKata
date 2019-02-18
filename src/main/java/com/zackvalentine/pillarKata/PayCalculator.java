package com.zackvalentine.pillarKata;

import java.time.LocalDateTime;

public class PayCalculator {
    LocalDateTime startTime;

    public PayCalculator(int startHour) {
        this.startTime = LocalDateTime.of(2019, 1, 1, startHour, 0);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
