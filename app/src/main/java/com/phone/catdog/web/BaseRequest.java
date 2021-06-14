package com.phone.catdog.web;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/*해당서버에 직접 로그인 요청을 보낼수 있도록 하는 액티비티*/

public class BaseRequest extends StringRequest { //StringRequest를 상속받아 사용



    // 접속할 서버주소를 의미 (자신의 웹서버주소 적용)
    private Map<String, String> parameters;
    // 맵 생성

    public BaseRequest(String addr, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        // LoginRequest는 유저 아이디, 비밀번호, 응답을 받을 수 있는 리스너 생성자구문
        super(Method.GET,addr+url, listener, errorListener);
        Log.e("url",addr+""+url);

        // 해당 URL에 파라미터들을 POST방식으로 해당 요청을 숨겨서 보냄
        parameters = new HashMap<>();
        /*String parmeter = "tf_code="+tf_code+"&tf_date="+tf_date+"&version="+version;
        try {
            String base64 = Util_String.encode(parmeter);
            Log.e("response",base64);
            parameters.put("mv_data", base64);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/


        // 아이디와 비밀번호를 파라미터로 매칭
    }
    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
