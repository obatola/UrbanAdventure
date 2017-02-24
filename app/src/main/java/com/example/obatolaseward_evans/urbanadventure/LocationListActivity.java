package com.example.obatolaseward_evans.urbanadventure;

import android.support.v4.app.Fragment;

public class LocationListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LocationListFragment();
    }
}
