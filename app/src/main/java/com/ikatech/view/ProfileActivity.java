package com.ikatech.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ikatech.MapsActivity;
import com.ikatech.R;
import com.ikatech.businessObject.DataBase;
import com.ikatech.dataObject.Location;
import com.ikatech.dataObject.User;

public class ProfileActivity extends Fragment {

    private static final String TEXT = "user";
    private  User user;
    DataBase db;
    TextView name, cc, addres;
    Button btnGuardar, btnCapGps;
    public String direc = "";
    Location location;

    public static ProfileActivity newInstance(User user) {
        ProfileActivity frag = new ProfileActivity();

        Bundle args = new Bundle();
        args.putSerializable(TEXT, user);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.profile_activity, container, false);

        db = new DataBase(getActivity());

        if (getArguments() != null) {
            user = new User();
            user = (User) getArguments().getSerializable(TEXT);
        }

        loadview(layout);


        return layout;
    }

    private void loadview(View layout) {
        name =((TextView) layout.findViewById(R.id.edName));
        cc =((TextView) layout.findViewById(R.id.edCc));
        addres = ((TextView) layout.findViewById(R.id.edDireccion));
        btnGuardar = ((Button) layout.findViewById(R.id.btnGuardar));
        btnCapGps = ((Button) layout.findViewById(R.id.btnCapGPS));

        if(db.existentUser(user)){
            name.setEnabled(false);
            addres.setEnabled(false);
            btnGuardar.setVisibility(View.GONE);
            user = db.loadUser(user.getUsername());
            name.setText(user.getName());
            cc.setText(String.valueOf(user.getUsername()));
            addres.setText(user.getAddress());
        }else{
            cc.setText(user.getUsername()+"");
            btnCapGps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    startActivityForResult(intent, 1);
                }
            });
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(String.valueOf(name.getText()).equals("") || String.valueOf(addres.getText()).equals("")){
                        Toast.makeText(getActivity(), "Debe diligenciar el formulario correctamente", Toast.LENGTH_SHORT).show();
                    }else {
                        user.setName(String.valueOf(name.getText()));
                        user.setAddress(String.valueOf(addres.getText()));
                        db.insertUser(user);
                        name.setEnabled(false);
                        addres.setEnabled(false);
                        btnGuardar.setVisibility(View.GONE);
                        btnCapGps.setVisibility(View.GONE);
                    }
                }
            });

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == 1){
                location = (Location) data.getSerializableExtra("lat");
                addres.setText(location.getLat() +" , "+ location.getLon());
            }
            if (resultCode == 2) {
                //Write your code if there's no result
            }
        }
    }
}