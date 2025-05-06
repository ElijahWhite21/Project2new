import java.math.BigInteger;

public class StatsLibrary2 {

    // Method to calculate factorial
    /**
     * Calculations of the factorial of a number n
     * 
     * @param n number to calculate the factorial
     * @return factorial of n as a BigInteger
     */
    private BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE; // Initialize result to 1
        for (int i = 2; i <= n; i++) // Start from 2 to n
        {
            result = result.multiply(BigInteger.valueOf(i)); // Multiply result by i
        }
        return result; // Return the factorial of n as a BigInteger
    }

    // Method to calculate combinations (nCr)
    /**
     * Calculating the combination of n choose r
     * 
     * @param n total number of items
     * @param r number of items to choose
     * @return number of combinations as a BigInteger
     */
    private BigInteger combination(int n, int r) {
        return factorial(n).divide(factorial(r).multiply(factorial(n - r))); // C(n, r) = n! / (r! * (n - r)!)
    }

    // Definition 3.9 - Negative Binomial Probability
    /**
     * Calculating the negative binomial probability distribution P(Y = y | r, p)
     * where Y is the number of trials until the rth success, r is the number of
     * successes, and p is the probability of success.
     * 
     * @param y number of trials until the nth success
     * @param r number of successes
     * @param p probability of success
     * @return negative binomial probability
     */
    public double negativeBinomialProbability(int y, int r, double p) {
        if (y < r || p < 0 || p > 1) {
            throw new IllegalArgumentException("Invalid inputs. Ensure y >= r and 0 <= p <= 1."); // Error message
        }
        double q = 1 - p; // Probability of failure
        BigInteger combinations = combination(y - 1, r - 1);// C(y - 1, r - 1)
        return combinations.doubleValue() * Math.pow(p, r) * Math.pow(q, y - r);// P(Y = y | r, p)
    }

    // Definition 3.10 - Hypergeometric Probability
    /**
     * Calculating the hypergeometric probability distribution P(Y = y | r, N, n)
     * where Y is the number of successes in the sample, r is the total number of
     * successes in the population, N is the population size, and n is the sample
     * size.
     * 
     * @param y number of successes in the sample
     * @param r total number of successes in the population
     * @param N population size
     * @param n sample size
     * @return hypergeometric probability
     */
    public double hypergeometricProbability(int y, int r, int N, int n) {
        if (y < 0 || y > r || n - y > N - r) { // Y must be a non-negative integer, y <= r, and n - y <= N - r
            throw new IllegalArgumentException("Invalid inputs. Ensure y <= r and n-y <= N-r."); // Error message
        }

        BigInteger top1 = combination(r, y); // C(r, y)
        BigInteger top2 = combination(N - r, n - y); // C(N - r, n - y)
        BigInteger bottom = combination(N, n); // C(N, n)

        return top1.multiply(top2).doubleValue() / bottom.doubleValue();// P(Y = y | r, N, n)
    }

    // Definition 3.11 - Poisson Probability
    /**
     * Calculating the Poisson probability distribution P(Y = y | lambda) where Y is
     * the number of events, and lambda is the average rate of events.
     * 
     * @param y      number of events
     * @param lambda average rate of events
     * @return Poisson probability
     */
    public double poissonProbability(int y, double lambda) {
        if (lambda <= 0) { // Lambda must be greater than 0
            throw new IllegalArgumentException("Lambda must be greater than 0."); // Error message
        }
        if (y < 0) { // Y must be a non-negative integer
            throw new IllegalArgumentException("Y must be a non-negative integer."); // Error message
        }

        double numerator = Math.pow(lambda, y) * Math.exp(-lambda); // e^(-lambda) * lambda^y
        double denominator = factorial(y).doubleValue();

        return numerator / denominator;// P(Y = y | lambda)
    }
}
