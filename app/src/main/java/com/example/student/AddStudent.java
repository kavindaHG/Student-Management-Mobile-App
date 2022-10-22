package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudent extends AppCompatActivity {

    EditText AddName,AddCourse,AddEmail;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        AddName = findViewById(R.id.AddName);
        AddCourse = findViewById(R.id.AddCourse);
        AddEmail = findViewById(R.id.AddEmail);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddStudent.this);
                myDB.addStudent(AddName.getText().toString().trim(),
                                AddCourse.getText().toString().trim(),
                                AddEmail.getText().toString().trim());
                Intent intent = new Intent(AddStudent.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}