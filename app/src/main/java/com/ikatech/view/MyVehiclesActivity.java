package com.ikatech.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ikatech.R;
import com.ikatech.adapter.VehicleAdapter;
import com.ikatech.businessObject.Const;
import com.ikatech.businessObject.DataBase;
import com.ikatech.businessObject.Endpoints;
import com.ikatech.dataObject.User;
import com.ikatech.dataObject.Vehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyVehiclesActivity extends Fragment implements VehicleAdapter.RecyclerViewOnItemClickListener {

    private static final String TEXT = "text";

    public User user;
    private EditText editTextSearch;
    private RecyclerView recyclerViewSearchResults;
    private RelativeLayout addManual;
    private ArrayList<Vehicle> listVehicle = new ArrayList<Vehicle>();
    private GridLayoutManager glm;
    boolean load = false;
    VehicleAdapter adapter;
    DataBase db;
    private Button add;

    public static MyVehiclesActivity newInstance(String text) {
        MyVehiclesActivity frag = new MyVehiclesActivity();

        Bundle args = new Bundle();
        args.putString(TEXT, text);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.available_vehicles_fragment, container, false);

        recyclerViewSearchResults = (RecyclerView) layout.findViewById(R.id.recyclerViewSearchResults);
        glm = new GridLayoutManager(getContext(), 1);
        recyclerViewSearchResults.setLayoutManager(glm);
        recyclerViewSearchResults.setAdapter(adapter);
        addManual = (RelativeLayout) layout.findViewById(R.id.lyManual);
        addManual.setVisibility(View.VISIBLE);
        add = (Button) layout.findViewById(R.id.btnmanualAdd);
        db = new DataBase(getActivity());
        user=db.getCurrentUser();
        if (getArguments() != null) {

        }

        loadUserList();

        setListener(layout);

        return layout;
    }

    private void loadUserList() {
       listVehicle =  db.loadMyVehicles(user);
       llenarListView(listVehicle);
    }

       private void llenarListView(final ArrayList<Vehicle> listaResponse) {
        adapter = new VehicleAdapter(listaResponse, getContext(), "");
        adapter.setListener(this);
        recyclerViewSearchResults.setAdapter(adapter);
    }

    public void setListener(View view){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormBuyVehicleActivity.class);
                intent.putExtra(Const.US, user);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        loadUserList();
    }

    @Override
    public void onClick(Vehicle v) {
        Toast.makeText(getActivity(), "click pra intent:   " + v.getMarca(), Toast.LENGTH_SHORT).show();
    }
}