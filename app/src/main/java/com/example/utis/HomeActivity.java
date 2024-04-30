package com.example.utis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private Spinner departureSpinner, arrivalSpinner;
    private Button timingButton, scheduleButton;

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
                // Handle schedule button click
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
