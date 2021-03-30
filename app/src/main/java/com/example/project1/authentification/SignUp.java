package com.example.project1.authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.validation.ValidationEmail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReferenceUsers;
    private boolean isUsernameUnique=true;
    private boolean isEmailUsed=false;
    private String email;
    private String username;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReferenceUsers=firebaseDatabase.getReference("users");
        Button btnSignUp=findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        TextView tvExistentAccount=findViewById(R.id.txtAlreadyHaveAccount);
        tvExistentAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,LogIn.class);
                startActivity(intent);
            }
        });


    }



    public boolean validateFormRegister() {

        boolean isUserFormRegisterValid=true;
        EditText editTextEmail = findViewById(R.id.etEmail);
        setEmail(editTextEmail.getText().toString().trim());

        EditText editTextUsername = findViewById(R.id.etUsername);
        setUsername(editTextUsername.getText().toString().trim());

        EditText editTextPassword = findViewById(R.id.etPassword);
        setPassword(editTextPassword.getText().toString().trim());

        EditText editTextConfirmPassword = findViewById(R.id.etConfirmPassword);
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (email.isEmpty()) {
            String errorEmailEmpty = "Email required";
            editTextEmail.setError(errorEmailEmpty);
            isUserFormRegisterValid= false;

        }
        ValidationEmail validationEmail = new ValidationEmail(email);
        if (!validationEmail.isValid()) {
            editTextEmail.setError("Invalid email adress");
            isUserFormRegisterValid= false;

        }
        if (username.isEmpty()) {
            editTextUsername.setError("Username required");
            isUserFormRegisterValid= false;

        }
        if (password.length() < 8 || password.length() > 30) {
            editTextPassword.setError("Your password must be between 8 and 30 characters.");
            isUserFormRegisterValid= false;

        }
        if (!confirmPassword.equals(password)) {
            editTextConfirmPassword.setError("Password doesn't match");
            isUserFormRegisterValid= false;


        }

        List<String> usernamesExistent=new ArrayList<>();
        List<String> existentEmails=new ArrayList<>();
        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User userExistent = dataSnapshot.getValue(User.class);
                    usernamesExistent.add(userExistent.getUsername());
                    existentEmails.add(userExistent.getEmail());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(usernamesExistent.contains(username))
        {
            editTextUsername.setError("Username already taken");
            Toast.makeText(SignUp.this,"Username already taken",Toast.LENGTH_LONG).show();

            isUserFormRegisterValid=false;
        }
        else
        {
            if(existentEmails.contains(email))
            {
                editTextEmail.setError("Email is already used for an another account");
                isUserFormRegisterValid=false;
            }
        }
        return  isUserFormRegisterValid;
    }
    public void registerUser() {
        if (validateFormRegister()) {

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        currentUser=firebaseAuth.getCurrentUser();
                        User user=new User(currentUser.getUid(),username,email,password);
                        databaseReferenceUsers.child(user.getuID()).setValue(user);
                        currentUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid)
                            {
                                Toast.makeText(SignUp.this,"Email sent, verify your inbox",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            });

        }

    }

}