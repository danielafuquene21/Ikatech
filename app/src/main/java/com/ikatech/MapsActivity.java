package com.ikatech;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ikatech.dataObject.Location;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private Marker markerG;
    Address address;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).draggable(true).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        LatLng location = new LatLng(4.6056728,-74.0555255);
        markerG = googleMap.addMarker(new MarkerOptions().position(location).draggable(true).title("Marker in Sydney"));


        if (markerG == null) {
            markerG = googleMap.addMarker(new MarkerOptions().position(location).title("location").draggable(true));
        } else {
            markerG.remove();
            //location = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
            markerG = googleMap.addMarker(new MarkerOptions().position(location).title("location").draggable(true));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));


        googleMap.setOnMarkerDragListener(this);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if(marker.equals(markerG)){
            Location locat = new Location();
            locat.setLat(Double.toString(marker.getPosition().latitude));
            locat.setLon(Double.toString(marker.getPosition().longitude));
            locat.setAddres("");
            Toast.makeText(this, locat.getLat() + " , " + locat.getLon(), Toast.LENGTH_LONG).show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("lat",locat);
            setResult(1,returnIntent);
            finish();
        }
    }
}