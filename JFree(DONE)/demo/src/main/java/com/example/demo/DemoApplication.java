package com.example.demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Random;

public class DemoApplication {
	public static void main(String[] args) {
		// Generate sample data
		int n = 50; // Number of data points
		double[] data = new double[n]; // Original data
		for (int i = 0; i < n; i++) // Fill with some values
		{
			data[i] = Math.sin(i * 0.2) + 2; // Example: sine wave
		}

		// Salt the data (add random noise)
		double[] salted = new double[n]; // Salted data
		Random rand = new Random(); // Random number generator
		for (int i = 0; i < n; i++) // Fill with some values
		{
			salted[i] = data[i] + rand.nextGaussian() * 0.3; // noise
		}

		// Smoothing
		double[] smoothAvg = smoothAverage(salted, 5); // Smooth the salted data with a window of 5

		// Plot all series
		XYSeriesCollection dataset = new XYSeriesCollection(); // Create dataset
		dataset.addSeries(toSeries("Original", data)); // Add original data
		dataset.addSeries(toSeries("Salted", salted)); // Add salted data
		dataset.addSeries(toSeries("Smoothed", smoothAvg)); // Add smoothed data

		JFreeChart chart = ChartFactory.createXYLineChart(
				"Plotting, Salting, and Smoothing", // Title
				"Index", // X-axis label
				"Value", // Y-axis label
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false); // Show legend, tooltips, and URLs

		JFrame frame = new JFrame("JFreeChart Example"); // Create a frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		frame.add(new ChartPanel(chart)); // Add chart to frame
		frame.setSize(800, 600); // Set frame size
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true); // Show the frame
	}

	// Helper to convert array to XYSeries
	private static XYSeries toSeries(String name, double[] arr) {
		XYSeries s = new XYSeries(name); // Create a new series
		for (int i = 0; i < arr.length; i++) // Fill the series with data
			s.add(i, arr[i]); // Add data points
		return s; // Return the series
	}

	// Smothing function
	private static double[] smoothAverage(double[] data, int window) {
		double[] result = new double[data.length]; // Result array
		for (int i = 0; i < data.length; i++) // Loop through each data point
		{
			int start = Math.max(0, i - window / 2); // Start index
			int end = Math.min(data.length - 1, i + window / 2); // End index
			double sum = 0; // Sum variable
			for (int j = start; j <= end; j++) // Loop through the window
				sum += data[j]; // Add data points to sum
			result[i] = sum / (end - start + 1); // Average
		}
		return result; // Return smoothed data
	}
}
