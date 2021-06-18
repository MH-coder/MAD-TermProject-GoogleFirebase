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

public class SectionRegistrationForm extends AppCompatActivity {

    List<Section> Sections = new ArrayList<>();

    //DBHelper dbHelper = new DBHelper(SectionRegistrationForm.this);

    EditText etSectionID;
    EditText etSection;

    Button btnSectionRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_registration_form);

        // References
        etSectionID = findViewById(R.id.etSectionID);
        etSection = findViewById(R.id.etSection);

        btnSectionRegister =findViewById(R.id.btnSectionRegister);

        btnSectionRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etSectionID.getText().toString().isEmpty() && !etSection.getText().toString().isEmpty()){

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Section");


                    Section section = new Section(etSectionID.getText().toString(), etSection.getText().toString());

                    myRef.child(etSectionID.getText().toString()).setValue(section);
                    Toast toast=Toast.makeText(getApplicationContext(),"Section Successfully Registered!",Toast.LENGTH_LONG);
                    toast.show();
                    finish();

                    /*dbHelper.insertSection(etSectionID.getText().toString() ,etSection.getText().toString());
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