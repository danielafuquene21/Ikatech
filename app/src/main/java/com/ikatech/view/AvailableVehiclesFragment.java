package com.ikatech.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ikatech.R;
import com.ikatech.adapter.VehicleAdapter;
import com.ikatech.businessObject.Const;
import com.ikatech.businessObject.Endpoints;
import com.ikatech.dataObject.Location;
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

public class AvailableVehiclesFragment extends Fragment  implements VehicleAdapter.RecyclerViewOnItemClickListener {

    private static final String TEXT = "text";

    public User user;
    private EditText editTextSearch;
    private RecyclerView recyclerViewSearchResults;
    private ArrayList<Vehicle> listVehicle = new ArrayList<Vehicle>();
    private GridLayoutManager glm;
    //private UserAdapterList adapter;
    //private EmptyListAdapter emptyAdapter;
    ArrayList<String> a = new ArrayList<String>();
    boolean load = false;
    public String json = "";
    VehicleAdapter adapter;
    ProgressDialog progressDialog;

    public static AvailableVehiclesFragment newInstance(String text) {
        AvailableVehiclesFragment frag = new AvailableVehiclesFragment();

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

        if (getArguments() != null) {

        }

        loadUserList();

        setListener(layout);

        return layout;
    }

    private void loadUserList() {

        progressDialog = ProgressDialog.show(getActivity(), "",
                "Esta cargando", false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                String url = Endpoints.URL_BASE+Endpoints.GET_VEHICLES;
                OkHttpClient peticion = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                peticion.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String myResponse = response.body().string();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                JSONArray jsonarray = null;
                                JSONObject jsonObject = null;
                                json = "";
                                try {
                                    jsonObject = new JSONObject(myResponse);
                                    jsonarray = (JSONArray) jsonObject.get("vehicles");
                                    json = jsonarray.toString();
                                    //Toast.makeText(AvailableVehiclesFragment.this, json, Toast.LENGTH_SHORT).show();
                                    loadJsonArray(jsonarray);
                                    llenarListView(listVehicle);
                                    if (progressDialog != null)
                                        progressDialog.cancel();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                });
            }
        }).start();
    }

    private void loadJsonArray(JSONArray newJArray) {

        try {
            for (int i = 0; i < newJArray.length(); i++) {
                Vehicle vehicle = new Vehicle();
                JSONObject item = (JSONObject) newJArray.get(i);
                vehicle.setMarca(item.getString(Const.BRAND));
                vehicle.setFavorito((Boolean.parseBoolean(item.getString(Const.FAVORITE)))); ;
                vehicle.setModelo(item.getString(Const.MODEL));
                vehicle.setEstado(item.getString(Const.STATE));
                vehicle.setImagen(item.getString(Const.IMAGE));
                vehicle.setNombreColeccion(item.getString(Const.COLLECTION_NAME));
                vehicle.setTipoCombustion(item.getString(Const.COMBUSTION_TYPE));
                JSONObject ubicacion = new JSONObject(String.valueOf(item.getString(Const.LOCATION)));
                //JSONArray jsonArray = (JSONArray) ubicacion.get(Const.LOCATION);
                //for (int j = 0 ;j < jsonArray.length(); j++){
                    Location location = new Location();
                    //JSONObject loc = (JSONObject) jsonArray.get(j);
                    location.setLon(ubicacion.getString(Const.L_LONGITUDE));
                    location.setLat(ubicacion.getString(Const.L_LATITUDE));
                    location.setAddres(ubicacion.getString(Const.L_ADDRESS));
                    vehicle.setUbicacion(location);
                //}
                listVehicle.add(vehicle);
            }
            load = true;
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void llenarListView(final ArrayList<Vehicle> listaResponse) {
        adapter = new VehicleAdapter(listaResponse, getContext(), "VeiculosDisponibles");
        adapter.setListener(this);
        recyclerViewSearchResults.setAdapter(adapter);
    }

    public void setListener(View view){

    }


    @Override
    public void onClick(Vehicle v) {
        Toast.makeText(getActivity(), "click pra intent:   " + v.getMarca(), Toast.LENGTH_SHORT).show();
    }
}