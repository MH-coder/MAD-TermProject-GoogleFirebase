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

public class SectionRUD extends AppCompatActivity {

    //DBHelper dbHelper = new DBHelper(SectionRUD.this);

    EditText etUSectionID;
    EditText etUSection;

    Button btnSectionUpdate, btnSectionDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_rud);

        /*Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");*/

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String ID = bundle.getString("ID");
        String section = bundle.getString("section");

        // References
        etUSectionID = findViewById(R.id.etUSectionID);
        etUSection = findViewById(R.id.etUSection);

        btnSectionUpdate = findViewById(R.id.btnSectionUpdate);
        btnSectionDelete = findViewById(R.id.btnSectionDelete);

        /*Cursor cursor = new DBHelper(this).getAllSectionData();

        while (cursor.moveToNext()){

            if(ID.equals(cursor.getString(0))){
                etUSectionID.setText(cursor.getString(0));
                etUSection.setText(cursor.getString(1));
            }

        }*/

        //
        etUSectionID.setText(ID);
        etUSection.setText(section);

        etUSectionID.setEnabled(false);
        //

        btnSectionUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*dbHelper.updateSection(ID, etUSectionID.getText().toString(),etUSection.getText().toString());
                finish();*/

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Section");

                if(!ID.equals(etUSectionID.getText().toString()) || !section.equals(etUSection.getText().toString())){

                    myRef.child(ID).child("sectionID").setValue(etUSectionID.getText().toString());
                    myRef.child(ID).child("section").setValue(etUSection.getText().toString());
                }

                finish();
            }
        });

        btnSectionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dbHelper.deleteSection(ID);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Section");

                myRef.child(ID).removeValue();
                finish();
            }
        });
    }

    }
