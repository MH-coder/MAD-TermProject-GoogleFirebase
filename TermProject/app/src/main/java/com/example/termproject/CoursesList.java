package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CoursesList extends AppCompatActivity {

   /* CourseAdapter courseAdapter;
    ListView lvCourseList;*/

    //
    RecyclerView rvCourseList;
    RecyclerView.LayoutManager layoutManager;
    List<Course> CourseList = new ArrayList<>();
    CourseRecyclerAdapter courseRecyclerAdapter;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        //
        rvCourseList = findViewById(R.id.rvCourseList);

        layoutManager = new LinearLayoutManager(this);
        rvCourseList.setLayoutManager(layoutManager);

        courseRecyclerAdapter = new CourseRecyclerAdapter(CourseList);
        rvCourseList.setAdapter(courseRecyclerAdapter);
        //

        //lvCourseList = findViewById(R.id.lvCourseList);
        //courseAdapter = new CourseAdapter(CourseList);
        //lvCourseList.setAdapter(courseAdapter);

        /*Cursor cursor = new DBHelper(this).getAllCourseData();
        while (cursor.moveToNext()){
            CourseList.add(new Course(cursor.getString(0),cursor.getString(1)));
        }
        courseRecyclerAdapter.notifyDataSetChanged();*/

        //
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Courses");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //
                for (DataSnapshot snap : snapshot.getChildren()) {

                    String cc = snap.child("courseCode").getValue(String.class);
                    String ct = snap.child("courseTitle").getValue(String.class);

                    CourseList.add(new Course(cc, ct));

                }
                courseRecyclerAdapter.notifyDataSetChanged();
                //

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        //

        Button btnAddCourse = findViewById(R.id.btnAddCourse);

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCourseIntent = new Intent(CoursesList.this, CourseRegistrationForm.class);
                startActivity(addCourseIntent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
