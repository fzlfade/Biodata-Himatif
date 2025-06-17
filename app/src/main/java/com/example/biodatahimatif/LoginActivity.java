package com.example.biodatahimatif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private Button tmblLogin, tmblLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        tmblLogin = (Button) findViewById(R.id.btnLogin);
//        tmblLogout = (Button) findViewById(R.id.btnLogout);
//
//        tmblLogout.setEnabled(false);

        tmblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = username.getText().toString();
                String pass = password.getText().toString();
                if (nama.isEmpty() || pass.isEmpty()){
                    showErrorLogin();
                } else {
                    if (nama.equals("admin") && pass.equals("admin")){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        successLogin();
                    } else {
                        errorLogin();
                    }
                }
            }
        });
    }
    public void showErrorLogin(){
        Toast.makeText(this, "Username dan Password Tidak Boleh Kosong",
                Toast.LENGTH_SHORT).show();
    }
    public void successLogin(){
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
    }
    public void errorLogin(){
        Toast.makeText(this, "Username atau Password Salah",
                Toast.LENGTH_SHORT).show();
    }
}