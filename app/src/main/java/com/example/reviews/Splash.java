package com.example.reviews;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();//for hiding action bar
        CountDownTimer countDownTimer=new CountDownTimer(3000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

        };countDownTimer.start();
    }
}
