package com.example.therapyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;





public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Burada kullanıcı adı ve şifreyi kontrol edin
                if (checkCredentials(username, password)) {
                    // Giriş başarılı ise grup terapisi kısmına geçiş yap
                    Intent intent = new Intent(LoginActivity.this, GroupTherapyActivity.class);
                    startActivity(intent);
                    finish(); // Giriş ekranını kapat
                } else {
                    // Giriş başarısız ise kullanıcıyı bilgilendir
                    Toast.makeText(LoginActivity.this, "Geçersiz kullanıcı adı veya şifre", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView registerTextView = findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Kullanıcı giriş bilgilerini kontrol etmek için bir metot
    private boolean checkCredentials(String username, String password) {
        // Burada kullanıcı adı ve şifreyi kontrol edin, örnek olarak basit bir doğrulama yapıyoruz
        return username.equals("kullanici") && password.equals("sifre");
    }
}
