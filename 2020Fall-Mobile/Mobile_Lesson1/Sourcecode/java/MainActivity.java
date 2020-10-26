package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View view){
        EditText usernameCtrl = (EditText) findViewById(R.id.userNameText);
        EditText passwordCtrl = (EditText) findViewById(R.id.passwordText);
        String username = usernameCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        boolean validationFlag = false;

        if (!username.isEmpty() && !password.isEmpty() ){
            if (username.equals("Jake") && password.equals("Jake")) {
                validationFlag = true;
            }
        }

        if (!validationFlag){
            // show error massage
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

        }

        else{
            Toast.makeText(MainActivity.this, "Log in successfully!", Toast.LENGTH_SHORT).show();
            reDirectToHomePage(view);
        }
    }
    public void reDirectToHomePage(View view){
        Intent redirect = new Intent(MainActivity.this, Home.class);
        startActivity(redirect);
    }
}