package com.androidteamproject;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddScheduleActivity extends AppCompatActivity {
    LinearLayout colorselector, repeatselector;
    Calendar myCalendar = Calendar.getInstance();
    private String getString;
    ImageView maincircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addschedule);

        colorselector = (LinearLayout) findViewById(R.id.colorselector);
        repeatselector = (LinearLayout) findViewById(R.id.repeatselector);
        Intent intent = getIntent();
        getString = intent.getStringExtra("rgb");
        maincircle = (ImageView) findViewById(R.id.maincircle);


        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        MapView mapView = new MapView(this);
        mapViewContainer.addView(mapView);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("rgbEvent"));

        colorselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddScheduleActivity.this, color_selectorActivity.class));
            }
        });

        repeatselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddScheduleActivity.this, repaet_selectorActivity.class));
            }
        });

// 시작 날짜 설정
        EditText startDate = (EditText) findViewById(R.id.startDate);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddScheduleActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        try {

                            Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                        updateStartLabel();
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
            }
        });
//시작 날짜 설정 종료

//        종료 날짜 설정
        EditText endDate = (EditText) findViewById(R.id.endDate);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddScheduleActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        try {

                            Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                        updateEndLabel();
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
            }
        });
//        종료 날짜 설정 종료

//        시작 시간 설정
        final EditText startTime = (EditText) findViewById(R.id.startTime);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                        if (view.isShown()) {
                            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            myCalendar.set(Calendar.MINUTE, minuteOfDay);

                        }
                        String state = "오전 ";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            state = "오후 ";
                        }
                        // EditText에 출력할 형식 지정
                        startTime.setText(state + " " + hourOfDay + "시 " + minuteOfDay + "분");
                    }
                };
                mTimePicker = new TimePickerDialog(AddScheduleActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, listener, hour, minute, false);
                mTimePicker.setTitle("시작 시간 선택");
                mTimePicker.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                mTimePicker.show();
            }
        });
//        시작 시간 설정 종료

//        종료 시간 설정
        final EditText endTime = (EditText) findViewById(R.id.endTime);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                        if (view.isShown()) {
                            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            myCalendar.set(Calendar.MINUTE, minuteOfDay);

                        }
                        String state = "오전 ";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            state = "오후 ";
                        }
                        // EditText에 출력할 형식 지정
                        endTime.setText(state + " " + hourOfDay + "시 " + minuteOfDay + "분");
                    }
                };
                mTimePicker = new TimePickerDialog(AddScheduleActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, listener, hour, minute, false);
                mTimePicker.setTitle("종료 시간 선택");
                mTimePicker.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                mTimePicker.show();
            }
        });
//        종료 시간 설정 종료


    }

    private void updateStartLabel() {
        String myFormat = "yyyy년 MM월 dd일";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText startDate = (EditText) findViewById(R.id.startDate);
        startDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateEndLabel() {
        String myFormat = "yyyy년 MM월 dd일";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText endDate = (EditText) findViewById(R.id.endDate);
        endDate.setText(sdf.format(myCalendar.getTime()));
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String rgb = intent.getStringExtra("rgb");
            maincircle.setColorFilter(Color.parseColor(rgb));
        }
    };

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}