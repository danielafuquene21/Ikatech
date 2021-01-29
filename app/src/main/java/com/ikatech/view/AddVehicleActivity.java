package com.ikatech.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.ikatech.MapsActivity;
import com.ikatech.R;
import com.ikatech.businessObject.Const;
import com.ikatech.businessObject.DataBase;
import com.ikatech.dataObject.User;
import com.ikatech.dataObject.Vehicle;

public class AddVehicleActivity extends AppCompatActivity {

    private TextView marca,modelo,eliminacion,estado ,ubicacion, coleccion, combustion;
    private Button btnComprar, btnCancelar, mapV;

    private LinearLayout lyFavorite;
    private ImageView imageV;
    DataBase db;
    public Vehicle vehicle;
    private User user;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_new_vehicle);
        db = new DataBase(AddVehicleActivity.this);
        vehicle = new Vehicle();
        user = db.getCurrentUser();
        getExtras();
        loadView();

    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            vehicle = new Vehicle();
            vehicle = (Vehicle) bundle.getSerializable(Const.VEHI);
            flag = bundle.getBoolean(Const.BOUGHT_VEHI);
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
        imageV = (ImageView) findViewById(R.id.imageV);
        lyFavorite = (LinearLayout) findViewById(R.id.lyFavorite);


        marca.setText(String.valueOf(vehicle.getMarca())+"");
        modelo.setText(String.valueOf(vehicle.getModelo())+"");
        eliminacion.setText("");
        estado.setText(String.valueOf(vehicle.getEstado()).toLowerCase()+"");
        ubicacion.setText(vehicle.getUbicacion().getAddres());
        coleccion.setText(String.valueOf(vehicle.getNombreColeccion().toLowerCase())+"");
        combustion.setText(String.valueOf(vehicle.getTipoCombustion().toLowerCase())+"");
        Glide.with(this)
                .load(vehicle.getImagen().toLowerCase())
                .into(imageV);
        if(vehicle.getFavorito() ==true){
            lyFavorite.setVisibility(View.VISIBLE);
        }

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnComprar = (Button) findViewById(R.id.btnAgregar);
        mapV = (Button) findViewById(R.id.mapV);
        if(flag==false) {
            if ((String.valueOf(vehicle.getEstado()).toLowerCase().equals("disponible"))) {
                btnComprar.setVisibility(View.VISIBLE);
            }
        }
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.buyVehicle(vehicle, user);
                finish();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mapV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddVehicleActivity.this, MapsActivity.class);
                intent.putExtra("coor", vehicle);
                startActivity(intent);
            }
        });

    }

}