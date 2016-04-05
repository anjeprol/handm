package com.prolan.hm.volleycustomlistview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.prolan.hm.R;
import com.prolan.hm.model.Stores;

public class ScrollingDetails extends AppCompatActivity implements OnMapReadyCallback {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private Double lat = 0.0, lon = 0.0;
    private String storeName;
    private String numToCall;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng marker = new LatLng(lat, lon);
        googleMap.clear();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 13));
        googleMap.addMarker(new MarkerOptions().title(storeName).snippet(getString(R.string.label_description)).position(marker));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //Getting intent from mainActivity
        Intent intentFromList = getIntent();
        Stores stores = (Stores) intentFromList.getParcelableExtra("stores");

        Log.d(TAG, stores.toString());
        storeName = stores.getName();
        toolbar.setTitle(storeName);

        //Inflating the views
        populateView(stores);
        setSupportActionBar(toolbar);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if android version is M ask for permissions
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //Asking for permissions before send the message
                    int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED)
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_PERMISSIONS);
                    else {

                        Snackbar.make(view, "Dialing to:" + numToCall, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + numToCall));
                        startActivity(callIntent);
                    }

                } else {//If android version is lower that M make the call without permissions

                    Snackbar.make(view, "Dialing to:" + numToCall, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + numToCall));
                    startActivity(callIntent);
                }


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void populateView(Stores stores) {
        TextView address = (TextView) findViewById(R.id.address);
        TextView city = (TextView) findViewById(R.id.city);
        TextView zip = (TextView) findViewById(R.id.zipcode);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView state = (TextView) findViewById(R.id.state);
        TextView storeID = (TextView) findViewById(R.id.storeID);

        numToCall = stores.getPhone();

        address.setText(stores.getAddress());
        city.setText(getString(R.string.labelCity) + stores.getCity());
        zip.setText(getString(R.string.labelZip) + stores.getZipcode());
        phone.setText(getString(R.string.labelPhone) + stores.getPhone());
        state.setText(getString(R.string.labelState) + stores.getState());
        storeID.setText(getString(R.string.labelIDStore) + stores.getStoreID());
        lat = Double.parseDouble(stores.getLatitude());
        lon = Double.parseDouble(stores.getLongitude());


    }


}
