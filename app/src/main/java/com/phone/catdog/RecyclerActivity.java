package com.phone.catdog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import com.phone.catdog.adapter.Data;
import com.phone.catdog.adapter.DataAdapter;
import com.phone.catdog.web.BaseRequest;
import com.phone.catdog.web.WebDefine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    Button btn_fav, btn_myinfo;
    ImageView iv_back;
    RecyclerView myRecycler;
    private DataAdapter mAdapter;
    ArrayList<Data> alData = new ArrayList<>();
    Boolean isFav;
    String A,B,C,D,E,G;
    int F;

    DBHelper2 dbHelper2;
    final static String dbName = "t4.db"; // DB 생성 파일 이름
    final static int dbVersion = 3; // DB Version


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        iv_back = findViewById(R.id.iv_back);
        btn_fav = findViewById(R.id.btn_fav);
        dbHelper2 = new DBHelper2(this, dbName, null, dbVersion);

        btn_myinfo = findViewById(R.id.btn_myinfo);
        btn_myinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerActivity.this, MyinfoActivity.class);
                startActivity(intent);
            }
        });



        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                String sql;

                db = dbHelper2.getWritableDatabase();

                for (int i = 0; i < alData.size(); i++) {
                    A = alData.get(i).getSort();
                    B = alData.get(i).getSize();
                    C = alData.get(i).getPath();
                    D = alData.get(i).getPrice();
                    E = alData.get(i).getUrl();
                    if(alData.get(i).isFav()){
                        F = 1;

                    }
                    else {
                        F = 0;
                    }
                    G = alData.get(i).getContent();
                    sql = "INSERT INTO t4 VALUES('" + A + "','" + B + "','" + C + "','" + D + "','" + E + "','"+ F + "','" + G + "');";
                    db.execSQL(sql);
                }
                isFav = alData.get(0).isFav();
                Intent Fav = new Intent(RecyclerActivity.this, FavActivity.class);
                startActivity(Fav);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
        initList();
    }

    private void initData() {
       /* String path = getIntent().getStringExtra("path");
        String content = getIntent().getStringExtra("content");
        String price = "10000원";
        String url = "http://naver.com";
        boolean isFav = getIntent().getBooleanExtra("isFav", false);
        alData.add(new Data(path,content,price,url,isFav));*/
        post_list(getIntent().getIntExtra("color_r",0),getIntent().getIntExtra("color_g",0),getIntent().getIntExtra("color_b",0));
    }

    private void initList() {
        myRecycler = (RecyclerView) findViewById(R.id.recyclerview);
        myRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new DataAdapter(this, alData);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void post_list(int color_r,int color_g,int color_b) {
        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Response 에서 리스너를 만들어서 결과를 받아올 수 있도록 함
                response = response.replace("ï»¿","");
                Log.e("response", response);

                alData.clear();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");
                    for(int i=0;i<result.length();i++){
                        JSONObject object = result.getJSONObject(i);
                        if(getIntent().getStringExtra("size").equals(object.getString("pet_size"))) {
                            if (getIntent().getStringExtra("cloth").equals(object.getString("kind"))) {
                                //PostData(int _id, String title, String writer, String writerImgPath, String date, String text, String imgUrl, int fav_cnt, int comment_cnt)
                                //
                                // {"post_idx":"5","post_title":"\uac00","user_idx":"1","post_date":"2021-05-23 00:00:00","post_text":"\ub098","post_file1":"20210523_172525_1.png","post_file2":"20210523_172525_2.pdf","post_file3":"20210523_172525_3.mp4","post_file4":null,"post_file5":null,"post_tag":"","project_idx":"22","file_type1":"1","file_type2":"0","file_type3":"3","file_type4":"-1","file_type5":"-1"},
                                String path = "http://idonghug.dothome.co.kr/imgs/" + object.getString("path");
                                String content = "크기: " + object.getString("pet_size") + "\n"
                                        + "의류종류: " + object.getString("kind");
                                String pet_size = object.getString("pet_size");
                                String kind = object.getString("kind");
                                String price = "가격: " + object.getString("price") + "원";
                                String url = object.getString("site");

                                alData.add(new Data(path, pet_size, kind, content, price, url, false));
                            }
                        }
                    }

                    mAdapter.notifyDataSetChanged();




                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // 예외처리
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("response", error.toString());
                Toast.makeText(RecyclerActivity.this, "서버오류 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        };

        try {




            BaseRequest request = new BaseRequest(WebDefine.BASE_URL, "product_list_color.php?color_r="+ color_r+"&color_g="+color_g+"&color_b="+color_b, responseListner,errorListener);
            // 실제 서버 응답 할 수 있는 tfRequest 생성
            RequestQueue queue = Volley.newRequestQueue(RecyclerActivity.this);
            // loginRequest를 queue에 담아 실행
            queue.add(request);
                /* 정상적으로 tfRequest가 보내지고 그 결과로 나온 Response가 jsonResponse를 통해서 다루어지게 됨
                 따라서 오류난경우만 예외처리함 */

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}