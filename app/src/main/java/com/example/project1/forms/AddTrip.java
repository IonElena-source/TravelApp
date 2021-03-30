package com.example.project1.forms;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.adapters.SpinnerCustomAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class AddTrip extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ArrayList<String> types=new ArrayList<>();
    private ArrayList<Uri> imageList=new ArrayList<>();
    private Uri imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        types.add("Business Travel");
        types.add("Family Vacation");
        types.add("Solo Travel");
        types.add("Group Travel");
        types.add("Student Exchange Program");
        SpinnerCustomAdapter spinnerCustomAdapter=new SpinnerCustomAdapter(AddTrip.this,types,R.layout.spinner_custom_adapter);
        Spinner spinnerTypeTravel=findViewById(R.id.spinnerTypeTravel);

        spinnerTypeTravel.setAdapter(spinnerCustomAdapter);
        EditText editTextArrivaldDate=findViewById(R.id.etArrivalDate);
        editTextArrivaldDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    editTextArrivaldDate.setAlpha(1);
                }
                else
                {
                    if(editTextArrivaldDate.getText().toString().trim().isEmpty()){
                        editTextArrivaldDate.setAlpha(0.5F);
                    }
                }
            }
        });

        TextView textViewLeaveDate=findViewById(R.id.etLeaveDate);
        TextView textViewArrivalDate=findViewById(R.id.etArrivalDate);

        ImageView pickDepartureDate=findViewById(R.id.pickLeaveDate);
        pickDepartureDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int monthSelected=month+1;
                        textViewLeaveDate.setText(dayOfMonth+"/"+monthSelected+"/"+year);
                    }
                }, Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                datePickerDialog.show();
            }
        });
        ImageView pickArrivalDate=findViewById(R.id.pickArriveDate);
        pickArrivalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int monthSelected=month+1;
                        textViewArrivalDate.setText(dayOfMonth+"/"+monthSelected+"/"+year);
                    }
                }, Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                datePickerDialog.show();
            }
        });


        //pick the photos

        Button btnChoose=findViewById(R.id.btnChoose);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(Intent.ACTION_PICK);
              intent.setType("image/*");
              intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
              startActivityForResult(intent,PICK_IMAGE);
            }
        });
        Button btnUpload=findViewById(R.id.btnUpload);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE){
            if(resultCode==RESULT_OK){
                if(data.getClipData()!=null){
                    int currentImage=0;
                    while (currentImage<data.getClipData().getItemCount()){
                        imageView=data.getClipData().getItemAt(currentImage).getUri();
                        imageList.add(imageView);
                        currentImage++;
                    }
                    TextView textViewCountPhotos=findViewById(R.id.countPhotos);
                    textViewCountPhotos.setText("You have selected "+imageList.size()+"photos");
                }
            }
        }
    }
}