package com.zackvalentine.pillarKata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PayCalculatorMainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void mainReturnsCalculatedValueFromInput() throws IOException {
        String[] args = {"17", "18", "A"};

        PayCalculatorMain.main(args);

        assertThat(outContent.toString(), is(equalTo("15")));
    }

    @Test
    public void mainReturnsCalculatedValueFromInputForMultipleHours() throws IOException {
        String[] args = {"17", "19", "B"};
        PayCalculatorMain.main(args);

        assertThat(outContent.toString(), is(equalTo("24")));
    }
}
