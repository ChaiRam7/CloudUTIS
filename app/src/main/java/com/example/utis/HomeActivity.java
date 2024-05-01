package com.example.utis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private Spinner departureSpinner, arrivalSpinner;
    private Button timingButton, scheduleButton;

    private ImageView notificationImageView, aboutUsImageView, cimage, dimage;

    private TextView timelyTextView;
    private DatabaseReference routesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        routesRef = database.getReference();

        // Get references to views
        departureSpinner = findViewById(R.id.departure);
        arrivalSpinner = findViewById(R.id.arrival);
        timingButton = findViewById(R.id.timing);
        scheduleButton = findViewById(R.id.schedule);

        notificationImageView = findViewById(R.id.notification);
        aboutUsImageView = findViewById(R.id.AboutUs);
        cimage = findViewById(R.id.chatImageView);
        dimage=findViewById(R.id.imageView3);

        cimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsAppChat("8144227063");
            }
            public void openWhatsAppChat(String phoneNumber) {
                // Open WhatsApp chat using Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + phoneNumber;
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        dimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsAppChat("9445014448");
            }
            public void openWhatsAppChat(String phoneNumber) {
                // Open WhatsApp chat using Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + phoneNumber;
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        // Inflate the layout containing the timelyTextView
        View timeLayout = getLayoutInflater().inflate(R.layout.activity_time, null);
        // Find the timelyTextView within the inflated layout
        timelyTextView = timeLayout.findViewById(R.id.timely);

        // Set click listeners
        notificationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Admin.class);
                startActivity(intent);
            }
        });

        aboutUsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });


        // Set up the spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departureSpinner.setAdapter(adapter);
        arrivalSpinner.setAdapter(adapter);

        // Load routes data
        loadRoutesData();

        // Set click listeners
        timingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle timing button click
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            fetchFirebaseDataSnapshot();

            }
        });

    }

    private void fetchFirebaseDataSnapshot() {
        routesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if there are any children in the snapshot
                if (dataSnapshot.getChildren().iterator().hasNext()) {
                    // Get the first child snapshot
                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                    // Log the first data snapshot
                    Log.d(TAG, "First data snapshot: " + firstChild.getValue());
                    // Display the first data snapshot in a dialog or toast
                    // For example:
                    Toast.makeText(HomeActivity.this, "First data snapshot: " + firstChild.getValue(), Toast.LENGTH_LONG).show();
                } else {
                    // No data found
                    Toast.makeText(HomeActivity.this, "No data found in Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "fetchFirstDataSnapshot:onCancelled", error.toException());
                // Handle database error
            }
        });

    }

    private void loadRoutesData() {
        routesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> destinations = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String destination = snapshot.child("Destination").getValue(String.class);
                    if (destination != null && !destinations.contains(destination)) {
                        destinations.add(destination);
                    }
                }
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) departureSpinner.getAdapter();
                adapter.clear();
                adapter.addAll(destinations);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "loadRoutesData:onCancelled", error.toException());
            }
        });
    }
}
