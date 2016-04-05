package com.prolan.hm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.prolan.hm.R;
import com.prolan.hm.app.AppController;
import com.prolan.hm.model.Stores;
import com.prolan.hm.volleycustomlistview.ScrollingDetails;

import java.util.List;
import java.util.SortedMap;

/**
 * Created by Prolan on 30/03/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Stores> storesItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private Intent intent;
    int pos ;

    public CustomListAdapter(Activity activity, List<Stores> storesItems){
        this.activity = activity;
        this.storesItems = storesItems;
    }

    @Override
    public int getCount() {
        return storesItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int location) {
        return storesItems.get(location);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
        if(imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView imgLogo = (NetworkImageView) convertView.findViewById(R.id.imgLogo);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);

        // getting stores data for the row
        Stores m = storesItems.get(position);

        // Store image
        imgLogo.setImageUrl( m.getStoreLogoURL(), imageLoader);

        // address
        address.setText( m.getAddress());

        phone.setText(m.getPhone());

        //Calling Scrolling Activity Details
        RelativeLayout relListRow = (RelativeLayout) convertView.findViewById(R.id.relListRow);
        //Creating the listener for each row
        relListRow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                intent = new Intent(activity, ScrollingDetails.class);
                intent.putExtra("stores",storesItems.get(position));
                activity.startActivity(intent);
            }
        });


        return convertView;
    }
}
