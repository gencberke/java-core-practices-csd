# OPERATORS IN JAVA

**Operators** are atoms that lead to a specific operation and produce a value as a result of the operation. Expressions that enter into operation with an operator are called **operands**. In `a + b`, `a` is the 1st operand and `b` is the 2nd operand.

To learn an operator, the following characteristics need to be examined:

## Operator Classification

### 1. By Function
- **Arithmetic operators** (arithmetic)
- **Comparison operators** (comparison)
- **Logical operators** (logical)
- **Bitwise operators** (bitwise)
- **Special purpose operators** (special)

### 2. By Number of Operands
- **Unary operators** (single operand)
- **Binary operators** (two operands)
- **Ternary operators** (three operands)

### 3. By Position
- **Prefix** (before operand)
- **Infix** (between operands)
- **Postfix** (after operand)

## Operator Properties

- **Product value**: The value produced as a result of the operation
- **Constraint**: Are there any mandatory restrictions for using the operator?
- **Side effect**: Does the operator change the value of its operand?
- **Precedence**: The operator's order of operation relative to other operators in the expression
- **Associativity**: When operators of the same precedence level are in the same expression, determines the order of operation (left-to-right or right-to-left)

## Arithmetic Operators

### Basic Arithmetic Operators (+, -, *, /, %)

These are binary and infix operators. Mathematical rules generally apply. They are left associative.

```java
public class BasicArithmetic {
    public static void main(String[] args) {
        int a = 15, b = 4;
        
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));    // Addition: 19
        System.out.println("a - b = " + (a - b));    // Subtraction: 11
        System.out.println("a * b = " + (a * b));    // Multiplication: 60
        System.out.println("a / b = " + (a / b));    // Division: 3 (integer division)
        System.out.println("a % b = " + (a % b));    // Modulus: 3 (remainder)
        
        // Floating-point division
        double result = (double) a / b;
        System.out.println("a / b (double) = " + result);  // 3.75
    }
}
```

### Modulus Operator (%)

The modulus operator finds the remainder. This operator has a special case: if the second operand is negative, it operates with its positive value, but if the first operand is negative, the found value is negative.

```java
public class ModulusOperator {
    public static void main(String[] args) {
        System.out.println("10 % 3 = " + (10 % 3));    // 1
        System.out.println("10 % -3 = " + (10 % -3));  // 1 (divisor sign ignored)
        System.out.println("-10 % 3 = " + (-10 % 3));  // -1 (dividend sign preserved)
        System.out.println("-10 % -3 = " + (-10 % -3)); // -1
        
        // Practical use: checking even/odd
        int number = 17;
        if (number % 2 == 0) {
            System.out.println(number + " is even");
        } else {
            System.out.println(number + " is odd");
        }
    }
}
```

### Unary Arithmetic Operators

#### Sign Operators (+, -)

```java
public class UnarySignOperators {
    public static void main(String[] args) {
        int a = 10;
        int b = -5;
        
        System.out.println("a = " + a);
        System.out.println("+a = " + (+a));    // Unary plus (no effect)
        System.out.println("-a = " + (-a));    // Unary minus (negation)
        System.out.println("-b = " + (-b));    // Double negation makes positive
        
        // Unary minus has higher precedence
        int result = -a * 2;  // (-a) * 2 = -20
        System.out.println("-a * 2 = " + result);
    }
}
```

#### Increment and Decrement Operators (++, --)

These operators can be used in both prefix and postfix forms. The operand must be a variable.

```java
public class IncrementDecrement {
    public static void main(String[] args) {
        int a = 5;
        int b = 5;
        
        // Prefix increment/decrement
        System.out.println("Initial: a = " + a + ", b = " + b);
        System.out.println("++a = " + (++a));  // Pre-increment: increment then use (6)
        System.out.println("--b = " + (--b));  // Pre-decrement: decrement then use (4)
        System.out.println("After prefix: a = " + a + ", b = " + b);
        
        // Reset values
        a = 5; b = 5;
        
        // Postfix increment/decrement
        System.out.println("a++ = " + (a++));  // Post-increment: use then increment (5)
        System.out.println("b-- = " + (b--));  // Post-decrement: use then decrement (5)
        System.out.println("After postfix: a = " + a + ", b = " + b);  // a=6, b=4
    }
}
```

#### Complex Expression with Increment/Decrement

```java
public class ComplexExpressions {
    public static void main(String[] args) {
        int a = 3;
        int result;
        
        // Complex expression
        result = a++ + a * --a;  // 3 + 4 * 3 = 15
        System.out.println("a = " + a + ", result = " + result);
        
        // Maximal munch rule example
        a = 3; int b = 4; int c;
        c = a+++b;  // Parsed as (a++) + b, not a + (++b)
        System.out.println("a = " + a + ", b = " + b + ", c = " + c);  // a=4, b=4, c=7
    }
}
```

## Comparison Operators

Basic comparison operators: `<`, `>`, `<=`, `>=`, `==`, `!=`

These operators are binary, infix, and have no side effects. They produce boolean values.

```java
public class ComparisonOperators {
    public static void main(String[] args) {
        int a = 10, b = 5;
        
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a < b: " + (a < b));    // false
        System.out.println("a > b: " + (a > b));    // true
        System.out.println("a <= b: " + (a <= b));  // false
        System.out.println("a >= b: " + (a >= b));  // true
        System.out.println("a == b: " + (a == b));  // false
        System.out.println("a != b: " + (a != b));  // true
        
        // Chaining comparisons (not recommended)
        // System.out.println(a < b < c);  // Error: boolean < int
        
        // Correct way to chain
        int c = 15;
        System.out.println("a < c && c < 20: " + (a < c && c < 20));  // true
    }
}
```

## Method Call Operator

The method call operator `()` is used to call methods. It's a special-purpose operator that can be unary or binary and is in infix position. It's at the first level of the precedence table.

```java
public class MethodCallOperator {
    public static void main(String[] args) {
        // Method calls produce values based on return types
        int sum = add(5, 3);        // Returns int
        boolean isPrime = isPrime(7); // Returns boolean
        
        System.out.println("Sum: " + sum);
        System.out.println("Is 7 prime? " + isPrime);
        
        // Void method call produces no value (void expression)
        printMessage("Hello");      // void expression
    }
    
    public static int add(int x, int y) {
        return x + y;
    }
    
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    public static void printMessage(String msg) {
        System.out.println("Message: " + msg);
    }
}
```

## Logical Operators

### Logical AND (&&) and OR (||)

These operators have operands of boolean type and produce boolean values. They have **short-circuit behavior**.

```java
public class LogicalOperators {
    public static void main(String[] args) {
        boolean result;
        
        // Short-circuit AND: if first is false, second is not evaluated
        result = false && expensiveOperation();  // expensiveOperation() not called
        System.out.println("Result 1: " + result);
        
        // Short-circuit OR: if first is true, second is not evaluated
        result = true || expensiveOperation();   // expensiveOperation() not called
        System.out.println("Result 2: " + result);
        
        // Both operands evaluated
        result = true && simpleOperation();      // simpleOperation() is called
        System.out.println("Result 3: " + result);
    }
    
    public static boolean expensiveOperation() {
        System.out.println("Expensive operation called!");
        return true;
    }
    
    public static boolean simpleOperation() {
        System.out.println("Simple operation called");
        return true;
    }
}
```

### Logical NOT (!)

Unary, prefix operator that produces the logical negation of its operand.

```java
public class LogicalNot {
    public static void main(String[] args) {
        boolean flag = true;
        
        System.out.println("flag = " + flag);
        System.out.println("!flag = " + (!flag));
        
        flag = !flag;  // Toggle the flag
        System.out.println("After toggle: flag = " + flag);
        
        // Double negation
        boolean original = false;
        boolean doubleNegated = !!original;
        System.out.println("!!false = " + doubleNegated);  // false
    }
}
```

## Assignment Operators

### Basic Assignment (=)

The assignment operator is special-purpose, binary, and infix. The first operand must be a variable. It has side effects and is right associative.

```java
public class AssignmentOperator {
    public static void main(String[] args) {
        int a, b, c;
        
        // Simple assignment
        a = 10;
        System.out.println("a = " + a);
        
        // Chained assignment (right associative)
        a = b = c = 5;  // Equivalent to: a = (b = (c = 5))
        System.out.println("a = " + a + ", b = " + b + ", c = " + c);
        
        // Assignment produces the assigned value
        int result = (a = 20) + 5;  // a becomes 20, result becomes 25
        System.out.println("a = " + a + ", result = " + result);
    }
}
```

### Compound Assignment Operators

Java has compound assignment operators that combine operation with assignment: `+=`, `-=`, `*=`, `/=`, `%=`

```java
public class CompoundAssignment {
    public static void main(String[] args) {
        int x = 10;
        
        System.out.println("Initial x = " + x);
        
        x += 5;    // Equivalent to: x = x + 5
        System.out.println("After x += 5: " + x);  // 15
        
        x -= 3;    // Equivalent to: x = x - 3
        System.out.println("After x -= 3: " + x);  // 12
        
        x *= 2;    // Equivalent to: x = x * 2
        System.out.println("After x *= 2: " + x);  // 24
        
        x /= 4;    // Equivalent to: x = x / 4
        System.out.println("After x /= 4: " + x);  // 6
        
        x %= 4;    // Equivalent to: x = x % 4
        System.out.println("After x %= 4: " + x);  // 2
        
        // Complex expressions
        int a = 10, b = 2;
        a *= b + 2;  // Equivalent to: a = a * (b + 2) = 10 * 4 = 40
        System.out.println("a *= b + 2: " + a);
    }
}
```

## Operator Precedence and Associativity

```java
public class OperatorPrecedence {
    public static void main(String[] args) {
        int a = 2, b = 3, c = 4;
        
        // Precedence: * has higher precedence than +
        int result1 = a + b * c;     // 2 + (3 * 4) = 14
        System.out.println("a + b * c = " + result1);
        
        // Use parentheses to override precedence
        int result2 = (a + b) * c;   // (2 + 3) * 4 = 20
        System.out.println("(a + b) * c = " + result2);
        
        // Associativity: left-to-right for same precedence
        int result3 = a - b + c;     // (2 - 3) + 4 = 3
        System.out.println("a - b + c = " + result3);
        
        // Right associativity for assignment
        int x, y, z;
        x = y = z = 5;  // z = 5, then y = 5, then x = 5
        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
    }
}
```

## Practical Examples

### Digit Sum Calculator

```java
public class DigitSum {
    public static void main(String[] args) {
        NumberUtilTest.run();
    }
}

class NumberUtilTest {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        
        System.out.printf("Sum of digits of %d: %d%n", n, NumberUtil.sumOfDigits(n));
    }
}

class NumberUtil {
    public static int sumOfDigits(int n) {
        int sum = 0;
        n = Math.abs(n);  // Handle negative numbers
        
        while (n != 0) {
            sum += n % 10;  // Add last digit
            n /= 10;        // Remove last digit
        }
        
        return sum;
    }
}
```

## Key Points

1. **Operators have precedence and associativity rules**
2. **Increment/decrement operators have different behavior in prefix vs postfix**
3. **Logical operators use short-circuit evaluation**
4. **Assignment operators are right associative**
5. **Compound assignment operators simplify common patterns**
6. **Method call operator can produce values or void expressions**
7. **Comparison operators always produce boolean results**
8. **Use parentheses to make precedence explicit**
9. **Side effects occur with assignment and increment/decrement operators**
10. **Understanding operator behavior is crucial for complex expressions**

Operators are fundamental building blocks in Java expressions and understanding their behavior, precedence, and side effects is essential for writing correct and maintainable code.
