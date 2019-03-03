package com.zackvalentine.pillarKata;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;

public class FamilyTest {
    @Test
    public void familyAHasCorrectLetter() {
        assertThat(Family.FAMILYA.getValue(), is(equalTo("A")));
    }

    @Test
    public void familyAHasCorrectFirstPeriodStartTime() {
        assertThat(Family.FAMILYA.getFirstPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void familyAHasCorrectFirstPeriodEndTime() {
        assertThat(Family.FAMILYA.getFirstPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 23, 0))));
    }

    @Test
    public void familyAHasCorrectSecondPeriodStartTime() {
        assertThat(Family.FAMILYA.getSecondPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 23, 0))));
    }

    @Test
    public void familyAHasCorrectSecondPeriodEndTime() {
        assertThat(Family.FAMILYA.getSecondPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
    }

    @Test
    public void familyAHasCorrectFirstPeriodRate() {
        assertThat(Family.FAMILYA.getFirstPeriodRate(), is(equalTo(15)));
    }

    @Test
    public void familyAHasCorrectSecondPeriodRate() {
        assertThat(Family.FAMILYA.getSecondPeriodRate(), is(equalTo(20)));
    }

    @Test
    public void familyBHasCorrectLetter() {
        assertThat(Family.FAMILYB.getValue(), is(equalTo("B")));
    }

    @Test
    public void familyBHasCorrectFirstPeriodStartTime() {
        assertThat(Family.FAMILYB.getFirstPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void familyBHasCorrectFirstPeriodEndTime() {
        assertThat(Family.FAMILYB.getFirstPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 22, 0))));
    }

    @Test
    public void familyBHasCorrectSecondPeriodStartTime() {
        assertThat(Family.FAMILYB.getSecondPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 22, 0))));
    }

    @Test
    public void familyBHasCorrectSecondPeriodEndTime() {
        assertThat(Family.FAMILYB.getSecondPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 0, 0))));
    }

    @Test
    public void familyBHasCorrectThirdPeriodStartTime() {
        assertThat(Family.FAMILYB.getThirdPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 0, 0))));
    }

    @Test
    public void familyBHasCorrectThirdPeriodEndTime() {
        assertThat(Family.FAMILYB.getThirdPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
    }

    @Test
    public void familyBHasCorrectFirstPeriodRate() {
        assertThat(Family.FAMILYB.getFirstPeriodRate(), is(equalTo(12)));
    }

    @Test
    public void familyBHasCorrectSecondPeriodRate() {
        assertThat(Family.FAMILYB.getSecondPeriodRate(), is(equalTo(8)));
    }

    @Test
    public void familyHasCorrectThirdPeriodRate() {
        assertThat(Family.FAMILYB.getThirdPeriodRate(), is(equalTo(16)));
    }

    @Test
    public void familyCHasCorrectLetter() {
        assertThat(Family.FAMILYC.getValue(), is(equalTo("C")));
    }

    @Test
    public void familyCHasCorrectFirstPeriodStartTime() {
        assertThat(Family.FAMILYC.getFirstPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }

    @Test
    public void familyCHasCorrectFirstPeriodEndTime() {
        assertThat(Family.FAMILYC.getFirstPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
    }

    @Test
    public void familyCHasCorrectSecondPeriodStartTime() {
        assertThat(Family.FAMILYC.getSecondPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
    }

    @Test
    public void familyCHasCorrectSecondPeriodEndTime() {
        assertThat(Family.FAMILYC.getSecondPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
    }

    @Test
    public void familyCHasCorrectFirstPeriodRate() {
        assertThat(Family.FAMILYC.getFirstPeriodRate(), is(equalTo(21)));
    }

    @Test
    public void familyCHasCorrectSecondPeriodRate() {
        assertThat(Family.FAMILYC.getSecondPeriodRate(), is(equalTo(15)));
    }

    @Test
    public void toType_A() {
        assertThat(Family.toType("A"), is(equalTo(Family.FAMILYA)));
    }

    @Test
    public void toType_B() {
        assertThat(Family.toType("B"), is(equalTo(Family.FAMILYB)));
    }

    @Test
    public void toType_C() {
        assertThat(Family.toType("C"), is(equalTo(Family.FAMILYC)));
    }

    @Test
    public void toType_invalid() {
        assertThat(Family.toType("Invalid String"), is(nullValue()));
    }

    @Test
    public void returnsThirdRatePeriodNotAllowedForFamilyA() {
        assertThat(Family.FAMILYA.isThirdRatePeriodAllowed(), is(false));
    }

    @Test
    public void returnsThirdRatePeriodAllowedForFamilyB() {
        assertThat(Family.FAMILYB.isThirdRatePeriodAllowed(), is(true));
    }

    @Test
    public void returnsThirdRatePeriodNotAllowedForFamilyC() {
        assertThat(Family.FAMILYC.isThirdRatePeriodAllowed(), is(false));
    }
}
