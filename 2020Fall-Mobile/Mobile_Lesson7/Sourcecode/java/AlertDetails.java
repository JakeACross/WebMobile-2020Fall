// Created by Vijaya Yeruva on 11/20/2020

package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AlertDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);
    }

    public void callCalendar(View view) {
        Intent redirect = new Intent(AlertDetails.this, CalendarActivity.class);
        startActivity(redirect);

    }
}