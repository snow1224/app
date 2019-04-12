package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ShowAct extends AppCompatActivity {

    Button ret1,color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ret1 = (Button) findViewById(R.id.btret1);
        color = (Button) findViewById(R.id.btColor);
        ImageView iv;
        Intent it = getIntent();
        int r = it.getIntExtra("Red",0),g = it.getIntExtra("Green",0),b = it.getIntExtra("Blue",0);
        color.setBackgroundColor(Color.rgb(r,g,b));
        ret1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "調色盤";
                Intent it = new Intent();
                it.putExtra("Return",str);
                setResult(RESULT_OK,it);
                finish();
            }
        });
    }
}
