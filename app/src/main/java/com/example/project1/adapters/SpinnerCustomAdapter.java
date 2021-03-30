package com.example.project1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project1.R;

import java.util.ArrayList;

public class SpinnerCustomAdapter extends ArrayAdapter<String> {
    private ArrayList<String> types;
    private Context context;
    private int resource;

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public SpinnerCustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public SpinnerCustomAdapter(Context context,ArrayList<String> types,int resource){
        super(context,resource);
        this.types=types;
        this.context=context;
        this.resource=resource;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return types.get(position);
    }

    @Override
    public int getCount() {
        return types.size();
    }

    public View initView(int position, View convertView, ViewGroup parent){
        String typeTravel = getItem(position);

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        
        ImageView imageViewTypeTravel = convertView.findViewById(R.id.imgTypeTravel);
        TextView textViewTypeTravel = convertView.findViewById(R.id.txtType);
        textViewTypeTravel.setText(typeTravel);
        switch (typeTravel) {
            case "Business Travel":
                imageViewTypeTravel.setImageResource(R.drawable.business_trip);
                return convertView;
            case "Family Vacation":
                imageViewTypeTravel.setImageResource(R.drawable.family);
                return convertView;
            case "Solo Travel":
                imageViewTypeTravel.setImageResource(R.drawable.traveler);
                return convertView;
            case "Group Travel":
                imageViewTypeTravel.setImageResource(R.drawable.excursion);
                return convertView;
            case "Student Exchange Program":
                imageViewTypeTravel.setImageResource(R.drawable.student);
                return convertView;
        }
        return convertView;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
}
