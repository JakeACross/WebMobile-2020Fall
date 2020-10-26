package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logout(View view){
        Toast.makeText(Home.this, "Log out", Toast.LENGTH_SHORT).show();
        reDirectToMainPage(view);
    }

    public void reDirectToMainPage(View view){
        Intent redirect = new Intent(Home.this, MainActivity.class);
        startActivity(redirect);
    }

}