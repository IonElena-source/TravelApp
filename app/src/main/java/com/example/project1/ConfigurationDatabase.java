package com.example.project1;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigurationDatabase {
    private  FirebaseDatabase firebaseDatabase;
    private  FirebaseUser firebaseUser;
    private  FirebaseAuth firebaseAuth;

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public void setFirebaseDatabase(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }



    private static ConfigurationDatabase configurationDatabase=null;
    private ConfigurationDatabase() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
    }
    public static ConfigurationDatabase  getInstance(){
        if(configurationDatabase==null){
            configurationDatabase=new ConfigurationDatabase();
        }
        return configurationDatabase;
    }
}
