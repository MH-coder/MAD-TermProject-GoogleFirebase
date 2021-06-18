package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseRUD extends AppCompatActivity {

    //DBHelper dbHelper = new DBHelper(CourseRUD.this);

    EditText etUCourseCode;
    EditText etUCourseTitle;

    Button btnCourseUpdate, btnCourseDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_rud);

        /*Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");*/

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String ID = bundle.getString("ID");
        String courseTitle = bundle.getString("courseTitle");


        // References
        etUCourseCode = findViewById(R.id.etUCourseCode);
        etUCourseTitle = findViewById(R.id.etUCourseTitle);

        btnCourseUpdate = findViewById(R.id.btnCourseUpdate);
        btnCourseDelete = findViewById(R.id.btnCourseDelete);

        /*Cursor cursor = new DBHelper(this).getAllCourseData();

        while (cursor.moveToNext()){

            if(ID.equals(cursor.getString(0))){
                etUCourseCode.setText(cursor.getString(0));
                etUCourseTitle.setText(cursor.getString(1));
            }

        }*/

        //
        etUCourseCode.setText(ID);
        etUCourseTitle.setText(courseTitle);

        etUCourseCode.setEnabled(false);
        //

        btnCourseUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*dbHelper.updateCourse(ID, etUCourseCode.getText().toString(),etUCourseTitle.getText().toString());
                finish();*/

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Courses");

                if(!ID.equals(etUCourseCode.getText().toString()) || !courseTitle.equals(etUCourseTitle.getText().toString())){

                    myRef.child(ID).child("courseCode").setValue(etUCourseCode.getText().toString());
                    myRef.child(ID).child("courseTitle").setValue(etUCourseTitle.getText().toString());
                }

                finish();
            }
        });

        btnCourseDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dbHelper.deleteCourse(ID);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Courses");

                myRef.child(ID).removeValue();
                finish();
            }
        });
    }


}
