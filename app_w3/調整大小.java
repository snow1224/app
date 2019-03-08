package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvName;
    Button btEnlarge,btSmall;
    int size=30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView)findViewById(R.id.tvName);
        btEnlarge = (Button)findViewById(R.id.btEnlarge);
        btSmall = (Button)findViewById(R.id.btSmall);

        btEnlarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size++;
                tvName.setTextSize((float)size);
            }
        });

        btSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size--;
                tvName.setTextSize((float)size);
            }
        });
    }
}
