package com.developer.sergey.calculator.calculator;

import com.developer.sergey.calculator.calculator.convertor.Converter;
import com.developer.sergey.calculator.common.ExtensionsKt;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Calculator implements ICalculator {
    private final double EPSILON = 1E-13;

    private Stack<String> stackRPN;
    private Stack<String> stackCalc;
    private Converter converter;


    public Calculator() {
        converter = new Converter();
        stackCalc = new Stack<>();
    }


    public String calculate(List<String> InputData) {
        try {
            stackRPN = converter.convertToRPN(InputData);

            Collections.reverse(stackRPN);
            stackCalc.clear();

            while (!stackRPN.isEmpty()) {
                String token = stackRPN.pop();
                if (ExtensionsKt.isNumeric(token))
                    stackCalc.push(token);
                else
                if (ExtensionsKt.isOperator(token)) {

                    double op1 = Double.parseDouble(stackCalc.pop());
                    double op2 = Double.parseDouble(stackCalc.pop());

                    switch (token) {
                        case "+":
                            stackCalc.push(String.valueOf(op2 + op1));
                            break;
                        case "-":
                            stackCalc.push(String.valueOf(op2 - op1));
                            break;
                        case "*":
                            stackCalc.push(String.valueOf(op2 * op1));
                            break;
                        case "/":
                            stackCalc.push(String.valueOf(op2 / op1));
                            break;
                    }
                }
            }

            double mDouble = Double.parseDouble(stackCalc.pop());
            if(isEquals(mDouble, Math.round(mDouble)))
                return String.valueOf(Math.round(mDouble));
            else
                return String.valueOf(mDouble);
        }
        catch (Exception ex) {
            return null;
        }
    }

    private boolean isEquals(double op1, double op2) {
        if(Math.abs(op1 - op2) <= EPSILON) return true;
        else
            return false;
    }

}