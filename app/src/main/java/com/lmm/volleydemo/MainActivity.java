package com.lmm.volleydemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lmm.volleydemo.model.WeatherDetailModel;

public class MainActivity extends AppCompatActivity {

    WeatherDC<WeatherDetailModel> mDC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDC = new WeatherDC();
        mDC.mContext = this;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestWeather();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    1000);
        }
        setContentView(R.layout.activity_main);


    }

    public void requestWeather() {
        mDC.requestWeather(() -> {
            Log.e("MainActivity",mDC.mData.city);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestWeather();
            }
        }
    }
}
