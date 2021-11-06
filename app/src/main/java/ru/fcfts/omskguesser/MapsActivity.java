package ru.fcfts.omskguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private GoogleMap mMap;
    private Button check;
    private LatLng sightLatLng;
    private LatLng myLatLng;
    private Sight sight;
    private Button results;
    private double distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        // Set the layout file as the content view.
        setContentView(R.layout.activity_maps);
        check = (Button) findViewById(R.id.check);
        check.setOnClickListener(this);
        check.setVisibility(View.GONE);
        results = (Button) findViewById(R.id.results);
        results.setOnClickListener(this);
        results.setVisibility(View.GONE);
        sight = (Sight)(getIntent().getExtras().get("sight"));
        sightLatLng = new LatLng(sight.getCoords()[0], sight.getCoords()[1]);
        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mapsContainer, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Opera and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sightLatLng));

        //Маркер пользователя
        mMap.setOnMapClickListener(point -> {

            mMap.clear();

            //Ставит маркер пользователя
            Marker mymark = mMap.addMarker(new MarkerOptions().position(point));
            Projection projection = mMap.getProjection();
            myLatLng = mymark.getPosition();
            check.setVisibility(View.VISIBLE);
            //Рисует линию между координатами

            //Нахождение расстояния
            Double x1 = (sight.getCoords()[0]*3.14)/180;
            Double y1 = (sight.getCoords()[1]*3.14)/180;
            Double x2 = (mymark.getPosition().latitude*3.14)/180;
            Double y2 = (mymark.getPosition().longitude*3.14)/180;
//            distance =  2*6371*Math.asin(Math.sqrt(Math.sin((x2-x1)/2)*Math.sin((x2-x1)/2)+Math.cos(x1)*Math.cos(x2)*Math.sin((y2-y1)/2)*Math.sin((y2-y1)/2)));
            float[] results = new float[1];
            Location.distanceBetween(myLatLng.latitude, myLatLng.longitude, sight.getCoords()[0], sight.getCoords()[1], results);
            distance = results[0];
        });


        // Create a LatLngBounds that includes the city Omsk.
        LatLngBounds adelaideBounds = new LatLngBounds(
                new LatLng(54.802268,72.933018 ), // SW bounds
                new LatLng(55.162508, 73.750756)  // NE bounds
        );

        // Constrain the camera target to the Centre.
        mMap.setLatLngBoundsForCameraTarget(adelaideBounds);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        mMap.setMinZoomPreference(10.0f);
        mMap.setMaxZoomPreference(25.0f);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.check:
                mMap.addMarker(new MarkerOptions().position(sightLatLng).title(sight.getName())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                        .add(sightLatLng, myLatLng));
                mMap.setOnMapClickListener(null);
                check.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                break;
            case R.id.results:
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("sight", sight);
                intent.putExtra("distance", distance);
                startActivity(intent);
            default:
                break;
        }
    }
}