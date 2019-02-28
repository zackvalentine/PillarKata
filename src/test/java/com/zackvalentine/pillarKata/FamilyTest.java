package com.zackvalentine.pillarKata;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;

public class FamilyTest {
    @Test
    public void familyAValues() {
        assertThat(Family.FAMILYA.getValue(), is(equalTo("A")));
        assertThat(Family.FAMILYA.getFirstPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
        assertThat(Family.FAMILYA.getFirstPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 23, 0))));
        assertThat(Family.FAMILYA.getSecondPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 23, 0))));
        assertThat(Family.FAMILYA.getSecondPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
        assertThat(Family.FAMILYA.getFirstPeriodRate(), is(equalTo(15)));
        assertThat(Family.FAMILYA.getSecondPeriodRate(), is(equalTo(20)));
    }

    @Test
    public void familyBValues() {
        assertThat(Family.FAMILYB.getValue(), is(equalTo("B")));
        assertThat(Family.FAMILYB.getFirstPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
        assertThat(Family.FAMILYB.getFirstPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 22, 0))));
        assertThat(Family.FAMILYB.getSecondPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 22, 0))));
        assertThat(Family.FAMILYB.getSecondPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 0, 0))));
        assertThat(Family.FAMILYB.getThirdPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 0, 0))));
        assertThat(Family.FAMILYB.getThirdPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
        assertThat(Family.FAMILYB.getFirstPeriodRate(), is(equalTo(12)));
        assertThat(Family.FAMILYB.getSecondPeriodRate(), is(equalTo(8)));
        assertThat(Family.FAMILYB.getThirdPeriodRate(), is(equalTo(16)));
    }

    @Test
    public void familyCValues() {
        assertThat(Family.FAMILYC.getValue(), is(equalTo("C")));
        assertThat(Family.FAMILYC.getFirstPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
        assertThat(Family.FAMILYC.getFirstPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
        assertThat(Family.FAMILYC.getSecondPeriodStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 21, 0))));
        assertThat(Family.FAMILYC.getSecondPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
        assertThat(Family.FAMILYC.getFirstPeriodRate(), is(equalTo(21)));
        assertThat(Family.FAMILYC.getSecondPeriodRate(), is(equalTo(15)));
    }

    @Test
    public void toType_A() {
        assertThat(Family.toType("A"), is(equalTo(Family.FAMILYA)));
    }
}
