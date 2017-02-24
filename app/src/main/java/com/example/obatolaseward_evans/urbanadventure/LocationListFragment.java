package com.example.obatolaseward_evans.urbanadventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class LocationListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView locationRecyclerView;
    private LocationAdapter adapter;
    private boolean subtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_list, container, false);

        locationRecyclerView = (RecyclerView) view
                .findViewById(R.id.location_recycler_view);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            subtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, subtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_location_list, menu);

        updateLocationNum();
    }

    private void updateLocationNum() {
        LocationLab locationLab = LocationLab.get(getActivity());
        //TODO: change to check for each location visited
        String locationCount = "" + locationLab.getLocations().size();
        String subtitle = getString(R.string.num_locations, locationCount);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        LocationLab locationLab = LocationLab.get(getActivity());
        List<Location> locations = locationLab.getLocations();

        if (adapter == null) {
            adapter = new LocationAdapter(locations);
            locationRecyclerView.setAdapter(adapter);
        } else {
            adapter.setLocations(locations);
            adapter.notifyDataSetChanged();
        }

        updateLocationNum();
    }

    private class LocationHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView titleTextView;
        private TextView locationTypeTextView;
        private CheckBox visitedCheckBox;

        private Location location;

        public LocationHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            titleTextView = (TextView) itemView.findViewById(R.id.list_item_location_title_text_view);
            locationTypeTextView = (TextView) itemView.findViewById(R.id.list_item_location_type_text_view);
            visitedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_location_visited_check_box);
        }

        public void bindLocation(Location bLocation) {
            location = bLocation;
            titleTextView.setText(location.getTitle());
            locationTypeTextView.setText(location.getLocationType().toString());
            visitedCheckBox.setChecked(location.isHasVisited());
        }

        @Override
        public void onClick(View v) {
            Intent intent = LocationPagerActivity.newIntent(getActivity(), location.getId());
            startActivity(intent);
        }
    }

    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {

        private List<Location> locations;

        public LocationAdapter(List<Location> lLocations) {
            locations = lLocations;
        }

        @Override
        public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_location, parent, false);
            return new LocationHolder(view);
        }

        @Override
        public void onBindViewHolder(LocationHolder holder, int position) {
            Location location = locations.get(position);
            holder.bindLocation(location);
        }

        @Override
        public int getItemCount() {
            return locations.size();
        }

        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }
    }
}
