package com.androidteamproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.androidteamproject.decorator.MySelectorDecorator;
import com.androidteamproject.decorator.OneDayDecorator;
import com.androidteamproject.decorator.SaturdayDecorator;
import com.androidteamproject.decorator.SundayDecorator;
import com.androidteamproject.decorator.WeekendDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity
    implements OnDateSelectedListener, OnMonthChangedListener {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, MM dd yyyy");
  private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 M월");

  private final OneDayDecorator oneDayDecorator = new OneDayDecorator();

  @BindView(R.id.calendarView)
  MaterialCalendarView widget;

  @BindView(R.id.textView)
  TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calendar);
    ButterKnife.bind(this);

    widget.setOnDateChangedListener(this);
    widget.setOnMonthChangedListener(this);

    widget.addDecorators(
            new MySelectorDecorator(this),
            new WeekendDecorator(),
            new SundayDecorator(),
            new SaturdayDecorator(),
            oneDayDecorator
    );

    //초기 문자열
    textView.setText("선택없음");
  }

  @Override
  public void onDateSelected(
      @NonNull MaterialCalendarView widget,
      @NonNull CalendarDay date,
      boolean selected) {

    oneDayDecorator.setDate(date);
    widget.invalidateDecorators();

    LocalDate localdate = LocalDate.of(date.getYear(), date.getMonth() + 1, date.getDay());
    textView.setText(selected ? FORMATTER.format(localdate): "선택없음");

  }

  @Override
  public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
    LocalDate localdate = LocalDate.of(date.getYear(), date.getMonth() + 1, date.getDay());
    //getSupportActionBar().setTitle(MONTH_FORMATTER.format(localdate));
  }


  @OnClick(R.id.floating_action_button)
  void addScheduleActivity() {
    Intent intent = new Intent(CalendarActivity.this, AddScheduleActivity.class);
    startActivity(intent);
  }
}
