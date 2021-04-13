package com.example.project1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.models.Travel;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class TravelAdapter extends BaseAdapter {
    private Context context;
    private List<Travel> travelList;
    private int resource;

    public TravelAdapter(Context context, List<Travel> travelList, int resource) {
        this.context = context;
        this.travelList = travelList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return travelList.size();
    }

    @Override
    public Object getItem(int position) {
        return travelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            convertView= LayoutInflater.from(context).inflate(resource,parent,false);
         Travel travel=travelList.get(position);
         TextView textViewDestination=convertView.findViewById(R.id.adapterDestinationTravel);
         textViewDestination.setText(travel.getDestination());
         TextView textViewDepartureDate=convertView.findViewById(R.id.adapterDepartureDate);
         textViewDepartureDate.setText(travel.getDepartureDate());
         TextView textViewArrivalDate=convertView.findViewById(R.id.adapterArrivalDate);
         textViewArrivalDate.setText(travel.getArrivalDate());
         ImageView imageView=convertView.findViewById(R.id.imageTypeTravel);
            switch (travel.getType()) {
            case "Business Travel":
                imageView.setImageResource(R.drawable.briefcase__1_);
                return convertView;
            case "Family Vacation":
                imageView.setImageResource(R.drawable.family);
                return convertView;
            case "Solo Travel":
                imageView.setImageResource(R.drawable.hiker);
                return convertView;
            case "Group Travel":
                imageView.setImageResource(R.drawable.excursion);

                return convertView;
            case "Student Exchange Program":
                imageView.setImageResource(R.drawable.student);
                return convertView;
        }
         return convertView;
    }
}
