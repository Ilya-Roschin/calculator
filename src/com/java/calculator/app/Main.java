package com.java.calculator.app;

import com.java.calculator.app.calculator.BullyingCalculator;

public class Main {

    public static void main(String[] args) {
        System.out.println(BullyingCalculator.expressionToRPN("(2+14)*2^3*2"));
        System.out.println(BullyingCalculator.getAnswerFromRPN(BullyingCalculator.expressionToRPN("(2+14)*2^3*2")));
    }
}
