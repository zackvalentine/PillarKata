package com.zackvalentine.pillarKata;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;

public class PayCalculatorTest {
    @Test
    public void convertsStartHourToLocalDateTime() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 21);
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void convertsEndHourToLocalDateTime() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 21);
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
    }

    @Test
    public void conversionSetsStartHourToNextDayIfAfterMidnight() throws IOException {
        PayCalculator payCalculator = new PayCalculator(1, 4);
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 1, 0))));
    }

    @Test
    public void conversionSetsEndHourToNextDayIfAfterMidnight() throws IOException {
        PayCalculator payCalculator = new PayCalculator(1, 4);
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
    }

    @Test(expected = IOException.class)
    public void throwsExceptionIfStartTimeIsOutOfBounds() throws IOException {
        PayCalculator payCalculator = new PayCalculator(9, 20);
    }

    @Test(expected = IOException.class)
    public void throwsExceptionIfEndTimeIsOutOfBounds() throws IOException {
        PayCalculator payCalculator = new PayCalculator(1, 8);
    }

    @Test(expected = IOException.class)
    public void throwsExceptionIfEndTimeIsNotAfterStartTime() throws IOException {
        PayCalculator payCalculator = new PayCalculator(20, 17);
    }

    @Test
    public void getsDurationOfShift() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 21);
        assertThat(payCalculator.getShiftDuration(), is(equalTo(4)));
    }

    @Test
    public void getsShiftDurationForOtherValues() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 23);
        assertThat(payCalculator.getShiftDuration(), is(equalTo(6)));
    }
}
