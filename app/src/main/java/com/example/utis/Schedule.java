package com.example.utis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Schedule extends AppCompatActivity {
    TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule2);

        temp=findViewById(R.id.time1);
        Intent intent=getIntent();
        String str=intent.getStringExtra("message");
        temp.setText(str);
    }
}