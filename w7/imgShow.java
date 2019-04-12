package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class imgShow extends AppCompatActivity {

    ImageView iv;
    TextView showText;
    Button ret2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_show);
        iv = (ImageView) findViewById(R.id.ivImg);
        showText = (TextView) findViewById(R.id.tvShow);
        Intent it = getIntent();
        String str = it.getStringExtra("input");
        String cat = it.getStringExtra("cat");
        showText.setText(str);
        int picid = getResources().getIdentifier(cat,"drawable","com.example.user.myapplication");
        iv.setImageResource(picid);
        ret2 = (Button) findViewById(R.id.btret2);
        ret2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "貓咪";
                Intent it = new Intent();
                it.putExtra("Return",str);
                setResult(RESULT_OK,it);
                finish();
            }
        });
    }
}
