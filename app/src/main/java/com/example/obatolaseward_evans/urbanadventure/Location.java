package com.example.obatolaseward_evans.urbanadventure;

/**
 * Created by Penthoue on 2/20/2017.
 */

public class Location {
    private String title;
    private String picturePath;
    private String description;
    private double longitude;
    private double latitude;

    public Location() {
        title = "";
        picturePath = "";
        description = "";
        longitude = 0;
        latitude = 0;
    }

    public Location(String newTitle, String newDescription, double newLatitude, double newLongitude) {
        title = newTitle;
        description = newDescription;
        latitude = newLatitude;
        longitude = newLongitude;
    }

    public String getTitle() {
        return title;
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
}
