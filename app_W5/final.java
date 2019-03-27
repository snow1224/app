package com.example.user.myapplication;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Spinner spLocation;
    RadioGroup reAge ;
    RadioButton age0_20,age21_40,age41_100;
    CheckBox cbSport,cbNovel,cbSing,cbEat;
    TextView result;
    Button btPrint;
    EditText etName;
//    int cbArr[] = {cbEat.getId(),cbSport.getId(),cbNovel.getId(),cbSing.getId()};
    String str1="",str2="",str3="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spLocation=(Spinner)findViewById(R.id.spLocation);
        reAge = (RadioGroup) findViewById(R.id.rgAge);
        age0_20 = (RadioButton) findViewById(R.id.age0_20);
        age21_40 = (RadioButton) findViewById(R.id.age21_40);
        age41_100 = (RadioButton) findViewById(R.id.age41_100);
        cbEat=(CheckBox)findViewById(R.id.cbIdEat);
        cbSport=(CheckBox)findViewById(R.id.cbIdSport);
        cbNovel=(CheckBox)findViewById(R.id.cbIdNovel);
        cbSing=(CheckBox)findViewById(R.id.cbIdSing);
        result=(TextView)findViewById(R.id.tvResult);
        btPrint=(Button)findViewById(R.id.btIdprint);
        etName=(EditText)findViewById(R.id.etName);

        btPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "姓名："+etName.getText().toString()+"\n"+"出生地："+str2+"\n"+"年齡："+str3+"\n"+"興趣："+str1;
                result.setText(str);
            }
        });

        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getSelectedItem().toString();
                str2 = parent.getSelectedItem().toString();
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
                str3=""+checkedId;
                Toast.makeText(MainActivity.this, "Age:"+str, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void doCB(View v){
        CheckBox cbTemp;
        cbTemp = (CheckBox) findViewById(v.getId());
        if(cbTemp.isChecked())
            str1 += cbTemp.getText().toString() + " ";

    }
}
