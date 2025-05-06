public class StatsLibrary2Tester {
    public static void main(String[] args) {

        // Create an instance of StatsLibrary2

        StatsLibrary2 statsLib = new StatsLibrary2();

        System.out.println("--- Testing StatsLibrary2 Methods ---");

        // Test 1 3.9: Negative Binomial Probability
        System.out.println("\nTest 1 3.9: Negative Binomial Probability");
        try {
            int y = 5; // Total trials
            int r = 3; // Number of successes
            double p = 0.4; // Probability of success
            double result = statsLib.negativeBinomialProbability(y, r, p);
            System.out.println("Inputs: y=" + y + ", r=" + r + ", p=" + p);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test 2 3.10: Hypergeometric Probability
        System.out.println("\nTest 2 3.10: Hypergeometric Probability");
        try {
            int y = 2; // Number of successes in the sample
            int r = 4; // Total number of successes in the population
            int N = 10; // Population size
            int n = 5; // Sample size
            double result = statsLib.hypergeometricProbability(y, r, N, n);
            System.out.println("Inputs: y=" + y + ", r=" + r + ", N=" + N + ", n=" + n);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test 3 3.11: Poisson Probability
        System.out.println("\nTest 3 3.11: Poisson Probability");
        try {
            int y = 4; // Number of events
            double lambda = 2.5; // Average rate of events
            double result = statsLib.poissonProbability(y, lambda);
            System.out.println("Inputs: y=" + y + ", lambda=" + lambda);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Testing Complete ---");
    }
}
