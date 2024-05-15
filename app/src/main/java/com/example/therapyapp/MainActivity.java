package com.example.therapyapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kullanıcı daha önceden giriş yaptı mı kontrol et
        boolean userLoggedIn = checkIfUserLoggedIn();

        // Eğer kullanıcı daha önceden giriş yapmışsa, doğrudan ana ekranı aç
        if (userLoggedIn) {
            // Giriş işlemi başarılıysa, grup terapisi ekranını aç
            startGroupTherapyActivity();
        } else {
            // Giriş işlemi başarısızsa veya kullanıcı daha önceden giriş yapmamışsa, giriş ekranını aç
            startLoginActivity();
        }
    }

    // Kullanıcı daha önceden giriş yaptı mı kontrol eden metot
    private boolean checkIfUserLoggedIn() {
        // Burada kullanıcı giriş durumunu kontrol edin ve sonucu döndürün
        // Örneğin, bir oturum yöneticisi (session manager) kullanarak kontrol edebilirsiniz
        return false; // Örnek olarak her zaman false dönüyoruz, siz bu kontrolü kendi gereksinimlerinize göre yapmalısınız
    }

    // Giriş ekranını açan metot
    private void startLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // MainActivity'yi kapat
    }

    // Grup terapisi ekranını açan metot
    private void startGroupTherapyActivity() {
        Intent intent = new Intent(MainActivity.this, GroupTherapyActivity.class);
        startActivity(intent);
        finish(); // MainActivity'yi kapat
    }
}