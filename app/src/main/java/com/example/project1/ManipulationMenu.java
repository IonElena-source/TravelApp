package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project1.activities.Favorites;
import com.example.project1.activities.MyTrips;
import com.example.project1.activities.Settings;
import com.example.project1.activities.Statistics;
import com.example.project1.activities.Upcoming;
import com.example.project1.activities.Wishlist;
import com.example.project1.authentification.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ManipulationMenu {
    private Context context;
    private DrawerLayout drawerLayout;
    private ConfigurationDatabase configurationDatabase;
    User currentUser;
    public ManipulationMenu(Context context, DrawerLayout drawerLayout)
    {
        this.context=context;
        this.drawerLayout=drawerLayout;
    }
    public void clickMyTrips()
    {
        Intent intent=new Intent(context, MyTrips.class);
        context.startActivity(intent);

    }
    public void clickUpcoming(){
        Intent intent=new Intent(context, Upcoming.class);
        context.startActivity(intent);
    }
    public void clickFavorites()
    {
        Intent intent=new Intent(context, Favorites.class);
        context.startActivity(intent);
    }
    public void clickStatistics(){
        Intent intent=new Intent(context, Statistics.class);
        context.startActivity(intent);
    }
    public void clickSettings()
    {
        Intent intent=new Intent(context, Settings.class);
        context.startActivity(intent);
    }
    public void clickWishlist(){
        Intent intent=new Intent(context, Wishlist.class);
        context.startActivity(intent);
    }
    public void clickLogOut(){
        Intent intent=new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    public void addUsernameInMenu(){
        TextView textViewUsername=drawerLayout.findViewById(R.id.tvUsername);
        ConfigurationDatabase configurationDatabase=ConfigurationDatabase.getInstance();
        String uidFirebase=configurationDatabase.getFirebaseUser().getUid();

        configurationDatabase.getFirebaseDatabase().getReference("users").child(uidFirebase).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentUser=snapshot.getValue(User.class);
                textViewUsername.setText("Welcome, "+currentUser.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
    public void  clickMenu(){
        drawerLayout.openDrawer(GravityCompat.START);

    }
    public void operateMenu()
    {
        drawerLayout.findViewById(R.id.menuSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSettings();
            }
        });
        drawerLayout.findViewById(R.id.menuLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogOut();
            }
        });
        drawerLayout.findViewById(R.id.menuMyTrips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMyTrips();
            }
        });
        drawerLayout.findViewById(R.id.menuUpcoming).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUpcoming();
            }
        });
        drawerLayout.findViewById(R.id.menuStatistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickStatistics();
            }
        });
        drawerLayout.findViewById(R.id.menuWishlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickWishlist();
            }
        });
        drawerLayout.findViewById(R.id.headerId).findViewById(R.id.imgMenu)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickMenu();
                    }
                });

    }
}
