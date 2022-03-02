package com.androidteamproject.scheduler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidteamproject.R;

public class ColorSelectorActivity extends Activity {
    ImageView circle1, circle2, circle3;
    LinearLayout colorselect1, colorselect2, colorselect3;
    private String rgb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_selector);
        colorselect1 = findViewById(R.id.colorselect1);
        colorselect2 = findViewById(R.id.colorselect2);
        colorselect3 = findViewById(R.id.colorselect3);
        circle1 = findViewById(R.id.circle1);
        circle1.setColorFilter(Color.parseColor("#FF0000"));
        circle2 = findViewById(R.id.circle2);
        circle2.setColorFilter(Color.parseColor("#0000FF"));
        circle3 = findViewById(R.id.circle3);
        circle3.setColorFilter(Color.parseColor("#008000"));
        Intent intent = new Intent("rgbEvent");
        View.OnClickListener color = new View.OnClickListener() {

            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.colorselect1:
                        rgb = "#FF0000";
                        intent.putExtra("rgb",rgb);
                        LocalBroadcastManager.getInstance(ColorSelectorActivity.this).sendBroadcast(intent);
                        finish();
                        break;

                    case R.id.colorselect2:
                        rgb = "#0000FF";
                        intent.putExtra("rgb",rgb);
                        LocalBroadcastManager.getInstance(ColorSelectorActivity.this).sendBroadcast(intent);
                        finish();
                        break;
                    case R.id.colorselect3:
                        rgb = "#008000";
                        intent.putExtra("rgb",rgb);
                        LocalBroadcastManager.getInstance(ColorSelectorActivity.this).sendBroadcast(intent);
                        finish();
                        break;
                }
                ;

            }


        };

        colorselect1.setOnClickListener(color);
        colorselect2.setOnClickListener(color);
        colorselect3.setOnClickListener(color);


    }
}
