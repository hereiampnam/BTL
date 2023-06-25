package com.example.btl.Account;



import java.util.regex.Pattern;

public class Account {
    private String email;
    private String password;
    private static String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isValidEmail(String email) {
//        Pattern emailPt = Pattern.compile(emailRegex);
//        return emailPt.matcher(email).matches();
        return true;//for test only
    }

    public static boolean isValidPassword(String password) {
//        return password.length() >= 6;
        return true; //for test only
    }
}
