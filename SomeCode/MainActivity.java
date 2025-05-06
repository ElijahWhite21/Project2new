package com.example.probappjavanew;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Reference to the RecyclerView in the layout
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // Sets the layout manager for the RecyclerView (single column list)
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // 1 for list, 2+ for grid
        // List to hold all project data
        ArrayList<Project> projectList = new ArrayList<>();
        projectList.add(new Project(
                "Plotting, Salting, Smoothing",
                "This project explores data visualization, salting, smoothing, and statistical analysis using Java, MATLAB/Octave, and JFreeChart with Apache Commons Math. To recover the original pattern, it first plots a basic linear function, adds noise (salting), and then uses smoothing techniques. Excel and JFreeChart were used to visualize the data, while Apache Commons Math was used for statistical analysis in order to compute descriptive statistics and produce graphical representations.",
                R.drawable.plotting));
        projectList.add(new Project(
                "HW Report",
                "Using statistical methods, this paper examines a number of basketball-related topics, including shooting accuracy, resource management, and player performance. The study illustrates how probability and descriptive statistics can be applied to maximize choices on the court by examining shot percentages, player roles, and court usage. The analysis's conclusions enable managers, coaches, and athletes to make data-driven decisions that boost training, increase performance, and simplify operations.",
                R.drawable.report));
        projectList.add(new Project(
                "Pokemon Manual",
                "I used my knowledge of Bootstrap in this project to produce an extensive and engaging guide for the Pokémon Trading Card Game (TCG). Essential game components including card types, actions, and game setup are covered on the website in an aesthetically pleasing, mobile-friendly manner. An adaptable layout that adapts fluidly to different screen sizes, modals with comprehensive card information, and a dynamic navigation bar are among the features.",
                R.drawable.pokemon_manual));
        projectList.add(new Project(
                "Pokemon Video Game Site",
                "The goal of this project is to develop an interactive, web-based Pokémon Trading Card Game (TCG) that allows users to interact with different cards, manage decks, and fight. Key components of the game include deck construction, card management, and player actions like attacking, drawing cards, and selecting Pokémon. The interface is upgraded using Bootstrap for a responsive and user-friendly design, guaranteeing a seamless experience across various screen sizes.",
                R.drawable.pokemon_vg));

        ProjectAdapter adapter = new ProjectAdapter(this, projectList);
        recyclerView.setAdapter(adapter);
    }
}