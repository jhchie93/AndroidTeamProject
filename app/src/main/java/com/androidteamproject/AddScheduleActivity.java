package com.androidteamproject;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

public class AddScheduleActivity extends AppCompatActivity {
    LinearLayout colorselector, repeatselector;
    Button btn_repeat1, btn_repeat2, btn_repeat3, btn_repeat4, btn_repeat5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_addschedule);

        colorselector = (LinearLayout) findViewById(R.id.colorselector);
        colorselector.setOnClickListener(this::onClick);

        repeatselector = (LinearLayout) findViewById(R.id.repeatselector);
        repeatselector.setOnClickListener(this::onClick);
        btn_repeat1 = findViewById(R.id.btn_repeat1);
        btn_repeat2 = findViewById(R.id.btn_repeat2);
        btn_repeat3 = findViewById(R.id.btn_repeat3);
        btn_repeat4 = findViewById(R.id.btn_repeat4);
        btn_repeat5 = findViewById(R.id.btn_repeat5);



        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        MapView mapView = new MapView(this);
        mapViewContainer.addView(mapView);

    }

    public void onRadioButtonClicked(View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.btn_repeat1:
                if (isSelected) {
                    btn_repeat1.setTextColor(Color.BLACK);
                    btn_repeat2.setTextColor(Color.LTGRAY);
                    btn_repeat3.setTextColor(Color.LTGRAY);
                    btn_repeat4.setTextColor(Color.LTGRAY);
                    btn_repeat5.setTextColor(Color.LTGRAY);
                }
                break;
            case R.id.btn_repeat2:
                if (isSelected) {
                    btn_repeat2.setTextColor(Color.LTGRAY);
                    btn_repeat2.setTextColor(Color.BLACK);
                    btn_repeat3.setTextColor(Color.LTGRAY);
                    btn_repeat4.setTextColor(Color.LTGRAY);
                    btn_repeat5.setTextColor(Color.LTGRAY);
                    break;
                }
            case R.id.btn_repeat3:
                if (isSelected) {
                    btn_repeat1.setTextColor(Color.LTGRAY);
                    btn_repeat2.setTextColor(Color.LTGRAY);
                    btn_repeat3.setTextColor(Color.BLACK);
                    btn_repeat4.setTextColor(Color.LTGRAY);
                    btn_repeat5.setTextColor(Color.LTGRAY);
                    break;
                }
            case R.id.btn_repeat4:
                if (isSelected) {
                    btn_repeat1.setTextColor(Color.LTGRAY);
                    btn_repeat2.setTextColor(Color.LTGRAY);
                    btn_repeat3.setTextColor(Color.LTGRAY);
                    btn_repeat4.setTextColor(Color.BLACK);
                    btn_repeat5.setTextColor(Color.LTGRAY);
                    break;
                }
            case R.id.btn_repeat5:
                if (isSelected) {
                    btn_repeat1.setTextColor(Color.LTGRAY);
                    btn_repeat2.setTextColor(Color.LTGRAY);
                    btn_repeat3.setTextColor(Color.LTGRAY);
                    btn_repeat4.setTextColor(Color.LTGRAY);
                    btn_repeat5.setTextColor(Color.BLACK);
                    break;
                }
        }
    }

    public void onClick(View view) {
        if (view == colorselector) { //view가 alert 이면 팝업실행 즉 버튼을 누르면 팝업창이 뜨는 조건
            Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

            //R.layout.dialog는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
            View layout = inflater.inflate(R.layout.color_selector, (ViewGroup) findViewById(R.id.colorpopup));
            AlertDialog.Builder aDialog = new AlertDialog.Builder(AddScheduleActivity.this);

            aDialog.setView(layout);

            //팝업창 생성
            AlertDialog ad = aDialog.create();
            ad.show();//보여줌!

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            Window window = ad.getWindow();
            int x = (int) (size.x * 0.8);
            int y = (int) (size.y * 0.8);
            window.setLayout(x, y);
        }

        if (view == repeatselector) { //view가 alert 이면 팝업실행 즉 버튼을 누르면 팝업창이 뜨는 조건
            Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

            //R.layout.dialog는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
            View layout = inflater.inflate(R.layout.repeat_selector, (ViewGroup) findViewById(R.id.repeatpopup));
            AlertDialog.Builder aDialog = new AlertDialog.Builder(AddScheduleActivity.this);

            aDialog.setView(layout);

            //팝업창 생성
            AlertDialog ad = aDialog.create();
            ad.show();//보여줌!

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            Window window = ad.getWindow();
            int x = (int) (size.x * 0.8);
            int y = (int) (size.y * 0.8);
            window.setLayout(x, y);
        }
    }

}
