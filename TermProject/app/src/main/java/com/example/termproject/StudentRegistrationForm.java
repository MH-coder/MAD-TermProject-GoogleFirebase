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

public class StudentRegistrationForm extends AppCompatActivity {

    List<Student> students = new ArrayList<>();

    DBHelper dbHelper = new DBHelper(StudentRegistrationForm.this);

    EditText etStdRollNo;
    EditText etStdName;
    EditText etStdFName;
    EditText etStdEmail;
    EditText etStdPh;

    Button btnStdRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_form);

        // References
        etStdRollNo = findViewById(R.id.etStdRollNo);
        etStdName = findViewById(R.id.etStdName);
        etStdFName = findViewById(R.id.etStdFName);
        etStdEmail = findViewById(R.id.etStdEmail);
        etStdPh = findViewById(R.id.etStdPh);

        btnStdRegister =findViewById(R.id.btnStdRegister);

        btnStdRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etStdRollNo.getText().toString().isEmpty() && !etStdName.getText().toString().isEmpty() && !etStdFName.getText().toString().isEmpty() && !etStdEmail.getText().toString().isEmpty() && !etStdPh.getText().toString().isEmpty()){

                    /*dbHelper.insertStudent(etStdRollNo.getText().toString() ,etStdName.getText().toString(), etStdFName.getText().toString(),
                            etStdEmail.getText().toString(), etStdPh.getText().toString());

                    finish();*/

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Student");

                    Student student = new Student(etStdRollNo.getText().toString(), etStdName.getText().toString(), etStdFName.getText().toString(),
                            etStdEmail.getText().toString(), etStdPh.getText().toString());

                    myRef.child(etStdRollNo.getText().toString()).setValue(student);
                    Toast toast=Toast.makeText(getApplicationContext(),"Student Successfully Registered!",Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Fill All Fields!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
