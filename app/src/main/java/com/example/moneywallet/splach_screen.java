package com.example.moneywallet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class splach_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        new Handler().postDelayed(() -> {

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();

        }, 3000);

//        public class splach_screen extends AppCompatActivity {
//
//            @Override
//            protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                setContentView(R.layout.activity_splach_screen);
//
//                new Handler().postDelayed(() -> {
//
//                    SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//
//                    boolean isFingerPrint = preferences.getBoolean("isFingerPrint", false);
//
//                    Constants.isFingerPrint = isFingerPrint;
//
//                    if (preferences.getString("firstTime", "").equals("no")) {
//
//                        if (Constants.isFingerPrint) {
//
//                            Intent i = new Intent(splach_screen.this, FingerprintActivity.class);
//                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(i);
//
//                        } else {
//
//                            Intent i = new Intent(splach_screen.this, UserPinActivity.class);
//                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(i);
//
//                        }
//                        finish();
//
//                    } else {
//
//                        Intent i = new Intent(splach_screen.this, PinActivity.class);
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(i);
//
//                        editor = preferences.edit();
//                        editor.putString("firstTime", "no");
//                        editor.apply();
//                        Log.e("", "firstrun");
//                    }
//
//                }, 3000);
//            }
//        }
    }
}