package com.example.utis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TimeActivity extends AppCompatActivity {
    TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        temp=findViewById(R.id.timely);
        Intent intent=getIntent();
        String str=intent.getStringExtra("message");
        temp.setText(str);



    }
}