package com.example.user.myapplication;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView actv;
    Button btAdd,btClear;
    ArrayAdapter<String> adapter;
    Spinner spLocation;
    RadioGroup reAge ;
    RadioButton age0_20,age21_40,age41_100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actv=(AutoCompleteTextView)findViewById(R.id.actv);
        btAdd=(Button)findViewById(R.id.tvIdAdd);
        btClear=(Button)findViewById(R.id.tvIdClear);
        spLocation=(Spinner)findViewById(R.id.spLocation);
        reAge = (RadioGroup) findViewById(R.id.rgAge);
        age0_20 = (RadioButton) findViewById(R.id.age0_20);
        age21_40 = (RadioButton) findViewById(R.id.age21_40);
        age41_100 = (RadioButton) findViewById(R.id.age41_100);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        actv.setAdapter(adapter);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=actv.getText().toString();
                adapter.add(str);
            }
        });
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();
            }
        });
        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        reAge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                String str = "";
                switch (checkedId){
                    case R.id.age0_20:
                        str=age0_20.getText().toString();
                        break;
                    case R.id.age21_40:
                        str=age21_40.getText().toString();
                        break;
                    case R.id.age41_100:
                        str=age41_100.getText().toString();
                        break;
                }
                Toast.makeText(MainActivity.this, "Age:"+str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
