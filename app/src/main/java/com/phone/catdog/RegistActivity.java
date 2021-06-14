package com.phone.catdog;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import android.database.sqlite.SQLiteDatabase;



public class RegistActivity extends AppCompatActivity {
    //뒤로가기
    ImageView iv_back;
    //종륲
    TextView tv_kind;
    String strKind;
    //크기
    Spinner spinSize;
    TextView tv_size;
    String strSize;
    int nSize=0;

    EditText editText;

    //의류종류
    Spinner spinCloth;
    String strCloth;
    int nCloth=0;

    Button btn_next;
    Button btn_save;


    //내정보,즐겨찾기
    Button btn_myinfo,btn_fav;

    DBHelper dbHelper;

    final static String dbName = "t3.db"; // DB 생성 파일 이름
    final static int dbVersion = 2; // DB Version


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        int type = getIntent().getIntExtra("type", 0);
        dbHelper = new DBHelper(this, dbName, null, dbVersion);

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        editText = findViewById(R.id.editTextTextPersonName);

        tv_kind = findViewById(R.id.tv_kind);



        //크기설정
        String str2 = type==0 ? "반려묘" : "반려견";

        tv_size = findViewById(R.id.tv_size);
        tv_size.setText(str2+" 크기");

        spinSize =  findViewById(R.id.spin_size);
        spinSize.setPrompt(str2+" 크기를 고르세요.");

        // Spinner 자체에는 선택된 이름만 출력되게 레이아웃 지정.
        ArrayAdapter Adapter2 = ArrayAdapter.createFromResource(this, R.array.dog_size, R.layout.item_list);


        spinSize.setAdapter(Adapter2);

        spinSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //크기 선택 문구 
                strSize = spinSize.getItemAtPosition(position).toString();
                nSize = position;
                // TODO Auto-generated method stub
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        strSize = spinSize.getItemAtPosition(0).toString();


        spinCloth =  findViewById(R.id.spin_cloth);
        spinCloth.setPrompt("의료종류를 고르세요.");

        // Spinner 자체에는 선택된 이름만 출력되게 레이아웃 지정.
        ArrayAdapter Adapter3 = ArrayAdapter.createFromResource(this, R.array.cloth, R.layout.item_list);

        spinCloth.setAdapter(Adapter3);

        spinCloth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //의류 선택 문구 
                strCloth = spinCloth.getItemAtPosition(position).toString();
                nCloth = position;
                // TODO Auto-generated method stub
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        strCloth = spinCloth.getItemAtPosition(0).toString();


        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this, PhotoActivity.class);
                intent.putExtra("kind",strKind);
                intent.putExtra("size",strSize);
                intent.putExtra("cloth",strCloth);
                startActivity(intent);
            }

        });

        btn_fav = findViewById(R.id.btn_fav);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavActivity.class);
                startActivity(intent);

            }
        });

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                strKind = editText.getText().toString();

                SQLiteDatabase db;
                String sql;

                db = dbHelper.getWritableDatabase();
                sql = String.format("INSERT INTO t3 VALUES('" + strKind + "','" + strSize + "','" + strCloth + "');");
                db.execSQL(sql);
                
                Intent intent = new Intent(RegistActivity.this, InfoActivity.class);
                intent.putExtra("kind",strKind);
                intent.putExtra("size",strSize);
                intent.putExtra("cloth",strCloth);
                startActivity(intent);
            }
        });

        btn_myinfo = findViewById(R.id.btn_myinfo);
        btn_myinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this, MyinfoActivity.class);
                startActivity(intent);
            }
        });
//        btn_fav = findViewById(R.id.btn_fav);
//        btn_fav.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegistActivity.this, InfoActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}