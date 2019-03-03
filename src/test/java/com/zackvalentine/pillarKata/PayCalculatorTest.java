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
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(90)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyA() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 4, "A");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(100)));
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

    @Test
    public void getsHoursInSecondRatePeriodForFamilyB() throws IOException {
        PayCalculator payCalculator = new PayCalculator(22, 0, "B");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(2)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyAWhenShiftExtendsIntoSecondPeriod() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(6)));
    }

    @Test
    public void getsHoursInSecondRatePeriodForFamilyAWhenShiftExtendsIntoSecondPeriod() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "A");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(5)));
    }

    @Test
    public void returnsZeroHoursInFirstRatePeriodWhenShiftHasNoHoursInPeriod() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 4, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(0)));
    }

    @Test
    public void returnsZeroHoursInSecondRatePeriodWhenShiftEndsBeforePeriodStarts() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 19, "A");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(0)));
    }

    @Test
    public void returnsZeroHoursInSecondRatePeriodWhenShiftStartsAfterPeriodEnds() throws IOException {
        PayCalculator payCalculator = new PayCalculator(1, 4, "B");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(0)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyC() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 21, "C");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(4)));
    }

    @Test
    public void getsHoursInSecondRatePeriodForFamilyC() throws IOException {
        PayCalculator payCalculator = new PayCalculator(21, 4, "C");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(7)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyC() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "C");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(84)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyC() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "C");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(105)));
    }

    @Test
    public void handlesThirdRatePeriodForFamilyB() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "B");
        assertThat(payCalculator.getHoursInThirdRatePeriod(), is(equalTo(4)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyB() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 22, "B");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(60)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyBWhenShiftExtendsPastPeriodEnd() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 0, "B");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(60)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyB() throws IOException {
        PayCalculator payCalculator = new PayCalculator(22, 0, "B");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(16)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyBWhenShiftExtendsPastPeriodEnd() throws IOException {
        PayCalculator payCalculator = new PayCalculator(22, 4, "B");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(16)));
    }

    @Test
    public void calculatesPayForThirdRatePeriodForFamilyB() throws IOException {
        PayCalculator payCalculator = new PayCalculator(0, 4, "B");
        assertThat(payCalculator.getPayForThirdRatePeriod(), is(equalTo(64)));
    }

    @Test
    public void getsTotalPayForFamilyA() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "A");
        assertThat(payCalculator.getTotalPay(), is(equalTo(190)));
    }

    @Test
    public void getsTotalPayForFamilyA_singleRatePeriod() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 20, "A");
        assertThat(payCalculator.getTotalPay(), is(equalTo(45)));
    }

    @Test
    public void getsTotalPayForFamilyB() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(140)));
    }

    @Test
    public void getsTotalPayForFamilyBOtherThanMaxShiftLength() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 3, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(124)));
    }

    @Test
    public void getsTotalPayForFamilyB_allInSecondPeriod() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 0, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(8)));
    }

    @Test
    public void getsTotalPayForFamilyB_startsInSecondPeriod() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 3, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(56)));
    }

    @Test
    public void getsTotalPayForFamilyC() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 4, "C");
        assertThat(payCalculator.getTotalPay(), is(equalTo(189)));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftEndsBeforePeriodStarts() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 18, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getSecondPeriodStartTime(),
                Family.FAMILYA.getSecondPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftEndsWhenPeriodStarts() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getSecondPeriodStartTime(),
                Family.FAMILYA.getSecondPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftStartTimeAtOrAfterPeriodEndIfAfter() throws IOException {
        PayCalculator payCalculator = new PayCalculator(0, 1, "A");
        assertThat(payCalculator.isShiftStartTimeAtOrAfterPeriodEnd(
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftStartTimeAtOrAfterPeriodEndIfEqual() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 0, "A");
        assertThat(payCalculator.isShiftStartTimeAtOrAfterPeriodEnd(
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftEndTimeAtOrBeforePeriodStartIfEqual() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.isShiftEndTimeAtOrBeforePeriodStart(
                Family.FAMILYA.getSecondPeriodStartTime()),
                is(true));
    }

    @Test
    public void returnsShiftEndTimeAtOrBeforePeriodStartIfBefore() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 22, "A");
        assertThat(payCalculator.isShiftEndTimeAtOrBeforePeriodStart(
                Family.FAMILYA.getSecondPeriodStartTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftStartsAfterPeriodEnds() throws IOException {
        PayCalculator payCalculator = new PayCalculator(0, 4, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getFirstPeriodStartTime(),
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftStartsWhenPeriodEnds() throws IOException {
        PayCalculator payCalculator = new PayCalculator(23, 4, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getFirstPeriodStartTime(),
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsInsideRatePeriodIfTrue() throws IOException {
        PayCalculator payCalculator = new PayCalculator(21, 4, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getFirstPeriodStartTime(),
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(false));
    }

    @Test
    public void determinesEarlierTime() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 19, "A");
        assertThat(payCalculator.getEarlierTime(
                LocalDateTime.of(2019, 1, 1, 17, 0),
                LocalDateTime.of(2019, 1, 1, 19, 0)),
                is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void determinesLaterTime() throws IOException {
        PayCalculator payCalculator = new PayCalculator(17, 19, "A");
        assertThat(payCalculator.getLaterTime(
                LocalDateTime.of(2019, 1, 1, 17, 0),
                LocalDateTime.of(2019, 1, 1, 19, 0)),
                is(equalTo(LocalDateTime.of(2019, 1, 1, 19, 0))));
    }
}
