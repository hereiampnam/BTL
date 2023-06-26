package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.Account.Account;
import com.example.btl.Account.AccountRepository;
import com.example.btl.Todo.TaskList;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnSignup;
    private EditText emailEdt, pwEdt;
    private AccountRepository accountRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountRepository = new AccountRepository(this);

        btnLogin = findViewById(R.id.btn3);
        btnSignup = findViewById(R.id.btnSignup);
        emailEdt = findViewById(R.id.email);
        pwEdt = findViewById(R.id.password);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdt.getText().toString();
                String password = pwEdt.getText().toString();

                if (Account.isValidEmail(email) && Account.isValidPassword(password)) {
                    Account account = new Account(email, password);

                    if (!accountRepository.checkExistAccount(account.getEmail())) {
                        Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, TaskList.class);
                        startActivity(intent);
                    }
                } else {
                    if (!Account.isValidPassword(password)) {
                        Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                    if (!Account.isValidEmail(email)) {
                        Toast.makeText(MainActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
