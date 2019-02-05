package com.developer.sergey.calculator.calculator.convertor;

import com.developer.sergey.calculator.common.ExtensionsKt;
import java.util.List;
import java.util.Stack;

public class Converter implements IConverter {
    private Stack<String> stackRPN;
    private Stack<String> stackOperations;

    public Converter() {
        stackOperations = new Stack<>();
        stackRPN = new Stack<>();
    }

    public Stack<String> convertToRPN(List<String> tokens) {
        stackRPN.clear();
        stackOperations.clear();

        String lastToken = null;
        for(int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (ExtensionsKt.isNumeric(token)) {
                if(lastToken == null || (!ExtensionsKt.isNumeric(lastToken) && !ExtensionsKt.isPoint(lastToken)))
                    stackRPN.push(token);
                else
                    stackRPN.push(stackRPN.pop() + token);
            }
            else
            if (ExtensionsKt.isOperator(token)) {
                while (!stackOperations.isEmpty()
                        && (ExtensionsKt.isOperator(stackOperations.peek()))
                        && (ExtensionsKt.definePriority(token)
                        <= ExtensionsKt.definePriority(stackOperations.peek())))
                    stackRPN.push(stackOperations.pop());

                stackOperations.push(token);
            }
            else
            if (ExtensionsKt.isLeftBracket(token))
                stackOperations.push(token);
            else
            if (ExtensionsKt.isRightBracket(token)) {
                while (!stackOperations.isEmpty()
                        && !ExtensionsKt.isLeftBracket(stackOperations.peek()))
                    stackRPN.push(stackOperations.pop());

                stackOperations.pop();
            }
            else
            if(ExtensionsKt.isPoint(token))
                stackRPN.push(stackRPN.pop() + token);

            lastToken = token;
        }

        while (!stackOperations.isEmpty()) {
            stackRPN.push(stackOperations.pop());
        }

        return stackRPN;
    }
}
