package com.java.calculator.app.calculator;

import java.util.Stack;

import static java.lang.Math.pow;

public class BullyingCalculator {

    public String expressionToRPN(String userString) {
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < userString.length(); i++) {
            int priority = getPriority(userString.charAt(i));
            if (priority == 0) {
                current.append(userString.charAt(i));
            }
            if (priority == 1) {
                stack.push(userString.charAt(i));
            }
            if (priority > 1) {
                current.append(" ");
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) current.append(stack.pop());
                    else break;
                }
                stack.push(userString.charAt(i));
            }
            if (priority == -1) {
                while (getPriority(stack.peek()) != 1) {
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

    public double getAnswerFromRPN(String rpn) {
        StringBuilder function = new StringBuilder(new String());
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    function.append(rpn.charAt(i++));
                    if (i == rpn.length()) {
                        break;
                    }
                }
                stack.push(Double.parseDouble(function.toString()));
                function = new StringBuilder(new String());
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                executeFunction(rpn.charAt(i), stack);
            }
        }
        return stack.pop();
    }

    private void executeFunction(char symbol, Stack<Double> stack) {
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
            return 3;
        } else if (symbol == '+' || symbol == '-') {
            return 2;
        } else if (symbol == '(') {
            return 1;
        } else if (symbol == ')') {
            return -1;
        } else return 0;
    }
}
