package com.example.project1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.project1.ManipulationMenu;
import com.example.project1.R;

public class MyTrips extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        ManipulationMenu manipulationMenu=new ManipulationMenu(getApplicationContext(),drawerLayout);
        manipulationMenu.addUsernameInMenu();
        manipulationMenu.operateMenu();
    }
}