# CONSTRUCTORS

A **constructor** is a special method that is called during object creation. Constructors are used to perform initial operations before the object becomes fully available for use.

## Object Creation Steps

When an object is created with the `new` operator, the following steps occur:

1. **Memory allocation**: Space is allocated in memory (heap)
2. **Default value assignment**: Non-static, non-final data members are assigned default values
3. **Constructor call**: The appropriate constructor is called

If any step fails, object creation is not completed.

## Constructor Characteristics

A constructor has the following properties:

- **Called during object creation**: Constructors are automatically invoked when objects are created
- **Can be overloaded**: A class can have multiple constructors with different parameter lists
- **Default constructor**: If no constructor is provided, the compiler generates a default public constructor
- **Same name as class**: Constructor name must be identical to the class name
- **No return type**: Constructors don't have return type information (not even void)
- **Non-static**: Constructors are always non-static methods
- **Method overload resolution**: Which constructor to call is determined by the arguments passed to `new`
- **Cannot be called directly**: Only called during object creation, not by programmer directly
- **Default values already assigned**: Non-static data members have default values before constructor execution
- **Can use return**: Return statement can be used to terminate constructor early (object is still created)

## Basic Constructor Examples

### Default Constructor

```java
public class Person {
    public String name;
    public int age;
    
    // Default constructor (no parameters)
    public Person() {
        System.out.println("Person object created with default constructor");
        name = "Unknown";
        age = 0;
    }
    
    public void displayInfo() {
        System.out.printf("Name: %s, Age: %d%n", name, age);
    }
}

public class DefaultConstructorExample {
    public static void main(String[] args) {
        Person person1 = new Person();  // Calls default constructor
        person1.displayInfo();
        
        Person person2 = new Person();  // Each object gets its own constructor call
        person2.displayInfo();
    }
}
```

### Parameterized Constructor

```java
public class Student {
    public String name;
    public int age;
    public String major;
    public double gpa;
    
    // Constructor with parameters
    public Student(String studentName, int studentAge, String studentMajor) {
        System.out.println("Student constructor called");
        name = studentName;
        age = studentAge;
        major = studentMajor;
        gpa = 0.0;  // Default value
    }
    
    public void displayInfo() {
        System.out.printf("Student: %s, Age: %d, Major: %s, GPA: %.2f%n", 
                         name, age, major, gpa);
    }
}

public class ParameterizedConstructorExample {
    public static void main(String[] args) {
        Student student1 = new Student("Alice Johnson", 20, "Computer Science");
        Student student2 = new Student("Bob Smith", 19, "Mathematics");
        
        student1.displayInfo();
        student2.displayInfo();
    }
}
```

## Constructor Overloading

A class can have multiple constructors with different parameter lists.

```java
public class Rectangle {
    public double width;
    public double height;
    
    // Default constructor - creates unit square
    public Rectangle() {
        System.out.println("Default constructor called");
        width = 1.0;
        height = 1.0;
    }
    
    // Constructor with equal width and height (square)
    public Rectangle(double side) {
        System.out.println("Square constructor called");
        width = side;
        height = side;
    }
    
    // Constructor with different width and height
    public Rectangle(double rectWidth, double rectHeight) {
        System.out.println("Rectangle constructor called");
        width = rectWidth;
        height = rectHeight;
    }
    
    public double calculateArea() {
        return width * height;
    }
    
    public void displayInfo() {
        System.out.printf("Rectangle: %.1f x %.1f, Area: %.2f%n", 
                         width, height, calculateArea());
    }
}

public class ConstructorOverloadingExample {
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle();          // Default constructor
        Rectangle rect2 = new Rectangle(5.0);       // Square constructor  
        Rectangle rect3 = new Rectangle(4.0, 6.0);  // Rectangle constructor
        
        rect1.displayInfo();
        rect2.displayInfo();
        rect3.displayInfo();
    }
}
```

## Default Constructor Rules

### Compiler-Generated Default Constructor

```java
// If no constructor is provided, compiler generates this:
public class SimpleClass {
    public int value;
    
    // Compiler generates:
    // public SimpleClass() {
    //     // Empty body
    // }
}

public class DefaultGenerationExample {
    public static void main(String[] args) {
        SimpleClass obj = new SimpleClass();  // Uses compiler-generated constructor
        System.out.println("Value: " + obj.value);  // Prints 0 (default value)
    }
}
```

### No Default Constructor When Custom Constructor Exists

```java
public class CustomConstructorClass {
    public String name;
    public int value;
    
    // Custom constructor provided
    public CustomConstructorClass(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    // Compiler does NOT generate default constructor
}

public class NoDefaultConstructorExample {
    public static void main(String[] args) {
        // This works
        CustomConstructorClass obj1 = new CustomConstructorClass("Test", 100);
        
        // This would cause compilation error
        // CustomConstructorClass obj2 = new CustomConstructorClass();  // ERROR
    }
}
```

### Providing Both Default and Custom Constructors

```java
public class FlexibleClass {
    public String name;
    public int value;
    
    // Explicit default constructor
    public FlexibleClass() {
        name = "Default";
        value = 0;
    }
    
    // Custom constructor
    public FlexibleClass(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    public void display() {
        System.out.printf("Name: %s, Value: %d%n", name, value);
    }
}

public class FlexibleConstructorExample {
    public static void main(String[] args) {
        FlexibleClass obj1 = new FlexibleClass();              // Default constructor
        FlexibleClass obj2 = new FlexibleClass("Custom", 50);  // Custom constructor
        
        obj1.display();
        obj2.display();
    }
}
```

## Constructor with Validation

```java
public class BankAccount {
    public String accountNumber;
    public String ownerName;
    public double balance;
    
    // Constructor with validation
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        System.out.println("Creating bank account...");
        
        // Validate account number
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            System.out.println("Warning: Invalid account number, using default");
            this.accountNumber = "UNKNOWN";
        } else {
            this.accountNumber = accountNumber;
        }
        
        // Validate owner name
        if (ownerName == null || ownerName.trim().isEmpty()) {
            System.out.println("Warning: Invalid owner name, using default");
            this.ownerName = "Unknown Owner";
        } else {
            this.ownerName = ownerName;
        }
        
        // Validate initial balance
        if (initialBalance < 0) {
            System.out.println("Warning: Negative balance not allowed, setting to 0");
            this.balance = 0.0;
        } else {
            this.balance = initialBalance;
        }
        
        System.out.println("Bank account created successfully");
    }
    
    public void displayAccount() {
        System.out.printf("Account: %s, Owner: %s, Balance: $%.2f%n", 
                         accountNumber, ownerName, balance);
    }
}

public class ValidationConstructorExample {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("ACC001", "John Doe", 1000.0);
        BankAccount account2 = new BankAccount("", "", -500.0);  // Invalid values
        
        account1.displayAccount();
        account2.displayAccount();
    }
}
```

## Constructor Chaining (Constructor Delegation)

Constructors can call other constructors in the same class using `this()`.

```java
public class Employee {
    public String name;
    public int id;
    public String department;
    public double salary;
    
    // Most complete constructor
    public Employee(String name, int id, String department, double salary) {
        System.out.println("Full constructor called");
        this.name = name;
        this.id = id;
        this.department = department;
        this.salary = salary;
    }
    
    // Constructor with default department
    public Employee(String name, int id, double salary) {
        this(name, id, "General", salary);  // Call full constructor
        System.out.println("Constructor with default department called");
    }
    
    // Constructor with default department and salary
    public Employee(String name, int id) {
        this(name, id, "General", 30000.0);  // Call full constructor
        System.out.println("Constructor with default department and salary called");
    }
    
    // Default constructor
    public Employee() {
        this("Unknown", 0, "General", 30000.0);  // Call full constructor
        System.out.println("Default constructor called");
    }
    
    public void displayEmployee() {
        System.out.printf("Employee: %s (ID: %d), Department: %s, Salary: $%.2f%n", 
                         name, id, department, salary);
    }
}

public class ConstructorChainingExample {
    public static void main(String[] args) {
        System.out.println("Creating employee with full constructor:");
        Employee emp1 = new Employee("Alice Johnson", 101, "Engineering", 75000.0);
        emp1.displayEmployee();
        
        System.out.println("\\nCreating employee with default department:");
        Employee emp2 = new Employee("Bob Smith", 102, 65000.0);
        emp2.displayEmployee();
        
        System.out.println("\\nCreating employee with defaults:");
        Employee emp3 = new Employee("Charlie Brown", 103);
        emp3.displayEmployee();
        
        System.out.println("\\nCreating employee with default constructor:");
        Employee emp4 = new Employee();
        emp4.displayEmployee();
    }
}
```

## Constructor vs Method Differences

### Constructor with Same Name as Class but with Return Type

```java
public class ConstructorVsMethod {
    public String name;
    
    // This is a CONSTRUCTOR (no return type)
    public ConstructorVsMethod(String name) {
        this.name = name;
        System.out.println("Constructor called");
    }
    
    // This is a REGULAR METHOD (has return type)
    public void ConstructorVsMethod(String newName) {
        this.name = newName;
        System.out.println("Method called");
    }
    
    public void display() {
        System.out.println("Name: " + name);
    }
}

public class ConstructorMethodExample {
    public static void main(String[] args) {
        // Constructor call (during object creation)
        ConstructorVsMethod obj = new ConstructorVsMethod("Initial Name");
        obj.display();
        
        // Method call (after object creation)
        obj.ConstructorVsMethod("New Name");  // Calling the method
        obj.display();
    }
}
```

## Practical Constructor Examples

### Date Class Example

```java
public class SimpleDate {
    public int day;
    public int month;
    public int year;
    
    // Default constructor - current date simulation
    public SimpleDate() {
        day = 1;
        month = 1;
        year = 2024;
        System.out.println("Default date constructor called");
    }
    
    // Constructor with day, month, year
    public SimpleDate(int day, int month, int year) {
        System.out.println("Full date constructor called");
        
        // Basic validation
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            System.out.println("Invalid date provided, using default");
            this.day = 1;
            this.month = 1;
            this.year = 2024;
        }
    }
    
    // Copy constructor-like behavior
    public SimpleDate(SimpleDate other) {
        if (other != null) {
            this.day = other.day;
            this.month = other.month;
            this.year = other.year;
            System.out.println("Copy constructor called");
        } else {
            this.day = 1;
            this.month = 1;
            this.year = 2024;
            System.out.println("Copy constructor called with null, using default");
        }
    }
    
    private boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;
        if (year < 1900) return false;
        
        // Simplified validation
        if (month == 2 && day > 29) return false;
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) return false;
        
        return true;
    }
    
    public void displayDate() {
        System.out.printf("Date: %02d/%02d/%04d%n", day, month, year);
    }
}

public class DateConstructorExample {
    public static void main(String[] args) {
        SimpleDate date1 = new SimpleDate();                    // Default
        SimpleDate date2 = new SimpleDate(15, 6, 2024);         // Valid date
        SimpleDate date3 = new SimpleDate(32, 13, 2024);        // Invalid date
        SimpleDate date4 = new SimpleDate(date2);               // Copy
        
        date1.displayDate();
        date2.displayDate();
        date3.displayDate();
        date4.displayDate();
    }
}
```

### Point Class with Multiple Constructors

```java
public class Point {
    public double x;
    public double y;
    
    // Origin point (0, 0)
    public Point() {
        x = 0.0;
        y = 0.0;
        System.out.println("Origin point created");
    }
    
    // Point with same x and y coordinates
    public Point(double coordinate) {
        x = coordinate;
        y = coordinate;
        System.out.printf("Point created at (%.1f, %.1f)%n", x, y);
    }
    
    // Point with different x and y coordinates
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        System.out.printf("Point created at (%.1f, %.1f)%n", x, y);
    }
    
    // Copy constructor
    public Point(Point other) {
        if (other != null) {
            this.x = other.x;
            this.y = other.y;
            System.out.printf("Point copied at (%.1f, %.1f)%n", x, y);
        } else {
            this.x = 0.0;
            this.y = 0.0;
            System.out.println("Null point provided, created origin");
        }
    }
    
    public double distanceToOrigin() {
        return Math.sqrt(x * x + y * y);
    }
    
    public double distanceTo(Point other) {
        if (other == null) return 0.0;
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public void display() {
        System.out.printf("Point(%.1f, %.1f) - Distance to origin: %.2f%n", 
                         x, y, distanceToOrigin());
    }
}

public class PointConstructorExample {
    public static void main(String[] args) {
        Point origin = new Point();                    // (0, 0)
        Point square = new Point(5.0);                 // (5, 5)
        Point custom = new Point(3.0, 4.0);           // (3, 4)
        Point copy = new Point(custom);                // Copy of (3, 4)
        
        origin.display();
        square.display();
        custom.display();
        copy.display();
        
        System.out.printf("Distance between custom and copy: %.2f%n", 
                         custom.distanceTo(copy));
    }
}
```

## Constructor Best Practices

### 1. Initialize All Fields

```java
public class GoodConstructor {
    private String name;
    private int age;
    private boolean active;
    
    public GoodConstructor(String name, int age) {
        this.name = name != null ? name : "Unknown";  // Handle null
        this.age = age >= 0 ? age : 0;                 // Validate age
        this.active = true;                            // Initialize all fields
    }
}
```

### 2. Use Constructor Chaining

```java
public class ChainedConstructors {
    private String name;
    private int id;
    private String email;
    
    // Main constructor
    public ChainedConstructors(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }
    
    // Delegating constructors
    public ChainedConstructors(String name, int id) {
        this(name, id, "no-email@example.com");
    }
    
    public ChainedConstructors(String name) {
        this(name, 0, "no-email@example.com");
    }
}
```

### 3. Validate Input Parameters

```java
public class ValidatedConstructor {
    private String email;
    private int age;
    
    public ValidatedConstructor(String email, int age) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age");
        }
        
        this.email = email;
        this.age = age;
    }
}
```

## Common Constructor Mistakes

### 1. Not Handling Null Values

```java
// BAD
public class BadConstructor {
    private String name;
    
    public BadConstructor(String name) {
        this.name = name.toUpperCase();  // NullPointerException if name is null
    }
}

// GOOD
public class GoodConstructor {
    private String name;
    
    public GoodConstructor(String name) {
        this.name = name != null ? name.toUpperCase() : "UNKNOWN";
    }
}
```

### 2. Not Initializing All Fields

```java
// BAD
public class IncompleteConstructor {
    private String name;
    private int count;
    private boolean flag;
    
    public IncompleteConstructor(String name) {
        this.name = name;
        // count and flag get default values (0 and false)
        // But this might not be the intended behavior
    }
}
```

## Key Points

1. **Constructors initialize objects** during creation
2. **Constructor name must match class name** exactly
3. **No return type** for constructors (not even void)
4. **Can be overloaded** with different parameter lists
5. **Default constructor** is provided if none exists
6. **Constructor chaining** with `this()` reduces code duplication
7. **Validation in constructors** ensures object integrity
8. **Default values** are assigned before constructor execution
9. **Early return** with `return` statement is allowed
10. **Cannot be called directly** - only during object creation

Constructors are essential for proper object initialization and ensuring that objects start in a valid, consistent state.
