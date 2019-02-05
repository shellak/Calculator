package com.developer.sergey.calculator.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.developer.sergey.calculator.R;


public class KeyPanelFragment extends Fragment implements View.OnClickListener {

    public interface OnKeyPanelClickListener {
        void onKeyPanelClick(int id);
    }

    private OnKeyPanelClickListener onKeyPanelClickListener;

    public KeyPanelFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_key_panel, container, false);
        init(view);

        return view;
    }

    private void init (View view) {
        Button allClearButton = view.findViewById(R.id.keyPanel_allClear);
        Button rightBracketButton = view.findViewById(R.id.keyPanel_right_bracket);
        Button leftBracketButton = view.findViewById(R.id.keyPanel_left_bracket);
        Button clearEntryButton = view.findViewById(R.id.keyPanel_clearEntry);
        Button divisionButton = view.findViewById(R.id.keyPanel_division);
        Button sevenButton = view.findViewById(R.id.keyPanel_seven);
        Button eightButton = view.findViewById(R.id.keyPanel_eight);
        Button nineButton = view.findViewById(R.id.keyPanel_nine);
        Button multiplicationButton = view.findViewById(R.id.keyPanel_multiplication);
        Button fourButton = view.findViewById(R.id.keyPanel_four);
        Button fiveButton = view.findViewById(R.id.keyPanel_five);
        Button sixButton = view.findViewById(R.id.keyPanel_six);
        Button subtractionButton = view.findViewById(R.id.keyPanel_subtraction);
        Button oneButton = view.findViewById(R.id.keyPanel_one);
        Button twoButton = view.findViewById(R.id.keyPanel_two);
        Button threeButton = view.findViewById(R.id.keyPanel_three);
        Button additionButton = view.findViewById(R.id.keyPanel_addition);
        Button zeroButton = view.findViewById(R.id.keyPanel_zero);
        Button pointButton = view.findViewById(R.id.keyPanel_point);
        Button resultButton = view.findViewById(R.id.keyPanel_result);

        allClearButton.setOnClickListener(this);
        leftBracketButton.setOnClickListener(this);
        rightBracketButton.setOnClickListener(this);
        clearEntryButton.setOnClickListener(this);
        divisionButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        multiplicationButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        subtractionButton.setOnClickListener(this);
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        additionButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        pointButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onKeyPanelClickListener.onKeyPanelClick(view.getId());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnKeyPanelClickListener) {
            onKeyPanelClickListener = (OnKeyPanelClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnKeyPanelClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onKeyPanelClickListener = null;
    }
}
