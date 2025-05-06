import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElijahWhiteSimpleHashMapTester {
    public static void main(String[] args) {
        int[] datasetSizes = { 10000, 50000, 100000 }; // Different dataset sizes for the experiment
        try {
            FileWriter writer = new FileWriter("experiment_results.csv"); // Create a CSV file to store results

            // Write header row to the CSV file
            writer.append(
                    "Dataset Size,Insertion Time (ns),Search Time (ns),Memory Before Resize,Memory After Resize\n");

            // Run the experiment for each dataset size and write to the CSV file
            for (int size : datasetSizes) // Iterate through each dataset size
            {
                System.out.println("Running experiment with dataset size: " + size); // Print the current dataset size
                runExperiment(size, writer); // Run the experiment with the current dataset size
            }

            writer.flush(); // Flush the writer to ensure all data is written to the file
            writer.close(); // Close the writer to release resources
            System.out.println("Results exported to experiment_results.csv"); // Notify user of completion

        } catch (IOException e) // Handle any IO exceptions that may occur
        {
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    // Run the experiment with a given dataset size
    private static void runExperiment(int largeDataSetSize, FileWriter writer) throws IOException {
        ElijahWhiteSimpleHashMap<String> map = new ElijahWhiteSimpleHashMap<>(10); // Create a new instance of the hash
                                                                                   // map
        List<String> largeDataSet = generateLargeDataSet(largeDataSetSize); // Generate a large dataset of strings

        // Measure time to insert all items into the HashMap
        long startTime = System.nanoTime(); // Start the timer
        for (String item : largeDataSet) // Iterate through each item in the dataset
        {
            map.put(item); // Insert the item into the hash map
        }
        long endTime = System.nanoTime(); // End the timer
        long insertionTime = endTime - startTime; // Calculate the time taken for insertion

        // Measure the time to check for a random element
        startTime = System.nanoTime(); // Start the timer for search
        map.contains("Item " + (largeDataSetSize / 2)); // Example search
        endTime = System.nanoTime(); // End the timer for search
        long searchTime = endTime - startTime; // Calculate the time taken for search

        // Measure memory usage before and after resizing
        long memoryBefore = Runtime.getRuntime().totalMemory(); // Get memory usage before resizing
        map.resize(); // Resize the map
        long memoryAfter = Runtime.getRuntime().totalMemory(); // Get memory usage after resizing

        // Write results to the CSV file
        writer.append(largeDataSetSize + "," + insertionTime + "," + searchTime + "," + memoryBefore + "," + memoryAfter
                + "\n");
    }

    // Generate a large dataset of random strings
    private static List<String> generateLargeDataSet(int size) // Method to generate a large datast of strings
    {
        List<String> data = new ArrayList<>(); // Create a list to hold the strings
        for (int i = 0; i < size; i++) // Iterate through the specified size
        {
            data.add("Item " + i); // Create strings like "Item 0", "Item 1", ...
        }
        return data; // Return the generated list of strings
    }
}
