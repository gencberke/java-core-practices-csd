# MATH CLASS AND UTILITY METHODS

The `java.lang.Math` class contains various methods for mathematical operations. Some of these methods are used in many places, while others are used in mathematical operations. Here we will cover some of the most commonly used ones. A Java programmer should first look at this class when mathematical operations are needed, and use the elements of this class if available.

## Key Notes

- Classes in the `java.lang` package can be used directly with their class names without any import declaration.
- All members of the Math class are static, so the constructor is made private as a convention.
- Math class is a **utility class** - classes where all members are declared as static.

## Basic Mathematical Methods

### Square Root - sqrt()

The `sqrt` method of the Math class returns the square root of the double value it receives as a parameter. When a negative argument is passed to the sqrt method, it returns NaN value.

```java
public class SqrtExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        double a = scanner.nextDouble();
        
        System.out.printf("sqrt(%.2f) = %.2f%n", a, Math.sqrt(a));
        
        // Examples with different values
        System.out.println("sqrt(16) = " + Math.sqrt(16));     // 4.0
        System.out.println("sqrt(2) = " + Math.sqrt(2));       // 1.414...
        System.out.println("sqrt(-1) = " + Math.sqrt(-1));     // NaN
    }
}
```

### Power - pow()

The `pow` method of the Math class is used for exponentiation.

```java
public class PowerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter base: ");
        double base = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter exponent: ");
        double exponent = Double.parseDouble(scanner.nextLine());
        
        System.out.printf("pow(%.2f, %.2f) = %.2f%n", base, exponent, Math.pow(base, exponent));
        
        // Common examples
        System.out.println("2^3 = " + Math.pow(2, 3));         // 8.0
        System.out.println("2^0.5 = " + Math.pow(2, 0.5));     // sqrt(2) = 1.414...
        System.out.println("10^-2 = " + Math.pow(10, -2));     // 0.01
    }
}
```

### Absolute Value - abs()

The Math class has absolute value methods for different number types.

```java
public class AbsoluteValueExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a negative number: ");
        int a = scanner.nextInt();
        
        System.out.printf("|%d| = %d%n", a, Math.abs(a));
        
        // Examples with different types
        System.out.println("|−5| = " + Math.abs(-5));           // 5
        System.out.println("|−3.14| = " + Math.abs(-3.14));     // 3.14
        System.out.println("|−5L| = " + Math.abs(-5L));         // 5L
    }
}
```

### Minimum and Maximum - min() and max()

```java
public class MinMaxExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter three numbers: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        
        System.out.printf("min(%d, %d, %d) = %d%n", a, b, c, Math.min(a, Math.min(b, c)));
        System.out.printf("max(%d, %d, %d) = %d%n", a, b, c, Math.max(a, Math.max(b, c)));
        
        // Finding min/max of multiple numbers using a utility method
        int minimum = findMin(a, b, c);
        int maximum = findMax(a, b, c);
        System.out.println("Using utility methods - Min: " + minimum + ", Max: " + maximum);
    }
    
    public static int findMin(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
    
    public static int findMax(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
}
```

## Rounding Methods

Methods that perform rounding to integer values.

```java
public class RoundingExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        double a = scanner.nextDouble();
        
        System.out.printf("round(%.2f) = %d%n", a, Math.round(a));    // Returns long
        System.out.printf("rint(%.2f) = %.2f%n", a, Math.rint(a));    // Returns double
        System.out.printf("ceil(%.2f) = %.2f%n", a, Math.ceil(a));    // Ceiling (round up)
        System.out.printf("floor(%.2f) = %.2f%n", a, Math.floor(a));  // Floor (round down)
        
        // Examples
        double value = 3.7;
        System.out.println("\\nExamples with " + value + ":");
        System.out.println("round: " + Math.round(value));  // 4
        System.out.println("ceil: " + Math.ceil(value));    // 4.0
        System.out.println("floor: " + Math.floor(value));  // 3.0
        
        value = -2.3;
        System.out.println("\\nExamples with " + value + ":");
        System.out.println("round: " + Math.round(value));  // -2
        System.out.println("ceil: " + Math.ceil(value));    // -2.0 (towards positive infinity)
        System.out.println("floor: " + Math.floor(value));  // -3.0 (towards negative infinity)
    }
}
```

## Mathematical Constants

The Math class has predefined mathematical constants.

```java
public class MathConstants {
    public static void main(String[] args) {
        System.out.println("π (PI) = " + Math.PI);
        System.out.println("e (Euler's number) = " + Math.E);
        
        // Using constants in calculations
        double radius = 5.0;
        double circumference = 2 * Math.PI * radius;
        double area = Math.PI * radius * radius;
        
        System.out.printf("\\nCircle with radius %.1f:%n", radius);
        System.out.printf("Circumference = %.2f%n", circumference);
        System.out.printf("Area = %.2f%n", area);
    }
}
```

## Trigonometric Functions

Trigonometric methods in the Math class work with radians.

```java
public class TrigonometryExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter angle in degrees: ");
        double degrees = scanner.nextDouble();
        double radians = Math.toRadians(degrees);
        
        System.out.printf("sin(%.1f°) = %.4f%n", degrees, Math.sin(radians));
        System.out.printf("cos(%.1f°) = %.4f%n", degrees, Math.cos(radians));
        System.out.printf("tan(%.1f°) = %.4f%n", degrees, Math.tan(radians));
        
        // Common angles
        System.out.println("\\nCommon angles:");
        printTrigValues(0);
        printTrigValues(30);
        printTrigValues(45);
        printTrigValues(60);
        printTrigValues(90);
    }
    
    public static void printTrigValues(double degrees) {
        double radians = Math.toRadians(degrees);
        System.out.printf("%.0f°: sin=%.3f, cos=%.3f, tan=%.3f%n", 
                         degrees, Math.sin(radians), Math.cos(radians), Math.tan(radians));
    }
}
```

## Logarithmic Functions

The Math class has logarithmic function methods: `log`, `log10`, `log1p`.

```java
public class LogarithmExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        double a = scanner.nextDouble();
        
        System.out.printf("log(%.2f) = %.6f%n", a, Math.log(a));      // Natural logarithm (base e)
        System.out.printf("log10(%.2f) = %.6f%n", a, Math.log10(a));  // Common logarithm (base 10)
        System.out.printf("log1p(%.2f) = %.6f%n", a, Math.log1p(a));  // log(1 + a)
        
        // Examples
        System.out.println("\\nCommon logarithms:");
        System.out.println("ln(e) = " + Math.log(Math.E));           // 1.0
        System.out.println("log10(100) = " + Math.log10(100));       // 2.0
        System.out.println("log10(1000) = " + Math.log10(1000));     // 3.0
    }
}
```

## Practical Application: Distance Calculator

A class work example: Write a method called `distance` in a class called `PointUtil` that returns the Euclidean distance between two points based on coordinate information of two points it receives as parameters.

```java
public class PointUtilTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter coordinates of two points (x1 y1 x2 y2): ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        
        double distance = PointUtil.distance(x1, y1, x2, y2);
        
        System.out.printf("Distance between (%.1f, %.1f) and (%.1f, %.1f) = %.2f%n", 
                         x1, y1, x2, y2, distance);
    }
}

class PointUtil {
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    // Alternative implementation without Math.pow (more efficient)
    public static double distanceEfficient(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```

## Additional Utility Methods

```java
public class AdditionalMathMethods {
    public static void main(String[] args) {
        // Sign function
        System.out.println("signum(5.7) = " + Math.signum(5.7));     // 1.0
        System.out.println("signum(-3.2) = " + Math.signum(-3.2));   // -1.0
        System.out.println("signum(0) = " + Math.signum(0));         // 0.0
        
        // Hyperbolic functions
        System.out.println("sinh(1) = " + Math.sinh(1));
        System.out.println("cosh(1) = " + Math.cosh(1));
        System.out.println("tanh(1) = " + Math.tanh(1));
        
        // Random number (0.0 to 1.0)
        System.out.println("Random: " + Math.random());
        
        // Convert to int for dice roll (1-6)
        int diceRoll = (int)(Math.random() * 6) + 1;
        System.out.println("Dice roll: " + diceRoll);
    }
}
```

## Key Benefits of Using Math Class

1. **Readability/Comprehensibility**: Method names clearly indicate operations
2. **Tested and Efficient**: Methods are well-tested and optimized
3. **Portability**: Works consistently across different platforms
4. **Focus on Domain**: Allows programmers to focus on problem-solving
5. **Standard Implementation**: Avoids reinventing mathematical operations

## Best Practices

1. **Always prefer Math class methods** over custom implementations for standard operations
2. **Use Math.PI and Math.E** instead of hardcoded values
3. **Be aware of edge cases** (like negative values for sqrt)
4. **Consider precision** when using floating-point calculations
5. **Use appropriate method overloads** for different numeric types

The Math class is fundamental for any mathematical calculations in Java and should be the first resource when mathematical operations are needed.
