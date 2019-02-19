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
        assertThat(Family.FAMILYA.getFirstPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 23, 0))));
        assertThat(Family.FAMILYA.getSecondPeriodEndTime(), is(equalTo(LocalDateTime.of(2019, 1, 2, 4, 0))));
        assertThat(Family.FAMILYA.getFirstPeriodRate(), is(equalTo(15)));
        assertThat(Family.FAMILYA.getSecondPeriodRate(), is(equalTo(20)));
    }
}
