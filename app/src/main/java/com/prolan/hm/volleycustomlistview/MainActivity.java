package com.prolan.hm.volleycustomlistview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.prolan.hm.R;
import com.prolan.hm.adapter.CustomListAdapter;
import com.prolan.hm.app.AppController;
import com.prolan.hm.model.Stores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    // Billionaires json url
    private static final String url = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json";

    private ProgressDialog pDialog;
    private List<Stores> allStoresList = new ArrayList<Stores>();
    private ListView listView;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readJSON();


    }

    public void readJSON(){
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomListAdapter(this, allStoresList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage(getString(R.string.msgLoading));
        pDialog.show();
        JsonObjectRequest storeRequest = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        try {
                            JSONArray results = response.getJSONArray("stores");
                            // Parsing json
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject obj = results.getJSONObject(i);
                                Stores mStores = new Stores();
                                mStores.setStoreLogoURL(obj.optString("storeLogoURL"));
                                mStores.setAddress(obj.optString("address"));
                                mStores.setPhone(obj.optString("phone"));
                                mStores.setCity(obj.optString("city"));
                                mStores.setLatitude(obj.optString("latitude"));
                                mStores.setLongitude(obj.optString("longitude"));
                                mStores.setState(obj.optString("state"));
                                mStores.setName(obj.optString("name"));
                                mStores.setStoreID(obj.optString("storeID"));
                                mStores.setZipcode(obj.optString("zipcode"));
                                allStoresList.add(mStores);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it shows updated data in ListView
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, R.string.err_noConnection, Toast.LENGTH_LONG).show();
                hidePDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(storeRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}

