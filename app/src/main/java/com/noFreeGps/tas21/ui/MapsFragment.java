package com.noFreeGps.tas21.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;
import com.noFreeGps.tas21.R;

public class MapsFragment extends Fragment {

    GoogleMap miMapa;
    Marker markerUbicacion;
    LatLng miPosicion;
    double longitudDouble = 0.0; double latitudDouble = 0.0;
    String latitudString, longitudString;
    private BroadcastReceiver broadcastReceiver;
    private static final String TAG = "MapsFragment";
    boolean ciclo = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            miMapa = googleMap;
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                    .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
                    .PERMISSION_GRANTED) {
            }
            miMapa.setMyLocationEnabled(true);
            miMapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            miPosicion = new LatLng(latitudDouble, longitudDouble);

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(miPosicion));

            }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };

    public void getlocationData(){

            latitudString = getArguments().getString("latitudKey", "0.0");
            longitudString = getArguments().getString("longitudKey", "0.0");
            System.out.println("XXXXX - XMapFragmentX - onCreate(): " +latitudString);

   }

    public void ponerMarker(String latitudString, String longitudString ){

        this.latitudString = latitudString;
        this.longitudString = longitudString;

        latitudDouble = Double.parseDouble(latitudString);
        longitudDouble = Double.parseDouble(longitudString);

        markerUbicacion = miMapa.addMarker(new MarkerOptions()
                .position(miPosicion)
                .alpha(0.5f)
                .snippet(latitudString+", "+longitudString)
                .title("Colombia") );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View viewFragment = inflater.inflate(R.layout.fragment_maps, container, false);
        return viewFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }



}