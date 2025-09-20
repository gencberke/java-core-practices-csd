# OBJECT-ORIENTED PROGRAMMING INTRODUCTION

Object-Oriented Programming (OOP) is a programming technique using classes. OOP is actually a combination of many key concepts (encapsulation, aggregation, polymorphism, etc.). The foundation of all these concepts is actually readability and comprehensibility. OOP takes the human way of perceiving nature as a model.

## Computer Architecture Basics

Understanding computer architecture helps us understand how Java manages memory:

- **CPU (Central Processing Unit)**: Performs operations like addition or comparison
- **RAM (Random Access Memory)**: Temporary memory where variables are stored during program execution  
- **Disk (Secondary Memory)**: Permanent storage (HDD/SSD)
- **Bus**: Electrical cables for communication between components

### Memory Organization

RAM consists of bytes, and bytes consist of bits (binary digits). Each byte in memory is indexed - these numbers are called **addresses**. Since variables are stored in memory, they have addresses.

**Important Note**: 
- **32-bit processor**: Can address 2^32 memory locations
- **64-bit processor**: Can address 2^64 memory locations
- Operating system limitations may restrict actual addressable memory

## Stack and Heap Memory Areas

When a Java application runs, two memory areas are allocated for that application: **stack** and **heap**. These areas are organized in RAM.

### Stack Memory

**Stack** is a data structure that works with LIFO (Last In First Out) principle, like the "undo-redo" mechanism in editor programs.

- **Stack operations**: 
  - **Push**: Adding an element
  - **Pop**: Removing (and retrieving) an element
- **Local variables and parameter variables** are created in stack area
- **Creation and destruction** is very fast in stack
- Variables are created when flow reaches declaration point
- Variables are destroyed when their block ends

```java
public class StackExample {
    public static void main(String[] args) {
        int x = 10;        // Created in stack
        method1(x);        // x passed as argument
        System.out.println("Back in main: " + x);  // x still exists
    }
    
    public static void method1(int parameter) {  // parameter created in stack
        int localVar = 20;  // localVar created in stack
        System.out.println("In method1: " + parameter + ", " + localVar);
        // localVar and parameter destroyed when method ends
    }
}
```

### Stack Variable Lifetime Example

```java
public class StackLifetimeExample {
    public static void main(String[] args) {
        int outer = 100;
        System.out.println("Outer variable: " + outer);
        
        {   // New block starts
            int inner = 200;
            System.out.println("Inner variable: " + inner);
            System.out.println("Outer still accessible: " + outer);
        }   // inner is destroyed here
        
        System.out.println("Outer still exists: " + outer);
        // System.out.println(inner); // ERROR: inner no longer exists
    }
}
```

## User Defined Types (UDT)

Class declaration is actually a type declaration. Besides class declaration, there are other type declarations (interfaces, annotations, etc.). Such declarations are called **User Defined Types (UDT)**.

Since class declaration is a type declaration, variables of class type can be declared.

### Value Types vs Reference Types

In Java, types are categorically divided into 2 groups:
- **Value types**: Variable contains the actual value
- **Reference types**: Variable contains an address (reference)

**In Java, a class is a reference type.** Variables of reference types are called **reference variables** or simply **references**.

## References and Objects

A **reference variable** stores an address. An area allocated in memory for a class type is called an **object**. Objects are created in the **heap area**. 

**Important Notes**:
- Objects cannot be created in stack in Java
- In Java, you cannot hold the object itself in a variable
- You can only hold the address of an object
- This address is stored in a reference variable of the same type

### Object Creation with new Operator

Objects are created using the `new` operator:

```
new <class name>([arguments]);
```

The `new` operator is a special-purpose, unary, prefix operator that produces the address of the created object.

### Basic Class and Object Example

```java
public class BasicClassExample {
    public static void main(String[] args) {
        // Creating objects
        Person person1 = new Person();  // person1 is a reference to Person object
        Person person2 = new Person();  // person2 is a reference to different Person object
        
        System.out.println("person1 reference: " + person1);
        System.out.println("person2 reference: " + person2);
        
        // Each new creates a different object
        System.out.println("Same object? " + (person1 == person2));  // false
    }
}

class Person {
    // Class content will be added as we learn more concepts
}
```

## Class Data Members (Fields)

Variables declared inside a class but outside all methods are called **class data members** or **fields**. Data members can have access modifiers and can be static or non-static.

### Non-Static Data Members

Non-static data members are created **inside each object** when the object is created. They are object-specific (each object has its own copy).

```java
public class DataMemberExample {
    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();
        
        // Each object has its own copy of data members
        student1.name = "Alice";
        student1.age = 20;
        student1.grade = 85.5;
        
        student2.name = "Bob";
        student2.age = 22;
        student2.grade = 92.0;
        
        // Accessing data members through references
        System.out.println("Student 1: " + student1.name + ", Age: " + student1.age + ", Grade: " + student1.grade);
        System.out.println("Student 2: " + student2.name + ", Age: " + student2.age + ", Grade: " + student2.grade);
    }
}

class Student {
    // Non-static data members (instance variables)
    public String name;    // Default value: null
    public int age;        // Default value: 0
    public double grade;   // Default value: 0.0
}
```

### Default Values for Data Members

When an object is created, non-static data members are assigned **default values**:

```java
public class DefaultValuesExample {
    public static void main(String[] args) {
        DataTypes obj = new DataTypes();
        
        System.out.println("int: " + obj.intValue);           // 0
        System.out.println("double: " + obj.doubleValue);     // 0.0
        System.out.println("boolean: " + obj.booleanValue);   // false
        System.out.println("char: '" + obj.charValue + "'");  // '\u0000' (null character)
        System.out.println("String: " + obj.stringValue);     // null
    }
}

class DataTypes {
    public int intValue;
    public double doubleValue;
    public boolean booleanValue;
    public char charValue;
    public String stringValue;
}
```

### Static Data Members

Static data members belong to the class itself, not to any specific object. They are created when the class is first used and exist throughout the program's lifetime.

```java
public class StaticDataExample {
    public static void main(String[] args) {
        // Accessing static members through class name (preferred)
        Counter.count = 100;
        System.out.println("Count: " + Counter.count);
        
        // Can also access through object reference (not recommended)
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        
        c1.count = 200;  // Changes the same static variable
        System.out.println("c2.count: " + c2.count);  // 200 (same variable)
        
        // Increment through different references
        c1.incrementCount();
        c2.incrementCount();
        
        System.out.println("Final count: " + Counter.count);  // 202
    }
}

class Counter {
    public static int count = 0;  // Static data member
    
    public static void incrementCount() {
        count++;
    }
}
```

## Object Size and Reference Size

- **Reference size**: Depends on the system (4 bytes on 32-bit, 8 bytes on 64-bit systems)
- **Object size**: At least the sum of its non-static data members, plus system overhead
- **Memory alignment**: System may add padding for optimal access

```java
public class SizeExample {
    public static void main(String[] args) {
        // All references have the same size regardless of class
        Person person = new Person();
        Student student = new Student();
        LargeClass large = new LargeClass();
        
        // Each object size depends on its data members
        System.out.println("Person object created");      // Small object
        System.out.println("Student object created");     // Medium object  
        System.out.println("LargeClass object created");  // Large object
    }
}

class Person {
    public String name;     // Reference: 8 bytes (on 64-bit)
}

class Student {
    public String name;     // Reference: 8 bytes
    public int age;         // int: 4 bytes
    public double grade;    // double: 8 bytes
    // Total: ~20 bytes + overhead
}

class LargeClass {
    public String name;
    public int[] numbers = new int[1000];  // Reference to large array
    public double value1, value2, value3, value4, value5;
    // Larger object due to multiple data members
}
```

## Methods and Objects

### Non-Static Methods

Non-static methods are called through object references and can access both static and non-static members.

```java
public class MethodExample {
    public static void main(String[] args) {
        Calculator calc1 = new Calculator();
        Calculator calc2 = new Calculator();
        
        // Each object maintains its own state
        calc1.setValue(10);
        calc2.setValue(20);
        
        calc1.add(5);
        calc2.multiply(3);
        
        calc1.displayResult();  // 15
        calc2.displayResult();  // 60
    }
}

class Calculator {
    private double value;  // Non-static data member
    
    // Non-static methods
    public void setValue(double value) {
        this.value = value;  // 'this' refers to current object
    }
    
    public void add(double amount) {
        value += amount;     // Accessing non-static member directly
    }
    
    public void multiply(double factor) {
        value *= factor;
    }
    
    public void displayResult() {
        System.out.println("Result: " + value);
    }
}
```

### Static Methods

Static methods belong to the class and cannot directly access non-static members.

```java
public class StaticMethodExample {
    public static void main(String[] args) {
        // Calling static methods through class name
        int result1 = MathUtils.add(10, 20);
        double result2 = MathUtils.calculateCircleArea(5.0);
        
        System.out.println("Addition result: " + result1);
        System.out.println("Circle area: " + result2);
        
        // Static methods cannot access non-static members directly
        MathUtils utils = new MathUtils();
        utils.setLastResult(result1);  // Must use object to access non-static
    }
}

class MathUtils {
    private static int operationCount = 0;  // Static member
    private int lastResult;                 // Non-static member
    
    // Static methods
    public static int add(int a, int b) {
        operationCount++;  // Can access static members
        return a + b;
    }
    
    public static double calculateCircleArea(double radius) {
        operationCount++;
        return Math.PI * radius * radius;
    }
    
    public static int getOperationCount() {
        return operationCount;
    }
    
    // Non-static method
    public void setLastResult(int result) {
        this.lastResult = result;  // Can access non-static members
        operationCount++;          // Can also access static members
    }
}
```

## Member Access Rules

### From Non-Static Methods
- ✅ Can access non-static data members directly
- ✅ Can call non-static methods directly  
- ✅ Can access static data members directly
- ✅ Can call static methods directly

### From Static Methods
- ❌ Cannot access non-static data members directly
- ❌ Cannot call non-static methods directly
- ✅ Can access static data members directly
- ✅ Can call static methods directly

```java
public class AccessRulesExample {
    private int instanceVar = 10;        // Non-static
    private static int staticVar = 20;   // Static
    
    // Non-static method - can access everything
    public void nonStaticMethod() {
        System.out.println("Instance var: " + instanceVar);  // ✅ OK
        System.out.println("Static var: " + staticVar);      // ✅ OK
        anotherNonStaticMethod();                             // ✅ OK
        staticMethod();                                       // ✅ OK
    }
    
    // Static method - limited access
    public static void staticMethod() {
        // System.out.println("Instance var: " + instanceVar);  // ❌ Error
        System.out.println("Static var: " + staticVar);         // ✅ OK
        // anotherNonStaticMethod();                            // ❌ Error
        anotherStaticMethod();                                  // ✅ OK
    }
    
    public void anotherNonStaticMethod() {
        System.out.println("Another non-static method");
    }
    
    public static void anotherStaticMethod() {
        System.out.println("Another static method");
    }
}
```

## Reference Assignment and Method Parameters

### Reference Assignment

```java
public class ReferenceAssignmentExample {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Alice";
        person1.age = 25;
        
        Person person2 = person1;  // Both references point to same object
        
        person2.name = "Bob";      // Changes the same object
        
        System.out.println("person1.name: " + person1.name);  // "Bob"
        System.out.println("person2.name: " + person2.name);  // "Bob"
        System.out.println("Same object? " + (person1 == person2));  // true
    }
}

class Person {
    public String name;
    public int age;
}
```

### Objects as Method Parameters

When an object reference is passed to a method, both the parameter and argument refer to the same object.

```java
public class ObjectParameterExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.balance = 1000.0;
        
        System.out.println("Before deposit: " + account.balance);
        
        // Pass object reference to method
        BankingService.deposit(account, 250.0);
        
        System.out.println("After deposit: " + account.balance);  // Object was modified
    }
}

class BankAccount {
    public double balance;
    public String accountNumber;
}

class BankingService {
    public static void deposit(BankAccount account, double amount) {
        // account parameter refers to the same object as the argument
        account.balance += amount;  // Modifies the original object
        System.out.println("Deposited: " + amount);
    }
    
    public static BankAccount createAccount(String accountNumber, double initialBalance) {
        BankAccount newAccount = new BankAccount();
        newAccount.accountNumber = accountNumber;
        newAccount.balance = initialBalance;
        return newAccount;  // Returns reference to the created object
    }
}
```

### Method Returning Object Reference

```java
public class ObjectReturnExample {
    public static void main(String[] args) {
        // Method returns reference to newly created object
        Point point1 = PointFactory.createPoint(10, 20);
        Point point2 = PointFactory.createOrigin();
        
        point1.display();
        point2.display();
        
        // Using returned object immediately
        PointFactory.createPoint(5, 15).display();
    }
}

class Point {
    public double x, y;
    
    public void display() {
        System.out.printf("Point(%.1f, %.1f)%n", x, y);
    }
}

class PointFactory {
    public static Point createPoint(double x, double y) {
        Point point = new Point();
        point.x = x;
        point.y = y;
        return point;  // Return reference to created object
    }
    
    public static Point createOrigin() {
        return createPoint(0, 0);  // Reuse existing method
    }
}
```

## Practical Example: Building a Simple Class

```java
public class StudentManagementExample {
    public static void main(String[] args) {
        // Create student objects
        Student student1 = StudentFactory.createStudent("Alice Johnson", 20, "Computer Science");
        Student student2 = StudentFactory.createStudent("Bob Smith", 19, "Mathematics");
        
        // Add grades
        StudentService.addGrade(student1, 85.5);
        StudentService.addGrade(student1, 92.0);
        StudentService.addGrade(student1, 78.5);
        
        StudentService.addGrade(student2, 95.0);
        StudentService.addGrade(student2, 88.0);
        
        // Display information
        StudentService.displayStudentInfo(student1);
        StudentService.displayStudentInfo(student2);
        
        // Show total number of students
        System.out.println("Total students created: " + Student.totalStudents);
    }
}

class Student {
    // Static member - shared by all instances
    public static int totalStudents = 0;
    
    // Non-static members - each object has its own copy
    public String name;
    public int age;
    public String major;
    public double totalGrades;
    public int gradeCount;
    
    // Constructor-like initialization will be covered later
    // For now, we'll use factory methods
}

class StudentFactory {
    public static Student createStudent(String name, int age, String major) {
        Student student = new Student();
        student.name = name;
        student.age = age;
        student.major = major;
        student.totalGrades = 0.0;
        student.gradeCount = 0;
        
        Student.totalStudents++;  // Increment static counter
        
        return student;
    }
}

class StudentService {
    public static void addGrade(Student student, double grade) {
        student.totalGrades += grade;
        student.gradeCount++;
    }
    
    public static double calculateAverage(Student student) {
        if (student.gradeCount == 0) {
            return 0.0;
        }
        return student.totalGrades / student.gradeCount;
    }
    
    public static void displayStudentInfo(Student student) {
        System.out.println("=== Student Information ===");
        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
        System.out.println("Major: " + student.major);
        System.out.println("Grades recorded: " + student.gradeCount);
        System.out.printf("Average grade: %.2f%n", calculateAverage(student));
        System.out.println();
    }
}
```

## Key Concepts Summary

1. **Stack vs Heap**: Local variables in stack, objects in heap
2. **References**: Variables that store object addresses
3. **Object Creation**: Using `new` operator creates objects in heap
4. **Data Members**: Non-static (per object) vs Static (per class)
5. **Methods**: Non-static (through objects) vs Static (through class)
6. **Access Rules**: Static methods have limited access to non-static members
7. **Default Values**: Automatic initialization of data members
8. **Reference Assignment**: Multiple references can point to same object
9. **Object Parameters**: Pass-by-reference behavior
10. **Memory Management**: System handles object creation and destruction

Understanding these fundamental concepts is essential before moving to constructors, encapsulation, and other advanced OOP features.
