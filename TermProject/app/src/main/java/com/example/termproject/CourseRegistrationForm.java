package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationForm extends AppCompatActivity {

    List<Course> Courses = new ArrayList<>();

    //DBHelper dbHelper = new DBHelper(CourseRegistrationForm.this);

    EditText etCourseCode;
    EditText etCourseTitle;

    Button btnCourseRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration_form);

        // References
        etCourseCode = findViewById(R.id.etCourseCode);
        etCourseTitle = findViewById(R.id.etCourseTitle);

        btnCourseRegister =findViewById(R.id.btnCourseRegister);

        btnCourseRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etCourseCode.getText().toString().isEmpty() && !etCourseTitle.getText().toString().isEmpty()){

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Courses");

                    Course course = new Course(etCourseCode.getText().toString(), etCourseTitle.getText().toString());

                    myRef.child(etCourseCode.getText().toString()).setValue(course);
                    Toast toast=Toast.makeText(getApplicationContext(),"Course Successfully Registered!",Toast.LENGTH_LONG);
                    toast.show();
                    finish();

                    /*dbHelper.insertCourse(etCourseCode.getText().toString() ,etCourseTitle.getText().toString());
                    finish();*/
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Fill All Fields!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
