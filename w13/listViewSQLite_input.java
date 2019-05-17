package com.example.user.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Input extends AppCompatActivity {

    EditText etTopic,etContent;
    Button btSave;
    String DATABASE_NAME="Memo.db";
    String TABLE_NAME = "Memo_table";
    public static SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        btSave= (Button) findViewById(R.id.btSave);
        etContent = (EditText) findViewById(R.id.etContent);
        etTopic = (EditText) findViewById(R.id.etTopic);

        getDatabase(this);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iTopic,iContent;
                iTopic=etTopic.getText().toString();
                iContent=etContent.getText().toString();
                insert(iTopic,iContent);
            }
        });

    }
    public void insert(String iTopic,String iContent){
        ContentValues cv = new ContentValues();
        cv.put("Topic",iTopic);
        cv.put("Content",iContent);
        database.insert(TABLE_NAME,null,cv);
        Intent it = new Intent();
        it.setClass(Input.this,MainActivity.class);
        startActivity(it);
    }

    private void getDatabase(Context context) {
        if(database == null || !database.isOpen()){
            database = new MyOpenHelpers(this,DATABASE_NAME,null,1).getWritableDatabase();
        }
    }


    public class MyOpenHelpers extends SQLiteOpenHelper {

        public MyOpenHelpers(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
