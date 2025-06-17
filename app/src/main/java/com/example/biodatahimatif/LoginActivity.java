package com.example.biodatahimatif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private Button tmblLogin;
    private TextView tvRegister;
    private DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi Database Helper
        dbHelper = new DataHelper(this);

        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        tmblLogin = findViewById(R.id.btnLogin);

        // Inisialisasi TextView untuk register
        tvRegister = findViewById(R.id.id_register);

        tmblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUser = username.getText().toString().trim();
                String inputPass = password.getText().toString().trim();

                if (inputUser.isEmpty() || inputPass.isEmpty()) {
                    showErrorLogin("Username dan Password tidak boleh kosong");
                } else {
                    // Validasi kredensial dari database
                    if (validateCredentials(inputUser, inputPass)) {
                        successLogin();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish(); // Tutup activity login setelah sukses
                    } else {
                        errorLogin("Username atau Password salah");
                    }
                }
            }
        });

        // Tambahkan listener untuk TextView Register
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke halaman registrasi
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    // Method untuk validasi kredensial dari database
    private boolean validateCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query untuk memeriksa kredensial
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    public void showErrorLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void successLogin() {
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
    }

    public void errorLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}