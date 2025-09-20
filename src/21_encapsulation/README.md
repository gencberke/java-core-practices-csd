# ENCAPSULATION / DATA HIDING

In OOP, hiding data members is called **encapsulation**. There are concepts called **server codes** and **client codes**. A person who is proficient in server codes has knowledge of both the people who use and write the code. A person who is proficient in client code will only benefit from it. In this sense, they belong to the user class.

When a class data member is hidden, it must be accessed from outside through **public methods**. For this purpose, we write public methods that access the data member (e.g., getters and setters). The **public** and **protected** parts of a class are documented because, as we have seen so far, **private** and **no-modifier** parts are only important for that class.

## When Should Data Members Be Hidden?

If **at least one** of the following reasons exists, the class member **must definitely be hidden**:

| Situation | Why Hide? | Example |
|-----------|-----------|---------|
| **1. Version changes** | To prevent old code from breaking when class code changes (name/type differences) | `int age` → later changed to `LocalDate birthDate` |
| **2. Boundary values** | Control is needed to not exceed certain value ranges | Grade must be between 0-100; controlled with setter |
| **3. Dependent calculation** | If a variable's value is calculated based on another variable, external interference should not be allowed | `area` value should only be calculated with `width * height` |
| **4. Action required when value changes** | Additional operations may need to be performed when value changes | If DB connection address changes, old connection must be closed and new one opened |

### Key Notes:
- Even if these situations don't exist, data members can be hidden for **integrity purposes**
- **Getter (accessor)** and **setter (mutator)** methods are written for access to hidden elements
- If one of the above situations exists, it **must definitely be hidden**
- If none exist (rare 3-5%), data member can be made **public**

## Method Hiding

If a method's external call is meaningless, in other words, if that method is meaningful for that class, the method is hidden.

## About Accessor Methods

In some cases, an accessor method may not directly return the value of a data member in the background. It can also return the result of an operation using some data members. For the client codes of the class, these methods are still accessor methods.

## Utility Class Convention

Creating an object from a class type whose all members are declared as **static** makes no sense. Because an object is meaningful for **non-static** data members of the related class and methods that use them.

### Utility Class Rules:
- The **constructor** of a class whose all members are declared as static is made **private** as a convention
- This convention is also followed in classes within JavaSE
- For example, since all members of `Math` and `Arrays` classes are declared as static, their constructors are made private
- Classes of this type whose all members are declared as static are generally called **utility classes**
- A Java programmer should follow this convention for the utility they write

### Why Private Constructor?
If the constructor were public, we could create objects from the public utility class, which would be meaningless.

## Examples

### Example 1: Basic Encapsulation

```java
public class Student {
    private String name;
    private int age;
    private double grade;
    
    // Constructor
    public Student(String name, int age, double grade) {
        this.name = name;
        setAge(age);     // Use setter for validation
        setGrade(grade); // Use setter for validation
    }
    
    // Getter methods (accessors)
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public double getGrade() {
        return grade;
    }
    
    // Setter methods (mutators) with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
    
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {  // Boundary values
            this.age = age;
        } else {
            System.out.println("Invalid age: " + age);
        }
    }
    
    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {  // Boundary values
            this.grade = grade;
        } else {
            System.out.println("Invalid grade: " + grade);
        }
    }
    
    // Computed accessor method
    public String getGradeStatus() {
        if (grade >= 90) return "Excellent";
        else if (grade >= 80) return "Good";
        else if (grade >= 70) return "Average";
        else if (grade >= 60) return "Pass";
        else return "Fail";
    }
}
```

### Example 2: Version Changes Example

```java
// Version 1: Using separate variables
public class TimeV1 {
    private int hour;
    private int minute;
    private int second;
    
    public TimeV1(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public int getHour() { return hour; }
    public int getMinute() { return minute; }
    public int getSecond() { return second; }
    
    public void setHour(int hour) { this.hour = hour; }
    public void setMinute(int minute) { this.minute = minute; }
    public void setSecond(int second) { this.second = second; }
}

// Version 2: Using string format (internal change, same interface)
public class TimeV2 {
    private String timeString;  // Changed internal representation
    
    public TimeV2(int hour, int minute, int second) {
        this.timeString = String.format("%02d:%02d:%02d", hour, minute, second);
    }
    
    // Same public interface - no client code needs to change
    public int getHour() {
        return Integer.parseInt(timeString.substring(0, 2));
    }
    
    public int getMinute() {
        return Integer.parseInt(timeString.substring(3, 5));
    }
    
    public int getSecond() {
        return Integer.parseInt(timeString.substring(6, 8));
    }
    
    public void setHour(int hour) {
        timeString = String.format("%02d:%02d:%02d", hour, getMinute(), getSecond());
    }
    
    public void setMinute(int minute) {
        timeString = String.format("%02d:%02d:%02d", getHour(), minute, getSecond());
    }
    
    public void setSecond(int second) {
        timeString = String.format("%02d:%02d:%02d", getHour(), getMinute(), second);
    }
}
```

### Example 3: Dependent Calculation

```java
public class Rectangle {
    private double width;
    private double height;
    // Note: area is NOT stored as a field - it's calculated
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public double getWidth() {
        return width;
    }
    
    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        }
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        }
    }
    
    // Computed accessor - calculates area when needed
    public double getArea() {
        return width * height;  // Always calculated, never stored
    }
    
    // No setArea() method - area depends on width and height
}
```

### Example 4: Utility Class

```java
public class MathUtils {
    // Private constructor - prevents instantiation
    private MathUtils() {
        // Utility class should not be instantiated
    }
    
    // All methods are static
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    public static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}

// Usage
public class TestMathUtils {
    public static void main(String[] args) {
        // MathUtils utils = new MathUtils();  // ERROR: Constructor is private
        
        double area = MathUtils.calculateCircleArea(5.0);
        double distance = MathUtils.calculateDistance(0, 0, 3, 4);
        boolean prime = MathUtils.isPrime(17);
        
        System.out.println("Area: " + area);
        System.out.println("Distance: " + distance);
        System.out.println("Is 17 prime? " + prime);
    }
}
```

## Benefits of Encapsulation

1. **Data Protection**: Prevents invalid data from being set
2. **Flexibility**: Internal implementation can change without affecting client code
3. **Maintainability**: Changes are localized to the class
4. **Debugging**: Easier to track where data is modified
5. **Code Reusability**: Well-encapsulated classes can be reused in different contexts
