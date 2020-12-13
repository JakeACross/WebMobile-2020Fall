package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    int yyyy;
    int mm;
    int dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendar = findViewById(R.id.calendar);
        TextView textview = findViewById(R.id.textView);

        // Update date depending on users' click
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                yyyy = year;
                mm = month + 1;
                dd = day;
                // Create strings for textview
                String selected= "Selected Date" + "\n" + yyyy + "-" + mm + "-" + dd;
                textview.setText(selected);

            }
        });



    }


    public void insert(View view) {

        Intent intent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        // Add the calendar event details
        intent.putExtra(CalendarContract.Events.TITLE, "The event is from 'Notification' App");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Learn Java Android Coding");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "UMKC.com");
        Calendar startTime = Calendar.getInstance();

        // Subtract month for bugs
        mm--;
        startTime.set(yyyy, mm, dd);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // Reset month
        mm++;
        // Use the Calendar app to add the new event.
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
