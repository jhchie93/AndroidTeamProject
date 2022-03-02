package com.androidteamproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class color_selectorActivity extends Activity {
    ImageView circle1, circle2, circle3, maincircle;
    LinearLayout colorselect1, colorselect2, colorselect3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_selector);
        colorselect1 = findViewById(R.id.colorselect1);
        colorselect2 = findViewById(R.id.colorselect2);
        colorselect3 = findViewById(R.id.colorselect3);
        maincircle = findViewById(R.id.maincircle);

        circle1 = findViewById(R.id.circle2);
        circle1.setColorFilter(Color.parseColor("#FF0000"));
        circle2 = findViewById(R.id.circle2);
        circle2.setColorFilter(Color.parseColor("#0000FF"));
        circle3 = findViewById(R.id.circle3);
        circle3.setColorFilter(Color.parseColor("#008000"));

        View.OnClickListener color = new View.OnClickListener() {

            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.colorselect1:
                        maincircle.setColorFilter(Color.parseColor("#FF0000"));
                        break;

                    case R.id.colorselect2:
                        maincircle.setColorFilter(Color.parseColor("#0000FF"));
                        break;
                    case R.id.colorselect3:
                        maincircle.setColorFilter(Color.parseColor("#008000"));
                        break;
                };
            }
        };

        colorselect1.setOnClickListener(color);
        colorselect2.setOnClickListener(color);
        colorselect3.setOnClickListener(color);




    }
}
