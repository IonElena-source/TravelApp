package com.example.project1.models;

import android.net.Uri;
import android.text.BoringLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class Travel implements Serializable {
    private String destination;
    private String departureDate;
    private String arrivalDate;
    private String description;
    private String type;
    private ArrayList<String> imageList;
    private float note;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Travel(String destination, String departureDate, String arrivalDate, String description, String type, ArrayList<String> imageList, float note) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.description = description;
        this.type = type;
        this.imageList = imageList;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "destination='" + destination + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", imageList=" + imageList +
                ", note=" + note +
                '}';
    }
}
