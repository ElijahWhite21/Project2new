import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlottingFunction {

    /**
     * This consits the main method to generate data and save to a CSV file for
     * plotting
     * the specifc function to calculate the y value is defined in the
     * calculateFunction method
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        // Define parameters
        double startX = -10.0; // Starting x-value
        double endX = 10.0; // Ending x-value
        double step = 0.1; // Step size

        // Generates the data
        int size = (int) ((endX - startX) / step) + 1; // Calculate the number of points
        double[] xValues = new double[size]; // Array to hold x values
        double[] yValues = new double[size]; // Array to hold y values

        int index = 0;
        for (double x = startX; x <= endX; x += step) // Loop through the x values
        {
            xValues[index] = x; // Store the x value
            yValues[index] = calculateFunction(x); // Calls the function to calculate the y
            index++; // Increment the index
        }

        // Exporting to the CSV
        exportToCSV("data.csv", xValues, yValues); // Call the function to export data to CSV

        System.out.println("Data has been generated and saved to data.csv"); // Confirmation message
    }

    /**
     * This method calculates the y value for a given x value using a linear function
     * y = mx + b
     * 
     * @param x The x value to calculate the y value for the function
     * @return The calculated y value using form y = mx + b
     */
    public static double calculateFunction(double x) {
        double m = 2.0; // The slope
        double b = 1.0; // The intercept
        return m * x + b; // Calculate the y value using the linear function
    }

    // Export x and y values to a CSV file for plotting
    /**
     * @param fileName The name of the file to save the data
     * @param xValues  The x values to be saved
     * @param yValues  The y values to be saved
     * 
     */
    public static void exportToCSV(String fileName, double[] xValues, double[] yValues) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("x,y\n"); // Write header
            for (int i = 0; i < xValues.length; i++) // Loop through the x and y values
            {
                writer.write(String.format("%.2f,%.4f\n", xValues[i], yValues[i])); // Format and write the values
            }
        } catch (IOException e) // Handle exceptions
        {
            System.err.println("Error writing to file: " + e.getMessage()); // Print error message
        }
    }
}
