package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TimePicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SessionRegistrationForm extends AppCompatActivity {

    List<Session> sessions = new ArrayList<>();

    DBHelper dbHelper = new DBHelper(SessionRegistrationForm.this);

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    EditText etSTitle;
    EditText etSDesc;
    EditText etSId;
    //Spinner spnSectionList;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_registration_form);

        // References
        etSId = findViewById(R.id.etSId);
        etSTitle = findViewById(R.id.etSTitle);
        etSDesc = findViewById(R.id.etSDesc);

        btnDatePicker = findViewById(R.id.btn_date);
        txtDate = findViewById(R.id.in_date);

        btnTimePicker = findViewById(R.id.btn_time);
        txtTime = findViewById(R.id.in_time);

        btnAdd = findViewById(R.id.btnAdd);

        //Section List
        /*String[] sectionList = {"A", "B", "C"};
        ArrayAdapter sectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, sectionList);
        spnSectionList.setAdapter(sectionAdapter);*/

        // Date
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SessionRegistrationForm.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // Time
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(SessionRegistrationForm.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etSTitle.getText().toString().isEmpty() && !etSDesc.getText().toString().isEmpty() && !txtDate.getText().toString().isEmpty() && !txtTime.getText().toString().isEmpty()) {
                    /*dbHelper.insertSession(etSId.getText().toString(), etSTitle.getText().toString(), etSDesc.getText().toString(),
                            txtDate.getText().toString(), txtTime.getText().toString());*/


                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Session");

                    Session session = new Session(etSId.getText().toString(), etSTitle.getText().toString(), etSDesc.getText().toString(),
                            txtDate.getText().toString(), txtTime.getText().toString());

                    myRef.child(etSId.getText().toString()).setValue(session);
                    Toast toast=Toast.makeText(getApplicationContext(),"Session Successfully Added!",Toast.LENGTH_LONG);
                    toast.show();
                    finish();


                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Fill All Fields!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
