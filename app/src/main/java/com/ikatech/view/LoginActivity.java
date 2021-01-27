package com.ikatech.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ikatech.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edUser, edPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadView();
    }

    private void loadView() {

        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);

        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

     public void onClicklogin (View view){
         Intent intent = new Intent(LoginActivity.this, AvailableVehiclesActivity.class);
         startActivity(intent);
    }
}