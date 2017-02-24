package com.example.obatolaseward_evans.urbanadventure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class LocationPagerActivity extends AppCompatActivity {
    private static final String EXTRA_LOCATION_ID =
            "com.example.obatolaseward_evans.urbanadventure.location_id";

    private ViewPager viewPager;
    private List<Location> locations;

    public static Intent newIntent(Context packageContext, UUID locationId) {
        Intent intent = new Intent(packageContext, LocationPagerActivity.class);
        intent.putExtra(EXTRA_LOCATION_ID, locationId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_pager);

        UUID locationId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_LOCATION_ID);

        viewPager = (ViewPager) findViewById(R.id.activity_location_pager_view_pager);

        locations = LocationLab.get(this).getLocations();
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Location location = locations.get(position);
                return LocationFragment.newInstance(location.getId());
            }

            @Override
            public int getCount() {
                return locations.size();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                Location location = locations.get(position);
                if (location.getTitle() != null) {
                    setTitle(location.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getId().equals(locationId)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
