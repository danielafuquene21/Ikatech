package com.ikatech.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ikatech.R;
import com.ikatech.businessObject.DataBase;
import com.ikatech.dataObject.User;

public class LoginActivity extends AppCompatActivity {

    private EditText edUser, edPass;
    private Button btnLogin;
    DataBase db;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DataBase(LoginActivity.this);
        user = new User();
        db.deleteCurrentUser();
        loadView();
    }

    private void loadView() {

        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);

        btnLogin = (Button) findViewById(R.id.btnLogin);


    }

     public void onClicklogin (View view){

        String userName= String.valueOf(edUser.getText());
        String pass= String.valueOf(edPass.getText());
        if(userName.equals("") || userName.equals(null) || pass.equals("") || pass.equals(null)){
            Toast.makeText(this, "Debe diligenciar correctamente el formulario", Toast.LENGTH_SHORT).show();
            return;
        }else{
           user.setUsername((Integer.parseInt(userName)));
           user.setPassword(pass);
           //if((db.existentUserLogin(user)) == false)
            db.login(user);
           Intent intent = new Intent(LoginActivity.this, AvailableVehiclesActivity.class);
           intent.putExtra("userLogin", user);
           startActivity(intent);
           finish();
        }

    }
}