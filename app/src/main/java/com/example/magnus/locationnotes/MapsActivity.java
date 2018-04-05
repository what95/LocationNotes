package com.example.magnus.locationnotes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private static ArrayList<LatLng> lokasjonList;

    public static LatLng hentLokasjonMarkor(){
        return lokasjonList.get(0);
    }

    private GoogleMap mGoogleMap;
    SupportMapFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        lokasjonList = new ArrayList<>();

        mFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mFragment.getMapAsync(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng){
        addMarker(latLng, "Lokasjon");
        startActivity(new Intent(this, CreateNoteActivity.class));


    }

    private void addMarker(LatLng latLng, String lokasjon) {
        //Clear all markers (Du kan bare velge en markør for din lokasjon)
        mGoogleMap.clear();
        // Create all the marker options for the kitty marker
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title(lokasjon);

        // Add the marker to the map
        Marker newMarker = mGoogleMap.addMarker(markerOptions);
        // Add the marker to the MarkersArray
        lokasjonList.add(latLng);
    }


    @Override
    public void onMapReady(GoogleMap gMap) {
        mGoogleMap = gMap;

        mGoogleMap.setOnMapLongClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        //ui settings
        UiSettings uiSettings = mGoogleMap.getUiSettings();
        uiSettings.setMapToolbarEnabled(false);
        uiSettings.setTiltGesturesEnabled(false);

        mGoogleMap.setMyLocationEnabled(true);
        }

}