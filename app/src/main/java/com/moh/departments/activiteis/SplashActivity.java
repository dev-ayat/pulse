package com.moh.departments.activiteis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.moh.departments.R;
import com.moh.departments.constants.Controller;

public class SplashActivity extends AppCompatActivity {
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.e("splash", Controller.pref.getString("LOGIN_MODE", "0"));
        Log.e("splash2", Controller.pref.getString("USER_NAME", "0"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Controller.pref.getString("LOGIN_MODE", "0").equals("1")) {
                    i = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
