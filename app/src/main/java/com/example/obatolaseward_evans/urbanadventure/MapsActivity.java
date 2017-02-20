package com.example.obatolaseward_evans.urbanadventure;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Location> allLocations = new ArrayList<Location>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Grab all locations
        generateLocations();

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

        // Add a marker in WPI and move the camera
        Location wpi = allLocations.get(0);
        if (wpi != null) {
            mMap.addMarker(new MarkerOptions().position(wpi.getLatLng()).title(wpi.getTitle()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(wpi.getLatLng(), 16));
        }

        populateMap(mMap);

    }

    /** Temporary location generator
     *
     * Creates a set of locations to populate the map
     * This should later be handled in a database.
     */
    public void generateLocations() {
        Location wpi = new Location("Worcester Polytechnic Institute", "A college", 42.2746, -71.8063); // done
        Location recCenter = new Location("Rec Center", "A college", 42.274205, -71.810708); // done
        Location beanCounter = new Location("The Bean Counter", "A college", 42.271729, -71.807335); // done
        Location fullerLabs = new Location("Fuller Labs", "A college", 42.275060, -71.806522); // done
        Location wam = new Location("Worcester Art Museum", "A college", 42.273345, -71.801973); // done
        Location moorePond = new Location("Moore Pond", "A college", 42.313249, -71.957684); // done
        Location library = new Location("Gordon Library", "A college", 42.275006, -71.806353); // done
        Location campusCenter = new Location("Campus Center", "A college", 42.274907, -71.808482); // done

        allLocations.add(wpi);
        allLocations.add(recCenter);
        allLocations.add(beanCounter);
        allLocations.add(fullerLabs);
        allLocations.add(wam);
        allLocations.add(moorePond);
        allLocations.add(library);
        allLocations.add(campusCenter);
    }

    public void  populateMap(GoogleMap map){
        for (Location loc: allLocations) {
            map.addMarker(new MarkerOptions().position(loc.getLatLng()).title(loc.getTitle()));
        }
    }
}
