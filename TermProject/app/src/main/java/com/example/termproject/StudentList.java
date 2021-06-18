package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentList extends AppCompatActivity {

    /*StudentAdapter studentAdapter;
    ListView lvStudentList;*/

    //
    RecyclerView rvStudentList;
    RecyclerView.LayoutManager layoutManager;
    List<Student> StudentList = new ArrayList<>();
    StudentRecyclerAdapter studentRecyclerAdapter;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        /*lvStudentList = findViewById(R.id.lvStudentList);
        studentAdapter = new StudentAdapter(StudentList);
        lvStudentList.setAdapter(studentAdapter);*/

        //
        rvStudentList = findViewById(R.id.rvStudentList);

        layoutManager = new LinearLayoutManager(this);
        rvStudentList.setLayoutManager(layoutManager);

        studentRecyclerAdapter = new StudentRecyclerAdapter(StudentList);
        rvStudentList.setAdapter(studentRecyclerAdapter);
        //

        /*Cursor cursor = new DBHelper(this).getAllStdData();
        while (cursor.moveToNext()){
            StudentList.add(new Student(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
        studentRecyclerAdapter.notifyDataSetChanged();*/



        //
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Student");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //
                for (DataSnapshot snap : snapshot.getChildren()) {

                    String sRoll = snap.child("rollNo").getValue(String.class);
                    String sName = snap.child("name").getValue(String.class);
                    String sFName = snap.child("fatherName").getValue(String.class);
                    String sEmail = snap.child("email").getValue(String.class);
                    String sPh = snap.child("phone").getValue(String.class);

                    StudentList.add(new Student(sRoll, sName, sFName, sEmail, sPh));

                }
                studentRecyclerAdapter.notifyDataSetChanged();
                //

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        //

        Button btnRegisterStudent = findViewById(R.id.btnRegisterStudent);

        btnRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerStudentIntent = new Intent(StudentList.this, StudentRegistrationForm.class);
                startActivity(registerStudentIntent);
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
