package com.example.probappjavanew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter for binding project data to the RecyclerView
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private Context context;
    private List<Project> projectList;

    // Adapter constructor
    public ProjectAdapter(Context context, List<Project> projectList) {
        this.context = context;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates the layout for each project card
        View view = LayoutInflater.from(context).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        // Retrieves the project for the current position
        Project project = projectList.get(position);
        holder.titleView.setText(project.getTitle());
        holder.imageView.setImageResource(project.getImageResId());
        // Handles click events to open the detail screen
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", project.getTitle());
            intent.putExtra("description", project.getDescription());
            intent.putExtra("imageResId", project.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        // Returns the total number of projects
        return projectList.size();
    }

    // ViewHolder for holding references to the views in each card
    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleView = itemView.findViewById(R.id.titleView);
        }
    }
}