package com.example.obatolaseward_evans.urbanadventure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.UUID;

public class LocationFragment extends Fragment {

    private static final String ARG_LOCATION_ID = "location_id";

    private Location location;


    public static LocationFragment newInstance(UUID locationId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LOCATION_ID, locationId);

        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID locationId = (UUID) getArguments().getSerializable(ARG_LOCATION_ID);
        location = LocationLab.get(getActivity()).getLocation(locationId);
    }

    @Override
    public void onPause() {
        super.onPause();

        LocationLab.get(getActivity())
                .updateLocation(location);
    }

    //TODO: fill with location fragment info (image view and text view of location)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);

        TextView title = (TextView)v.findViewById(R.id.location_fragement_title);
        TextView type = (TextView)v.findViewById(R.id.location_fragement_category);
        TextView des = (TextView)v.findViewById(R.id.location_fragement_descripton);
        ImageView image = (ImageView)v.findViewById(R.id.location_fragment_image);
        Button directionButton = (Button) v.findViewById(R.id.location_fragment_directions_button);

        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGoogleMapDirections();
            }
        });

        title.setText(location.getTitle());
        type.setText(location.getLocationType().toString());

        String description = location.getDescription();
        des.setText(description);

        String im = "@drawable/" + location.getPicturePath();

        int id = getResources().getIdentifier(im, "drawable", getActivity().getPackageName());

        image.setImageResource(id);

        return v;
    }

    private void getGoogleMapDirections() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="+ location.getLatitude() +"," + location.getLongitude()));
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
