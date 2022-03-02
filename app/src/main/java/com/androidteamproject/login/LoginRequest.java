package com.androidteamproject.login;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
//    서버 URL 설정
    final static private String URL = "http://13.209.76.14:8080/login";

    private Map<String, String> map;

    public LoginRequest(String userId, String userPw, Response.Listener<String> listener, Response.ErrorListener responseErrorListener) {
        super(Method.POST, URL, listener, responseErrorListener);

        map = new HashMap<>();
        map.put("userId", userId);
        map.put("userPw", userPw);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
