package com.androidteamproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

/**
 * Shows off the most basic usage
 */
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

    LocalDate localdate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    textView.setText(selected ? FORMATTER.format(localdate): "선택없음");

  }

  @Override
  public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
    LocalDate localdate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    getSupportActionBar().setTitle(MONTH_FORMATTER.format(localdate));
  }
}
