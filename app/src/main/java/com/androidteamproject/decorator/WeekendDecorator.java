package com.androidteamproject.decorator;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.threeten.bp.DayOfWeek;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

public class WeekendDecorator implements DayViewDecorator {

    private final Drawable highlightDrawable;
    private static final int color = Color.parseColor("#228BC34A");

    Calendar calendar;

    public WeekendDecorator() {
        highlightDrawable = new ColorDrawable(color);
        calendar = Calendar.getInstance();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int weekday = day.getCalendar().get(Calendar.DAY_OF_WEEK);
        return weekday == calendar.SATURDAY || weekday == calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
    }
}
