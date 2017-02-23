package com.example.obatolaseward_evans.urbanadventure;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Location> allLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //populate database with locations
        populateDatabase();

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

    private void populateDatabase() {



    }

    /** Temporary location generator
     *
     * Creates a set of locations to populate the map
     * This should later be handled in a database.
     */
    public void generateLocations() {
        // TODO: Put all locations in the data base

        // WPI Facility Locations
//        Location wpi = new Location("Worcester Polytechnic Institute", LocationType.WPIFACILITY, "sample description", 42.2746, -71.8063);
        Location recCenter = new Location("Rec Center", LocationType.WPIFACILITY, "sample description", 42.274205, -71.810708);
        Location fullerLabs = new Location("Fuller Labs", LocationType.WPIFACILITY, "sample description", 42.275060, -71.806522);
        Location library = new Location("Gordon Library", LocationType.WPIFACILITY, "sample description", 42.274229, -71.806352);
        Location campusCenter = new Location("Campus Center", LocationType.WPIFACILITY, "sample description", 42.274907, -71.808482);
        Location gateway = new Location("Gateway Park", LocationType.WPIFACILITY, "sample description", 42.275387, -71.799020);

        // Food Locations
        Location beanCounter = new Location("The Bean Counter", LocationType.FOOD, "sample description", 42.271729, -71.807335);
        Location boynton = new Location("The Boynton Restaurant", LocationType.FOOD, "sample description", 42.270867, -71.807431);
        Location wooberry = new Location("Wooberry Frozen Yogurt & Ice Cream", LocationType.FOOD, "sample description", 42.270724, -71.808211);
        Location theFix = new Location("The Fix", LocationType.FOOD, "sample description", 42.276723, -71.801415);

        // Culture Locations
        Location moorePond = new Location("Moore Pond", LocationType.CULTURE, "sample description", 42.313249, -71.957684);
        Location wam = new Location("Worcester Art Museum", LocationType.CULTURE, "sample description", 42.273345, -71.801973);
        Location newtonHill = new Location("Newton Hill", LocationType.CULTURE, "Great place to hike, play disc golf, and exercise", 42.267565, -71.819960);

        // TODO: remove the next line in production code
        campusCenter.setHasVisited(true);

        allLocations.add(recCenter);
        allLocations.add(beanCounter);
        allLocations.add(fullerLabs);
        allLocations.add(wam);
        allLocations.add(moorePond);
        allLocations.add(library);
        allLocations.add(campusCenter);
        allLocations.add(wooberry);
        allLocations.add(boynton);
        allLocations.add(theFix);
        allLocations.add(gateway);
        allLocations.add(newtonHill);
    }

    // Populate map with markers in allLocations
    public void  populateMap(GoogleMap map){
        float markerColor;

        for (Location loc: allLocations) {

            markerColor = getCorrespondingMarkerColor(loc);

            map.addMarker(new MarkerOptions()
                    .position(loc.getLatLng()).title(loc.getTitle())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(markerColor)));
        }
    }

    private float getCorrespondingMarkerColor(Location loc){
        LocationType type = loc.getLocationType();

        if (loc.hasVisited()) {
            return BitmapDescriptorFactory.HUE_CYAN;
        }

        switch (type) {
            case WPIFACILITY:
                return BitmapDescriptorFactory.HUE_RED;
            case CULTURE:
                return BitmapDescriptorFactory.HUE_ORANGE;
            case FOOD:
                return BitmapDescriptorFactory.HUE_GREEN;
            default:
                return BitmapDescriptorFactory.HUE_YELLOW;
        }
    }
}
