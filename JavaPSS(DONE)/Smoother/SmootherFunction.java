import java.io.*;
import java.util.*;

/*
 * Write a new promgram called smoother. It's is to lpad a csv data and smooth "the data"How?
 * We will take the avg of some window of points and replace the focus point with the new point
 * So set some configurable bound called "WindowValue" and this will be some +/- sya 5 for now
 * Then loop through the data structure that holds your loaded data and tke as many points left
 * and right as you can based on window value and average the points together, replacing the 
 * y value with the new smoothed y value.
 * 
 */

public class SmootherFunction {

    private static final int WINDOW_VALUE = 5; // Window size for smoothing

    /**
     * This is the main method that runs the program. It loads the CSV file, 
     * applies smoothing to the y-values, and exports the smoothed data to a new CSV file.
     * 
     * @param args not used
     */

    public static void main(String[] args) 
    {
        String inputFile = "salted_data.csv"; // Input CSV file with x,y data
        String outputFile = "smoothed_data.csv"; // Output CSV file for smoothed data

        List<Double> xValues = new ArrayList<>(); // List to hold x-values
        List<Double> yValues = new ArrayList<>(); // List to hold y-values

        // Load the CSV into lists
        loadCSV(inputFile, xValues, yValues); // Load the data from the CSV file

        // Apply smoothing to y-values
        List<Double> smoothedY = smoothYValues(yValues, WINDOW_VALUE); // Smooth the y-values

        // Export to new CSV
        exportToCSV(outputFile, xValues, smoothedY); // Export the smoothed data to a new CSV file

        System.out.println("Smoothed data written to " + outputFile); // Confirmation message
    }

    /**
     * This method loads data from a CSV file into two lists: xList and yList.
     * It reads the file line by line, splits each line by a comma, and parses the values into doubles.
     * 
     * @param fileName the name of the CSV file to load
     * @param xList the list to store x-values
     * @param yList the list to store y-values
     */

    public static void loadCSV(String fileName, List<Double> xList, List<Double> yList) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) // Reads each line
            {
                String[] parts = line.split(","); // Split by comma
                if (parts.length == 2) // Check if there are two parts
                {
                    xList.add(Double.parseDouble(parts[0])); // Add x value to list
                    yList.add(Double.parseDouble(parts[1])); // Add y value to list
                }
            }
        } catch (IOException e) // Handle file reading errors
        {
            System.err.println("Error reading file: " + e.getMessage()); // Print error message
        }
    }

    /**
     * This method smooths the y-values using a moving average with a specified window size.
     * It calculates the average of y-values within the window around each point and replaces the original y-value with the average.
     * 
     * @param yValues the list of y-values to smooth
     * @param window the size of the window for smoothing
     * @return a new list of smoothed y-values
     */

    public static List<Double> smoothYValues(List<Double> yValues, int window) 
    {
        List<Double> smoothed = new ArrayList<>(); // List to hold smoothed y-values

        for (int i = 0; i < yValues.size(); i++) // Loop through each y-value
        {
            int start = Math.max(0, i - window); // Calculate start index for smoothing
            int end = Math.min(yValues.size() - 1, i + window); // Calculate end index for smoothing

            double sum = 0.0; // Sum of y-values in the window
            int count = 0; // Count of y-values in the window

            for (int j = start; j <= end; j++) // Loop through the window
            {
                sum += yValues.get(j); // Add y-value to sum
                count++; // Increment count
            }

            double avg = sum / count; // Calculate average
            smoothed.add(avg); // Replaces y[i] with the average of its neighbors
        }

        return smoothed; // Return the smoothed y-values
    }

    /**
     * This method exports the smoothed data to a CSV file.
     * It writes the x-values and smoothed y-values to the file in CSV format.
     * 
     * @param fileName the name of the output CSV file
     * @param xList the list of x-values
     * @param yList the list of smoothed y-values
     */

    public static void exportToCSV(String fileName, List<Double> xList, List<Double> yList) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) 
        {
            writer.write("x,y\n"); // Write header to CSV file
            for (int i = 0; i < xList.size(); i++) // Loop through each x-value 
            {
                writer.write(String.format("%.2f,%.4f\n", xList.get(i), yList.get(i))); // Write x and smoothed y values to file
            }
        } catch (IOException e) // Handle file writing errors
        {
            System.err.println("Error writing to file: " + e.getMessage()); // Print error message
        }
    }
}
