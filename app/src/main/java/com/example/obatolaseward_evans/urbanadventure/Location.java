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
    private String phoneNumber;
    private String website;

    public Location() {
        id = UUID.randomUUID();
        title = "";
        picturePath = "";
        description = "";
        longitude = 0;
        latitude = 0;
        hasVisited = false;
        phoneNumber = "";
        website = "";

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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLocationTypeInt() {
        if (locationType.equals(LocationType.WPIFACILITY)) { return 0; }
        else if (locationType.equals(LocationType.FOOD)) { return 1; }
        else { return 2; }
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationTypeInt(int locationType) {
        if (locationType == 0) { this.locationType = LocationType.WPIFACILITY; }
        else if (locationType == 1) { this.locationType = LocationType.FOOD; }
        else { this.locationType = LocationType.CULTURE; }
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isHasVisited() {
        return hasVisited;
    }

    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
