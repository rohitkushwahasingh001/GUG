package com.example.gurugramuniversity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        EdgeToEdge.enable(this);



        // Find the ImageView by its ID in the layout
        ImageView imageView = findViewById(R.id.imageView);

        // Replace "your_image_view_id" with the actual ID

        // Load the fade-in animation (assuming the filename is 'fade_in.xml')
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageView.startAnimation(fadeInAnimation); // Apply the animation to the ImageView

        // Create a handler to delay starting the next activity
        Handler handler = new Handler();

        // Start the next activity (MainActivity) after 3 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish(); // Close the Splash activity after transition
            }
        }, 2000); // Delay for 3 seconds
    }
}
