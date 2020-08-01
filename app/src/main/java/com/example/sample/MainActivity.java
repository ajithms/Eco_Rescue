package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {
    EditText num;
    int PERMISSION_ID = 45;
    Button snd;
    boolean isotpsent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Shared.getLogin()){
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }

        num = (EditText) findViewById(R.id.phone);
        snd = (Button) findViewById(R.id.check);
        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isotpsent == false) {
                    if(checkPermissions()) {
                        Shared.setOtp(String.valueOf(generateRandomNumber()));
                        SmsManager.getDefault().sendTextMessage(num.getText().toString(), null, " Wellcome: One Time Password for Login : " + Shared.getOtp(), null, null);
                        isotpsent = true;
                        //.setText("Enter OTP");
                        snd.setText("Verify");
                        num.setHint("Enter OTP");
                        num.setText("");
                    }
                    else requestPermissions();
                } else {
                    if (num.getText().toString().equals(Shared.getOtp())) {
                        Shared.setLogin();
                        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                        //finish();
                    } else
                        Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED ) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.SEND_SMS},
                PERMISSION_ID
        );
    }


    int range = 9;  // to generate a single number with this range, by default its 0..9
    int length = 4; // by default length is 4

    public int generateRandomNumber () {
        int randomNumber;

        SecureRandom secureRandom = new SecureRandom();
        String s = "";
        for (int i = 0; i < length; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0) { // to prevent the Zero to be the first number as then it will reduce the length of generated pin to three or even more if the second or third number came as zeros
                i = -1;
                continue;
            }
            s = s + number;
        }

        randomNumber = Integer.parseInt(s);

        return randomNumber;

    }
}
