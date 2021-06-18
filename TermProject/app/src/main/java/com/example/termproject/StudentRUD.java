package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRUD extends AppCompatActivity {

    //DBHelper dbHelper = new DBHelper(StudentRUD.this);

    EditText etUStdRollNo;
    EditText etUStdName;
    EditText etUStdFName;
    EditText etUStdEmail;
    EditText etUStdPh;
    Button btnStdUpdate, btnStdDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_rud);

        /*Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");*/

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String ID = bundle.getString("ID");
        String name = bundle.getString("name");
        String fName = bundle.getString("fName");
        String email = bundle.getString("email");
        String ph = bundle.getString("ph");

        // References
        etUStdRollNo = findViewById(R.id.etUStdRollNo);
        etUStdName = findViewById(R.id.etUStdName);
        etUStdFName = findViewById(R.id.etUStdFName);
        etUStdEmail = findViewById(R.id.etUStdEmail);
        etUStdPh = findViewById(R.id.etUStdPh);

        btnStdUpdate = findViewById(R.id.btnStdUpdate);
        btnStdDelete = findViewById(R.id.btnStdDelete);

        //
        etUStdRollNo.setText(ID);
        etUStdName.setText(name);
        etUStdFName.setText(fName);
        etUStdEmail.setText(email);
        etUStdPh.setText(ph);

        etUStdRollNo.setEnabled(false);
        //

        /*Cursor cursor = new DBHelper(this).getAllStdData();

        while (cursor.moveToNext()){

            if(ID.equals(cursor.getString(0))){
                etUStdRollNo.setText(cursor.getString(0));
                etUStdName.setText(cursor.getString(1));
                etUStdFName.setText(cursor.getString(2));
                etUStdEmail.setText(cursor.getString(3));
                etUStdPh.setText(cursor.getString(4));
            }

        }*/

        btnStdUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*dbHelper.updateStudent(ID, etUStdRollNo.getText().toString(),etUStdName.getText().
                                toString(), etUStdFName.getText().toString(), etUStdEmail.getText().toString(),
                        etUStdPh.getText().toString());*/

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Student");

                if(!ID.equals(etUStdRollNo.getText().toString()) || !name.equals(etUStdName.getText().toString()) ||
                        !fName.equals(etUStdFName.getText().toString()) || !email.equals(etUStdEmail.getText().toString()) ||
                        !ph.equals(etUStdPh.getText().toString())){

                    myRef.child(ID).child("rollNo").setValue(etUStdRollNo.getText().toString());
                    myRef.child(ID).child("name").setValue(etUStdName.getText().toString());
                    myRef.child(ID).child("fatherName").setValue(etUStdFName.getText().toString());
                    myRef.child(ID).child("email").setValue(etUStdEmail.getText().toString());
                    myRef.child(ID).child("phone").setValue(etUStdPh.getText().toString());
                }

                finish();
            }
        });

        btnStdDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Student");

                myRef.child(ID).removeValue();

                //dbHelper.deleteStudent(ID);
                finish();
            }
        });
    }
}
