package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MySurfaceview mysurfaceview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mysurfaceview=new MySurfaceview(this);
        mysurfaceview.go();
        setContentView(mysurfaceview);
//        setContentView(R.layout.activity_main);
    }
}
