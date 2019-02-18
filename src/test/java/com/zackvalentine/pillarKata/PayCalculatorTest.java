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
        PayCalculator payCalculator = new PayCalculator(17, 21, "A");
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void convertsEndHourToLocalDateTime() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 21, "A");
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
    }

    @Test
    public void conversionSetsStartHourToNextDayIfAfterMidnight() throws IOException {
        PayCalculator payCalculator = new PayCalculator(1, 4, "A");
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 1, 0))));
    }

    @Test
    public void conversionSetsEndHourToNextDayIfAfterMidnight() throws IOException {
        PayCalculator payCalculator = new PayCalculator(1, 4, "A");
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
    }

    @Test(expected = IOException.class)
    public void throwsExceptionIfStartTimeIsOutOfBounds() throws IOException {
        PayCalculator payCalculator = new PayCalculator(9, 20, "A");
    }

    @Test(expected = IOException.class)
    public void throwsExceptionIfEndTimeIsOutOfBounds() throws IOException {
        PayCalculator payCalculator = new PayCalculator(1, 8, "A");
    }

    @Test(expected = IOException.class)
    public void throwsExceptionIfEndTimeIsNotAfterStartTime() throws IOException {
        PayCalculator payCalculator = new PayCalculator(20, 17, "A");
    }

    @Test
    public void getsDurationOfShift() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 21, "A");
        assertThat(payCalculator.getShiftDuration(), is(equalTo(4)));
    }

    @Test
    public void getsShiftDurationForOtherValues() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.getShiftDuration(), is(equalTo(6)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyA() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 22, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(5)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyAForOtherValues() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(6)));
    }

    @Test
    public void getsHoursInSecondRatePeriodForFamilyA() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 2, "A");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(3)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyA() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(11 * 6)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyA() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 4, "A");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(20 * 5)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyB() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 20, "B");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(3)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyBIfNotEqualToFamilyA() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 0, "B");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(5)));
    }
}
