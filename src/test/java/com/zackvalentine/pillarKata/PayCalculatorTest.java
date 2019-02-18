package com.zackvalentine.pillarKata;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;

public class PayCalculatorTest {
    @Test
    public void convertsStartHourToLocalDateTime() {
        PayCalculator payCalculator = new PayCalculator(17);
        assertThat(payCalculator.getStartTime(), is(equalTo(LocalDateTime.of(2019, 1, 1, 17, 0))));
    }
}
