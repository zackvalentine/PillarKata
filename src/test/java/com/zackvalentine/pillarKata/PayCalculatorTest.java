package com.zackvalentine.pillarKata;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;

public class PayCalculatorTest {
    @Test
    public void convertsStartHourToLocalDateTime() {
        PayCalculator payCalculator = new PayCalculator(17, 21, "A");
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void convertsEndHourToLocalDateTime() {
        PayCalculator payCalculator = new PayCalculator(17, 21, "A");
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
    }

    @Test
    public void conversionSetsStartHourToNextDayIfAfterMidnight() {
        PayCalculator payCalculator = new PayCalculator(1, 4, "A");
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 1, 0))));
    }

    @Test
    public void conversionSetsEndHourToNextDayIfAfterMidnight() {
        PayCalculator payCalculator = new PayCalculator(1, 4, "A");
        assertThat(payCalculator.getEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfStartTimeIsOutOfBounds() {
        new PayCalculator(9, 20, "A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfEndTimeIsOutOfBounds() {
        new PayCalculator(1, 8, "A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfEndTimeIsNotAfterStartTime() {
        new PayCalculator(20, 17, "A");
    }

    @Test
    public void getsDurationOfShift() {
        PayCalculator payCalculator = new PayCalculator(17, 21, "A");
        assertThat(payCalculator.getShiftDuration(), is(equalTo(4)));
    }

    @Test
    public void getsShiftDurationForOtherValues() {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.getShiftDuration(), is(equalTo(6)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyA() {
        PayCalculator payCalculator = new PayCalculator(17, 22, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(5)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyAForOtherValues() {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(6)));
    }

    @Test
    public void getsHoursInSecondRatePeriodForFamilyA() {
        PayCalculator payCalculator = new PayCalculator(23, 2, "A");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(3)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyA() {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(90)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyA() {
        PayCalculator payCalculator = new PayCalculator(23, 4, "A");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(100)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyB() {
        PayCalculator payCalculator = new PayCalculator(17, 20, "B");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(3)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyBIfNotEqualToFamilyA() {
        PayCalculator payCalculator = new PayCalculator(17, 0, "B");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(5)));
    }

    @Test
    public void getsHoursInSecondRatePeriodForFamilyB() {
        PayCalculator payCalculator = new PayCalculator(22, 0, "B");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(2)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyAWhenShiftExtendsIntoSecondPeriod() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(6)));
    }

    @Test
    public void getsHoursInSecondRatePeriodForFamilyAWhenShiftExtendsIntoSecondPeriod() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "A");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(5)));
    }

    @Test
    public void returnsZeroHoursInFirstRatePeriodWhenShiftHasNoHoursInPeriod() {
        PayCalculator payCalculator = new PayCalculator(23, 4, "A");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(0)));
    }

    @Test
    public void returnsZeroHoursInSecondRatePeriodWhenShiftEndsBeforePeriodStarts() {
        PayCalculator payCalculator = new PayCalculator(17, 19, "A");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(0)));
    }

    @Test
    public void returnsZeroHoursInSecondRatePeriodWhenShiftStartsAfterPeriodEnds() {
        PayCalculator payCalculator = new PayCalculator(1, 4, "B");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(0)));
    }

    @Test
    public void getsHoursInFirstRatePeriodForFamilyC() {
        PayCalculator payCalculator = new PayCalculator(17, 21, "C");
        assertThat(payCalculator.getHoursInFirstRatePeriod(), is(equalTo(4)));
    }

    @Test
    public void getsHoursInSecondRatePeriodForFamilyC() {
        PayCalculator payCalculator = new PayCalculator(21, 4, "C");
        assertThat(payCalculator.getHoursInSecondRatePeriod(), is(equalTo(7)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyC() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "C");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(84)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyC() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "C");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(105)));
    }

    @Test
    public void handlesThirdRatePeriodForFamilyB() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "B");
        assertThat(payCalculator.getHoursInThirdRatePeriod(), is(equalTo(4)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyB() {
        PayCalculator payCalculator = new PayCalculator(17, 22, "B");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(60)));
    }

    @Test
    public void calculatesPayForFirstRatePeriodForFamilyBWhenShiftExtendsPastPeriodEnd() {
        PayCalculator payCalculator = new PayCalculator(17, 0, "B");
        assertThat(payCalculator.getPayForFirstRatePeriod(), is(equalTo(60)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyB() {
        PayCalculator payCalculator = new PayCalculator(22, 0, "B");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(16)));
    }

    @Test
    public void calculatesPayForSecondRatePeriodForFamilyBWhenShiftExtendsPastPeriodEnd() {
        PayCalculator payCalculator = new PayCalculator(22, 4, "B");
        assertThat(payCalculator.getPayForSecondRatePeriod(), is(equalTo(16)));
    }

    @Test
    public void calculatesPayForThirdRatePeriodForFamilyB() {
        PayCalculator payCalculator = new PayCalculator(0, 4, "B");
        assertThat(payCalculator.getPayForThirdRatePeriod(), is(equalTo(64)));
    }

    @Test
    public void getsTotalPayForFamilyA() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "A");
        assertThat(payCalculator.getTotalPay(), is(equalTo(190)));
    }

    @Test
    public void getsTotalPayForFamilyA_singleRatePeriod() {
        PayCalculator payCalculator = new PayCalculator(17, 20, "A");
        assertThat(payCalculator.getTotalPay(), is(equalTo(45)));
    }

    @Test
    public void getsTotalPayForFamilyB() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(140)));
    }

    @Test
    public void getsTotalPayForFamilyBOtherThanMaxShiftLength() {
        PayCalculator payCalculator = new PayCalculator(17, 3, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(124)));
    }

    @Test
    public void getsTotalPayForFamilyB_allInSecondPeriod() {
        PayCalculator payCalculator = new PayCalculator(23, 0, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(8)));
    }

    @Test
    public void getsTotalPayForFamilyB_startsInSecondPeriod() {
        PayCalculator payCalculator = new PayCalculator(23, 3, "B");
        assertThat(payCalculator.getTotalPay(), is(equalTo(56)));
    }

    @Test
    public void getsTotalPayForFamilyC() {
        PayCalculator payCalculator = new PayCalculator(17, 4, "C");
        assertThat(payCalculator.getTotalPay(), is(equalTo(189)));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftEndsBeforePeriodStarts() {
        PayCalculator payCalculator = new PayCalculator(17, 18, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getSecondPeriodStartTime(),
                Family.FAMILYA.getSecondPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftEndsWhenPeriodStarts() {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getSecondPeriodStartTime(),
                Family.FAMILYA.getSecondPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftStartTimeAtOrAfterPeriodEndIfAfter() {
        PayCalculator payCalculator = new PayCalculator(0, 1, "A");
        assertThat(payCalculator.isShiftStartTimeAtOrAfterPeriodEnd(
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftStartTimeAtOrAfterPeriodEndIfEqual() {
        PayCalculator payCalculator = new PayCalculator(23, 0, "A");
        assertThat(payCalculator.isShiftStartTimeAtOrAfterPeriodEnd(
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftEndTimeAtOrBeforePeriodStartIfEqual() {
        PayCalculator payCalculator = new PayCalculator(17, 23, "A");
        assertThat(payCalculator.isShiftEndTimeAtOrBeforePeriodStart(
                Family.FAMILYA.getSecondPeriodStartTime()),
                is(true));
    }

    @Test
    public void returnsShiftEndTimeAtOrBeforePeriodStartIfBefore() {
        PayCalculator payCalculator = new PayCalculator(17, 22, "A");
        assertThat(payCalculator.isShiftEndTimeAtOrBeforePeriodStart(
                Family.FAMILYA.getSecondPeriodStartTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftStartsAfterPeriodEnds() {
        PayCalculator payCalculator = new PayCalculator(0, 4, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getFirstPeriodStartTime(),
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsOutsideRatePeriodIfShiftStartsWhenPeriodEnds() {
        PayCalculator payCalculator = new PayCalculator(23, 4, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getFirstPeriodStartTime(),
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(true));
    }

    @Test
    public void returnsShiftIsInsideRatePeriodIfTrue() {
        PayCalculator payCalculator = new PayCalculator(21, 4, "A");
        assertThat(payCalculator.isShiftOutsideRatePeriod(
                Family.FAMILYA.getFirstPeriodStartTime(),
                Family.FAMILYA.getFirstPeriodEndTime()),
                is(false));
    }

    @Test
    public void determinesEarlierTime() {
        PayCalculator payCalculator = new PayCalculator(17, 19, "A");
        assertThat(payCalculator.getEarlierTime(
                LocalDateTime.of(2019, 1, 1, 17, 0),
                LocalDateTime.of(2019, 1, 1, 19, 0)),
                is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void determinesLaterTime() {
        PayCalculator payCalculator = new PayCalculator(17, 19, "A");
        assertThat(payCalculator.getLaterTime(
                LocalDateTime.of(2019, 1, 1, 17, 0),
                LocalDateTime.of(2019, 1, 1, 19, 0)),
                is(equalTo(LocalDateTime.of(2019, 1, 1, 19, 0))));
    }
}
