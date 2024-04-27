package com.example.utis;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a Handler to delay the navigation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity after 2 seconds delay
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                // Finish the current activity
                finish();
            }
        }, 1); // 2000 milliseconds = 2 seconds delay
    }
}
