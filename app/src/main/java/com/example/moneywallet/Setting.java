package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    TextView user_profile_setting,user_profile_setting_1,categories_setting,categories_setting_1;
    Switch isFingerPrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        user_profile_setting=findViewById(R.id.user_profile_setting);
        user_profile_setting_1=findViewById(R.id.user_profile_setting_1);
        categories_setting=findViewById(R.id.categories_setting);
        categories_setting_1=findViewById(R.id.categories_setting_1);
        isFingerPrint = findViewById(R.id.fingerPrint);

        isFingerPrint.setChecked(Constants.isFingerPrint);
        SharedPreferences preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        user_profile_setting.setOnClickListener(v -> {
            Intent intent=new Intent(Setting.this,user_profile.class);
            startActivity(intent);
        });
        user_profile_setting_1.setOnClickListener(v -> {
            Intent intent=new Intent(Setting.this,user_profile.class);
            startActivity(intent);
        });

        categories_setting.setOnClickListener(v -> {
            Intent intent=new Intent(Setting.this,catogries.class);
            startActivity(intent);
        });
        categories_setting_1.setOnClickListener(v -> {
            Intent intent=new Intent(Setting.this,catogries.class);
        });

        isFingerPrint.setOnCheckedChangeListener((buttonView, isChecked) -> {


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
                KeyguardManager keyguardManager =(KeyguardManager) getSystemService(KEYGUARD_SERVICE);

                if(!fingerprintManager.isHardwareDetected()){

                    Constants.isFingerPrint = false;
                    isFingerPrint.setChecked(false);

                    Toast.makeText(getApplicationContext(),
                            "Fingerprint Scanner not detected in Device", Toast.LENGTH_SHORT).show();

                } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){

                    Constants.isFingerPrint = false;
                    isFingerPrint.setChecked(false);

                    Toast.makeText(getApplicationContext(),
                            "Permission not granted to use Fingerprint Scanner", Toast.LENGTH_SHORT).show();

                } else if (!keyguardManager.isKeyguardSecure()){

                    Constants.isFingerPrint = false;
                    isFingerPrint.setChecked(false);

                    Toast.makeText(getApplicationContext(),
                            "Add Lock to your Phone in Settings", Toast.LENGTH_SHORT).show();

                } else if (!fingerprintManager.hasEnrolledFingerprints()){

                    Constants.isFingerPrint = false;
                    isFingerPrint.setChecked(false);

                    Toast.makeText(getApplicationContext(),
                            "You should add at least 1 Fingerprint to use this Feature", Toast.LENGTH_SHORT).show();

                } else {

                    Constants.isFingerPrint = isChecked;

                    isFingerPrint.setChecked(Constants.isFingerPrint);

                    if(Constants.isFingerPrint){

                        Toast.makeText(getApplicationContext(),
                                "Fingerprint activated ", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getApplicationContext(),
                                "Fingerprint deactivated ", Toast.LENGTH_SHORT).show();
                    }
                    editor.putBoolean("isFingerPrint", Constants.isFingerPrint);
                    editor.apply();
                }
            }


        });

    }
}