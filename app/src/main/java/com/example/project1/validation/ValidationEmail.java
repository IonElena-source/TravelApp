package com.example.project1.validation;
import android.util.Patterns;
public class ValidationEmail {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ValidationEmail(String email) {
        this.email = email;
    }

    public boolean isValid()
    {
        return  Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
