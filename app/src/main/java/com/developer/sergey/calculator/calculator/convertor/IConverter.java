package com.developer.sergey.calculator.calculator.convertor;

import java.util.List;
import java.util.Stack;

public interface IConverter {
     Stack<String> convertToRPN(List<String> tokens);
}
