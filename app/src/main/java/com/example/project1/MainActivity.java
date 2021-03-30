package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.project1.authentification.LogIn;
import com.example.project1.authentification.SignUp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogIn=findViewById(R.id.btnLogIn);
        btnLogIn.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_anim));
        btnLogIn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LogIn.class));

        });
        Button btnSignUp=findViewById(R.id.btnSignUp);
        btnSignUp.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_anim));
        btnSignUp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SignUp.class));



        });


    }


}