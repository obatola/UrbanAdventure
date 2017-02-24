package com.example.obatolaseward_evans.urbanadventure;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private List<Location> allLocations = new ArrayList<Location>();
    private LocationLab locationLab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //populate database with locations
        locationLab = LocationLab.get(this);

        //make sure locations are populated and copied into allLocation array
        if(locationLab.getLocations().size() < 1) {
            populateDatabase();
        } else {
            //make sure not duplicating
            allLocations.clear();
            allLocations.addAll(locationLab.getLocations());
        }

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Add the actionbar to the top of the view
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mapview_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.list:
                goToListActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //make sure locations are populated and copied into allLocation array
        if(locationLab.getLocations().size() < 1) {
            populateDatabase();
        } else {
            //make sure not duplicating
            allLocations.clear();
            allLocations.addAll(locationLab.getLocations());
        }

        // Add a marker in WPI and move the camera
        Location wpi = allLocations.get(0);
        if (wpi != null) {
            mMap.addMarker(new MarkerOptions().position(wpi.getLatLng()).title(wpi.getTitle()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(wpi.getLatLng(), 16));
        }

        populateMap(mMap);

    }

    private List<Location> populateDatabase() {
        // WPI Facility Locations
        String wpid = "Worcester Polytechnic Institute was founded in 1865 to create and convey the latest science and " +
                "engineering knowledge in ways that are most beneficial to society. Today, WPI holds firm to its founding " +
                "mission to provide an education that balances theory with practice, a philosophy symbolized by the towers " +
                "of our two original buildings, pictured above. WPI's 14 academic departments offer more than 50 " +
                "undergraduate and graduate degree programs in science, engineering, technology, business, the social " +
                "sciences, and the humanities and arts, leading to bachelor’s, master’s, and doctoral degrees. ";
        Location wpi = new Location("Worcester Polytechnic Institute", LocationType.WPIFACILITY, wpid, 42.2746, -71.8063);
        wpi.setPicturePath("wpi");

        String rec = "The Sports & Recreation Center offers many opportunities for fitness, recreation, weight lifting, cardio " +
                "exercise, swimming, racquet sports, yoga, and aerobics. Spread across four floors, the Sports & Recreation " +
                "Center serves our varsity athletes as well as our community members maintaining their fitness regimen.";
        Location recCenter = new Location("Rec Center", LocationType.WPIFACILITY, rec, 42.274205, -71.810708);
        recCenter.setPicturePath("rec");

        String fuller = "The Sports & Recreation Center offers many opportunities for fitness, recreation, weight lifting, " +
                "cardio exercise, swimming, racquet sports, yoga, and aerobics. Spread across four floors, the Sports & " +
                "Recreation Center serves our varsity athletes as well as our community members maintaining their fitness regimen.";
        Location fullerLabs = new Location("Fuller Labs", LocationType.WPIFACILITY, fuller , 42.275060, -71.806522);
        fullerLabs.setPicturePath("fuller");

        String lib = "Opened in 1967, George C. Gordon Library is named for one of its benefactors who graduated from WPI " +
                "with an electrical engineering degree in 1895. Gordon generously bequeathed $5 million of his estate to the Institute in 1964. " +
                "Today, Gordon Library is one of the busiest buildings on campus-with an average of 13,000+ visitors per week during the academic year.";
        Location library = new Location("Gordon Library", LocationType.WPIFACILITY, lib, 42.274229, -71.806352);
        library.setPicturePath("lib");

        String cc = "WPI’s Rubin Campus Center is at the crossroads of the campus. Whether you want to pick up your mail, grab a cup of coffee, " +
                "or host a meeting, the Rubin Campus Center fits all these needs. It’s home to our Bookstore and Food Court, the Office " +
                "of Student Affairs and Campus Life , as well as a comfortable location to study or visit with friends. Enjoy a concert on " +
                "the patio, meet for lunch, catch a comedy show on the stage in the Food Court, reserve a room for a meeting, grab a coffee at " +
                "Dunkin' Donuts, or relax in the Class of 1946 Lounge.";
        Location campusCenter = new Location("Campus Center", LocationType.WPIFACILITY, cc, 42.274907, -71.808482);
        campusCenter.setPicturePath("cc");

        String gate = "This growing center of research, innovation, and commerce is located in downtown Worcester just a short walk from" +
                " WPI's main campus. The park began as a joint venture with the Worcester Business Development Corporation to transform a" +
                " blighted and underutilized area in into a clean, thriving, mixed-use facility for a range of academic, research, and " +
                "commercial enterprises. WPI became the sole owner of Gateway Park in 2010 and is pursing the build-out of the park in " +
                "partnership with companies in the region.";
        Location gateway = new Location("Gateway Park", LocationType.WPIFACILITY, gate, 42.275387, -71.799020);
        gateway.setPicturePath("gateway");

        // Food Locations

        String bean = "At the Bean Counter Bakery, all of the fine cakes, cupcakes, cookies, tarts, pies, desserts, pastries" +
                " and baked goods are hand made from scratch. Only premium quality fresh all natural ingredients " +
                "and no preservatives or artificial flavors are used. All of the coffee and espresso beans are micro-roasted " +
                "in small batches and shipped several times a week. Only  premium quality roasted beans are selected and " +
                "carefully brewed for every cup in order to meet and exceed the customers’ continued high " +
                "expectations. All beans are bought Direct-Trade single origin.";
        Location beanCounter = new Location("The Bean Counter", LocationType.FOOD, bean, 42.271729, -71.807335);
        beanCounter.setPicturePath("bean");

        String boyntonDes = "The Boynton was originally a small tavern in the 1930's most often frequented by Worcester Polytechnic Institute (WPI) professors, " +
                "students and the general neighborhood population. (Not too much different from today!) The name \"Boynton\" seems " +
                "to have been the popular name of choice during a bygone era. Nearby is Boynton Street, Boynton Hall (the main " +
                "administration building of WPI), and Boynton Park. You may or may not know that the frequent use of the name " +
                "\"Boynton\" originates from John Boynton, one of the founding fathers of Worcester Polytechnic Institute. ";
        Location boynton = new Location("The Boynton Restaurant", LocationType.FOOD, boyntonDes, 42.270867, -71.807431);
        boynton.setPicturePath("boynton");

        String woo = "WooBerry was founded on simple principles: combining delicious frozen desserts with outstanding " +
                "customer service in a fun and inviting atmosphere. Locally owned and operated on Highland Street in " +
                "Worcester Massachusetts, WooBerry is in close proximity to multiple universities, cultural institutions, " +
                "retail and restaurants. Great pride is taken in being a part of the Worcester community, bringing together " +
                "people of all ages and interests through a universal love of delicious frozen desserts.";
        Location wooberry = new Location("Wooberry Frozen Yogurt & Ice Cream", LocationType.FOOD, woo, 42.270724, -71.808211);
        wooberry.setPicturePath("wooberry");

        String fix = "Modern setting for custom burgers served with craft beers, unique cocktails & spiked milkshakes.";
        Location theFix = new Location("The Fix", LocationType.FOOD, fix, 42.276723, -71.801415);
        theFix.setPicturePath("fix");

        // Culture Locations
        String moore = "Moore State Park is a 737-acre public recreation area located in the town of Paxton, Massachusetts, portions of " +
                "which were listed on the National Register of Historic Places as the Moore State Park Historic District in 2004.";
        Location moorePond = new Location("Moore Pond", LocationType.CULTURE, moore, 42.313249, -71.957684);
        moorePond.setPicturePath("moore");

        String art = "The Worcester Art Museum, also known by its acronym WAM, houses over 35,000 works of art dating from antiquity " +
                "to the present day, representing cultures from all over the world.";
        Location wam = new Location("Worcester Art Museum", LocationType.CULTURE, art, 42.273345, -71.801973);
        wam.setPicturePath("wam");

        String newton = "The Newton Hill portion of Elm Park (west of Park Avenue) remains minimally landscaped and contains basketball " +
                "and tennis courts, walking trails and also Doherty Memorial High School, a high school within the Worcester Public " +
                "Schools system.";
        Location newtonHill = new Location("Newton Hill", LocationType.CULTURE, newton, 42.267565, -71.819960);
        newtonHill.setPicturePath("newton");

        //TODO: delete later
        newtonHill.setHasVisited(true);
        recCenter.setHasVisited(true);
        gateway.setHasVisited(true);
        wooberry.setHasVisited(true);

        locationLab.addLocation(wpi);
        locationLab.addLocation(recCenter);
        locationLab.addLocation(beanCounter);
        locationLab.addLocation(fullerLabs);
        locationLab.addLocation(wam);
        locationLab.addLocation(moorePond);
        locationLab.addLocation(library);
        locationLab.addLocation(campusCenter);
        locationLab.addLocation(wooberry);
        locationLab.addLocation(boynton);
        locationLab.addLocation(theFix);
        locationLab.addLocation(gateway);
        locationLab.addLocation(newtonHill);

        //make sure to not add duplicates
        allLocations.clear();
        allLocations.addAll(locationLab.getLocations());

        return locationLab.getLocations();
    }

    // Populate map with markers in allLocations
    public void  populateMap(GoogleMap map){
        float markerColor;

        for (Location loc: allLocations) {

            markerColor = getCorrespondingMarkerColor(loc);

            Marker m = map.addMarker(new MarkerOptions()
                    .position(loc.getLatLng()).title(loc.getTitle())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(markerColor)));

            m.setTag(loc.getId());
        }

        mMap.setOnMarkerClickListener(this);
    }

    private float getCorrespondingMarkerColor(Location loc){
        LocationType type = loc.getLocationType();

        if (loc.isHasVisited()) {
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        UUID locID = (UUID) marker.getTag();
        return goToLocationDetailActivity(locID);
    }

    public boolean goToListActivity() {
        Intent intent = new Intent(this, LocationListActivity.class);
        intent.setFlags(FLAG_ACTIVITY_REORDER_TO_FRONT); // Reopen activity if already exists.
        startActivity(intent);
        return true;
    }

    public boolean goToLocationDetailActivity(UUID locationID) {
        Intent intent = LocationPagerActivity.newIntent(this, locationID);
        startActivity(intent);
        return true;
    }
}
