package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class Main7Activity extends AppCompatActivity {
ImageView image;
TextView desc,lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        //byte[] decodedString = Base64.decode(Shared.obj.encode, Base64.URL_SAFE);
        //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        // image.setImageBitmap(decodedByte);
        byte[] decodeByte = Base64.decode(Shared.obj.encode,0);
        Bitmap b= BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.length);
        image=(ImageView)findViewById(R.id.img);
        image.setImageBitmap(b);
        if(decodeByte != null) {
        desc = findViewById(R.id.desc);
        desc.setText(Shared.obj.desc); }

        lat = findViewById(R.id.latitude);
        lat.setText("Latitude  : "+ Shared.obj.latitude + "\nLongitude : " + Shared.obj.longitude);
    }
}
