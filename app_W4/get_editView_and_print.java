package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvName,tvContent;
    Button btEnlarge,btSmall,btgetData;
    EditText edName,edBir;
    int size=30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView)findViewById(R.id.tvName);
        btEnlarge = (Button)findViewById(R.id.btEnlarge);
        btSmall = (Button)findViewById(R.id.btSmall);
        btgetData = (Button)findViewById(R.id.getData);
        tvContent = (TextView)findViewById(R.id.tvContent);
        edName = (EditText)findViewById(R.id.edIdname);
        edBir = (EditText)findViewById(R.id.edIdBir);

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
        btgetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edName.getText().toString()+"的生日是"+edBir.getText().toString();
                tvContent.setText(str);
            }
        });
    }
}
