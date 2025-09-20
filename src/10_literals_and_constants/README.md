# LITERALS AND CONSTANTS

**Literals (Constants)**: A value written directly in a program is called a literal (literal, constant).

**Key Note**: As remembered, numbers within quotes ("") are also called string literals.

Literals also have types. The types of literals are determined at compile time, i.e., by the compiler.

**Key Note**: The compiler's determination of an expression's type is called type inference/deduction.

## Rules for Determining Literal Types

The types of literals are determined according to the following rules:

### Integer Literals

- If a number **does not contain a decimal point** and **does not take any suffix** and **is within the limits of int type**, it is an **int type** literal
- If a number does not contain a decimal point but is **outside int type limits** (large numbers), it causes an error unless it has the L suffix
- If a number does not contain a decimal point and **takes the L suffix** (lowercase or uppercase), it is of **long type**. Example: `10L`
- If an L-suffixed literal is outside long type limits, an error occurs
- **Java has no byte and short type literals**

### Floating-Point Literals

- If a number **contains a decimal point** and **does not take any suffix**, it is of **double type**
- If a number contains or does not contain a decimal point and **takes the D suffix** (lowercase or uppercase), it is of **double type**
- If a number contains or does not contain a decimal point and **takes the F suffix** (lowercase or uppercase), it is of **float type**
- When a decimal-containing number that is not all zeros after the decimal point is suffixed with F and the number cannot be represented in float type, the number is rounded to the nearest value (rounding errors)

### Boolean Literals

- Boolean type has two literals: **`true`** and **`false`**

### Character Literals

- Expressions written with quotes are kept as **char type** including the quotes. Character literals are of char type
- Generally, a single character is written within single quotes, except for some special cases. Outside these situations, an error occurs
- A character literal corresponds to the sequence number of the relevant character in the character table. Actually, by writing a char type literal, we obtain the sequence number of the character related to that literal

## Examples

### Basic Literal Types

```java
public class BasicLiterals {
    public static void main(String[] args) {
        // Integer literals
        int a = 10;          // int literal
        long b = 10L;        // long literal
        long c = 1000000L;   // long literal (large number)
        
        // Floating-point literals
        double d = 3.14;     // double literal (default for decimals)
        double e = 3.14D;    // double literal (explicit)
        float f = 3.14F;     // float literal
        
        // Boolean literals
        boolean flag1 = true;
        boolean flag2 = false;
        
        // Character literals
        char ch1 = 'A';      // char literal
        char ch2 = '1';      // char literal (digit character)
        char ch3 = ' ';      // char literal (space character)
        
        System.out.println("Integer: " + a + ", Long: " + b);
        System.out.println("Double: " + d + ", Float: " + f);
        System.out.println("Boolean: " + flag1 + ", " + flag2);
        System.out.println("Characters: " + ch1 + ", " + ch2 + ", '" + ch3 + "'");
    }
}
```

### Precision Differences

```java
public class PrecisionExample {
    public static void main(String[] args) {
        // Demonstrating precision differences
        System.out.printf("%.20f%n", 3.4);    // double precision
        System.out.printf("%.20f%n", 3.4F);   // float precision (less precise)
        
        // Large numbers
        long bigNumber = 123456789012345L;     // Must use L suffix
        System.out.println("Big number: " + bigNumber);
        
        // This would cause error: int tooBig = 123456789012345; // Too big for int
    }
}
```

## Escape Sequences

Some characters cannot be written with keyboard key combinations, and some characters cannot be printed directly on the screen. Such characters cannot be written directly as character literals within ('') quotes. These characters are written as character literals within single quotes using the backslash character together with a special character.

Characters written with backslash are called **escape sequences**.

### Common Escape Sequences

| Escape Sequence | Description |
|----------------|-------------|
| `'\n'` | Line feed (newline) |
| `'\r'` | Carriage return |
| `'\t'` | Horizontal tab |
| `'\f'` | Form feed |
| `'\b'` | Backspace |
| `'\''` | Single quote |
| `'\"'` | Double quote |
| `'\\'` | Backslash |

### Escape Sequence Examples

```java
public class EscapeSequences {
    public static void main(String[] args) {
        char newline = '\n';
        char tab = '\t';
        char singleQuote = '\'';
        char doubleQuote = '\"';
        char backslash = '\\';
        
        System.out.print("Hello World");
        System.out.print(newline);
        System.out.print("Goodbye World");
        
        System.out.println("\nTab example:");
        System.out.println("Name:\tJohn");
        System.out.println("Age:\t25");
        
        System.out.println("\nQuote examples:");
        System.out.println("Single quote: " + singleQuote);
        System.out.println("Double quote: " + doubleQuote);
        System.out.println("Backslash: " + backslash);
    }
}
```

### String Literals with Escape Sequences

```java
public class StringEscapes {
    public static void main(String[] args) {
        // Single quote in string - can be used directly or as escape sequence
        System.out.println("'Zonguldak'");
        System.out.println("\'Zonguldak\'");
        
        // Double quote in string - must use escape sequence
        System.out.println("\"Zonguldak\"");
        
        // Path examples (Windows-style paths need escaped backslashes)
        System.out.println("File path: C:\\Users\\John\\Documents\\file.txt");
        
        // Multi-line effect using \n
        System.out.println("Line 1\nLine 2\nLine 3");
        
        // Tab-separated values
        System.out.println("Name\tAge\tCity");
        System.out.println("John\t25\tNew York");
        System.out.println("Alice\t30\tLondon");
    }
}
```

## Scientific Notation

Literals can be written in scientific/exponential notation. Literals written this way are of double type.

```java
public class ScientificNotation {
    public static void main(String[] args) {
        // Scientific notation examples
        double avogadro = 6.02e+23;      // 6.02 × 10²³
        double planck = 6.62e-34;        // 6.62 × 10⁻³⁴
        double lightSpeed = 2.998e8;     // 2.998 × 10⁸
        
        System.out.printf("Avogadro's number: %e%n", avogadro);
        System.out.printf("Planck constant: %e%n", planck);
        System.out.printf("Speed of light: %e m/s%n", lightSpeed);
        
        // Both positive and negative exponents
        System.out.printf("Large number: %f%n", 1.5e6);     // 1,500,000
        System.out.printf("Small number: %f%n", 1.5e-6);    // 0.0000015
    }
}
```

## Underscore in Literals (Java 7+)

Since Java 7, the underscore character (_) can be used between digits in literals. The underscore character can be used between digits as desired. This usage improves readability depending on the situation. When the program runs, these underscore characters are ignored in the output.

```java
public class UnderscoreInLiterals {
    public static void main(String[] args) {
        // Using underscores for readability
        int million = 1_000_000;
        long billion = 1_000_000_000L;
        double pi = 3.141_592_653;
        
        // Credit card number format
        long creditCard = 1234_5678_9012_3456L;
        
        // Binary representation
        int binary = 0b1010_1100_0011_1111;
        
        // Hexadecimal with grouping
        int hex = 0xFF_EC_DE_5E;
        
        System.out.println("Million: " + million);
        System.out.println("Billion: " + billion);
        System.out.println("Pi: " + pi);
        System.out.println("Credit card: " + creditCard);
        System.out.printf("Binary: %d (decimal)%n", binary);
        System.out.printf("Hex: %d (decimal)%n", hex);
    }
}
```

## Number Systems

Integer literals can be represented in decimal, hexadecimal, octal, and (since Java 7) binary. If there is no prefix at the beginning of the literal, it's decimal; if there's 0x, it's hexadecimal; if there's 0, it's octal; and if there's 0b, it's binary. The digits of the number must be appropriate for the number system, otherwise an error occurs.

```java
public class NumberSystems {
    public static void main(String[] args) {
        // Different representations of the same number (42)
        int decimal = 42;        // Decimal
        int hex = 0x2A;         // Hexadecimal
        int octal = 052;        // Octal
        int binary = 0b101010;  // Binary
        
        System.out.println("Decimal: " + decimal);
        System.out.println("Hexadecimal: " + hex);
        System.out.println("Octal: " + octal);
        System.out.println("Binary: " + binary);
        
        // All should print the same value
        System.out.println("All equal? " + (decimal == hex && hex == octal && octal == binary));
        
        // More examples
        int[] values = {10, 0xA, 012, 0b1010};
        System.out.println("Different representations of 10:");
        for (int value : values) {
            System.out.println(value);
        }
    }
}
```

## Character Literals and ASCII Values

```java
public class CharacterLiterals {
    public static void main(String[] args) {
        // Character literals and their ASCII values
        char letter = 'A';
        char digit = '9';
        char symbol = '@';
        
        System.out.println("Character: " + letter + ", ASCII: " + (int)letter);
        System.out.println("Character: " + digit + ", ASCII: " + (int)digit);
        System.out.println("Character: " + symbol + ", ASCII: " + (int)symbol);
        
        // Creating characters from ASCII values
        char fromAscii1 = 65;   // 'A'
        char fromAscii2 = 97;   // 'a'
        
        System.out.println("From ASCII 65: " + fromAscii1);
        System.out.println("From ASCII 97: " + fromAscii2);
        
        // Character arithmetic
        char nextLetter = (char)(letter + 1);
        System.out.println("Next letter after " + letter + " is " + nextLetter);
    }
}
```

## Key Points

1. **Literal types are determined at compile time**
2. **Integer literals default to int**, use L suffix for long
3. **Decimal literals default to double**, use F suffix for float
4. **Character literals use single quotes**
5. **Escape sequences handle special characters**
6. **Underscores improve readability** (Java 7+)
7. **Multiple number systems** can represent the same value
8. **Scientific notation** is available for floating-point numbers
9. **Boolean literals** are true and false
10. **String literals** use double quotes and can contain escape sequences

Understanding literals is fundamental for writing Java code effectively and avoiding type-related compilation errors.
