package com.example.edu.stopwatchthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonStop;
    long endTime = 0;
    final String TAG = "ErrorThreadActivity Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStop = (Button)findViewById(R.id.buttonStop);
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView textResult = (TextView)findViewById(R.id.textViewResult);
        switch (v.getId()){
            case R.id.buttonStart:
                endTime = System.currentTimeMillis() + (2*5000);
                Log.i(TAG, "Thread running!");
                while (System.currentTimeMillis() < endTime){
                    synchronized (this){
                        long currentTime = System.currentTimeMillis();
                        String strTime = String.valueOf(endTime-currentTime);
//                        Log.i(TAG,"Thread running!");
                        Log.i(TAG,strTime);
//                        textResult.setText(String.valueOf(System.currentTimeMillis()));
                        textResult.setText(strTime);
                    }
                }
                break;
            case R.id.buttonStop:
                endTime = System.currentTimeMillis();
                break;
        }

    }
}
