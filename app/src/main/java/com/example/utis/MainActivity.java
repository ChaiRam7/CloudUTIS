package com.example.utis;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View; // Import View
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Handler handler;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        handler = new Handler();

        // Start a timer to update progress
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++;
                    // Update the progress bar on the UI thread
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                    try {
                        // Sleep for 20 milliseconds to simulate progress
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // Hide the progress bar after 2 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 3000);

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
        }, 3000); // 2000 milliseconds = 2 seconds delay
    }
}
