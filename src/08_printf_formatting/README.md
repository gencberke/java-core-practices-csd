# PRINTF FORMATTING AND FORMAT SPECIFIERS

The `System.out.printf` method allows formatted printing (like dates 22.02.2024, 3.45, etc.). The `printf` method, like others that use printf formatting, is important to learn for printf method formatting details.

The first parameter of the printf method can be a string literal (text). This method is designed to take a variable number of arguments along with the first parameter. Details of methods that take a variable number of arguments (vararg methods) will not be covered in this section.

## Format Specifiers

In the text related to the first parameter of the printf method, special characters can be used together with the `%` character. These special characters are called **format specifiers**. Using a format character together with the `%` character is called a **place holder**. A format character corresponds to a type (except for some special ones), and the text is formatted by placing the values of the arguments passed to printf in place of that placeholder.

### Basic Format Specifiers

| Specifier | Type | Description |
|-----------|------|-------------|
| `%d` | Integer types | Decimal formatting of numbers |
| `%x` | Integer types | Hexadecimal formatting (lowercase) |
| `%X` | Integer types | Hexadecimal formatting (uppercase) |
| `%o` | Integer types | Octal formatting |
| `%f` | Floating-point | Decimal point formatting |
| `%e` | Floating-point | Scientific notation (lowercase) |
| `%E` | Floating-point | Scientific notation (uppercase) |
| `%g` | Floating-point | General format (shorter of %f or %e) |
| `%G` | Floating-point | General format (shorter of %f or %E) |
| `%c` | char | Character formatting |
| `%s` | String | String formatting |
| `%b` | boolean | Boolean formatting |
| `%n` | - | Platform-specific line separator |
| `%%` | - | Literal % character |

## Integer Format Specifiers Examples

```java
public class IntegerFormatting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter second number: ");
        int y = Integer.parseInt(scanner.nextLine());
        
        // Different number base formatting
        System.out.printf("%d + %d = %d%n", x, y, (x + y));           // Decimal
        System.out.printf("%x + %x = %x%n", x, y, (x + y));           // Hexadecimal (lowercase)
        System.out.printf("%X + %X = %X%n", x, y, (x + y));           // Hexadecimal (uppercase)
        System.out.printf("%o + %o = %o%n", x, y, (x + y));           // Octal
    }
}
```

## Width and Padding

For integer-related format characters, a number written between `%` and the format character can be used for alignment. This number must be positive. When only a positive number is written between `%` and the format character, if the digit count of the formatted number is smaller than the number between `%` and the format character, space characters are added to the left. If a `0` is written before the number between `%` and the format character, formatting is done with `0` value instead of space.

```java
public class WidthAndPadding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter date information as day month year: ");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        
        // Zero-padded formatting
        System.out.printf("%02d/%02d/%04d%n", day, month, year);
        
        // Space-padded formatting
        System.out.printf("%2d/%2d/%4d%n", day, month, year);
    }
}
```

## Floating-Point Formatting

For the `%f` format character, when a `.` and a positive number are written between `%` and `f`, it determines how many digits the decimal part of the real number to be formatted will be shown. The remaining digits of the real number are formatted by scientific rounding.

```java
public class FloatingPointFormatting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        double x = scanner.nextDouble();
        
        System.out.printf("%.2f%n", x);  // 2 decimal places
        System.out.printf("%.4f%n", x);  // 4 decimal places
        System.out.printf("%10.2f%n", x); // Width 10, 2 decimal places
    }
}
```

## Character and Boolean Formatting

```java
public class CharBoolFormatting {
    public static void main(String[] args) {
        char c = 68;  // ASCII value for 'D'
        boolean flag1 = true;
        boolean flag2 = false;
        
        System.out.printf("c = %c%n", c);  // Outputs: c = D
        System.out.printf("Flag1: %b%n", flag1);  // Outputs: Flag1: true
        System.out.printf("Flag2: %b%n", flag2);  // Outputs: Flag2: false
    }
}
```

## Special Values and Percent Character

```java
public class SpecialFormatting {
    public static void main(String[] args) {
        // IEEE 754 special values
        double positive = 5.0 / 0.0;    // Infinity
        double negative = -5.0 / 0.0;   // -Infinity
        double notANumber = 0.0 / 0.0;  // NaN
        
        System.out.printf("Positive infinity: %f%n", positive);
        System.out.printf("Negative infinity: %f%n", negative);
        System.out.printf("Not a number: %f%n", notANumber);
        
        // Percent character formatting
        int percentage = 85;
        System.out.printf("Score: %d%%%n", percentage);  // Outputs: Score: 85%
    }
}
```

## Advanced Formatting Examples

### Table Formatting

```java
public class TableFormatting {
    public static void main(String[] args) {
        System.out.printf("%10s %10s %10s%n", "Name", "Age", "Salary");
        System.out.printf("%10s %10s %10s%n", "----------", "---", "------");
        System.out.printf("%10s %10d $%9.2f%n", "John", 25, 50000.00);
        System.out.printf("%10s %10d $%9.2f%n", "Alice", 30, 75000.50);
        System.out.printf("%10s %10d $%9.2f%n", "Bob", 35, 90000.75);
    }
}
```

### Left and Right Alignment

```java
public class Alignment {
    public static void main(String[] args) {
        String[] names = {"John", "Alice", "Bob", "Charlie"};
        int[] ages = {25, 30, 35, 28};
        
        System.out.println("Right-aligned (default):");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%10s: %3d years old%n", names[i], ages[i]);
        }
        
        System.out.println("\nLeft-aligned:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-10s: %d years old%n", names[i], ages[i]);
        }
    }
}
```

## Key Points

1. **Format specifiers** determine how data is displayed
2. **Width specifiers** control field width and alignment
3. **Precision specifiers** control decimal places for floating-point numbers
4. **Zero-padding** adds leading zeros to numbers
5. **Left alignment** uses the `-` flag
6. **The `%n` specifier** provides platform-independent line breaks
7. **Special values** like Infinity and NaN are handled automatically

Printf formatting is essential for creating readable, professional-looking console output.
