package com.java.calculator.app;

import com.java.calculator.app.calculator.BullyingCalculator;

public class Main {

    public static void main(String[] args) {
        BullyingCalculator bullyingCalculator = new BullyingCalculator();
        System.out.println(bullyingCalculator.expressionToRPN("(2+14)*2^3*2"));
        System.out.println(bullyingCalculator.findAnswerFromRPN(bullyingCalculator.expressionToRPN("(2+14)*2^3*2")));
    }
}
