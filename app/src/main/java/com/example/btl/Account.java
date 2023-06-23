package com.example.btl;
import android.text.TextUtils;

public class Account {
    private String email;
    private String password;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static boolean isValidEmail(String email) {
        // Add your email validation logic here
        return !TextUtils.isEmpty(email);
    }

    public static boolean isValidPassword(String password) {
        // Add your password validation logic here
        return !TextUtils.isEmpty(password);
    }
}

