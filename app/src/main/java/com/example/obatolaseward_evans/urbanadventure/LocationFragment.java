package com.example.obatolaseward_evans.urbanadventure;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

        title.setText(title.getText() + " " + location.getTitle());
        type.setText(type.getText() + " " + location.getLocationType().toString());
        des.setText(des.getText() + " " + location.getDescription());

        String im = "@drawable/" + location.getPicturePath();
        Log.e("LocationFragment", "picturePath: " + location.getPicturePath());
        Log.e("LocationFragment", "im: " + im);

        int id = getResources().getIdentifier(im, "drawable", getActivity().getPackageName());

        Log.e("LocationFragment", "id: " + id);

        image.setImageResource(id);

        return v;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
