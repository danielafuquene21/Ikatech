package com.ikatech.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.ikatech.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler  = new Handler();
        handler.postDelayed(getRunnableStartApp(), 2000);
    }

    //Valida si ya tiene sesion iniciada
    private Runnable getRunnableStartApp() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        return  runnable;
    }
}