package com.phone.catdog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GoogleCloudActivity extends AppCompatActivity {

    ImageView iv_back;
    //다음버튼
    ImageView iv_photo;

    Button btn_next;
    //내정보,즐겨찾기
    Button btn_myinfo,btn_fav;
    //추출색상,추천색상
    ImageView fabColor, devColor;

    String cloth, size;

    int d,e,f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_cloud);


        int a = getIntent().getIntExtra("RGB_R",0);
        int b = getIntent().getIntExtra("RGB_G",0);
        int c = getIntent().getIntExtra("RGB_B",0);
        cloth = getIntent().getStringExtra("cloth");
        size = getIntent().getStringExtra("size");

        Bitmap bm = (Bitmap)getIntent().getExtras().get("image");

        btn_fav = findViewById(R.id.btn_fav);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoogleCloudActivity.this, FavActivity.class);
                startActivity(intent);

            }
        });

        btn_myinfo = findViewById(R.id.btn_myinfo);
        btn_myinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoogleCloudActivity.this, MyinfoActivity.class);
                startActivity(intent);
            }
        });

        fabColor = findViewById(R.id.color);
        fabColor.setBackgroundColor(Color.rgb(a,b,c));
        if(a-255 < 0){
            d = -(a-255);
        }else{
            d = a-255;
        }
        if(b-255 < 0){
            e = -(b-255);
        }else{
            e = b-255;
        }
        if(c-255 < 0){
            f = -(c-255);
        }else{
            f = c-255;
        }

        devColor = findViewById(R.id.color2);
        devColor.setBackgroundColor(Color.rgb(d,e,f));

        iv_photo = findViewById(R.id.iv_photo2);
        iv_photo.setImageBitmap(bm);


        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        Glide
//                .with(this)
//                .load(getIntent().getStringExtra("path"))
//                .into(iv_photo);

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoogleCloudActivity.this, RecyclerActivity.class);

                intent.putExtra("size",size);
                intent.putExtra("cloth",cloth);
                intent.putExtra("color_r",d);
                intent.putExtra("color_g",e);
                intent.putExtra("color_b",f);

                startActivity(intent);
            }
        });
    }
}