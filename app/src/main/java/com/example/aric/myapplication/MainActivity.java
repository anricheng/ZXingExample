package com.example.aric.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private FrameLayout frameLayout;
    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;
        final FragmentManager supportFragmentManager = getSupportFragmentManager();

        button = (Button) findViewById(R.id.bt_scaner);
        final EditText searchView;
        frameLayout = (FrameLayout) findViewById(R.id.fl_scaner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frameLayout.setVisibility(View.VISIBLE);


                supportFragmentManager.beginTransaction().add(R.id.fl_scaner, new myFragment()).commit();

            }
        });

        searchView = (EditText) findViewById(R.id.seach_text);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.requestFocus();
            }
        });
        searchView.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    frameLayout.measure(0,0);
                    int measuredHeight = frameLayout.getMeasuredHeight();
                    frameLayout.setPadding(0,-measuredHeight,0,0);
                } else {
                    frameLayout.setPadding(0,0,0,0);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}

