package com.example.student;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btn_add, btn_exit;

    MyDatabaseHelper myDB;
    ArrayList<String> StID, StName, StCourse, StEmail;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btn_add = findViewById(R.id.btn_add);
        btn_exit = findViewById(R.id.btn_exit);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        StID = new ArrayList<>();
        StName = new ArrayList<>();
        StCourse = new ArrayList<>();
        StEmail = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(MainActivity.this,this, StID,StName,StCourse,StEmail);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray(){
        Cursor cursor = myDB.reaAllData();
        if (cursor.getCount()==0) {
            Toast.makeText(this, "No Data is Available", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                StID.add(cursor.getString(0));
                StName.add(cursor.getString(1));
                StCourse.add(cursor.getString(2));
                StEmail.add(cursor.getString(3));

            }
        }
        }
    }
