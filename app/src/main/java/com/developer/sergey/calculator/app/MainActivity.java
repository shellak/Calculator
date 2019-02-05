package com.developer.sergey.calculator.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developer.sergey.calculator.R;

public class MainActivity extends AppCompatActivity
        implements KeyPanelFragment.OnKeyPanelClickListener {

    private DisplayFragment displayFragment;
    private KeyPanelFragment keyPanelFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyPanelFragment = new KeyPanelFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_keyPanel, keyPanelFragment)
                .commit();

        if(savedInstanceState == null) {
            displayFragment = new DisplayFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_display, displayFragment)
                    .commit();
        }
        else {
            displayFragment = (DisplayFragment)getSupportFragmentManager()
                    .getFragment(savedInstanceState, "displayFragment");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        getSupportFragmentManager().putFragment(savedInstanceState,"displayFragment", displayFragment);
    }

    @Override
    public void onKeyPanelClick(int id) {
        displayFragment.addToken(id);
    }
}
