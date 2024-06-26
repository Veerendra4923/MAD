package com.example.finalalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.SystemClock;

import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    public Chronometer chronometer;
    public long pauseOffset;
    public boolean running;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        chronometer = findViewById(R.id.chronometer);
        //chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity3.this, "Bing!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void startChronometer(View v) {

        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
