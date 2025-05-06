import java.util.*;

/*
 * Let's write a program. YourNameSimpleHashMap. Have a global variable data.
 * It will be an array of LinkedLists. The array indices are the results of your hashing.
 * (get to that in a second). Your linked lists will be of String or Integer. If you've
 * taken me in any capacit, instead of String or Integer, I want generics. Write a dumbHash Functions.
 * dumbHash will take a String and return an int. The hash technique we'll use.... count the number
 * of letters in your String write a method contains(). Write a tester method adding a bunch of strings 
 * see if your map contains a certain value. Write a new method public, that will dynamically resize the array.
 * And we shall call it rezize().
 */
public class ElijahWhiteSimpleHashMap<T> {
    private LinkedList<T>[] data; // Array of LinkedLists to store the data

    /**
     * Constructor to initialize the hash map with given size
     * 
     * @param size the size of the hash map
     */
    public ElijahWhiteSimpleHashMap(int size) // Constructor to initialize the hash map with a given size
    {
        data = new LinkedList[size]; // Initialize the array of LinkedLists
        for (int i = 0; i < size; i++) // Creation of a new LinkedList for each index in the array
        {
            data[i] = new LinkedList<>(); // Initialize each LinkedList
        }
    }

    /**
     * Hash function to compute the index for a given key
     * 
     * 
     * @param key the key to be hashed
     * @return the index in the array where the key should be stored
     */
    private int dumbHash(T key) // Hash function to compute the index for a given key
    {
        return key.toString().length() % data.length; // Hashing based on the length of the key's string representation
    }

    /**
     * Method to add a key to the hash map
     * 
     * @param key the key to be added
     */
    public void put(T key) // Method to add a key to the hash map
    {
        int index = dumbHash(key); // Compute the index using the hash function
        if (!data[index].contains(key)) // Check if the key is already present in the list
        {
            data[index].add(key); // If not, add it to the list
        }
    }

    /**
     * Method to check if a key is present in the hash map
     * 
     * @param key the key to be checked
     * @return true if the key is present, false otherwise
     */
    public boolean contains(T key) // Method to check if a key is present in the hash map
    {
        int index = dumbHash(key); // Compute the index using the hash function
        return data[index].contains(key); // Check if the key is present in the corresponding list
    }

    /**
     * Method to resize the hash map when it becomes too full
     * and is called
     * 
     */
    public void resize() // Method to resize the hash map
    {
        int newSize = data.length * 2; // Double the size of the array
        @SuppressWarnings("unchecked") // Suppress warning for unchecked cast
        LinkedList<T>[] newData = (LinkedList<T>[]) new LinkedList[newSize]; // Create a new array of LinkedLists
        for (int i = 0; i < newSize; i++) // Initialize the new array
        {
            newData[i] = new LinkedList<>(); // Initialize each LinkedList in the new array
        }

        for (LinkedList<T> list : data) // Iterate through each list in the old array
        {
            for (T key : list) // Iterate through each key in the list
            {
                int index = key.toString().length() % newSize; // Compute the new index using the hash function
                newData[index].add(key); // Add the key to the new list at the computed index
            }
        }

        data = newData; // Update the reference to the new array
    }
}