import java.io.*;
import java.util.*;

public class SaltingFunction {

    /*
     * Write a new program who's job is to salt the data in the file data.csv
     * It receives a CSV of xy data. The data is loaded to a data structure of my
     * chcoice
     * Then write a salting method who's job is to go through your data structure
     * and add or substract
     * a random number to the y value. The random number should be between -1 and
     * 1.some random number from it.
     * From it mean the "Y" values only here. Don;t salt the x values.
     * The +/- is a new random number every time same range that you can configure
     * at the start
     */
    private static final double MAX_SALT = 1.0; // Maximum salt value

    /**
     * This is the main method to generate data and save to a CSV file for
     * plotting the specifc function to calculate the y value is defined in the
     * calculateFunction method
     * 
     * @param args not used 
     */
    public static void main(String[] args) {
        String inputFile = "data.csv"; // Input CSV file with x,y data
        String outputFile = "salted_data.csv"; // Output CSV file for salted data

        List<Double> xValues = new ArrayList<>(); // List to hold x-values
        List<Double> yValues = new ArrayList<>(); // List to hold y-values

        // Load CSV data into ArrayLists
        loadCSV(inputFile, xValues, yValues); // Load the data from the CSV file

        // Salt the y-values
        saltYValues(yValues, MAX_SALT); // Salt the y-values with a random number between -MAX_SALT and +MAX_SALT

        // Write the salted data to a new CSV
        exportToCSV(outputFile, xValues, yValues); // Export the salted data to a new CSV file

        System.out.println("Salted data written to " + outputFile); // Confirmation message
    }

    // Load data from the CSV file into two lists one for x and one for y
    // The first line is the header and should be skipped
    /**
     * 
     * @param fileName name of the file to load
     * @param xList list to hold x-values
     * @param yList list to hold y-values
     * @throws IOException if an error occurs while reading the file
     */
    public static void loadCSV(String fileName, List<Double> xList, List<Double> yList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) // Read each line
            {
                String[] parts = line.split(","); // Split by comma
                if (parts.length == 2) // Check if there are two parts
                {
                    xList.add(Double.parseDouble(parts[0])); // Add x-value to list
                    yList.add(Double.parseDouble(parts[1])); // Add y-value to list
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage()); // Print error message
        }
    }

    /**
     * Salt the y-values with a random number between -maxSalt and +maxSalt
     * 
     * @param yList list of y-values to be salted/modified
     * @param maxSalt maximum salt value to be added/subtracted from the y-values
     * @throws IllegalArgumentException if maxSalt is negative
     */
    public static void saltYValues(List<Double> yList, double maxSalt) // Salt the y-values with a random number between
                                                                       // -maxSalt and +maxSalt
    {
        Random rand = new Random(); // Random number generator
        for (int i = 0; i < yList.size(); i++) // Loop through each y-val
        {
            double salt = (rand.nextDouble() * 2 * maxSalt) - maxSalt; // -maxSalt to +maxSalt
            yList.set(i, yList.get(i) + salt); // Add the salt to the y-value
        }
    }

    /**
     * Export the x and y values to a CSV file for plotting the data
     * 
     * @param fileName name of the file to save the data
     * @param xList list of x-values to be saved
     * @param yList list of y-values to be saved
     */
    public static void exportToCSV(String fileName, List<Double> xList, List<Double> yList) // Export the x and y val to
                                                                                            // use for plotting
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("x,y\n"); // Write header
            for (int i = 0; i < xList.size(); i++) // Loop through the x and y values
            {
                writer.write(String.format("%.2f,%.4f\n", xList.get(i), yList.get(i))); // Format and write the values
            }
        } catch (IOException e) // Handle exceptions
        {
            System.err.println("Error writing to file: " + e.getMessage()); // Print error message
        }
    }
}