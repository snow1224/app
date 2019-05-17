package com.example.user.sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv ;
    Button btAdd;
    ArrayList<String> ls;
    Cursor c;
    ArrayAdapter<String> adapter;
    String DATABASE_NAME="Memo.db";
    String TABLE_NAME = "Memo_table";
    public static SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDatabase(this);
        if(database.isOpen()){
            database.execSQL("create table if not exists friend_table(Topic TEXT,Content TEXT);");
        }
        ls=new ArrayList<String>();
        c=getAll();
        c.moveToFirst();
        while(c.moveToNext()){
            ls.add(c.getString(0));
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lv= (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(MainActivity.this,Input.class);
            }
        });
    }
    private void getDatabase(Context context) {
        if(database == null || !database.isOpen()){
            database = new MyOpenHelpers(this,DATABASE_NAME,null,1).getWritableDatabase();
        }
    }

    public Cursor getAll() {
        return database.query(TABLE_NAME,		//資料表名稱
                new String[] {"Topic","Content"},	//欄位名稱
                null, // WHERE
                null, // WHERE 的參數
                null, // GROUP BY
                null, // HAVING
                null  // ORDOR BY
        );
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
