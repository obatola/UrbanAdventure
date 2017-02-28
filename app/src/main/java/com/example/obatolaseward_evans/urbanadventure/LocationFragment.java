package com.example.obatolaseward_evans.urbanadventure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.UUID;

public class LocationFragment extends Fragment {

    private static final String ARG_LOCATION_ID = "location_id";
    private Brain brain = Brain.getInstance();
    private TextView distanceText;

    private Location location;
    private Button directionButton;
    private Button phoneButton;
    private Button websiteButton;



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

    @Override
    public void onResume() {
        super.onResume();
        displayDistanceFromUserToLocation();

    }

    //TODO: fill with location fragment info (image view and text view of location)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);

        TextView title = (TextView)v.findViewById(R.id.location_fragement_title);
        TextView type = (TextView)v.findViewById(R.id.location_fragement_category);
        TextView des = (TextView)v.findViewById(R.id.location_fragement_descripton);
        distanceText = (TextView)v.findViewById(R.id.location_fragement_distance);
        ImageView image = (ImageView)v.findViewById(R.id.location_fragment_image);
        directionButton = (Button) v.findViewById(R.id.location_fragment_directions_button);
        phoneButton = (Button) v.findViewById(R.id.location_fragment_phone_button);
        websiteButton = (Button) v.findViewById(R.id.location_fragment_website_button);

        initializeButtons();

        title.setText(location.getTitle());
        type.setText(location.getLocationType().toString());

        String description = location.getDescription();
        des.setText(description);

        String im = "@drawable/" + location.getPicturePath();

        int id = getResources().getIdentifier(im, "drawable", getActivity().getPackageName());

        image.setImageResource(id);
        return v;
    }

    private void initializeButtons() {
        if (location.getPhoneNumber() == null) {
            Log.d("locationdetailview", "phone number: " + location.getPhoneNumber());
            phoneButton.setVisibility(View.GONE);
        } else {
            phoneButton.setVisibility(View.VISIBLE);
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callPhoneNumber(location.getPhoneNumber());
                }
            });
        }

        if (location.getWebsiteURL() == null) {
            websiteButton.setVisibility(View.GONE);
        } else {
            websiteButton.setVisibility(View.VISIBLE);
            websiteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWebsite(location.getWebsiteURL());
                }
            });
        }
//
//        phoneButton.setVisibility(View.VISIBLE);
//        phoneButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(null, location.getPhoneNumber());
//                callPhoneNumber(location.getPhoneNumber());
//            }
//        });
//
//        websiteButton.setVisibility(View.VISIBLE);
//        websiteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(null, location.getWebsiteURL());
//                openWebsite(location.getWebsiteURL());
//            }
//        });

        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGoogleMapDirections(location.getLatitude(), location.getLongitude());
            }
        });
    }


    public void displayDistanceFromUserToLocation() {

        if (brain.getCurrentLocation() != null) {
            double distance = brain.getDistanceBetweenTwo(location.getLatitude(),location.getLongitude(),
                    brain.getCurrentLocation().getLatitude(),brain.getCurrentLocation().getLongitude());

            // round distance
            distance = Math.round(distance * 10) / 10.0;

            String dist = Double.toString(distance);
            distanceText.setText(" - " + dist + " mi");
        } else {
            distanceText.setText("");
        }
    }

    private void getGoogleMapDirections(double latitude, double longitude) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="+ latitude +"," + longitude));
        startActivity(intent);
    }

    private void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void openWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
