package com.ikatech.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ikatech.R;
import com.ikatech.businessObject.Const;
import com.ikatech.businessObject.DataBase;
import com.ikatech.dataObject.User;
import com.ikatech.dataObject.Vehicle;

public class AddVehicleActivity extends AppCompatActivity {

    private TextView marca,modelo,eliminacion,estado ,ubicacion, coleccion, combustion;
    private Button btnComprar, btnCancelar;
    DataBase db;
    public Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_new_vehicle);
        db = new DataBase(AddVehicleActivity.this);
        vehicle = new Vehicle();
        getExtras();
        loadView();

    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            vehicle = new Vehicle();
            vehicle = (Vehicle) bundle.getSerializable(Const.VEHI);
        }
    }

    private void loadView() {

        marca = (TextView) findViewById(R.id.marca);
        modelo = (TextView) findViewById(R.id.modelo);
        eliminacion = (TextView) findViewById(R.id.eliminacion);
        estado = (TextView) findViewById(R.id.estado);
        ubicacion = (TextView) findViewById(R.id.ubicacion);
        coleccion = (TextView) findViewById(R.id.coleccion);
        combustion = (TextView) findViewById(R.id.combustion);

        marca.setText(String.valueOf(vehicle.getMarca())+"");
        modelo.setText(String.valueOf(vehicle.getModelo())+"");
        eliminacion.setText("");
        estado.setText(String.valueOf(vehicle.getEstado()).toLowerCase()+"");
        ubicacion.setText("");
        coleccion.setText(String.valueOf(vehicle.getNombreColeccion())+"");
        combustion.setText(String.valueOf(vehicle.getTipoCombustion())+"");

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnComprar = (Button) findViewById(R.id.btnAgregar);
        if(String.valueOf(vehicle.getEstado()).toLowerCase().equals("disponible")){
            btnComprar.setVisibility(View.VISIBLE);
        }

    }

    public void onClicklogin (View view){

        /*String userName= String.valueOf(edUser.getText());
        String pass= String.valueOf(edPass.getText());
        if(userName.equals("") || userName.equals(null) || pass.equals("") || pass.equals(null)){
            Toast.makeText(this, "Debe diligenciar correctamente el formulario", Toast.LENGTH_SHORT).show();
            return;
        }else{
            user.setUsername((Integer.parseInt(userName)));
            user.setPassword(pass);
            if((db.existentUserLogin(user)) == false) db.login(user);
            Intent intent = new Intent(AddVehicleActivity.this, AvailableVehiclesActivity.class);
            intent.putExtra("userLogin", user);
            startActivity(intent);
            finish();
        }*/

    }
}