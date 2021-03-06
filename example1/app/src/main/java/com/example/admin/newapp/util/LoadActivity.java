package com.example.admin.newapp.Util;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.admin.newapp.BaseClass.MyAppCompatActivity;
import com.example.admin.newapp.MainMenuActivity;
import com.example.admin.newapp.R;

public class LoadActivity extends MyAppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(LoadActivity.this, MainMenuActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
