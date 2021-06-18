package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class SessionRUD extends AppCompatActivity {

    //DBHelper dbHelper = new DBHelper(SessionRUD.this);

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    EditText etUSTitle;
    EditText etUSDesc;
    EditText etUSId;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_rud);

        //Intent intent = getIntent();
        //String ID = intent.getStringExtra("ID");

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String ID = bundle.getString("ID");
        String title = bundle.getString("title");
        String desc = bundle.getString("desc");
        String date = bundle.getString("date");
        String time = bundle.getString("time");

        Log.i("Result after", ID);

        // References
        etUSId = findViewById(R.id.etUSId);
        etUSTitle = findViewById(R.id.etUSTitle);
        etUSDesc = findViewById(R.id.etUSDesc);

        btnDatePicker = findViewById(R.id.btn_date);
        txtDate = findViewById(R.id.in_date);

        btnTimePicker = findViewById(R.id.btn_time);
        txtTime = findViewById(R.id.in_time);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //
        etUSId.setText(ID);
        etUSTitle.setText(title);
        etUSDesc.setText(desc);
        txtDate.setText(date);
        txtTime.setText(time);

        etUSId.setEnabled(false);
        //

        /*Cursor cursor = new DBHelper(this).getAllData();

        while (cursor.moveToNext()){

            if(ID.equals(cursor.getString(0))){
                etUSId.setText(cursor.getString(0));
                etUSTitle.setText(cursor.getString(1));
                etUSDesc.setText(cursor.getString(2));
                txtDate.setText(cursor.getString(3));
                txtTime.setText(cursor.getString(4));
            }

        }*/

        // Date
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SessionRUD.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }}, mYear, mMonth, mDay);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(SessionRUD.this,
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etUSTitle.getText().toString().isEmpty() && !etUSDesc.getText().toString().isEmpty() && !txtDate.getText().toString().isEmpty() && !txtTime.getText().toString().isEmpty()){

                    /*dbHelper.updateSession(ID, etUSId.getText().toString(),etUSTitle.getText().
                                    toString(), etUSDesc.getText().toString(), txtDate.getText().toString(),
                            txtTime.getText().toString());*/

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Session");

                    if(!ID.equals(etUSId.getText().toString()) || !title.equals(etUSTitle.getText().toString()) ||
                            !desc.equals(etUSDesc.getText().toString()) || !date.equals(txtDate.getText().toString()) ||
                            !time.equals(txtTime.getText().toString())){

                        myRef.child(ID).child("sessionId").setValue(etUSId.getText().toString());
                        myRef.child(ID).child("sessionTitle").setValue(etUSTitle.getText().toString());
                        myRef.child(ID).child("sessionDesc").setValue(etUSDesc.getText().toString());
                        myRef.child(ID).child("sessionDate").setValue(txtDate.getText().toString());
                        myRef.child(ID).child("sessionTime").setValue(txtTime.getText().toString());
                    }


                    finish();
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Fill All Fields!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Session");

                myRef.child(ID).removeValue();

                //dbHelper.deleteSession(ID);
                finish();
            }
        });
    }
}
