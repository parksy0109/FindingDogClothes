package com.phone.catdog;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyinfoActivity extends AppCompatActivity {

    TextView textViewMent, textViewKind, textViewSize, textViewCloth;
    Button btn_fav, btn_main, btn_delete;
    ImageView iv_back;

    SQLiteDatabase db = null;
    String sql;

    DBHelper dbHelper;

    final static String dbName = "t3.db"; // DB 생성 파일 이름
    final static int dbVersion = 2; // DB Version


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this, dbName, null, dbVersion);
        setContentView(R.layout.activity_myinfo);

        textViewMent = findViewById(R.id.textViewMent);
        textViewKind = findViewById(R.id.textViewKind);
        textViewSize = findViewById(R.id.textViewSize);
        textViewCloth = findViewById(R.id.textViewCloth);
        btn_fav = findViewById(R.id.btn_fav);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyinfoActivity.this, FavActivity.class);
                startActivity(intent);

            }
        });
        btn_main = findViewById(R.id.btn_main);
        iv_back = findViewById(R.id.iv_back);
        btn_delete = findViewById(R.id.btn_delete);


        db = dbHelper.getReadableDatabase();

        sql = "SELECT * FROM t3;";

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                textViewKind.append(String.format("\n 이름: %s", cursor.getString(0)));
                textViewSize.append(String.format("\n 크기: %s",cursor.getString(1)));
                textViewCloth.append(String.format("\n 옷: %s",cursor.getString(2)));
            }
        }else{
        }
        cursor.close();
        dbHelper.close();


        btn_main.setOnClickListener(v -> {
            Intent intent = new Intent(MyinfoActivity.this, MainActivity.class);
            startActivity(intent);
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                sql = "DELETE FROM t3;";
                db.execSQL(sql);
                Intent intent = new Intent(MyinfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}