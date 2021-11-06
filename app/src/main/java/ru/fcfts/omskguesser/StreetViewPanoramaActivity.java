package ru.fcfts.omskguesser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.ViewGroup;

import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.LatLng;
import android.view.ViewGroup.LayoutParams;

public class StreetViewPanoramaActivity extends AppCompatActivity {

    private static final LatLng OMSK = new LatLng(54.9822702,73.3801783);

    private StreetViewPanoramaView streetViewPanoramaView;

    private static final String STREETVIEW_BUNDLE_KEY = "StreetViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
        if (savedInstanceState == null){
            options.position(OMSK);
        }

        streetViewPanoramaView = new StreetViewPanoramaView(this, options);
        addContentView(streetViewPanoramaView,
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        Bundle streetViewBundle = null;
        if (savedInstanceState != null){
            streetViewBundle = savedInstanceState.getBundle(STREETVIEW_BUNDLE_KEY);
        }
        streetViewPanoramaView.onCreate(streetViewBundle);
    }

    @Override
    protected void onResume(){
        streetViewPanoramaView.onResume();
        super.onResume();
    }
    @Override
    protected void onPause(){
        streetViewPanoramaView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        streetViewPanoramaView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);

        Bundle streetViewBundle = outState.getBundle(STREETVIEW_BUNDLE_KEY);
        if (streetViewBundle == null){
            streetViewBundle = new Bundle();
            outState = new Bundle();
            outState.putBundle(STREETVIEW_BUNDLE_KEY, streetViewBundle);
        }

        streetViewPanoramaView.onSaveInstanceState(streetViewBundle);
    }
}