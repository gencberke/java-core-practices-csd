# EXPRESSIONS AND METHOD RETURN VALUES

## EXPRESSION (EXPRESSION) CONCEPT

An **expression** is any combination of constants, operators, and variables. If an expression consists only of constants and operators, this expression is a **constant expression**. However, an operator alone cannot be an expression.

`total = 10` is an expression. Every expression has a type. There is one exception to this, which we will learn later.

### Types of Expressions

#### Simple Expressions
```java
public class SimpleExpressions {
    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        
        // Simple expressions
        int sum = a + b;        // Expression: a + b
        int product = a * 2;    // Expression: a * 2
        boolean isEqual = a == b; // Expression: a == b
        
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
        System.out.println("Equal: " + isEqual);
    }
}
```

#### Constant Expressions
```java
public class ConstantExpressions {
    public static void main(String[] args) {
        // Constant expressions (evaluated at compile time)
        final int RESULT1 = 10 + 20;        // 30
        final int RESULT2 = 5 * 4;          // 20
        final boolean FLAG = true && false;  // false
        
        System.out.println("Constant results: " + RESULT1 + ", " + RESULT2 + ", " + FLAG);
    }
}
```

#### Complex Expressions
```java
public class ComplexExpressions {
    public static void main(String[] args) {
        int x = 10, y = 5, z = 2;
        
        // Complex expression with multiple operators
        int result = (x + y) * z - x / y;
        
        // Expression with method calls
        double sqrt = Math.sqrt(x * x + y * y);
        
        // Boolean expression
        boolean condition = x > y && y > z || x == 10;
        
        System.out.println("Result: " + result);
        System.out.println("Square root: " + sqrt);
        System.out.println("Condition: " + condition);
    }
}
```

## METHOD RETURN VALUES

When a type name is written instead of a method's return information, this method has a **return value**. If a method has a return value, a type is written instead of the return value information. When a method with a return value finishes its call, it returns to the calling point with a value.

If a method does not have return value information, the `void` keyword is written instead of return information.

### Return Statement

A method's return value is created with the `return` statement. The general form of the return statement is:

```
return [expression];
```

As seen, the return statement can be used alone or with an expression. However the return statement is used, when the flow reaches the return statement, the method ends. So the **first task of the return statement is to end the method**. The flow returns to the calling point. If the return statement is used with an expression, the value of the expression is transferred to the calling point.

### Examples of Methods with Return Values

#### Basic Return Value Example

```java
public class BasicReturnExample {
    public static void main(String[] args) {
        int result = add(10, 20);
        System.out.println("Result: " + result);
        
        double circleArea = calculateCircleArea(5.0);
        System.out.println("Circle area: " + circleArea);
    }
    
    public static int add(int a, int b) {
        return a + b;  // Returns the sum
    }
    
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
}
```

#### Method with Multiple Return Points

```java
public class MultipleReturns {
    public static void main(String[] args) {
        System.out.println("Max of 15, 25: " + findMax(15, 25));
        System.out.println("Absolute value of -7: " + absoluteValue(-7));
    }
    
    public static int findMax(int a, int b) {
        if (a > b) {
            return a;  // Early return
        }
        return b;      // Default return
    }
    
    public static int absoluteValue(int number) {
        if (number < 0) {
            return -number;  // Return negative of number if negative
        }
        return number;       // Return number as is if positive
    }
}
```

#### Void Methods vs Return Methods

```java
public class VoidVsReturn {
    public static void main(String[] args) {
        // Void method - performs action but returns nothing
        printMessage("Hello World");
        
        // Method with return value - can be used in expressions
        int square = calculateSquare(5);
        System.out.println("Square: " + square);
        
        // Using return value directly in expression
        System.out.println("Square of 7: " + calculateSquare(7));
    }
    
    // Void method
    public static void printMessage(String message) {
        System.out.println("Message: " + message);
        // return; // Optional - method ends here anyway
    }
    
    // Method with return value
    public static int calculateSquare(int number) {
        return number * number;
    }
}
```

### Important Rules for Return Values

1. **A method with a return value cannot use return statement alone** (without expression)
2. **A method with return value must return the correct type**
3. **All code paths must return a value**

#### Compilation Errors with Return Values

```java
public class ReturnErrors {
    // ERROR: Not all code paths return a value
    /*
    public static int problematicMethod(int x) {
        if (x > 0) {
            return x * 2;
        }
        // ERROR: No return statement for when x <= 0
    }
    */
    
    // CORRECT: All paths return a value
    public static int correctMethod(int x) {
        if (x > 0) {
            return x * 2;
        } else {
            return 0;
        }
    }
    
    // ERROR: Cannot return wrong type
    /*
    public static int wrongType() {
        return "Hello"; // ERROR: Cannot return String from int method
    }
    */
}
```

### Temporary Variables and Method Calls

When a method with a return value is called, the compiler creates a **temporary variable** of the same type as the return value and uses this variable.

```java
public class TemporaryVariables {
    public static void main(String[] args) {
        // The compiler creates something like:
        // int temp = Util.add(5, 3);
        // int result = temp * 2;
        int result = Util.add(5, 3) * 2;
        
        System.out.println("Result: " + result);
    }
}

class Util {
    public static int add(int a, int b) {
        return a + b;
    }
}
```

### Assignment Types in Java

So far we know 2 types of assignment operations:
1. **Assignment operator (`=`)**
2. **Calling a method with return value**

```java
public class AssignmentTypes {
    public static void main(String[] args) {
        int x;
        
        // Type 1: Assignment operator
        x = 10;
        
        // Type 2: Method call with return value
        x = getValue();
        
        System.out.println("x = " + x);
    }
    
    public static int getValue() {
        return 42;
    }
}
```

### Practical Examples

#### Calculator Methods

```java
public class Calculator {
    public static void main(String[] args) {
        double a = 10.5;
        double b = 3.2;
        
        System.out.println("Addition: " + add(a, b));
        System.out.println("Subtraction: " + subtract(a, b));
        System.out.println("Multiplication: " + multiply(a, b));
        System.out.println("Division: " + divide(a, b));
    }
    
    public static double add(double x, double y) {
        return x + y;
    }
    
    public static double subtract(double x, double y) {
        return x - y;
    }
    
    public static double multiply(double x, double y) {
        return x * y;
    }
    
    public static double divide(double x, double y) {
        if (y != 0) {
            return x / y;
        }
        return Double.NaN; // Not a Number for division by zero
    }
}
```

#### Utility Methods

```java
public class MathUtils {
    public static void main(String[] args) {
        System.out.println("Is 17 prime? " + isPrime(17));
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("GCD of 48 and 18: " + gcd(48, 18));
    }
    
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
```

## Key Points

1. **Expressions produce values and have types**
2. **Methods can return values using the return statement**
3. **Return statement ends method execution**
4. **Methods with return values must return appropriate type**
5. **Returned values can be used in further expressions**
6. **Void methods perform actions but don't return values**
7. **All code paths in non-void methods must return a value**

Return values are essential for creating methods that compute and provide results, making code modular and reusable.
