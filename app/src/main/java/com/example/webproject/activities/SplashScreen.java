package com.example.webproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.webproject.R;

public class SplashScreen extends AppCompatActivity {

    private TextView  txt;
    private ProgressBar  progressBar;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initializeViews();

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent it=new Intent(SplashScreen.this, HomeScreen.class);
                    startActivity(it);
                    finish();

                }
            },6000);
    }


    private void initializeViews(){
        txt=findViewById(R.id.loadads);
        progressBar=findViewById(R.id.prograssbar);
    }


}
