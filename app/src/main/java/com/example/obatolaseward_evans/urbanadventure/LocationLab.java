package com.example.obatolaseward_evans.urbanadventure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.obatolaseward_evans.urbanadventure.database.LocationDbSchema.*;
import com.example.obatolaseward_evans.urbanadventure.database.LocationBaseHelper;
import com.example.obatolaseward_evans.urbanadventure.database.LocationCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocationLab {
    private static LocationLab locationLab;

    private Context context;
    private SQLiteDatabase database;

    public static LocationLab get(Context context) {
        if (locationLab == null) {
            locationLab = new LocationLab(context);
        }
        return locationLab;
    }

    private LocationLab(Context context) {
        this.context = context.getApplicationContext();
        database = new LocationBaseHelper(this.context)
                .getWritableDatabase();
    }


    public void addLocation(Location c) {
        ContentValues values = getContentValues(c);

        database.insert(LocationTable.NAME, null, values);
    }

    public List<Location> getLocations() {
        List<Location> location = new ArrayList<>();

        LocationCursorWrapper cursor = queryLocation(null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            location.add(cursor.getLocation());
            cursor.moveToNext();
        }
        cursor.close();

        return location;
    }

    public Location getLocation(UUID id) {
        LocationCursorWrapper cursor = queryLocation(
                LocationTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getLocation();
        } finally {
            cursor.close();
        }
    }

    public void updateLocation(Location location) {
        String uuidString = location.getId().toString();
        ContentValues values = getContentValues(location);

        database.update(LocationTable.NAME, values,
                LocationTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Location location) {
        ContentValues values = new ContentValues();
        values.put(LocationTable.Cols.UUID, location.getId().toString());
        values.put(LocationTable.Cols.TITLE, location.getTitle());
        values.put(LocationTable.Cols.LOCATIONTYPE, location.getLocationTypeInt());
        values.put(LocationTable.Cols.PICTUREPATH, location.getPicturePath());
        values.put(LocationTable.Cols.DESCRIPTION, location.getDescription());
        values.put(LocationTable.Cols.LONGITUDE, location.getLongitude());
        values.put(LocationTable.Cols.LATITUDE, location.getLatitude());
        values.put(LocationTable.Cols.HASVISITED, location.isHasVisited() ? 1 : 0);

        return values;
    }

    private LocationCursorWrapper queryLocation(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                LocationTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy\

        );

        return new LocationCursorWrapper(cursor);
    }

    void deleteAll()
    {
        database.delete(LocationTable.NAME, null, null);

    }
}
