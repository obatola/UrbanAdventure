package com.example.obatolaseward_evans.urbanadventure;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Penthoue on 2/25/2017.
 */

public class Brain {
    private static Brain instance = null;
    private android.location.Location currentLocation;

    protected Brain() {
        currentLocation = null;
        // Exists only to defeat instantiation.
    }

    public static Brain getInstance() {
        if(instance == null) {
            instance = new Brain();
        }
        return instance;
    }

    //returns hashmap with locations and its distances frmo the given current Location
    private HashMap<Location,Double> getDistanceAll(Location currentLocation, List<Location> allLocations){
        double latitude = currentLocation.getLatitude();
        double longtitude = currentLocation.getLongitude();
        HashMap<Location,Double> locationDistances = new HashMap<Location,Double>();
        for(int i=0;i<allLocations.size();i++){
            Location a = allLocations.get(i);
            double alat = a.getLatitude();
            double alng = a.getLongitude();
            double distance = getDistanceBetweenTwo(latitude,longtitude,alat,alng);
            locationDistances.put(a,distance);
        }
        return locationDistances;
    }

    private double getDistance(Location currentLocation,Location other){
        double latitude = currentLocation.getLatitude();
        double longtitude = currentLocation.getLongitude();
        double lat2 = other.getLatitude();
        double lng2 = other.getLongitude();
        double distance = getDistanceBetweenTwo(latitude,longtitude,lat2,lng2);
        return distance;
    }

    public double getDistanceBetweenTwo(double lat1, double lng1, double lat2, double lng2) {
        int r = 6371; // average radius of the earth in km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c;

        // convert d from km to mi
        d = d * 0.621371;
        return d;
    }

    public android.location.Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(android.location.Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /*
    public static void sort(List<Location> allLocations, List<Double> distances){
        for(int i=0;i<distances.size();i++){
            for(int j=0;j<distances.size();j++){
                if(distances.get(j)<distances.get(i)){
                    double temp = distances.get(i);
                    distances.set(i,distances.get(j));
                    distances.set(j,temp);

                }
            }
        }
    }
    */
}

