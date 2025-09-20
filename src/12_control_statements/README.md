# CONTROL STATEMENTS

**Control Statements**: Parts of a program that are executed are called statements. A program runs by executing statements. Control statements direct the flow of execution.

In Java, statements are:
- **Simple statements** (simple statements): Created by putting a semicolon at the end of an expression
- **Compound statements** (compound statements): Statements written within "{" and "}" (block)
- **Declaration statements** (declaration statements): Statements where variables are declared
- **Control statements** (control statements): Statements that direct the flow
- **Empty statement** (null/empty statements): Consists of using semicolon alone

## IF STATEMENT

The `if` statement is **not a loop**! It's a control statement that directs the flow based on a condition.

### Basic If Statement

```java
public class BasicIfExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (number % 2 == 0) {
            System.out.println("The number is even");
            number /= 2;
        } else {
            System.out.println("The number is odd");
            number *= 2;
        }
        
        System.out.printf("Result: %d%n", number);
    }
}
```

### If-Else If-Else Chain

```java
public class IfElseChain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (number > 0) {
            System.out.println("Positive number");
        } else if (number == 0) {
            System.out.println("Zero");
        } else {
            System.out.println("Negative number");
        }
    }
}
```

### Boolean Expression Guidelines

For boolean expressions in conditions, avoid using == or != operators directly:

```java
public class BooleanConditions {
    public static void main(String[] args) {
        boolean isEven = true;
        
        // GOOD: Direct boolean evaluation
        if (isEven) {
            System.out.println("Number is even");
        }
        
        // AVOID: Unnecessary comparison
        // if (isEven == true) { ... }
        
        // GOOD: Negation
        if (!isEven) {
            System.out.println("Number is odd");
        }
        
        // AVOID: Unnecessary comparison
        // if (isEven == false) { ... }
    }
}
```

### Predicate Methods

Methods that return boolean values are called **predicate methods**. They typically start with "is", "has", "can", etc.

```java
public class PredicateMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (NumberUtil.isEven(number)) {
            System.out.printf("%d is even%n", number);
        } else {
            System.out.printf("%d is odd%n", number);
        }
        
        if (NumberUtil.isPrime(number)) {
            System.out.printf("%d is prime%n", number);
        } else {
            System.out.printf("%d is not prime%n", number);
        }
    }
}

class NumberUtil {
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    public static boolean isOdd(int number) {
        return !isEven(number);
    }
    
    public static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
```

## WHILE LOOPS

### While Loop

The `while` loop repeats as long as the condition is true.

```java
public class WhileLoopExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        
        int i = 0;
        while (i < n) {
            System.out.printf("%d ", i);
            i++;
        }
        System.out.printf("%nLoop finished, i = %d%n", i);
    }
}
```

### Counting Digits Example

```java
public class DigitCounter {
    public static void main(String[] args) {
        NumberUtilTest.run();
    }
}

class NumberUtilTest {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        
        System.out.printf("Number of digits in %d: %d%n", n, NumberUtil.countDigits(n));
    }
}

class NumberUtil {
    public static int countDigits(int n) {
        if (n == 0) return 1;
        
        int count = 0;
        n = Math.abs(n);  // Handle negative numbers
        
        while (n != 0) {
            count++;
            n /= 10;
        }
        return count;
    }
}
```

### Do-While Loop

The `do-while` loop executes at least once, then checks the condition.

```java
public class DoWhileExample {
    public static void main(String[] args) {
        NumberUtilTest.run();
    }
}

class NumberUtilTest {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int value;
        
        do {
            System.out.print("Enter a number (0 to exit): ");
            value = Integer.parseInt(scanner.nextLine());
            
            if (value != 0) {
                System.out.printf("Number of digits in %d: %d%n", 
                                value, NumberUtil.countDigits(value));
            }
        } while (value != 0);
        
        System.out.println("Program ended.");
    }
}

class NumberUtil {
    public static int countDigits(int value) {
        int count = 0;
        value = Math.abs(value);
        
        do {
            count++;
            value /= 10;
        } while (value != 0);
        
        return count;
    }
}
```

### Input Validation with While Loop

```java
public class InputValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = 0;
        int value;
        
        System.out.println("Enter numbers (0 to finish):");
        while ((value = Integer.parseInt(scanner.nextLine())) != 0) {
            total += value;
        }
        
        System.out.printf("Total: %d%n", total);
    }
}
```

## FOR LOOPS

The `for` loop is the most versatile loop statement that can encompass other loop types.

### Basic For Loop Structure

```
for ([initialization]; [condition]; [update])
    statement
```

```java
public class BasicForLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        
        // Standard for loop
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        
        // Reverse order
        for (int i = n - 1; i >= 0; i--) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
}
```

### Multiple Variables in For Loop

```java
public class MultipleVariablesFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter two numbers: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        // Multiple initialization and update
        for (int i = 0, j = m - 1; i < n && j >= 0; i++, j--) {
            System.out.printf("i=%d, j=%d%n", i, j);
        }
    }
}
```

### Complex For Loop Example

```java
public class ComplexForLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = 0;
        int val;
        
        for (System.out.println("Enter numbers:"), System.out.print("Number: ");
             (val = Integer.parseInt(scanner.nextLine())) != 0;
             total += val, System.out.print("Number: ")) {
            // Empty body - all work done in for statement parts
        }
        
        System.out.printf("Total: %d%n", total);
    }
}
```

## NESTED LOOPS

```java
public class NestedLoops {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter dimensions (rows cols): ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        
        // Multiplication table
        System.out.println("Coordinate pairs:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("(%d,%d) ", i, j);
            }
            System.out.println();
        }
    }
}
```

## BREAK AND CONTINUE

### Break Statement

```java
public class BreakExample {
    public static void main(String[] args) {
        // Simple break
        for (int i = 0; i < 10; i++) {
            if (i == 5) break;
            System.out.print(i + " ");
        }
        System.out.println("\\nBreak example finished");
        
        // Break with label (for nested loops)
        outerLoop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("(%d,%d) ", i, j);
                if ((i + j) % 3 == 0 && i > 0) {
                    System.out.println("\\nBreaking outer loop");
                    break outerLoop;
                }
            }
            System.out.println();
        }
    }
}
```

### Continue Statement

```java
public class ContinueExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter two numbers: ");
        int min = scanner.nextInt();
        int max = scanner.nextInt();
        
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        
        System.out.println("Odd numbers in range:");
        for (int i = min; i <= max; i++) {
            if (i % 2 == 0) continue;  // Skip even numbers
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
```

## SWITCH STATEMENT

### Basic Switch Statement

```java
public class BasicSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a day number (1-7): ");
        int day = scanner.nextInt();
        
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day number");
        }
    }
}
```

### Switch with Fall-through

```java
public class SwitchFallthrough {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a month number: ");
        int month = scanner.nextInt();
        
        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("Winter");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("Spring");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("Summer");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("Autumn");
                break;
            default:
                System.out.println("Invalid month");
        }
    }
}
```

### Switch Expression (Java 14+)

```java
public class SwitchExpression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a grade (A-F): ");
        String grade = scanner.nextLine().toUpperCase();
        
        // Switch expression
        String result = switch (grade) {
            case "A" -> "Excellent";
            case "B" -> "Good";
            case "C" -> "Average";
            case "D" -> "Below Average";
            case "F" -> "Fail";
            default -> "Invalid grade";
        };
        
        System.out.println("Result: " + result);
        
        // Switch expression with block
        int points = switch (grade) {
            case "A" -> {
                System.out.println("Outstanding performance!");
                yield 4;
            }
            case "B" -> 3;
            case "C" -> 2;
            case "D" -> 1;
            case "F" -> 0;
            default -> -1;
        };
        
        System.out.println("Grade points: " + points);
    }
}
```

## Practical Examples

### Prime Number Checker

```java
public class PrimeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (NumberUtil.isPrime(number)) {
            System.out.printf("%d is prime%n", number);
        } else {
            System.out.printf("%d is not prime%n", number);
        }
    }
}

class NumberUtil {
    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        if (n % 3 == 0) return n == 3;
        if (n % 5 == 0) return n == 5;
        if (n % 7 == 0) return n == 7;
        
        for (long i = 11; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

### Factorial Calculator

```java
public class FactorialCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        
        for (int i = 0; i <= n; i++) {
            System.out.printf("%d! = %d%n", i, NumberUtil.factorial(i));
        }
    }
}

class NumberUtil {
    public static long factorial(int number) {
        if (number < 0) return -1;  // Invalid input
        
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}
```

## Key Points

1. **If statements direct flow based on conditions**
2. **While loops continue while condition is true**
3. **Do-while loops execute at least once**
4. **For loops are the most versatile loop type**
5. **Break exits loops or switch statements**
6. **Continue skips current iteration**
7. **Switch statements work with discrete values**
8. **Switch expressions (Java 14+) return values**
9. **Nested loops require careful break/continue handling**
10. **Use appropriate control structure for each situation**

Control statements are essential for creating programs that can make decisions and repeat operations, forming the foundation of program logic and flow control.
