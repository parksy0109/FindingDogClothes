package com.phone.catdog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.phone.catdog.adapter.Data;
import com.phone.catdog.adapter.DataAdapter;


import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {
    ImageView iv_back;
    RecyclerView myRecycler;
    private DataAdapter mAdapter;

    Button btn_delete, btn_main;

    ArrayList<Data> alData = new ArrayList<>();

    SQLiteDatabase db;
    String sql;

    DBHelper2 dbHelper2;

    final static String dbName = "t4.db"; // DB 생성 파일 이름
    final static int dbVersion = 3; // DB Version

    String A,B,C,D,E,G;
    int F;
    Boolean H;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        dbHelper2 = new DBHelper2(this, dbName, null, dbVersion);
        db = dbHelper2.getReadableDatabase();
        sql = "SELECT * FROM t4;";
        Cursor cursor = db.rawQuery(sql, null);

        btn_main = findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delete = findViewById(R.id.btn_delete2);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper2.getWritableDatabase();
                sql = "DELETE FROM t4;";
                db.execSQL(sql);
                Intent intent = new Intent(FavActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                if(cursor.getString(5).equals("1")) {
                    A = cursor.getString(0);
                    B = cursor.getString(1);
                    C = cursor.getString(2);
                    D = cursor.getString(3);
                    E = cursor.getString(4);
                    F = Integer.parseInt(cursor.getString(5));
                    G = cursor.getString(6);

                    H = (F == 1);

                    alData.add(new Data (C,B,A,G,D,E,H));
                }
            }
            myRecycler = (RecyclerView) findViewById(R.id.recyclerview);
            myRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mAdapter = new DataAdapter(this, alData);
            myRecycler.setLayoutManager(new LinearLayoutManager(this));
            myRecycler.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        }
        cursor.close();


       /* if (isFav == true) {
            String path = getIntent().getStringExtra("path");
            String content = "종류는 " + getIntent().getStringExtra("kind") + "\n"
                    + "크기는 " + getIntent().getStringExtra("size");
            sort = getIntent().getStringExtra("kind");
            size = getIntent().getStringExtra("size");

            String price = getIntent().getStringExtra("price");
            String url = getIntent().getStringExtra("url");
            alData.add(cursor.getString(0));

            myRecycler = (RecyclerView) findViewById(R.id.recyclerview);
            myRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mAdapter = new DataAdapter(this, alData);
            myRecycler.setLayoutManager(new LinearLayoutManager(this));
            myRecycler.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

        */
    }



}

