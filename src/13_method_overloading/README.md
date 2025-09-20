# METHOD OVERLOADING

**Method Overloading**: Declaring methods with the same name but different parameter structures within the same class. Methods in different classes with the same name are not overloading - they are separate methods.

Method overloading is examined in two ways: syntax and semantics:
- **Syntax**: What are the rules for method overloading?
- **Semantics**: How does the compiler decide which method to call when an overloaded method is called? This is called **method overload resolution**.

## Rules for Method Overloading

### What Makes Methods Different

Methods are distinguished by their **signature**: the combination of method name and parameter structure.

**Important Notes**: The following do NOT affect method overloading:
- **Access modifiers** (public, private, etc.) do not make methods different
- **static vs non-static** does not affect overloading
- **Return type differences** do not affect overloading
- **Parameter variable names** do not affect overloading

### Valid Method Overloading Examples

```java
public class OverloadingExamples {
    // Valid overloading - different parameter types
    public static void foo(int a, double b) {
        System.out.println("foo(int, double): " + a + ", " + b);
    }
    
    public static void foo(double a, int b) {
        System.out.println("foo(double, int): " + a + ", " + b);
    }
    
    // Valid overloading - different number of parameters
    public static void foo(int a) {
        System.out.println("foo(int): " + a);
    }
    
    public static void foo(int a, int b, int c) {
        System.out.println("foo(int, int, int): " + a + ", " + b + ", " + c);
    }
    
    // Return type difference doesn't matter for overloading
    public static int foo(double a) {
        System.out.println("foo(double): " + a);
        return (int) a;
    }
    
    public static void main(String[] args) {
        foo(10, 3.14);      // Calls foo(int, double)
        foo(3.14, 10);      // Calls foo(double, int)
        foo(5);             // Calls foo(int)
        foo(1, 2, 3);       // Calls foo(int, int, int)
        foo(2.5);           // Calls foo(double)
    }
}
```

### Invalid Overloading Examples

```java
public class InvalidOverloading {
    // These would cause compilation errors:
    
    /*
    // ERROR: Only parameter names are different
    public static void method1(int a, int b) { }
    public static void method1(int x, int y) { }
    
    // ERROR: Only return type is different
    public static void method2(int a) { }
    public static int method2(int a) { return a; }
    
    // ERROR: Only access modifier is different
    public static void method3(int a) { }
    private static void method3(int a) { }
    */
}
```

## Method Overload Resolution

When an overloaded method is called, the compiler determines which method to call through these steps:

### 1. Candidate Methods
All methods in the class with the same name as the called method.

### 2. Applicable Methods
Among candidate methods, those where:
- The number of arguments matches the number of parameters
- Each argument type can be implicitly converted to the corresponding parameter type

### 3. Most Applicable Method
The method that provides the best conversion quality for arguments.

**Conversion Quality Rules**:
1. **Exact match** is best (T1 → T1)
2. **Widening conversion** is preferred over others (int → long is better than int → float)

```java
public class OverloadResolution {
    public static void test(int a) {
        System.out.println("test(int): " + a);
    }
    
    public static void test(double a) {
        System.out.println("test(double): " + a);
    }
    
    public static void test(long a) {
        System.out.println("test(long): " + a);
    }
    
    public static void main(String[] args) {
        test(10);        // Exact match: test(int)
        test(10L);       // Exact match: test(long)
        test(10.5);      // Exact match: test(double)
        
        byte b = 5;
        test(b);         // Widening conversion: byte → int
        
        short s = 100;
        test(s);         // Widening conversion: short → int
    }
}
```

### Best Match Example

```java
public class BestMatchExample {
    public static void process(int a, double b) {
        System.out.println("process(int, double): " + a + ", " + b);
    }
    
    public static void process(double a, double b) {
        System.out.println("process(double, double): " + a + ", " + b);
    }
    
    public static void main(String[] args) {
        int x = 10;
        double y = 3.14;
        
        process(x, y);    // Best match: process(int, double)
        
        // Candidate methods: 1, 2
        // Applicable methods: 1, 2 (both can accept int, double)
        // Most applicable: 1 (exact match for int parameter)
    }
}
```

### Ambiguous Method Call

```java
public class AmbiguousExample {
    public static void ambiguous(int a, long b) {
        System.out.println("ambiguous(int, long)");
    }
    
    public static void ambiguous(long a, int b) {
        System.out.println("ambiguous(long, int)");
    }
    
    public static void main(String[] args) {
        short x = 10;
        short y = 20;
        
        // This would cause compilation error: ambiguous method call
        // ambiguous(x, y);  // Both methods are equally applicable
        
        // Explicit casting resolves ambiguity
        ambiguous((int) x, (long) y);   // Calls ambiguous(int, long)
        ambiguous((long) x, (int) y);   // Calls ambiguous(long, int)
    }
}
```

## Practical Overloading Examples

### Calculator Class

```java
public class Calculator {
    // Overloaded add methods
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static double add(double a, double b) {
        return a + b;
    }
    
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static double add(double a, double b, double c) {
        return a + b + c;
    }
    
    // Overloaded multiply methods
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    public static long multiply(long a, long b) {
        return a * b;
    }
    
    public static void main(String[] args) {
        System.out.println("int + int: " + add(5, 3));
        System.out.println("double + double: " + add(5.5, 3.2));
        System.out.println("three ints: " + add(1, 2, 3));
        System.out.println("three doubles: " + add(1.1, 2.2, 3.3));
        
        System.out.println("int * int: " + multiply(4, 5));
        System.out.println("double * double: " + multiply(4.5, 2.0));
        System.out.println("long * long: " + multiply(100L, 200L));
    }
}
```

### Math Utility Class

```java
public class MathUtil {
    // Overloaded max methods
    public static int max(int a, int b) {
        return a > b ? a : b;
    }
    
    public static double max(double a, double b) {
        return a > b ? a : b;
    }
    
    public static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }
    
    public static double max(double a, double b, double c) {
        return max(max(a, b), c);
    }
    
    // Overloaded absolute value methods
    public static int abs(int value) {
        return value < 0 ? -value : value;
    }
    
    public static double abs(double value) {
        return value < 0 ? -value : value;
    }
    
    public static long abs(long value) {
        return value < 0 ? -value : value;
    }
    
    public static void main(String[] args) {
        System.out.println("max(10, 5): " + max(10, 5));
        System.out.println("max(3.14, 2.71): " + max(3.14, 2.71));
        System.out.println("max(1, 2, 3): " + max(1, 2, 3));
        
        System.out.println("abs(-5): " + abs(-5));
        System.out.println("abs(-3.14): " + abs(-3.14));
        System.out.println("abs(-100L): " + abs(-100L));
    }
}
```

### String Processing Utility

```java
public class StringUtil {
    // Overloaded repeat methods
    public static String repeat(String str, int times) {
        if (times <= 0) return "";
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(str);
        }
        return result.toString();
    }
    
    public static String repeat(char ch, int times) {
        return repeat(String.valueOf(ch), times);
    }
    
    // Overloaded reverse methods
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    public static char[] reverse(char[] chars) {
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[chars.length - 1 - i];
        }
        return result;
    }
    
    // Overloaded capitalize methods
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public static String capitalize(String str, boolean allWords) {
        if (!allWords) return capitalize(str);
        
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (i > 0) result.append(" ");
            result.append(capitalize(words[i]));
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("repeat('Hello', 3): " + repeat("Hello", 3));
        System.out.println("repeat('*', 5): " + repeat('*', 5));
        
        System.out.println("reverse('Hello'): " + reverse("Hello"));
        System.out.println("reverse(['a','b','c']): " + 
                          java.util.Arrays.toString(reverse(new char[]{'a','b','c'})));
        
        System.out.println("capitalize('hello world'): " + capitalize("hello world"));
        System.out.println("capitalize('hello world', true): " + 
                          capitalize("hello world", true));
    }
}
```

## Constructor Overloading Preview

Although constructors will be covered later, they can also be overloaded:

```java
public class Person {
    private String name;
    private int age;
    
    // Default constructor
    public Person() {
        this("Unknown", 0);
    }
    
    // Constructor with name only
    public Person(String name) {
        this(name, 0);
    }
    
    // Constructor with both name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void displayInfo() {
        System.out.printf("Name: %s, Age: %d%n", name, age);
    }
    
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person("Alice");
        Person p3 = new Person("Bob", 25);
        
        p1.displayInfo();
        p2.displayInfo();
        p3.displayInfo();
    }
}
```

## Benefits of Method Overloading

### 1. Improved API Usability

```java
public class PrintUtil {
    public static void print(int value) {
        System.out.println("Integer: " + value);
    }
    
    public static void print(double value) {
        System.out.println("Double: " + value);
    }
    
    public static void print(String value) {
        System.out.println("String: " + value);
    }
    
    public static void print(boolean value) {
        System.out.println("Boolean: " + value);
    }
    
    // Users can call print() with any type without remembering different method names
    public static void main(String[] args) {
        print(42);
        print(3.14);
        print("Hello");
        print(true);
    }
}
```

### 2. Flexibility with Default Values

```java
public class ConnectionUtil {
    public static void connect(String host) {
        connect(host, 80);  // Default port
    }
    
    public static void connect(String host, int port) {
        connect(host, port, 30);  // Default timeout
    }
    
    public static void connect(String host, int port, int timeout) {
        System.out.printf("Connecting to %s:%d (timeout: %d seconds)%n", 
                         host, port, timeout);
    }
    
    public static void main(String[] args) {
        connect("example.com");              // Uses defaults: port 80, timeout 30
        connect("example.com", 8080);        // Uses default timeout: 30
        connect("example.com", 8080, 60);    // All parameters specified
    }
}
```

## Common Overloading Patterns

### 1. Type Variations

```java
public class TypeVariations {
    public static void process(byte value) { System.out.println("Processing byte: " + value); }
    public static void process(short value) { System.out.println("Processing short: " + value); }
    public static void process(int value) { System.out.println("Processing int: " + value); }
    public static void process(long value) { System.out.println("Processing long: " + value); }
    public static void process(float value) { System.out.println("Processing float: " + value); }
    public static void process(double value) { System.out.println("Processing double: " + value); }
}
```

### 2. Parameter Count Variations

```java
public class ParameterCountVariations {
    public static int sum(int a, int b) {
        return a + b;
    }
    
    public static int sum(int a, int b, int c) {
        return a + b + c;
    }
    
    public static int sum(int a, int b, int c, int d) {
        return a + b + c + d;
    }
    
    // Or using varargs (covered later)
    public static int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
}
```

## Key Points

1. **Method overloading allows multiple methods with the same name**
2. **Methods must differ in parameter structure (signature)**
3. **Return type, access modifiers, and parameter names don't affect overloading**
4. **Compiler uses overload resolution to choose the best method**
5. **Exact matches are preferred over type conversions**
6. **Ambiguous calls result in compilation errors**
7. **Overloading improves API usability and flexibility**
8. **Common patterns include type variations and parameter count variations**
9. **Constructors can also be overloaded**
10. **Understanding overload resolution helps avoid unexpected behavior**

Method overloading is a powerful feature that allows creating flexible and user-friendly APIs while maintaining code clarity and avoiding naming conflicts.
