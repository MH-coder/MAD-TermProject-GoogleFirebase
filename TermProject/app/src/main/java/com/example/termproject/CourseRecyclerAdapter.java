package com.example.termproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.courseRecyclerHolder> {

    Context context;
    List<Course> courseList;
    public CourseRecyclerAdapter(List<Course> cl){
        courseList=cl;
    }

    @NonNull
    @Override
    public courseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.course_list_layout, parent, false);

        CourseRecyclerAdapter.courseRecyclerHolder courseRecyclerHolder = new CourseRecyclerAdapter.courseRecyclerHolder(view);
        return courseRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRecyclerAdapter.courseRecyclerHolder holder, int position) {
        holder.tvCourseItem.setText(courseList.get(position).toString());

        holder.btnCourseView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CourseRUD.class);

                String cc = courseList.get(position).getCourseCode();
                String ct = courseList.get(position).getCourseTitle();

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("ID", cc);
                bundle.putString("courseTitle", ct);

                //Add the bundle to the intent
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class courseRecyclerHolder extends RecyclerView.ViewHolder {
        EditText tvCourseItem;
        Button btnCourseView;
        public courseRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseItem = itemView.findViewById(R.id.tvCourseItem);
            btnCourseView = itemView.findViewById(R.id.btnCourseView);
        }
    }
}
