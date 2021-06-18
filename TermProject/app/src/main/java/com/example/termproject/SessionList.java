package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessionList extends AppCompatActivity {

    /*SessionAdapter sessionAdapter;
    ListView lvSessionList;*/

    //
    RecyclerView rvSessionList;
    RecyclerView.LayoutManager layoutManager;
    List<Session> SessionList = new ArrayList<>();
    SessionRecyclerAdapter sessionRecyclerAdapter;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        /*lvSessionList = findViewById(R.id.lvSessionList);
        sessionAdapter = new SessionAdapter(SessionList);
        lvSessionList.setAdapter(sessionAdapter);*/

        //
        rvSessionList = findViewById(R.id.rvSessionList);

        layoutManager = new LinearLayoutManager(this);
        rvSessionList.setLayoutManager(layoutManager);

        sessionRecyclerAdapter = new SessionRecyclerAdapter(SessionList);
        rvSessionList.setAdapter(sessionRecyclerAdapter);
        //

        /*Cursor cursor = new DBHelper(this).getAllData();
        while (cursor.moveToNext()) {
            SessionList.add(new Session(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
        sessionRecyclerAdapter.notifyDataSetChanged();*/

        //
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Session");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //
                for (DataSnapshot snap : snapshot.getChildren()) {

                    String sId = snap.child("sessionId").getValue(String.class);
                    String stitle = snap.child("sessionTitle").getValue(String.class);
                    String sDesc = snap.child("sessionDesc").getValue(String.class);
                    String sDate = snap.child("sessionDate").getValue(String.class);
                    String sTime = snap.child("sessionTime").getValue(String.class);

                    SessionList.add(new Session(sId, stitle, sDesc, sDate, sTime));

                }
                sessionRecyclerAdapter.notifyDataSetChanged();
                //

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        //

        Button btnScheduleSession = findViewById(R.id.btnScheduleSession);

        btnScheduleSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scheduleSessionIntent = new Intent(SessionList.this, SessionRegistrationForm.class);
                startActivity(scheduleSessionIntent);
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
