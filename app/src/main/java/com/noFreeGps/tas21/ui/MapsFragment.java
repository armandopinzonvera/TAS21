package com.noFreeGps.tas21.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.noFreeGps.tas21.R;

import static com.noFreeGps.tas21.config.ServiceLocation.DATO_ALTURA;
import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LATITUD;
import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LONGITUD;

public class MapsFragment extends Fragment {

    GoogleMap miMapa;
    Marker marker;
    double longitudDouble = 1.0;
    double latitudDouble = 1.0;
    String latitudString, longitudString;
    private BroadcastReceiver broadcastReceiver;

    Context context;
    Intent intent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /***********        NOOOOO   FUNCIONA
         * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!!!!!!!!
         *
         *
         * ************/
        longitudDouble = getActivity().getIntent().getDoubleExtra("longitud_map", 0.0);
        latitudDouble = getActivity().getIntent().getDoubleExtra("latitud_map", 0.0);
       /***************************/
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            miMapa = googleMap;
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            miMapa.setMyLocationEnabled(true);
            miMapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            LatLng miPosicion = new LatLng(latitudDouble,longitudDouble);
            

            marker = miMapa.addMarker(new MarkerOptions()
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