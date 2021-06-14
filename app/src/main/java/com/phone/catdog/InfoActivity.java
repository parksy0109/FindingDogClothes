package com.phone.catdog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    //뒤로가기
    ImageView iv_back;
    //내정보,즐겨찾기
    Button btn_main,btn_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btn_fav = findViewById(R.id.btn_fav);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this,FavActivity.class);
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

        TextView tv_kind = findViewById(R.id.tv_kind);
        TextView tv_size = findViewById(R.id.tv_size);
        TextView tv_cloth = findViewById(R.id.tv_cloth);

        Intent intent = getIntent();
        String kind = intent.getStringExtra("kind");
        String size = intent.getStringExtra("size");
        String cloth = intent.getStringExtra("cloth");

        tv_kind.setText(kind);
        tv_size.setText(size+" 크기");
        tv_cloth.setText(cloth+" 옷종류");

        btn_main = findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}