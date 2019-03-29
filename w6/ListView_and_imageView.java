package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv ;
    String[] stArr = {"黃阿瑪的後宮生活","走路痛","菜渣、魚乾"};
    String[][] itemArr = {{"黃阿瑪","招弟","柚子","三腳","浣腸","Socles","嚕嚕"},{"拜拜","小黑"},{"APA","OLI、OLA","ULU"}};
    String[][] picname = {{"ama","judy","threeleg","w","soso","lulu"},{"byebye","black"},{"apa","olaoli","ulu"}};
    int picindex=0;
    ArrayAdapter<String> adapter,itemAdapter;
    ArrayList<String> list;
    TextView output;
    Spinner spn;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,stArr);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
        output = (TextView) findViewById(R.id.output);
        list = new ArrayList<>();
        spn = (Spinner) findViewById(R.id.sp);
        iv = (ImageView) findViewById(R.id.iv);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                          @Override
                                          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                int picid = getResources().getIdentifier(picname[picindex][position],"drawable","com.example.user.myapplication");
                                                iv.setImageResource(picid);
                                          }

                                          @Override
                                          public void onNothingSelected(AdapterView<?> parent) {

                                          }
                                      }
        );
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str = stArr[position];
                        picindex = position;
                        itemAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, itemArr[position]);
                        spn.setAdapter(itemAdapter);
                        if (list.contains(str)) {
                            list.remove(str);
                        } else {
                            list.add(str);
                        }
                        String st = "";
                        for (String s : list) {
                            st += " " + s;
                        }
                        output.setText(st);
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();


                    }
                });

    }
}
