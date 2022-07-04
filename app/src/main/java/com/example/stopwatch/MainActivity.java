package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int  seconds;
    private boolean running;
    private boolean Wasrunning;

    ImageView start,stop,restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        restart = findViewById(R.id.restart);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            Wasrunning = savedInstanceState.getBoolean("Wasrunning");
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
                Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_SHORT).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                Toast.makeText(MainActivity.this, "Stopped", Toast.LENGTH_SHORT).show();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                seconds = 0;
                Toast.makeText(MainActivity.this, "Resetted", Toast.LENGTH_SHORT).show();
            }
        });

        StopWatch();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("Wasrunning", Wasrunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Wasrunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Wasrunning){
            running = true;
        }
    }

    public void StopWatch(){
        TextView textView = findViewById(R.id.textView);

        Handler handler = new Handler();


        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int second = seconds%60;

                String time = String.format("%d:%02d:%02d",hours,minutes,second);
                textView.setText(time);

                if(running){
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });
    }

}