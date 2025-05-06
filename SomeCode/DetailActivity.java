package com.example.probappjavanew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Sets up the toolbar as the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // References to the views for displaying project details
        ImageView imageView = findViewById(R.id.detailImageView);
        TextView titleView = findViewById(R.id.detailTitleView);
        TextView descView = findViewById(R.id.detailDescView);

        // Retrieves data passed from the main screen
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        int imageResId = intent.getIntExtra("imageResId", -1);

        // Populates the views with the project data
        titleView.setText(title);
        descView.setText(description);
        if (imageResId != -1) {
            imageView.setImageResource(imageResId);
        }
    }

    // Handles the up (back) button in the toolbar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}