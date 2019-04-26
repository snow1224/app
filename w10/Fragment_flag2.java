package com.example.user.myapplication;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class flag2 extends Fragment {

    TextView mainTV;
    SeekBar sbRed,sbGreen,sbBlue;
    int p1=0,p2=0,p3=0;
    public flag2() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_flag2, container, false);
        mainTV= ((MainActivity)getActivity()).tvMain;
        sbRed = (SeekBar) v.findViewById(R.id.sbIdRed);
        sbGreen = (SeekBar) v.findViewById(R.id.sbIdGreen);
        sbBlue = (SeekBar) v.findViewById(R.id.sbIdBlue);
        sbRed.setMax(255);
        sbGreen.setMax(255);
        sbBlue.setMax(255);

        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mainTV.setBackgroundColor(Color.rgb(progress,p2,p3));
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
                mainTV.setBackgroundColor(Color.rgb(p1,progress,p3));
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
                mainTV.setBackgroundColor(Color.rgb(p1,p2,progress));
                p3=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;
    }

}
