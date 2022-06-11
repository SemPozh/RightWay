package com.example.rightway.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.rightway.CalendarHolder;
import com.example.rightway.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    LinearLayout menuLayout;
    View popupView;
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
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setColorFilter(Color.argb(255, 255, 255, 255));
        floatingActionButton.setScaleType(ImageView.ScaleType.CENTER);

        menuLayout = findViewById(R.id.menuLayout);
        popupView = findViewById(R.id.popupView);

        mondayDate = findViewById(R.id.mondayDate);
        tuesdayDate = findViewById(R.id.tuesdayDate);
        wednesdayDate = findViewById(R.id.wednesdayDate);
        thursdayDate = findViewById(R.id.thursdayDate);
        fridayDate = findViewById(R.id.fridayDate);
        saturdayDate = findViewById(R.id.saturdayDate);
        sundayDate = findViewById(R.id.sundayDate);


        CalendarHolder calendarHolder = new CalendarHolder(mondayDate, tuesdayDate, wednesdayDate, thursdayDate, fridayDate, saturdayDate, sundayDate);
        calendarHolder.setCalendar();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(popupView);
            }
        });

    }


    private void showPopupMenu(View v){
        Context wrapper = new ContextThemeWrapper(getApplicationContext(), R.style.popupMenuStyle);
        PopupMenu popupMenu = new PopupMenu(wrapper, v);
        popupMenu.inflate(R.menu.add_haibt_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.newGoalItem:
                        Log.d("item", "1");
                        return true;
                    case R.id.newTemplateItem:
                        Log.d("item", "2");
                        return true;
                    case R.id.addTemplateItem:
                        Log.d("item", "3");
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }
}