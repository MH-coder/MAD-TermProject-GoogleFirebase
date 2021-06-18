package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    //DBHelper dbHelper = new DBHelper(Login.this);

    EditText etLEmail;
    EditText etLPassword;
    TextView tvNewSignup;
    Button btnLogin;

    SharedPreferences prefs = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            Toast toast=Toast.makeText(getApplicationContext(),"Device is syncing......",Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Toast toast=Toast.makeText(getApplicationContext(),"Offline....A",Toast.LENGTH_LONG);
            toast.show();
        }

        prefs = getSharedPreferences("com.example.termproject", MODE_PRIVATE);

        etLEmail = findViewById(R.id.etLEmail);
        etLPassword = findViewById(R.id.etLPassword);

        btnLogin = findViewById(R.id.btnLogin);
        tvNewSignup = findViewById(R.id.tvNewSignup);

        etLEmail.setText("admin");
        etLPassword.setText("admin");


        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.keepSynced(true);

        DatabaseReference refCourse = FirebaseDatabase.getInstance().getReference("Courses");
        refCourse.keepSynced(true);

        DatabaseReference refSection = FirebaseDatabase.getInstance().getReference("Section");
        refSection.keepSynced(true);

        DatabaseReference refSession = FirebaseDatabase.getInstance().getReference("Section");
        refSession.keepSynced(true);

        DatabaseReference refStudent = FirebaseDatabase.getInstance().getReference("Student");
        refStudent.keepSynced(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEnteredUserName = etLEmail.getText().toString().trim();
                String userEnteredPassword = etLPassword.getText().toString().trim();

                Query checkUser = ref.orderByChild("username").equalTo(userEnteredUserName);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String passFromDB = snapshot.child(userEnteredUserName).child("password").getValue(String.class);

                        if(passFromDB.equals(userEnteredPassword)){
                            Toast toast=Toast.makeText(getApplicationContext(),"Log in successful!",Toast.LENGTH_LONG);
                            toast.show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast toast=Toast.makeText(getApplicationContext(),"Invalid Email/Password. Try Again!",Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        tvNewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            Context context = this;

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                Toast toast=Toast.makeText(getApplicationContext(),"Device is syncing......",Toast.LENGTH_LONG);
                toast.show();
                connected = true;
            }
            else{
                Toast toast=Toast.makeText(getApplicationContext(),"Please connect to internet to start sync!",Toast.LENGTH_LONG);
                toast.show();
                connected = false;
            }
        }
        prefs.edit().putBoolean("firstrun", false).commit();
    }
}