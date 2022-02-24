package com.androidteamproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AddScheduleActivity extends AppCompatActivity {
    LinearLayout colorselector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_addschedule);

        colorselector = (LinearLayout) findViewById(R.id.colorselector);
        colorselector.setOnClickListener(this::onClick);
    }
    public void onClick(View view){
        if(view==colorselector){ //view가 alert 이면 팝업실행 즉 버튼을 누르면 팝업창이 뜨는 조건
            Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

            //R.layout.dialog는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
            View layout = inflater.inflate(R.layout.color_selector,(ViewGroup) findViewById(R.id.popup));
            AlertDialog.Builder aDialog = new AlertDialog.Builder(AddScheduleActivity.this);

            aDialog.setView(layout);

            //팝업창 생성
            AlertDialog ad = aDialog.create();
            ad.show();//보여줌!

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            Window window = ad.getWindow();
            int x = (int)(size.x * 0.8);
            int y = (int)(size.y * 0.8);
            window.setLayout(x, y);
        }
}}
