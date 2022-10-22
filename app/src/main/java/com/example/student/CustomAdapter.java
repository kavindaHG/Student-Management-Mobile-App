package com.example.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    private ArrayList stId,stName,stCourse,stEmail;

    CustomAdapter(Activity activity,Context context, ArrayList stId, ArrayList stName, ArrayList stCourse, ArrayList stEmail){
        this.activity=activity;
        this.context = context;
        this.stId = stId;
        this.stName = stName;
        this.stCourse = stCourse;
        this.stEmail = stEmail;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.st_id_txt.setText(String.valueOf(stId.get(position)));
        holder.st_name_txt.setText(String.valueOf(stName.get(position)));
        holder.st_course_txt.setText(String.valueOf(stCourse.get(position)));
        holder.st_email_txt.setText(String.valueOf(stEmail.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updateActivity.class);
                intent.putExtra("id", String.valueOf(stId.get(position)));
                intent.putExtra("name", String.valueOf(stName.get(position)));
                intent.putExtra("course", String.valueOf(stCourse.get(position)));
                intent.putExtra("email", String.valueOf(stEmail.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView st_id_txt,st_name_txt,st_course_txt,st_email_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            st_id_txt=itemView.findViewById(R.id.st_id_txt);
            st_name_txt=itemView.findViewById(R.id.st_name_txt);
            st_course_txt=itemView.findViewById(R.id.st_course_txt);
            st_email_txt=itemView.findViewById(R.id.st_email_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
