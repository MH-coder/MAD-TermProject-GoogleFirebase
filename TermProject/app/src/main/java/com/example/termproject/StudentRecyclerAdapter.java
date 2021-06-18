package com.example.termproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.studentRecyclerHolder>{
    Context context;
    List<Student> studentList;
    public StudentRecyclerAdapter(List<Student> sl){
        studentList=sl;
    }

    @NonNull
    @Override
    public StudentRecyclerAdapter.studentRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.student_list_layout, parent, false);

        StudentRecyclerAdapter.studentRecyclerHolder studentRecyclerHolder = new StudentRecyclerAdapter.studentRecyclerHolder(view);
        return studentRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRecyclerAdapter.studentRecyclerHolder holder, int position) {
        holder.tvStudentItem.setText(studentList.get(position).toString());

        holder.btnStdView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, StudentRUD.class);

                String ID = studentList.get(position).getRollNo();
                String name = studentList.get(position).getName();
                String fName = studentList.get(position).getFatherName();
                String email = studentList.get(position).getEmail();
                String ph = studentList.get(position).getPhone();

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("ID", ID);
                bundle.putString("name", name);
                bundle.putString("fName", fName);
                bundle.putString("email", email);
                bundle.putString("ph", ph);

                //Add the bundle to the intent
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class studentRecyclerHolder extends RecyclerView.ViewHolder {
        EditText tvStudentItem;
        Button btnStdView;
        public studentRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentItem = itemView.findViewById(R.id.tvStudentItem);
            btnStdView = itemView.findViewById(R.id.btnStdView);
        }
    }
}
