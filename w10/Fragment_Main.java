package com.example.user.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvMain;
    flag1 flag1 = new flag1();
    flag2 flag2 = new flag2();
    Button tea,color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMain= (TextView) findViewById(R.id.mainTV);
        tea= (Button) findViewById(R.id.afternoonTea);
        color= (Button) findViewById(R.id.color);
        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction tx = manager.beginTransaction();
                tx.replace(R.id.mainFL,flag1);
                tx.addToBackStack("First");
                tx.commit();
            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction tx = manager.beginTransaction();
                tx.replace(R.id.mainFL,flag2);
                tx.addToBackStack("Second");
                tx.commit();
            }
        });

    }
}
