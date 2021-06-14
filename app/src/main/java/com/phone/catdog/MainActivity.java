package com.phone.catdog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    //퍼미션 관련
    private final int MY_PERMISSION_REQUEST = 100;
    /**
     * Application permission 목록, android build target 23
     */
    public static final String[] MANDATORY_PERMISSIONS = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CAMERA"

    };
    //고양이//강아지
    ImageView iv_dog;
    //내정보,즐겨찾기
    Button btn_myinfo,btn_fav;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //23이상부터 퍼미션 체크
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            checkPermission(MANDATORY_PERMISSIONS);
        }

        context = this;

        iv_dog = findViewById(R.id.iv_dog);
        iv_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RegistActivity.class);
                //강아지 타입 넘김
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

        btn_myinfo = findViewById(R.id.btn_myinfo);
        btn_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyinfoActivity.class);
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
    }

    //퍼미션 체크 함수
    @TargetApi(23)
    private void checkPermission(String[] permissions) {
        requestPermissions(permissions, MY_PERMISSION_REQUEST);
    }
}

