package com.ikatech.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ikatech.MapsActivity;
import com.ikatech.R;
import com.ikatech.businessObject.Const;
import com.ikatech.businessObject.DataBase;
import com.ikatech.dataObject.Location;
import com.ikatech.dataObject.User;
import com.ikatech.dataObject.Vehicle;

public class FormBuyVehicleActivity extends AppCompatActivity {

    EditText marca,modelo,eliminacion , coleccion, imagenUrl;
    TextView ubicacion, estado;
    Spinner combustion;
    ImageView imageV;
    Switch fav;
    private Button btnComprar, btnCancelar, btnCapGps;
    DataBase db;
    public User user;
    public Vehicle vehicle;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_buy_manual_vehicle);
        db = new DataBase(FormBuyVehicleActivity.this);
        user = new User();
        vehicle = new Vehicle();
        getExtras();
        loadView();

    }
    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user = new User();
            user = (User) bundle.getSerializable(Const.US);
        }
    }

    private void loadView() {

        marca = (EditText) findViewById(R.id.marca);
        modelo = (EditText) findViewById(R.id.modelo);
        eliminacion = (EditText) findViewById(R.id.eliminacion);
        estado = (TextView) findViewById(R.id.estado);
        ubicacion = (TextView) findViewById(R.id.ubicacion);
        coleccion = (EditText) findViewById(R.id.coleccion);
        combustion = (Spinner) findViewById(R.id.combustion);
        imageV = (ImageView) findViewById(R.id.imageV);
        imagenUrl = (EditText) findViewById(R.id.imagenUrl);
        fav = (Switch) findViewById(R.id.fav);

        @SuppressLint("ResourceType") ArrayAdapter adapter = new ArrayAdapter<String>(FormBuyVehicleActivity.this,
                android.R.layout.simple_spinner_dropdown_item, R.array.tipos_combustion );

        btnCapGps = (Button) findViewById(R.id.btnGps);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnComprar = (Button) findViewById(R.id.btnAgregar);

        btnCapGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(FormBuyVehicleActivity.this, MapsActivity.class);
                        startActivityForResult(intent, 1);
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == 1){
                location = (Location) data.getSerializableExtra("lat");
                ubicacion.setText(location.getLat() +" , "+ location.getLon());
            }
            if (resultCode == 2) {
                //Write your code if there's no result
            }
        }
    }

    public void onClickCancelar (View view){
        finish();
    }
    public void onClickBuy (View view){
        if((String.valueOf(marca.getText()).equals("")) || (String.valueOf(modelo.getText()).equals("")) ||
                (String.valueOf(eliminacion.getText()).equals("")) || (String.valueOf(estado.getText()).equals("")) ||
                (String.valueOf(ubicacion.getText()).equals("")) || (String.valueOf(coleccion.getText()).equals("")) ||
                 (String.valueOf(imagenUrl.getText()).equals(""))){
            Toast.makeText(this, "Debe diligenciar correctamente el formulario", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if(db.sizeManualVehicle(user) >= 3){
                Toast.makeText(this, "Supero el maximo de vehiculos agregados manualmente permitidos", Toast.LENGTH_SHORT).show();
                return;
            }else {
                vehicle.setMarca(String.valueOf(marca.getText()));
                vehicle.setModelo(String.valueOf(modelo.getText()));
                vehicle.setEstado(String.valueOf(estado.getText()));
                //vehicle.setUbicacion(String.valueOf(ubicacion.getText()));
                vehicle.setNombreColeccion(String.valueOf(coleccion.getText()));
                vehicle.setTipoCombustion(String.valueOf(combustion.getSelectedItem()));

                location.setAddres("");
                vehicle.setUbicacion(location);
                vehicle.setTipo("manual");
                vehicle.setFavorito(fav.isChecked());
                //if((db.existentUserLogin(user)) == false)
                db.buyVehicle(vehicle, user);
                finish();
            }
        }
    }
}