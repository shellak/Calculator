package com.developer.sergey.calculator.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.sergey.calculator.calculator.Calculator;
import com.developer.sergey.calculator.R;
import com.developer.sergey.calculator.common.ExtensionsKt;

import java.util.ArrayList;
import java.util.List;


public class DisplayFragment extends Fragment {

    private String result;
    private ArrayList<String> tokens;
    private TextView resultTextView;
    private TextView tokensTextView;
    private Calculator calculator;

    public DisplayFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            tokens = savedInstanceState.getStringArrayList("tokens");
            result = savedInstanceState.getString("result");
        }
        else {
            tokens = new ArrayList<>();
            addNumericToken("0");
            result = "0";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        init(view);
        return view;
    }

    public void init(View view) {
        resultTextView = view.findViewById(R.id.display_result);
        tokensTextView = view.findViewById(R.id.display_tokens);
        tokensTextView.setMovementMethod(new ScrollingMovementMethod());

        calculator = new Calculator();
        
        updateDisplay();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putStringArrayList("tokens", tokens);
        savedInstanceState.putString("result", result);
    }


    public void addToken(int id) {
        switch (id){
            case R.id.keyPanel_allClear:
                tokens.clear();
                tokens.add("0");
                break;
            case R.id.keyPanel_right_bracket:
                addBracketToken("(");
                break;
            case R.id.keyPanel_left_bracket:
                addBracketToken(")");
                break;
            case R.id.keyPanel_clearEntry:
                if(!tokens.isEmpty()) {
                    tokens.remove(tokens.size() - 1);
                    if (tokens.isEmpty()) tokens.add("0");
                }
                break;
            case R.id.keyPanel_division:
                addOperatorToken("/");
                break;
            case R.id.keyPanel_seven:
                addNumericToken("7");
                break;
            case R.id.keyPanel_eight:
                addNumericToken("8");
                break;
            case R.id.keyPanel_nine:
                addNumericToken("9");
                break;
            case R.id.keyPanel_multiplication:
                addOperatorToken("*");
                break;
            case R.id.keyPanel_four:
                addNumericToken("4");
                break;
            case R.id.keyPanel_five:
                addNumericToken("5");
                break;
            case R.id.keyPanel_six:
                addNumericToken("6");
                break;
            case R.id.keyPanel_subtraction:
                addOperatorToken("-");
                break;
            case R.id.keyPanel_one:
                addNumericToken("1");
                break;
            case R.id.keyPanel_two:
                addNumericToken("2");
                break;
            case R.id.keyPanel_three:
                addNumericToken("3");
                break;
            case R.id.keyPanel_addition:
                addOperatorToken("+");
                break;
            case R.id.keyPanel_zero:
                addNumericToken("0");
                break;
            case R.id.keyPanel_point:
                addPointToken();
                break;
            case R.id.keyPanel_result:
                resultPressed();
                break;
        }

        if(id != R.id.keyPanel_result) {
            String temp = calculator.calculate(tokens);
            result = temp != null ? temp : result;
        }
        updateDisplay();
    }

    private void updateDisplay() {
        tokensTextView.setText(tokens.toString().replaceAll("\\[|\\]|,| ",""));
        resultTextView.setText(result);
    }

    private void resultPressed() {
        String str = calculator.calculate(tokens);
        tokens.clear();

        if(str != null) {
            result = str;
            addNumericToken(str);
        }
        else
        {
            result = "0";
            addNumericToken("0");
        }
    }

    private void addOperatorToken(String token) {
        if(tokens.isEmpty()) {
            addNumericToken("0");
            tokens.add(token);
        }
        else
        if(ExtensionsKt.isOperator(tokens.get(tokens.size() - 1)))
            tokens.set(tokens.size() - 1, token);
        else
        if(tokens.get(tokens.size() - 1).equals(".")) {
            addNumericToken("0");
            tokens.add(token);
        }
        else
            tokens.add(token);
    }

    private void addNumericToken(String token) {
        boolean flag = true;

        if(!tokens.isEmpty()) {
            if(tokens.size() == 1 && tokens.get(0).equals("0"))
                flag = false;
            else
                for (int i = tokens.size() - 1; i >= 0; i--)
                    if (!ExtensionsKt.isNumeric(tokens.get(i))) break;
                    else if (!tokens.get(i).equals("0")) {
                        flag = true;
                        break;
                    } else
                        flag = false;
        }

        if(flag)
            tokens.add(token);
            else
                tokens.set(tokens.size() - 1, token);
    }

    private void addPointToken() {
        boolean flag = true;
        if(tokens.isEmpty() || ExtensionsKt.isOperator(tokens.get(tokens.size() - 1)))
            addNumericToken("0");
        else {
            for (int i = tokens.size() - 1; i >= 0; i--)
                if (ExtensionsKt.isOperator(tokens.get(i))) break;
                else if (ExtensionsKt.isPoint(tokens.get(i))) {
                    flag = false;
                    break;
                }
        }

        if(flag)
            tokens.add(".");
    }

    private void addBracketToken(String token) {
        tokens.add(token);
    }
}
