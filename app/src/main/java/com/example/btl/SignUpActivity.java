package com.example.btl;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private Button btnLogin, btnSignup;
    private AccountRepository accountRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        accountRepository = new AccountRepository(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btn3);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email_edt = findViewById(R.id.email);
                EditText pw1_edt = findViewById(R.id.password);
                EditText pw2_edt = findViewById(R.id.password2);

                if (pw1_edt.getText().toString().equals(pw2_edt.getText().toString())) {
                    if (Account.isValidEmail(email_edt.getText().toString()) && Account.isValidPassword(pw1_edt.getText().toString())) {
                        Account account = new Account(email_edt.getText().toString(), pw1_edt.getText().toString());
                        accountRepository.addAccount(email_edt.getText().toString(),pw1_edt.getText().toString());
                        Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
