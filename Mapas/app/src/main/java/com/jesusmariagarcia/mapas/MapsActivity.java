package com.jesusmariagarcia.mapas;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Pair<Double, Double>[] mLocations = new Pair[] {
                new Pair(43.26837179744203, -2.9344065621201065),   // Guggenheim Bilbao
                new Pair(43.44700001504195, -2.784999999999968),    // Gaztelugatxe
                new Pair(43.384114232854245, -3.2183655328185523),  // Castro Urdiales
                new Pair(43.32175367181494, -1.985963619403046) };  // Donostia

        int nLocation = getIntent().getExtras().getInt("Location");

        if(nLocation != 0) {
            mLocation = new LatLng(mLocations[nLocation - 1].first, mLocations[nLocation - 1].second);

        } else {
            mLocation = new LatLng(0, 0);
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            finish();
        }


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

        mMap.addMarker(new MarkerOptions()
                .position(mLocation)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.geocaching_48))
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocation, 12));
    }
}
