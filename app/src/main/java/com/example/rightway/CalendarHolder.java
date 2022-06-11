package com.example.rightway;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarHolder {
    TextView mondayDate;
    TextView tuesdayDate;
    TextView wednesdayDate;
    TextView thursdayDate;
    TextView fridayDate;
    TextView saturdayDate;
    TextView sundayDate;

    public CalendarHolder(TextView mondayDate, TextView tuesdayDate, TextView wednesdayDate, TextView thursdayDate, TextView fridayDate, TextView saturdayDate, TextView sundayDate) {
        this.mondayDate = mondayDate;
        this.tuesdayDate = tuesdayDate;
        this.wednesdayDate = wednesdayDate;
        this.thursdayDate = thursdayDate;
        this.fridayDate = fridayDate;
        this.saturdayDate = saturdayDate;
        this.sundayDate = sundayDate;
    }

    public void setCalendar(){
        //      Setting dates to calendar
        Calendar now = Calendar.getInstance();
        int weekday = now.get(java.util.Calendar.DAY_OF_WEEK);
        if (weekday == 1){
            weekday = 8;
        }
        int monday = 2;
        now.add(Calendar.DAY_OF_YEAR, -(weekday-monday));

        TextView[] calendarDays = {this.mondayDate, this.tuesdayDate, this.wednesdayDate, this.thursdayDate, this.fridayDate, this.saturdayDate, this.sundayDate};
        DateFormat dateFormat = new SimpleDateFormat("d", Locale.getDefault());
        for (int i=0; i<7; i++){
            Date date = now.getTime();
            String dateText = dateFormat.format(date);
            calendarDays[i].setText(dateText);
            if (weekday-2 == i){
                calendarDays[i].setTextColor(Color.parseColor("#FFFFFF"));
                calendarDays[i].setBackgroundResource(R.drawable.circle);

            }

            now.add(java.util.Calendar.DAY_OF_YEAR, 1);
        }
    }
}
