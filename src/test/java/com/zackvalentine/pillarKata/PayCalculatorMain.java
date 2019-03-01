package com.zackvalentine.pillarKata;

import java.io.IOException;

public class PayCalculatorMain {
    public static void main(String[] args) throws IOException {
        PayCalculator payCalculator = new PayCalculator(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
        System.out.print(payCalculator.getTotalPay());
    }
}
