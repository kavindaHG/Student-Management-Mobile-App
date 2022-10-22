package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    TextView txt_uname, txt_pwd;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_uname = findViewById(R.id.txt_uname);
        txt_pwd = findViewById(R.id.txt_pwd);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_uname.getText().toString().equals("Admin") && txt_pwd.getText().toString().equals("Admin")){
                    Toast.makeText(login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(login.this, "Check Your Credentials", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}