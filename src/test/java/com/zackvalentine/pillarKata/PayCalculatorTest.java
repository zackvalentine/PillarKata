package com.zackvalentine.pillarKata;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;

public class PayCalculatorTest {
    @Test
    public void convertsStartHourToLocalDateTime() {
        PayCalculator payCalculator = new PayCalculator(17, 21);
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void convertsEndHourToLocalDateTime() {
        PayCalculator payCalculator = new PayCalculator(17, 21);
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
    }

    @Test
    public void conversionSetsStartHourToNextDayIfAfterMidnight() {
        PayCalculator payCalculator = new PayCalculator(1, 4);
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 1, 0))));
    }

    @Test
    public void conversionSetsEndHourToNextDayIfAfterMidnight() {
        PayCalculator payCalculator = new PayCalculator(1, 4);
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
    }
}
