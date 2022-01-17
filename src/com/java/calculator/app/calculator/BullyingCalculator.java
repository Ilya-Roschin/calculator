package com.java.calculator.app.calculator;

import java.util.Stack;

import static java.lang.Math.pow;

public class BullyingCalculator {

    private static final int ZERO_PRIORITY = 0;
    private static final int FIRST_PRIORITY = 1;
    private static final int MINUS_FIRST_PRIORITY = -1;
    private static final int SECOND_PRIORITY = 2;
    private static final int THIRD_PRIORITY = 3;
    private static final String SPACE = " ";
    
    public String expressionToRPN(String userString) {
        StringBuilder current = new StringBuilder();
        final Stack<Character> stack = new Stack<>();
        for (int i = 0; i < userString.length(); i++) {
            int priority = getPriority(userString.charAt(i));
            if (priority == ZERO_PRIORITY) {
                current.append(userString.charAt(i));
            }
            if (priority == FIRST_PRIORITY) {
                stack.push(userString.charAt(i));
            }
            if (priority > FIRST_PRIORITY) {
                current.append(SPACE);
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        current.append(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(userString.charAt(i));
            }
            if (priority == MINUS_FIRST_PRIORITY) {
                while (getPriority(stack.peek()) != FIRST_PRIORITY) {
                    current.append(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            current.append(stack.pop());
        }
        return current.toString();
    }

    public double findAnswerFromRPN(String rpn) {
        StringBuilder function = new StringBuilder();
        final Stack<Double> stack = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == ZERO_PRIORITY) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == ZERO_PRIORITY) {
                    function.append(rpn.charAt(i++));
                    if (i == rpn.length()) {
                        break;
                    }
                }
                stack.push(Double.parseDouble(function.toString()));
                function = new StringBuilder();
            }
            if (getPriority(rpn.charAt(i)) > FIRST_PRIORITY) {
                executeFunction(rpn.charAt(i), stack);
            }
        }
        return stack.pop();
    }

    private void executeFunction(char symbol, final Stack<Double> stack) {
        double number1 = stack.pop();
        double number2 = stack.pop();
        switch (symbol) {
            case '+':
                stack.push(number2 + number1);
                break;
            case '-':
                stack.push(number2 - number1);
                break;
            case '*':
                stack.push(number2 * number1);
                break;
            case '/':
                stack.push(number2 / number1);
                break;
            case '%':
                stack.push(number2 * number1 / 100);
                break;
            case '^':
                stack.push(pow(number2, number1));
                break;
        }
    }

    private int getPriority(char symbol) {
        if (symbol == '^') {
            return 4;
        } else if (symbol == '*' || symbol == '/' || symbol == '%') {
            return THIRD_PRIORITY;
        } else if (symbol == '+' || symbol == '-') {
            return SECOND_PRIORITY;
        } else if (symbol == '(') {
            return ZERO_PRIORITY;
        } else if (symbol == ')') {
            return MINUS_FIRST_PRIORITY ;
        } else return 0;
    }
}
