package com.example.user.myapplication;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar sbRed,sbGreen,sbBlue;
    Button btOne;
    NumberPicker npRed,npGreen,npBlue;
    int p1=0,p2=0,p3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sbRed = (SeekBar) findViewById(R.id.sbIdRed);
        sbGreen = (SeekBar) findViewById(R.id.sbIdGreen);
        sbBlue = (SeekBar) findViewById(R.id.sbIdBlue);
        sbRed.setMax(255);
        sbGreen.setMax(255);
        sbBlue.setMax(255);
        npRed = (NumberPicker)findViewById(R.id.npIdRed);
        npGreen = (NumberPicker)findViewById(R.id.npIdGreen);
        npBlue = (NumberPicker)findViewById(R.id.npIdBlue);
        npRed.setMinValue(0);
        npRed.setMaxValue(255);
        npGreen.setMinValue(0);
        npGreen.setMaxValue(255);
        npBlue.setMinValue(0);
        npBlue.setMaxValue(255);

        btOne = (Button) findViewById(R.id.btOne);



        npRed.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                p1=newVal;
                btOne.setBackgroundColor(Color.rgb(p1,p2,p3));
            }
        });
        npGreen.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                p2=newVal;
                btOne.setBackgroundColor(Color.rgb(p1,p2,p3));
            }
        });
        npBlue.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                p3=newVal;
                btOne.setBackgroundColor(Color.rgb(p1,p2,p3));
            }
        });
        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                btOne.setBackgroundColor(Color.rgb(progress,p2,p3));
                p1=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                btOne.setBackgroundColor(Color.rgb(p1,progress,p3));
                p2=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                btOne.setBackgroundColor(Color.rgb(p1,p2,progress));
                p3=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }



}
