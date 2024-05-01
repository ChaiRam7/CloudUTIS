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
        // Assuming you've already created an instance of FirebaseDatabase and a reference to your database somewhere in your code
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        routesRef = database.getReference("BusBuddy");

// FirebaseDatabase.getInstance().getReference("BusBuddy");
// routesRef = database.getReference();

        // Get references to views
        departureSpinner = findViewById(R.id.departure);
        arrivalSpinner = findViewById(R.id.arrival);
        timingButton = findViewById(R.id.timing);
        scheduleButton = findViewById(R.id.schedule);

        notificationImageView = findViewById(R.id.notification);
        aboutUsImageView = findViewById(R.id.AboutUs);
        cimage = findViewById(R.id.chatImageView);
        dimage = findViewById(R.id.imageView3);


        cimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsAppChat("8144227063");
                Toast.makeText(HomeActivity.this, "🌟Thank you for reaching out our Support team", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(HomeActivity.this, "We're ready to hear about your transportation experience. What can we help you with today?", Toast.LENGTH_SHORT).show();
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


        //spinner - Departure
        ArrayAdapter<String> departureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"KATPADI", "VELLORE"});
        departureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departureSpinner.setAdapter(departureAdapter);


        //Spinner Arrival
        ArrayAdapter<String> arrivalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"66 PUTHUR", "A.K.PADAVEDU", "ADUKAMPARAI", "ADUKAMPARAI G.H.", "ADUKKAMPARAI", "AGARAM", "AGARAMCHERI", "AMATHUR", "AMIRTHI",
                "AMMAPALAYAM", "AMMAVARPALLI", "ANAICUT", "ANAICUT TALUK OFFICE", "ANANTHAPURAM H.S", "ARAKKONAM", "ARCOT", "ARCOTTANKUDISAI", "ARJUNAPURAM", "ATHIYUR", "AUTHUKADAI", "AUXILIM COLLEGE", "AVANARANGAPALLI", "Arni", "BAGAYAM", "BHEL", "CHETTITHANGAL", "CHETTIYANTHAL", "CHINNACHERI", "CHINNAPUTHUR", "CHOLAVARAM", "Che.Agaram", "Cheyyar", "DEVARISHIKUPPAM", "DURAIMOOLAI", "DURUVAM", "ERIPUDUR", "ERIYUR", "G.R.PALAYAM", "GUDIYATHAM", "GURUVA" +
                "RAJAPALAYAM", "IRUMBULI", "JAMALPURAM", "KALANGAMEDU", "KALLANKULAM", "KALPUDUR", "KANIYAMBADI", "KANNAMANGALAM", "KARUNGALI", "KASIMANAGAR", "KATPADI", "KAVANUR", "KAVANUR R.S", "KILKOTHUR", "KILNAGAR", "KND DEPOT", "KOLLAMANGALAM", "KONAVATTAM", "KUPPIREDDITHANGAL", "LABAIKRISHNAPURAM", "LALAPET", "LAPPAIKRISHNAPURAM", "LATHERI GATE", "MAHADEVAMALAI", "MAHAMADUPURAM", "MARUHTHAMPAKKAM",
                "MARUTHAMPAKKAM", "MELAKUPPAM", "MELARASAMPET", "MELMANKUPPAM", "MELPADICHATRAM", "MELSENGANATHAM", "MOOJURPATTU", "MOOLAKANKUPPAM", "MOOLAVALASAI", "MOTHAKKAL", "MULLENDRAM", "MUTHUKADAI", "NAICKENERI", "NANJUKONDAPURAM", "ODUGATHUR", "ONNUPURAM", "OTHIYATHUR", "P.K.PURAM", "PACHANAYANIKUPPAM", "PALAMANER", "PALAYATHONDANTHULASI", "PALLATHUR", "PALLIKONDA", "PARADARAMI", "PASUMATHUR", "PERIACHITTERI", "PERIYABODINATHAM", "PERIYAPUDUR",
                "PERUMALKUPPAM", "PINNATHURAI", "PONNAI", "PONNAIPUDUR", "PUDUPALAYAM", "PUNGANOOR", "PUTHUR", "Polur", "R.KRISHNAPURAM", "RAGUNATHAPURAM", "RANIPET", "RATHINAGIRI", "REDDIPALAYAM", "REDDIYUR", "SEDUVALAI", "SENDRAMPALLI", "SENDRAYANKOTTAI", "SENJI", "SENJIKRISHNAPURAM", "SERKADU", "SHOLINGHUR", "SINGAREDDIYUR", "SINGIRIKOIL", "SIVANATHAPURAM", "SRIPURAM", "T.E.L.", "T.E.L./AP", "THENGAL", "THIPPASAMUDRAM", "THIRUTHANI", "THIRUVALLUVAR UNIV",
                "THONDANTHULASI", "TINDIVANAM", "Thiruvannamalai", "ULLIPUDUR", "ULUNTHURPET", "VALAYAKARANPATTI", "VALLIMALAI", "VANDRANTHANGAL", "VARADALAMPATTU", "VARADALAMPET", "VARADARAJAPURAM", "VARGURPATTINAM", "VAZHAVANKUNDRAM", "VAZIYUR", "VELLORE", "VENKATAPURAM", "VILAPAKKAM", "VINNAMPALLI", "VIRUDHUNAGAR", "Vazhiyur", "WALAJA"});
        arrivalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrivalSpinner.setAdapter(arrivalAdapter);


        // Load routes data
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ScheduleDetails();
            }
        });


        // Set click listeners
        timingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from Firebase when timing button is clicked
                retrieveBusTiming();
            }
        });
    }




    private void retrieveBusTiming() {
        // Assuming currentTime is a string representing the current time
        String currentTime = getCurrentTime();

        routesRef.orderByChild("Source Time").startAt(currentTime).limitToFirst(1)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String busName = snapshot.child("Route").getValue(String.class);
                                String sourceTime = snapshot.child("Source Time").getValue(String.class);
                                String destinationTime = snapshot.child("Destination Time").getValue(String.class);

                                // Create the timing string
                                String timing = "BUS NAME: " + busName + "\n" +
                                        "SOURCE TIME: " + sourceTime + "\n" +
                                        "DESTINATION TIME: " + destinationTime;

                                // Display the timing string
                                Toast.makeText(HomeActivity.this, timing, Toast.LENGTH_SHORT).show();
                                return; // Exit after displaying the timing for the first matching bus
                            }
                        } else {
                            Toast.makeText(HomeActivity.this, "No buses available at the moment", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "Error fetching data", databaseError.toException());
                        Toast.makeText(HomeActivity.this, "Error fetching data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String getCurrentTime() {
        // Implement logic to get the current time in the desired format
        // For example, you can use SimpleDateFormat to format the current time
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }



}
