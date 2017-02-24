package com.example.obatolaseward_evans.urbanadventure.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.obatolaseward_evans.urbanadventure.database.LocationDbSchema.LocationTable;
import com.example.obatolaseward_evans.urbanadventure.Location;

import java.util.UUID;

public class LocationCursorWrapper extends CursorWrapper {
    public LocationCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Location getLocation() {
        String uuidString = getString(getColumnIndex(LocationTable.Cols.UUID));
        String title = getString(getColumnIndex(LocationTable.Cols.TITLE));
        int locationType = getInt(getColumnIndex(LocationTable.Cols.LOCATIONTYPE));
        String picturePath = getString(getColumnIndex(LocationTable.Cols.PICTUREPATH));
        String description = getString(getColumnIndex(LocationTable.Cols.DESCRIPTION));
        double longitude = getDouble(getColumnIndex(LocationTable.Cols.LONGITUDE));
        double latitude = getDouble(getColumnIndex(LocationTable.Cols.LATITUDE));
        int hasVisited = getInt(getColumnIndex(LocationTable.Cols.HASVISITED));

        Location location = new Location(title, locationType, description, latitude, longitude);
        location.setId(UUID.fromString(uuidString));
        location.setPicturePath(picturePath);
        location.setHasVisited(hasVisited != 0);

        return location;
    }
}
