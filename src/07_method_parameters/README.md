# METHOD PARAMETERS AND ARGUMENTS

## METHOD PARAMETER VARIABLES

Variables declared within parentheses before the method body are called **method parameter variables**. Method parameter variables are separated by commas. Even if variables are of the same type, type information must be written for each one. Method parameter variables are like local variables declared at the beginning of the method in terms of scope, meaning they are visible throughout the related method.

The expressions passed to a method during method call are called **arguments**. Parameter declarations are actually the inputs (inputs) of the method. A method must be called with as many arguments as it has parameters. Parameter variables get their values from the expressions passed to the related parameter during method call. That is, during method call, the expression related to the argument is first calculated and its value is transferred to the parameter variable. In this sense, the transfer from arguments to parameters is also an assignment operation.

## Assignment Operations in Java

There are **3 types of assignment operations** in Java:
1. **Plain assignment operation** (`=` operator)
2. **Assignment of method's return value to temporary variable**
3. **Assignment from argument to parameter declaration**

### Basic Parameter Example

```java
public class ParameterExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter two numbers: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        
        int result = Util.add(x, y); // x and y are arguments
        System.out.println("Result: " + result);
    }
}

class Util {
    // a and b are parameter variables
    public static int add(int a, int b) { 
        return a + b; // Parameter names can be different from argument names
    }
}
```

### Parameter Rules and Examples

#### 1. Parameter Count Must Match Argument Count

```java
public class ParameterCountExample {
    public static void main(String[] args) {
        // Correct calls
        printSum(5, 10);           // 2 arguments for 2 parameters
        printInfo("John", 25, true); // 3 arguments for 3 parameters
        
        // These would cause compilation errors:
        // printSum(5);              // ERROR: Too few arguments
        // printSum(5, 10, 15);      // ERROR: Too many arguments
    }
    
    public static void printSum(int a, int b) {
        System.out.println("Sum: " + (a + b));
    }
    
    public static void printInfo(String name, int age, boolean isStudent) {
        System.out.println("Name: " + name + ", Age: " + age + ", Student: " + isStudent);
    }
}
```

#### 2. Type Compatibility

```java
public class TypeCompatibilityExample {
    public static void main(String[] args) {
        // Valid assignments (implicit conversions)
        processNumber(10);        // int to int
        processNumber(10L);       // long to int (if within range)
        processDecimal(5);        // int to double
        processDecimal(3.14);     // double to double
        
        // Invalid assignments would cause errors:
        // processNumber(3.14);   // ERROR: double cannot be assigned to int
        // processNumber("10");   // ERROR: String cannot be assigned to int
    }
    
    public static void processNumber(int number) {
        System.out.println("Processing number: " + number);
    }
    
    public static void processDecimal(double decimal) {
        System.out.println("Processing decimal: " + decimal);
    }
}
```

#### 3. Same Type Parameters

```java
public class SameTypeParameters {
    public static void main(String[] args) {
        // Even if parameters are the same type, each must be declared separately
        calculateRectangleArea(5.0, 3.0);
        findMaxOfThree(10, 25, 18);
    }
    
    // Correct: Each parameter declared with type
    public static double calculateRectangleArea(double width, double height) {
        return width * height;
    }
    
    // Correct: All three parameters declared separately
    public static int findMaxOfThree(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
    
    // INCORRECT syntax examples:
    // public static int add(int a, b) { }        // ERROR: b must have type
    // public static int multiply(int x, y, z) { } // ERROR: y and z must have types
}
```

### Parameter Variable Scope

```java
public class ParameterScope {
    public static void main(String[] args) {
        testScope(100);
    }
    
    public static void testScope(int parameter) {
        // Parameter is accessible throughout the method
        System.out.println("Parameter at start: " + parameter);
        
        if (parameter > 50) {
            parameter = parameter * 2; // Modifying parameter (local copy)
            System.out.println("Parameter in if block: " + parameter);
        }
        
        System.out.println("Parameter at end: " + parameter);
        
        // parameter is not accessible outside this method
    }
    
    // Cannot access 'parameter' here - out of scope
}
```

### Complex Parameter Examples

#### Method with Mixed Parameter Types

```java
public class MixedParameters {
    public static void main(String[] args) {
        // Different argument types
        displayPersonInfo("Alice", 30, 175.5, true);
        
        // Variables as arguments
        String name = "Bob";
        int age = 25;
        double height = 182.0;
        boolean isEmployed = false;
        
        displayPersonInfo(name, age, height, isEmployed);
    }
    
    public static void displayPersonInfo(String name, int age, double height, boolean employed) {
        System.out.println("=== Person Information ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " years");
        System.out.println("Height: " + height + " cm");
        System.out.println("Employed: " + (employed ? "Yes" : "No"));
        System.out.println("========================");
    }
}
```

#### Methods Calling Other Methods with Parameters

```java
public class MethodChaining {
    public static void main(String[] args) {
        double result = calculateCircleData(5.0);
        System.out.println("Circle area: " + result);
        
        // Using method result as argument
        displayResult("Circle calculation", calculateCircleData(3.0));
    }
    
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    public static double calculateCircleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }
    
    public static double calculateCircleData(double radius) {
        double area = calculateCircleArea(radius);        // Method call with parameter
        double circumference = calculateCircleCircumference(radius); // Another method call
        
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + area);
        System.out.println("Circumference: " + circumference);
        
        return area;
    }
    
    public static void displayResult(String operation, double value) {
        System.out.println(operation + " result: " + value);
    }
}
```

### Expression Arguments

```java
public class ExpressionArguments {
    public static void main(String[] args) {
        int a = 10, b = 5;
        
        // Using expressions as arguments
        System.out.println("Sum: " + add(a + 2, b * 3));           // 12 + 15 = 27
        System.out.println("Max: " + findMax(a * b, a + b));       // max(50, 15) = 50
        System.out.println("Area: " + calculateArea(a / 2.0, b + 1.5)); // 5.0 * 6.5 = 32.5
        
        // Complex expressions
        int result = multiply(add(a, b), subtract(a, b));  // (10+5) * (10-5) = 15 * 5 = 75
        System.out.println("Complex result: " + result);
    }
    
    public static int add(int x, int y) {
        return x + y;
    }
    
    public static int subtract(int x, int y) {
        return x - y;
    }
    
    public static int multiply(int x, int y) {
        return x * y;
    }
    
    public static int findMax(int x, int y) {
        return x > y ? x : y;
    }
    
    public static double calculateArea(double width, double height) {
        return width * height;
    }
}
```

### Parameter vs Local Variables

```java
public class ParameterVsLocal {
    public static void main(String[] args) {
        processData(100, "Test");
    }
    
    public static void processData(int inputValue, String message) {
        // inputValue and message are parameters
        
        // Local variables
        int localVar = inputValue * 2;
        String processedMessage = "Processing: " + message;
        
        // Both parameters and local variables are accessible
        System.out.println("Input: " + inputValue);
        System.out.println("Local: " + localVar);
        System.out.println("Message: " + processedMessage);
        
        // Modifying parameter (affects only local copy)
        inputValue = inputValue + 10;
        System.out.println("Modified parameter: " + inputValue);
        
        // Original argument value is unchanged in calling method
    }
}
```

## Benefits of Using Methods with Parameters

### 1. Code Reusability
```java
public class Reusability {
    public static void main(String[] args) {
        // Same method, different arguments
        printGreeting("John");
        printGreeting("Alice");
        printGreeting("Bob");
        
        // Same calculation method, different values
        System.out.println("5! = " + factorial(5));
        System.out.println("7! = " + factorial(7));
        System.out.println("10! = " + factorial(10));
    }
    
    public static void printGreeting(String name) {
        System.out.println("Hello, " + name + "! Welcome to Java programming.");
    }
    
    public static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
```

### 2. Avoiding Code Duplication
```java
public class AvoidDuplication {
    public static void main(String[] args) {
        // Instead of duplicating validation code, use a method
        int age1 = validateAge(25);
        int age2 = validateAge(-5);   // Will be corrected
        int age3 = validateAge(150);  // Will be corrected
        
        System.out.println("Ages: " + age1 + ", " + age2 + ", " + age3);
    }
    
    public static int validateAge(int age) {
        if (age < 0) {
            System.out.println("Warning: Negative age corrected to 0");
            return 0;
        }
        if (age > 120) {
            System.out.println("Warning: Age over 120 corrected to 120");
            return 120;
        }
        return age;
    }
}
```

## Key Points

1. **Parameters are method inputs** - they define what data the method needs
2. **Arguments are actual values** passed when calling the method
3. **Parameter count must match argument count**
4. **Types must be compatible** (assignment-compatible)
5. **Each parameter must be declared with its type**
6. **Parameters have method scope** - visible throughout the method
7. **Parameter modification affects only the local copy**
8. **Methods with parameters promote code reusability**
9. **Expressions can be used as arguments**
10. **Assignment from arguments to parameters is one of three assignment types in Java**

Parameters and arguments are fundamental to creating flexible, reusable methods that can work with different data values.
