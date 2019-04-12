package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar sbRed,sbGreen,sbBlue;
    Button btShow;
    TextView ret1;
    int p1=0,p2=0,p3=0;
    ListView lv ;
    String[] stArr = {"黃阿瑪","招弟","柚子","三腳","浣腸","Socles","嚕嚕"};
    String[] picname = {"ama","judy","threeleg","w","soso","lulu"};
    ArrayAdapter<String> adapter;
    EditText input;
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
        input = (EditText) findViewById(R.id.etInput);
        ret1 = (TextView) findViewById(R.id.ret1);
        // ListView
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,stArr);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, stArr[position], Toast.LENGTH_SHORT).show();
                Intent it2 = new Intent(MainActivity.this,imgShow.class);
                it2.putExtra("cat",picname[position]);
                it2.putExtra("input",input.getText().toString());
                startActivityForResult(it2,100);
            }
        });
        // ListView
        btShow = (Button) findViewById(R.id.btShow);
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,ShowAct.class);
                it.putExtra("Red",p1);
                it.putExtra("Green",p2);
                it.putExtra("Blue",p3);
                startActivityForResult(it,100);
            }
        });
        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100 && resultCode==RESULT_OK){
            String str = data.getStringExtra("Return");
            ret1.setText("從 "+str+" 頁面回來");
        }
    }
}
