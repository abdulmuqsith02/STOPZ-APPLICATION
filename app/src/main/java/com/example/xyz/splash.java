package com.example.xyz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class splash extends AppCompatActivity {
    // Initializing my textView
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView = findViewById(R.id.textView);
        // Time is in millisecond so 50sec = 50000 I have used
        // countdown Interveal is 1sec = 1000 I have used
        new CountDownTimer(1800000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                textView.setText("00:00:00");
            }
        }.start();
    }
}