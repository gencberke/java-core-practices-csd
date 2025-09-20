# RANDOM NUMBER GENERATION

Random number generation is widely used in the computer world. For example, in a game, random numbers can be generated to create and move various characters (game objects) randomly. Random numbers are also generated in simulation programs.

True randomness in real life cannot be implemented in the computer world. In this sense, random numbers can be generated deterministically (with mathematical operations) in the computer world. For this reason, random numbers generated in the computer world are also called **"pseudo random numbers"**.

## Quality of Random Number Generation

The quality of random number generation is important in the computer world. As the operations related to random number generation become more complex within a certain logical framework, the quality generally increases, but in this case, there is also a cost related to the operations performed. In this sense, scientific studies on random number generation in the computer world are still ongoing.

## Java Random Number Generation

Java has many classes for random number generation. Especially with Java 17, additions have been made to JavaSE for generating random numbers according to various algorithms. The most basic class for random number generation in Java is the **Random** class in the java.util package. The random number generation of this class is medium quality and is often sufficient. When it's not sufficient, the programmer turns to others.

**Note**: Especially from Java 17 onwards, random number generation related to the Random class is also called **"legacy random"**.

The Random class uses the algorithm described in the "seminumerical algorithms" section of Donald Knuth's "The Art of Computer Programming" book series, volume 2.

## Seed Values

In computer world random number generation, a value called **"seed value"** is used. Random number generation actually starts with this value and this value is updated with each generation. So the seed value is used in mathematical operations related to random number generation.

### Default Constructor Behavior

When an object is created with the default constructor of the `java.util.Random` class, the seed value tends to be as different as possible from the seed values of objects previously created using the default constructor in that application.

### Deterministic Behavior with Fixed Seed

When the same seed value is used, the same sequence of random numbers will be generated. This is useful for:
- **Testing**: Reproducible test scenarios
- **Debugging**: Consistent behavior during development
- **Simulations**: Repeatable experiments

## Basic Random Methods

### nextInt() Methods

```java
import java.util.Random;

public class BasicRandomExample {
    public static void main(String[] args) {
        Random random = new Random();
        
        // nextInt() - returns int within full int range
        System.out.println("Random int: " + random.nextInt());
        
        // nextInt(bound) - returns int from 0 to bound-1
        System.out.println("Random int (0-99): " + random.nextInt(100));
        
        // Multiple random numbers in range
        System.out.println("Five random numbers (1-6):");
        for (int i = 0; i < 5; i++) {
            int diceRoll = random.nextInt(6) + 1;  // 1 to 6
            System.out.print(diceRoll + " ");
        }
        System.out.println();
    }
}
```

### nextInt(origin, bound) - Java 17+

```java
public class RangeRandomExample {
    public static void main(String[] args) {
        Random random = new Random();
        
        // nextInt(origin, bound) - Java 17+ feature
        // Returns random int in range [origin, bound)
        System.out.println("Random numbers between 10 and 20:");
        for (int i = 0; i < 10; i++) {
            int randomInRange = random.nextInt(10, 20);  // 10 to 19
            System.out.print(randomInRange + " ");
        }
        System.out.println();
        
        // Negative ranges also work
        System.out.println("Random numbers between -5 and 5:");
        for (int i = 0; i < 10; i++) {
            int randomNegPos = random.nextInt(-5, 5);  // -5 to 4
            System.out.print(randomNegPos + " ");
        }
        System.out.println();
    }
}
```

### Other Numeric Types

```java
public class NumericRandomExample {
    public static void main(String[] args) {
        Random random = new Random();
        
        // nextLong() - random long value
        System.out.println("Random long: " + random.nextLong());
        
        // nextDouble() - random double in range [0.0, 1.0)
        System.out.println("Random double: " + random.nextDouble());
        
        // nextFloat() - random float in range [0.0, 1.0)
        System.out.println("Random float: " + random.nextFloat());
        
        // Custom range for double
        double min = 1.5, max = 10.5;
        double randomDouble = random.nextDouble() * (max - min) + min;
        System.out.printf("Random double (%.1f to %.1f): %.2f%n", min, max, randomDouble);
    }
}
```

### Boolean Random Values

```java
public class BooleanRandomExample {
    public static void main(String[] args) {
        Random random = new Random();
        
        // nextBoolean() - random true/false
        System.out.println("Random decisions:");
        for (int i = 0; i < 10; i++) {
            boolean decision = random.nextBoolean();
            System.out.println("Decision " + (i + 1) + ": " + (decision ? "Yes" : "No"));
        }
        
        // Simulating coin flips
        System.out.println("\\nCoin flips:");
        int heads = 0, tails = 0;
        for (int i = 0; i < 100; i++) {
            if (random.nextBoolean()) {
                heads++;
            } else {
                tails++;
            }
        }
        System.out.printf("Heads: %d, Tails: %d%n", heads, tails);
    }
}
```

## Seed Values and Reproducibility

### Fixed Seed Example

```java
public class SeedExample {
    public static void main(String[] args) {
        // Using fixed seed for reproducible results
        long seed = 12345L;
        
        System.out.println("First run with seed " + seed + ":");
        generateSequence(seed);
        
        System.out.println("\\nSecond run with same seed:");
        generateSequence(seed);
        
        System.out.println("\\nThird run with different seed:");
        generateSequence(54321L);
    }
    
    public static void generateSequence(long seed) {
        Random random = new Random(seed);
        for (int i = 0; i < 5; i++) {
            System.out.print(random.nextInt(100) + " ");
        }
        System.out.println();
    }
}
```

### setSeed() Method

```java
public class SetSeedExample {
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generate some random numbers
        System.out.println("Initial random numbers:");
        for (int i = 0; i < 3; i++) {
            System.out.print(random.nextInt(100) + " ");
        }
        System.out.println();
        
        // Reset seed and generate again
        random.setSeed(12345L);
        System.out.println("After setSeed(12345):");
        for (int i = 0; i < 3; i++) {
            System.out.print(random.nextInt(100) + " ");
        }
        System.out.println();
        
        // Reset same seed again
        random.setSeed(12345L);
        System.out.println("After setSeed(12345) again:");
        for (int i = 0; i < 3; i++) {
            System.out.print(random.nextInt(100) + " ");
        }
        System.out.println();
    }
}
```

## Practical Applications

### Dice Simulation

```java
public class DiceSimulation {
    public static void main(String[] args) {
        Random random = new Random();
        int[] frequency = new int[7];  // Index 0 unused, 1-6 for dice faces
        int numRolls = 10000;
        
        // Roll dice many times
        for (int i = 0; i < numRolls; i++) {
            int roll = random.nextInt(6) + 1;
            frequency[roll]++;
        }
        
        // Display results
        System.out.println("Dice roll frequency (" + numRolls + " rolls):");
        for (int i = 1; i <= 6; i++) {
            double percentage = (frequency[i] * 100.0) / numRolls;
            System.out.printf("Face %d: %d times (%.2f%%)%n", i, frequency[i], percentage);
        }
    }
}
```

### Password Generator

```java
public class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARS;
    
    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator();
        
        System.out.println("Generated passwords:");
        for (int i = 0; i < 5; i++) {
            String password = generator.generatePassword(12);
            System.out.println(password);
        }
    }
    
    public String generatePassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALL_CHARS.length());
            password.append(ALL_CHARS.charAt(index));
        }
        
        return password.toString();
    }
}
```

### Random Array Generation

```java
public class RandomArrayExample {
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generate random integer array
        int[] numbers = generateRandomArray(10, 1, 100, random);
        System.out.println("Random integers (1-100):");
        printArray(numbers);
        
        // Generate random double array
        double[] decimals = generateRandomDoubleArray(8, 0.0, 10.0, random);
        System.out.println("\\nRandom decimals (0.0-10.0):");
        printArray(decimals);
    }
    
    public static int[] generateRandomArray(int size, int min, int max, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
    
    public static double[] generateRandomDoubleArray(int size, double min, double max, Random random) {
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble() * (max - min) + min;
        }
        return array;
    }
    
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    
    public static void printArray(double[] array) {
        for (double value : array) {
            System.out.printf("%.2f ", value);
        }
        System.out.println();
    }
}
```

### Random Selection from Arrays

```java
public class RandomSelection {
    public static void main(String[] args) {
        Random random = new Random();
        
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"};
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Purple", "Orange"};
        
        System.out.println("Random selections:");
        for (int i = 0; i < 10; i++) {
            String randomName = selectRandom(names, random);
            String randomColor = selectRandom(colors, random);
            System.out.printf("Person: %s, Favorite color: %s%n", randomName, randomColor);
        }
    }
    
    public static <T> T selectRandom(T[] array, Random random) {
        int index = random.nextInt(array.length);
        return array[index];
    }
}
```

### Lottery Number Generator

```java
public class LotteryGenerator {
    public static void main(String[] args) {
        LotteryGenerator lottery = new LotteryGenerator();
        
        System.out.println("Lottery number sets:");
        for (int i = 0; i < 5; i++) {
            int[] numbers = lottery.generateLotteryNumbers(6, 1, 49);
            System.out.print("Set " + (i + 1) + ": ");
            printSortedArray(numbers);
        }
    }
    
    public int[] generateLotteryNumbers(int count, int min, int max) {
        Random random = new Random();
        int[] numbers = new int[count];
        boolean[] used = new boolean[max - min + 1];
        
        for (int i = 0; i < count; i++) {
            int number;
            do {
                number = random.nextInt(max - min + 1) + min;
            } while (used[number - min]);
            
            used[number - min] = true;
            numbers[i] = number;
        }
        
        return numbers;
    }
    
    public static void printSortedArray(int[] array) {
        java.util.Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(array[i]);
        }
        System.out.println();
    }
}
```

## Best Practices

### 1. Reuse Random Objects

```java
public class RandomBestPractices {
    private static final Random random = new Random();  // Reuse instance
    
    public static void main(String[] args) {
        // GOOD: Reuse the same Random instance
        for (int i = 0; i < 5; i++) {
            System.out.println(generateRandomNumber());
        }
    }
    
    public static int generateRandomNumber() {
        return random.nextInt(100);  // Use the shared instance
    }
    
    // AVOID: Creating new Random objects repeatedly
    public static int inefficientRandomNumber() {
        Random r = new Random();  // Don't do this in loops
        return r.nextInt(100);
    }
}
```

### 2. Testing with Fixed Seeds

```java
public class TestableRandomCode {
    private Random random;
    
    public TestableRandomCode() {
        this.random = new Random();  // Default constructor for production
    }
    
    public TestableRandomCode(long seed) {
        this.random = new Random(seed);  // Fixed seed for testing
    }
    
    public int rollDice() {
        return random.nextInt(6) + 1;
    }
    
    public static void main(String[] args) {
        // Production usage
        TestableRandomCode game = new TestableRandomCode();
        System.out.println("Production roll: " + game.rollDice());
        
        // Testing usage
        TestableRandomCode testGame = new TestableRandomCode(12345L);
        System.out.println("Test roll (reproducible): " + testGame.rollDice());
    }
}
```

## Math.random() Alternative

Java also provides `Math.random()` which returns a double between 0.0 and 1.0:

```java
public class MathRandomExample {
    public static void main(String[] args) {
        // Math.random() returns double [0.0, 1.0)
        System.out.println("Math.random(): " + Math.random());
        
        // Convert to integer range
        int randomInt = (int)(Math.random() * 100);  // 0 to 99
        System.out.println("Random int (0-99): " + randomInt);
        
        // Random in specific range
        int min = 10, max = 20;
        int randomRange = (int)(Math.random() * (max - min + 1)) + min;
        System.out.println("Random int (10-20): " + randomRange);
        
        // Note: Math.random() is less flexible than Random class
        // and cannot be seeded for reproducible results
    }
}
```

## Key Points

1. **Random class provides flexible random number generation**
2. **Use seed values for reproducible sequences in testing**
3. **Default constructor creates different sequences each run**
4. **nextInt(bound) generates numbers in range [0, bound)**
5. **nextDouble() generates values in range [0.0, 1.0)**
6. **nextBoolean() generates random true/false values**
7. **Reuse Random objects for better performance**
8. **Fixed seeds enable deterministic testing**
9. **Quality is sufficient for most applications**
10. **Consider Java 17+ features for enhanced functionality**

Random number generation is essential for games, simulations, testing, and many other applications where unpredictable behavior is desired.
