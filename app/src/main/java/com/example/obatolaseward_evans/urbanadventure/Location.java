package com.example.obatolaseward_evans.urbanadventure;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Penthoue on 2/20/2017.
 */

public class Location {
    private String title;
    private LocationType locationType;
    private String picturePath;
    private String description;
    private double longitude;
    private double latitude;
    private boolean hasVisited;

    public Location() {
        title = "";
        picturePath = "";
        description = "";
        longitude = 0;
        latitude = 0;
        hasVisited = false;
    }

    public Location(String locTitle, LocationType locType, String locDescription, double locLatitude, double locLongitude) {
        title = locTitle;
        locationType = locType;
        description = locDescription;
        latitude = locLatitude;
        longitude = locLongitude;
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
