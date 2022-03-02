package com.androidteamproject.scheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidteamproject.R;

public class RepaetSelectorActivity extends Activity {
    RadioButton btn_repeat1, btn_repeat2, btn_repeat3, btn_repeat4, btn_repeat5;
    TextView testView;
    RadioGroup btngroup_repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeat_selector);

        btngroup_repeat = findViewById(R.id.btngroup_repeat);
        testView = findViewById(R.id.test_view);
        btngroup_repeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.btn_repeat1:
                        testView.setText("버튼 1 선택됨");
                        Toast.makeText(getApplicationContext(), "버튼1 눌림", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.btn_repeat2:
                        testView.setText("버튼 2 선택됨");
                        Toast.makeText(getApplicationContext(), "버튼2 눌림", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.btn_repeat3:
                        testView.setText("버튼 3 선택됨");
                        Toast.makeText(getApplicationContext(), "버튼3 눌림", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.btn_repeat4:
                        testView.setText("버튼 4 선택됨");
                        Toast.makeText(getApplicationContext(), "버튼4 눌림", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.btn_repeat5:
                        testView.setText("버튼 5 선택됨");
                        Toast.makeText(getApplicationContext(), "버튼5 눌림", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

    }

}