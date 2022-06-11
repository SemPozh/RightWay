package com.example.rightway.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rightway.CalendarHolder;
import com.example.rightway.R;


public class NewsActivity extends AppCompatActivity {
    TextView mondayDate;
    TextView tuesdayDate;
    TextView wednesdayDate;
    TextView thursdayDate;
    TextView fridayDate;
    TextView saturdayDate;
    TextView sundayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        CalendarHolder calendarHolder = new CalendarHolder(mondayDate, tuesdayDate, wednesdayDate, thursdayDate, fridayDate, saturdayDate, sundayDate);
        calendarHolder.setCalendar();
    }
}