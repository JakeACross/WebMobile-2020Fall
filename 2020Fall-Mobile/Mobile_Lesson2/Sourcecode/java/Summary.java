package com.example.mobile_icp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Summary extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // update the text to summary text
        TextView textView = (TextView) findViewById(R.id.text);
        String summary = getIntent().getStringExtra("Summary");
        textView.setText(summary);
    }

    public void confirmOrder(View v){
        String summary = getIntent().getStringExtra("Summary");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pizzaorder@gmail.com"});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        sendIntent.putExtra(Intent.EXTRA_TEXT, summary);
        sendIntent.setType("text/plain");

        // execute if email app exists
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }

    }


}