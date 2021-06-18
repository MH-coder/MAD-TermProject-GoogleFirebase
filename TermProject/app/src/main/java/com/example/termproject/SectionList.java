package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SectionList extends AppCompatActivity {

    //
    RecyclerView rvSectionList;
    RecyclerView.LayoutManager layoutManager;
    List<Section> SectionList = new ArrayList<>();
    SectionRecyclerAdapter sectionRecyclerAdapter;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_list);

        //
        rvSectionList = findViewById(R.id.rvSectionList);

        layoutManager = new LinearLayoutManager(this);
        rvSectionList.setLayoutManager(layoutManager);

        sectionRecyclerAdapter = new SectionRecyclerAdapter(SectionList);
        rvSectionList.setAdapter(sectionRecyclerAdapter);
        //

        /*Cursor cursor = new DBHelper(this).getAllSectionData();
        while (cursor.moveToNext()){
            SectionList.add(new Section(cursor.getString(0), cursor.getString(1)));
        }
        sectionRecyclerAdapter.notifyDataSetChanged();*/

        //
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Section");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //
                for (DataSnapshot snap : snapshot.getChildren()) {

                    String si = snap.child("sectionID").getValue(String.class);
                    String s = snap.child("section").getValue(String.class);

                    SectionList.add(new Section(si, s));

                }
                sectionRecyclerAdapter.notifyDataSetChanged();
                //

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        //

        Button btnAddSection = findViewById(R.id.btnAddSection);

        btnAddSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCourseIntent = new Intent(SectionList.this, SectionRegistrationForm.class);
                startActivity(addCourseIntent);
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
