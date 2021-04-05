package com.example.project1.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.ManipulationMenu;
import com.example.project1.R;
import com.example.project1.adapters.TravelAdapter;
import com.example.project1.forms.AddTrip;
import com.example.project1.models.Travel;

import java.util.ArrayList;

public class Europa extends AppCompatActivity {

    private ArrayList<Travel> travelArrayList=new ArrayList<>();
    private int addTravel=1;
    private TravelAdapter travelAdapter;
    ListView listView;
    ImageView imageViewNoTravels;
    TextView textViewOops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_europa);
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        ManipulationMenu manipulationMenu=new ManipulationMenu(getApplicationContext(),drawerLayout);
        manipulationMenu.addUsernameInMenu();
        manipulationMenu.operateMenu();

        Button btnAddTrip=findViewById(R.id.btnAddTrip);
        btnAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Europa.this, AddTrip.class);
                intent.putExtra("NameContinent","Europa");
                startActivityForResult(intent,addTravel);
            }
        });
        listView=findViewById(R.id.listViewTravel);
         imageViewNoTravels=findViewById(R.id.imgOops);
         textViewOops=findViewById(R.id.txtOops);

        if(travelArrayList.size()!=0){
            imageViewNoTravels.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            textViewOops.setVisibility(View.GONE);
        }
        travelAdapter=new TravelAdapter(getApplicationContext(),travelArrayList,R.layout.travel_adapter);
        listView.setAdapter(travelAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(addTravel==requestCode){
            if(resultCode==RESULT_OK){
                if(data.hasExtra("Travel")){
                    Travel travel= (Travel) data.getExtras().get("Travel");
                    Toast.makeText(Europa.this,travel.toString(),Toast.LENGTH_LONG).show();
                    travelArrayList.add(travel);
                    ListView listView=findViewById(R.id.listViewTravel);
                    ImageView imageViewNoTravels=findViewById(R.id.imgOops);

                        imageViewNoTravels.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        textViewOops.setVisibility(View.GONE);

                    travelAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}