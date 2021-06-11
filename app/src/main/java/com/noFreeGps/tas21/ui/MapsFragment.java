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
import com.noFreeGps.tas21.R;

public class MapsFragment extends Fragment {

    GoogleMap miMapa;
    Marker markerUbicacion;
    double longitudDouble = 1.0;
    double latitudDouble = 1.0;
    String latitudString = "36.0";
    String longitudString = "-4.0";
    private BroadcastReceiver broadcastReceiver;

    Context context;
    Intent intent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {



        @Override
        public void onMapReady(GoogleMap googleMap) {

            try {
                /*latitudDouble = Double.parseDouble(latitudString);
                longitudDouble = Double.parseDouble(longitudString);*/
                System.out.println("XXX22: "+ latitudDouble);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            miMapa = googleMap;
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            miMapa.setMyLocationEnabled(true);
            miMapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            LatLng miPosicion = new LatLng(6, -74);
            
            /*while(latitudDouble > 0){
                try {
                    Thread.sl
                    eep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "Lat: "+ latitudDouble, Toast.LENGTH_SHORT).show();
            }
*/
            markerUbicacion = miMapa.addMarker(new MarkerOptions()
                    .position(miPosicion)
                    .alpha(0.5f)
                    .snippet(latitudString+", "+longitudString)
                    .title("Colombia")
            );

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(miPosicion));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View viewFragment = inflater.inflate(R.layout.fragment_maps, container, false);

        if(getArguments() != null){
        latitudString = getArguments().getString("latitudKey");
        longitudString = getArguments().getString("longitudKey");
        System.out.println("XXXNNN - ServiceLocation - onCreateView(), Se reciben Datos: " + latitudString + longitudString);//
        }

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

/**********************************/
    public void updateLocationMap(double latitudDouble, double longitudDouble){
        this.latitudDouble = latitudDouble;
        this.longitudDouble = longitudDouble;
    }

/**********************************/

}