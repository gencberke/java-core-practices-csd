# SCANNER USAGE AND USER INPUT

Reading from the keyboard is actually writing to a file called **stdin** (standard input). The results presented to the user in the IDE console are from **stdout** (standard output). When using the Scanner class, you need to specify what type of data you expect to receive. The Scanner usage differs between when you need to use whitespace and when you need to press enter.

These methods don't directly interact with the keyboard. For example, `parseInt` tries to convert a pattern of text to an integer pattern, while `nextLine` actually reads text from the keyboard.

## Scanner Class Usage

The `Scanner` class is used for reading input from various sources, primarily from the keyboard (standard input).

### Basic Scanner Setup

```java
import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());
        
        System.out.printf("Hello %s, you are %d years old%n", name, age);
    }
}
```

### Different Input Methods

#### Using nextLine() with Parsing (Recommended)

```java
public class InputExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter a decimal: ");
        double decimal = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        System.out.printf("Number: %d, Decimal: %.2f, Text: %s%n", 
                         number, decimal, text);
    }
}
```

#### Using Direct Scanner Methods

```java
public class DirectScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();
        
        System.out.print("Enter a double: ");
        double dbl = scanner.nextDouble();
        
        // Note: May need to consume remaining newline
        scanner.nextLine(); // Consume the leftover newline
        
        System.out.print("Enter a line of text: ");
        String line = scanner.nextLine();
        
        System.out.printf("Integer: %d, Double: %.2f, Text: %s%n", 
                         num, dbl, line);
    }
}
```

### Important Notes About Scanner

1. **Mixed Input Issues**: When using `nextInt()`, `nextDouble()`, etc., followed by `nextLine()`, you may encounter issues because these methods don't consume the newline character.

2. **Recommended Approach**: Use `nextLine()` for all input and parse as needed using wrapper class methods like `Integer.parseInt()`, `Double.parseDouble()`.

3. **Buffer Issues**: Some systems may have buffering issues when mixing different Scanner methods.

### Input Validation Example

```java
public class ValidatedInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int age = getValidAge(scanner);
        System.out.printf("Valid age entered: %d%n", age);
    }
    
    public static int getValidAge(Scanner scanner) {
        while (true) {
            System.out.print("Enter your age (0-150): ");
            try {
                int age = Integer.parseInt(scanner.nextLine());
                if (age >= 0 && age <= 150) {
                    return age;
                }
                System.out.println("Age must be between 0 and 150");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}
```

### Reading Multiple Values from One Line

```java
public class MultipleValuesExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter three numbers separated by spaces: ");
        String line = scanner.nextLine();
        
        Scanner lineScanner = new Scanner(line);
        int a = lineScanner.nextInt();
        int b = lineScanner.nextInt();
        int c = lineScanner.nextInt();
        
        System.out.printf("Numbers: %d, %d, %d%n", a, b, c);
        System.out.printf("Sum: %d%n", a + b + c);
        
        lineScanner.close();
    }
}
```

### File Input with Scanner

```java
import java.io.File;
import java.io.FileNotFoundException;

public class FileInputExample {
    public static void main(String[] args) {
        try {
            Scanner fileScanner = new Scanner(new File("input.txt"));
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Read: " + line);
            }
            
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
```

## Best Practices

1. **Always use `nextLine()` for consistent input handling**
2. **Parse strings to required types using wrapper class methods**
3. **Validate input before processing**
4. **Close Scanner when reading from files**
5. **Handle exceptions when parsing numbers**
6. **Use try-with-resources for automatic resource management**

### Try-with-Resources Example

```java
import java.util.Scanner;

public class TryWithResourcesExample {
    public static void readInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int number = Integer.parseInt(scanner.nextLine());
            System.out.println("You entered: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
        // Scanner is automatically closed
    }
}
```

The Scanner class is fundamental for user interaction in console applications and provides flexible methods for reading different types of input.
