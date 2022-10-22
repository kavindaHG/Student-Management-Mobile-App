package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {

    EditText upName,upCourse,upEmail;
    Button btnUpdate, btnDelete;

    String id,name,course,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        upName = findViewById(R.id.upName);
        upCourse = findViewById(R.id.upCourse);
        upEmail = findViewById(R.id.upEmail);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);


        //getset Data first **important
        getAndSetIntentData();
        ActionBar ab=getSupportActionBar();
        if (ab !=null) {
            ab.setTitle(name);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //then update method
                MyDatabaseHelper myDB = new MyDatabaseHelper(updateActivity.this);
                myDB.updateData(id, name, course, email);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                    getIntent().hasExtra("course") && getIntent().hasExtra("email")){
                    //getting data to the update fields
                    id = getIntent().getStringExtra("id");
                    name = getIntent().getStringExtra("name");
                    course = getIntent().getStringExtra("course");
                    email = getIntent().getStringExtra("email");

                    //setting data
                    upName.setText(name);
                    upCourse.setText(course);
                    upEmail.setText(email);


        }else {
            Toast.makeText(this, "No data Selected", Toast.LENGTH_SHORT).show();
            }
        }

        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete " + name + " ? ");
            builder.setMessage("Are You Sure you want to delete" + name + " ?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyDatabaseHelper myDB =  new MyDatabaseHelper(updateActivity.this);
                    myDB.deleteOneRow(id);
                    finish();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }
}
