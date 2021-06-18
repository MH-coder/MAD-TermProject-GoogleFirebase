package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // References
        ImageView SessionsLogo = findViewById(R.id.SessionsLogo);
        TextView tvSession = findViewById(R.id.tvSession);

        ImageView StudentLogo = findViewById(R.id.studentLogo);
        TextView tvStudent = findViewById(R.id.tvStudent);

        ImageView CourseLogo = findViewById(R.id.CourseLogo);
        TextView tvCourses = findViewById(R.id.tvCourses);

        ImageView SectionLogo = findViewById(R.id.SectionLogo);
        TextView tvSection = findViewById(R.id.tvSection);

        // Opening Sessions Activity by clicking on Logo
        SessionsLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SessionIntent = new Intent(MainActivity.this, SessionList.class);
                startActivity(SessionIntent);
            }
        });

        // Opening Sessions Activity by clicking on text
        tvSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SessionIntent = new Intent(MainActivity.this, SessionList.class);
                startActivity(SessionIntent);
            }
        });

        // Opening Students Activity by clicking on Logo
        StudentLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudentIntent = new Intent(MainActivity.this, StudentList.class);
                startActivity(StudentIntent);
            }
        });

        // Opening Student Activity by clicking on text
        tvStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudentIntent = new Intent(MainActivity.this, StudentList.class);
                startActivity(StudentIntent);
            }
        });

        // Opening Courses Activity by clicking on Logo
        CourseLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CoursesIntent = new Intent(MainActivity.this, CoursesList.class);
                startActivity(CoursesIntent);
            }
        });

        // Opening Courses Activity by clicking on text
        tvCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CoursesIntent = new Intent(MainActivity.this, CoursesList.class);
                startActivity(CoursesIntent);
            }
        });

        // Opening Section Activity by clicking on Logo
        SectionLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SectionIntent = new Intent(MainActivity.this, SectionList.class);
                startActivity(SectionIntent);
            }
        });

        // Opening Section Activity by clicking on text
        tvSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SectionIntent = new Intent(MainActivity.this, SectionList.class);
                startActivity(SectionIntent);
            }
        });

    }
}
