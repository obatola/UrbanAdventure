package com.example.obatolaseward_evans.urbanadventure;

import com.google.android.gms.maps.model.LatLng;

import java.util.UUID;

public class Location {

    private UUID id;
    private String title;
    private LocationType locationType; // 0 = WPIFACILITY, 1 = FOOD, 2 = CULTURE;
    private String picturePath;
    private String description;
    private double longitude;
    private double latitude;
    private boolean hasVisited;

    public Location() {
        id = UUID.randomUUID();
        title = "";
        picturePath = "";
        description = "";
        longitude = 0;
        latitude = 0;
        hasVisited = false;
    }

    public Location(String locTitle, LocationType locType, String locDescription, double locLatitude, double locLongitude) {
        id = UUID.randomUUID();
        title = locTitle;
        locationType = locType;
        description = locDescription;
        latitude = locLatitude;
        longitude = locLongitude;
    }

    //constructor for db with locType = int
    public Location(String locTitle, int locType, String locDescription, double locLatitude, double locLongitude) {
        id = UUID.randomUUID();
        title = locTitle;
        description = locDescription;
        latitude = locLatitude;
        longitude = locLongitude;
        if (locType == 0) { locationType = LocationType.WPIFACILITY; }
        else if (locType == 1) { locationType = LocationType.FOOD; }
        else { locationType = LocationType.CULTURE; }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
         this.picturePath = picturePath;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    public boolean hasVisited() {
        return hasVisited;
    }

    public void setHasVisited(boolean bool) {
        hasVisited = bool;
    }
}
