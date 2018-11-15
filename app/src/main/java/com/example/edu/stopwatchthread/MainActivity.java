package com.example.edu.stopwatchthread;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonPause, buttonReset;
    TextView textResult;
    long startTime = 0, timeBuff = 0;
    Handler  handler;
    long updateTime=0,millisecondTime=0;
    int minutes, seconds,milliSeconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonPause = (Button)findViewById(R.id.buttonPause);
        buttonReset = (Button)findViewById(R.id.buttonReset);
        textResult = (TextView)findViewById(R.id.textViewResult);

        buttonStart.setOnClickListener(this);
        buttonPause.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        handler = new Handler();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonStart:
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                break;
            case R.id.buttonPause:
                timeBuff += millisecondTime;
                handler.removeCallbacks(runnable);
                break;
            case R.id.buttonReset:
                millisecondTime = 0;
                timeBuff = 0;
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                break;
        }
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int)(updateTime/1000);
            minutes = seconds/60;
            seconds = seconds%60;
            milliSeconds = (int)(updateTime % 1000);
            textResult.setText(minutes + " : " + String.format("%02d",seconds) +
                    " : " + String.format("%03d",milliSeconds));

            handler.postDelayed(this,0);
        }
    };

}
