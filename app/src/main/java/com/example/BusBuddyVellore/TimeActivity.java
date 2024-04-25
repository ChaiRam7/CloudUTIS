package com.example.BusBuddyVellore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utis.R;

public class TimeActivity extends AppCompatActivity {
    TextView temp;
    ImageView backImg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);


        temp=findViewById(R.id.timely);
        Intent intent=getIntent();
        String str=intent.getStringExtra("message");
        temp.setText(str);


        backImg = findViewById(R.id.imageBackView);
        //Back Button
        backImg.setOnClickListener(v -> {
            Intent intent1 = new Intent( this,HomeActivity.class); // Use MainActivity.this as the context
            startActivity(intent1);
            finish();
        });

    }
}