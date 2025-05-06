package com.example.probappjavanew;

// Represents a single project with a title, description, and image resource
public class Project {
    private String title;
    private String description;
    private int imageResId;

    // Constructor to initialize all fields
    public Project(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Returns the project title
    public String getTitle() {
        return title;
    }

    // Returns the project description
    public String getDescription() {
        return description;
    }

    // Returns the image resource ID for the project
    public int getImageResId() {
        return imageResId;
    }
}