# FINAL KEYWORD

The `final` keyword is an important keyword used in the following declarations:

1. **final variables**
2. **final classes**
3. **final methods**

## Final Variables

As remembered, Java has 3 types of variables: local variables, member variables, parameter variables. When a variable is declared as `final`, it means **it can only be assigned a value once**, and you cannot change it afterward.

Some variables can have a final effect under certain conditions even if the `final` keyword is not used. This concept is called **effectively final**. This concept will be covered later.

With Java 8, the requirement for local variables and parameter variables to be declared as `final` has been eliminated. The reason for this will be covered in the Java Application Development I course. However, **declaring member variables as final is quite important**.

## Final Variable Rules

### Local Variables
A final local variable can be assigned a value **only once** within its scope, either by initialization or assignment.

### Parameter Variables
A method's parameter variable can be `final`. In this case, the value of that parameter variable cannot be changed within the method.

### Member Variables
A member variable can be declared as `final`. **Final member variables are NOT given default values**.

> **Note:** For class member variables, access modifier, `static` keyword, and `final` keyword are at the same syntax level, so they can be written in any order. However, for readability/comprehensibility, it is appropriate to write them in the following order in declarations: **[access modifier] [static] [final]**

## Assigning Values to Final Member Variables

### Non-Static Final Member Variables
A class's **non-static** and **final** declared member variable can be assigned a value in **only one** of the following 3 places:

- **At declaration point** (initialization)
- **Inside all constructors**
- **Inside non-static initializer**

### Static Final Member Variables
A class's **static** and **final** declared member variable can be assigned a value in **only one** of the following 2 places:

- **At declaration point** (initialization)
- **Inside static initializer**

> **Note:** Static and non-static initializer elements will be covered later.

> **Note:** If a non-static and final declared member variable is not assigned a value outside the constructor, **it must be assigned a value in all constructors**. Otherwise, an error occurs.

## Constant Expressions and Naming Convention

It is not mandatory to assign a value to a final variable with a constant expression. When a value is assigned to a class's static and final declared member variable with a constant expression, that member variable can be used as a constant expression.

For a class's `public`, `static`, and `final` declared member variable (especially when assigned with a constant expression), as a **convention**, it should be named in **all uppercase letters**, and if it consists of multiple words, the words should be separated by **underscore characters**.

## Examples

### Example 1: Basic Final Usage

```java
public class FinalBasics {
    // Static final constants - naming convention
    public static final int MAX_SIZE = 100;
    public static final String DEFAULT_NAME = "Unknown";
    public static final double PI_VALUE = 3.14159;
    
    // Non-static final member variable
    private final String id;
    private final int value;
    
    // Constructor must initialize all final member variables
    public FinalBasics(String id, int value) {
        this.id = id;     // Must be assigned
        this.value = value; // Must be assigned
    }
    
    // Alternative constructor
    public FinalBasics(String id) {
        this.id = id;
        this.value = 0;   // Must be assigned in all constructors
    }
    
    public void demonstrateLocalFinal() {
        final int localVar = 10;        // Initialized at declaration
        final int anotherVar;           // Will be assigned later
        
        anotherVar = 20;                // First and only assignment
        
        // localVar = 15;               // ERROR: Cannot reassign
        // anotherVar = 25;             // ERROR: Cannot reassign
        
        System.out.println("Local variables: " + localVar + ", " + anotherVar);
    }
    
    public void methodWithFinalParameter(final int param) {
        // param = 10;                  // ERROR: Cannot modify final parameter
        System.out.println("Parameter: " + param);
    }
    
    public String getId() { return id; }
    public int getValue() { return value; }
}
```

### Example 2: Array Reference Problem

The following example demonstrates a critical issue with final references and mutable objects.

```java
import java.util.Random;

// PROBLEM: Direct array reference exposure
public class RandomStringArrayGenerator {
    private final String[] m_texts;
    
    public RandomStringArrayGenerator(Random random, int count, int min, int max) {
        m_texts = generateRandomTexts(random, count, min, max);
    }
    
    // DANGEROUS: Returns direct reference to internal array
    public String[] getTexts() {
        return m_texts; // Client can modify internal array!
    }
    
    private String[] generateRandomTexts(Random random, int count, int min, int max) {
        String[] texts = new String[count];
        for (int i = 0; i < count; i++) {
            int length = random.nextInt(max - min + 1) + min;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                char c = (char) (random.nextInt(26) + 'a');
                sb.append(c);
            }
            texts[i] = sb.toString();
        }
        return texts;
    }
}

// Demonstration of the problem
class ProblemDemo {
    public static void main(String[] args) {
        RandomStringArrayGenerator generator = 
            new RandomStringArrayGenerator(new Random(), 5, 4, 8);
        String[] texts = generator.getTexts();
        
        System.out.println("Original texts:");
        for (String text : texts) {
            System.out.println(text);
        }
        System.out.println("---------------------------------------------------------------");
        
        // CLIENT CODE MODIFIES THE INTERNAL ARRAY!
        for (int i = 0; i < texts.length; ++i) {
            texts[i] = texts[i].toUpperCase();
        }
        
        System.out.println("After client modification:");
        for (String text : texts) {
            System.out.println(text);
        }
        System.out.println("---------------------------------------------------------------");
        
        System.out.println("Generator's internal array (corrupted!):");
        for (String text : generator.getTexts()) {
            System.out.println(text);
        }
        System.out.println("---------------------------------------------------------------");
    }
}
```

In the above `RandomStringArrayGenerator` class, the `m_texts` member variable is hidden. However, since the address inside the `m_texts` reference is given to the outside and the reference points to an array, the array elements have been modified in the client code. Therefore, the array held inside the class has also been affected. So hiding the reference variable (even making it final to prevent assignment) did not prevent the array object from being modified. **Because arrays are not immutable**.

### Example 3: Solution with Defensive Copying

```java
import java.util.Arrays;
import java.util.Random;

// SOLUTION: Defensive copying
public class SafeRandomStringArrayGenerator {
    private final String[] m_texts;
    
    public SafeRandomStringArrayGenerator(Random random, int count, int min, int max) {
        m_texts = generateRandomTexts(random, count, min, max);
    }
    
    // SAFE: Returns copy of internal array
    public String[] getTexts() {
        return Arrays.copyOf(m_texts, m_texts.length); // Defensive copy
    }
    
    private String[] generateRandomTexts(Random random, int count, int min, int max) {
        String[] texts = new String[count];
        for (int i = 0; i < count; i++) {
            int length = random.nextInt(max - min + 1) + min;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                char c = (char) (random.nextInt(26) + 'a');
                sb.append(c);
            }
            texts[i] = sb.toString();
        }
        return texts;
    }
}

// Demonstration of the safe solution
class SafeSolutionDemo {
    public static void main(String[] args) {
        SafeRandomStringArrayGenerator generator = 
            new SafeRandomStringArrayGenerator(new Random(), 5, 4, 8);
        String[] texts = generator.getTexts();
        
        System.out.println("Original texts:");
        for (String text : texts) {
            System.out.println(text);
        }
        System.out.println("---------------------------------------------------------------");
        
        // CLIENT CODE MODIFIES ONLY THE COPY
        for (int i = 0; i < texts.length; ++i) {
            texts[i] = texts[i].toUpperCase();
        }
        
        System.out.println("Modified copy:");
        for (String text : texts) {
            System.out.println(text);
        }
        System.out.println("---------------------------------------------------------------");
        
        System.out.println("Generator's internal array (protected!):");
        for (String text : generator.getTexts()) {
            System.out.println(text);
        }
        System.out.println("---------------------------------------------------------------");
    }
}
```

When the above class is written as shown, the address of a copy (a newly created array) is now given to the outside, not the direct address of the internally held array. In this case, the programmer who writes the class also indicates this situation in the documentation of the `getTexts` method.

### Arrays.copyOf() Method

**Key Notes:** The `Arrays.copyOf()` method returns a reference to a **newly allocated** array consisting of `newLength` elements from the array taken with the first parameter. If the array's length is passed as an argument to the second parameter, a copy of all elements of the array is created.

```java
import java.util.Arrays;

public class ArraysCopyExample {
    public static void main(String[] args) {
        String[] original = {"apple", "banana", "cherry", "date", "elderberry"};
        
        // Copy entire array
        String[] fullCopy = Arrays.copyOf(original, original.length);
        
        // Copy with smaller size
        String[] partialCopy = Arrays.copyOf(original, 3);
        
        // Copy with larger size (fills with null)
        String[] extendedCopy = Arrays.copyOf(original, 8);
        
        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Full copy: " + Arrays.toString(fullCopy));
        System.out.println("Partial copy: " + Arrays.toString(partialCopy));
        System.out.println("Extended copy: " + Arrays.toString(extendedCopy));
    }
}
```

### Example 4: Index-Based Access (No Array Exposure)

```java
import java.util.Random;

// BEST SOLUTION: No array exposure at all
public class SecureRandomStringArrayGenerator {
    private final String[] m_texts;
    
    public SecureRandomStringArrayGenerator(Random random, int count, int min, int max) {
        m_texts = generateRandomTexts(random, count, min, max);
    }
    
    public int size() {
        return m_texts.length;
    }
    
    public String get(int index) {
        if (index < 0 || index >= m_texts.length) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return m_texts[index];
    }
    
    private String[] generateRandomTexts(Random random, int count, int min, int max) {
        String[] texts = new String[count];
        for (int i = 0; i < count; i++) {
            int length = random.nextInt(max - min + 1) + min;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                char c = (char) (random.nextInt(26) + 'a');
                sb.append(c);
            }
            texts[i] = sb.toString();
        }
        return texts;
    }
}

// Usage with index-based access
class SecureAccessDemo {
    public static void main(String[] args) {
        SecureRandomStringArrayGenerator generator = 
            new SecureRandomStringArrayGenerator(new Random(), 5, 4, 8);
        int size = generator.size();
        
        for (int i = 0; i < size; ++i) {
            System.out.println(generator.get(i));
        }
    }
}
```

In the above example, the programmer using the class cannot obtain an array reference at all. This is the most secure approach as the internal array cannot be modified externally.

## Key Points

1. **Final reference ≠ Immutable object**: A final reference cannot be reassigned, but the object it points to may still be mutable
2. **Arrays are mutable**: Even if the array reference is final, array elements can be changed
3. **Defensive copying**: Return copies of internal mutable objects to protect encapsulation
4. **Index-based access**: Sometimes the safest approach is not to expose arrays at all
5. **Documentation**: Always document whether methods return copies or direct references

## Best Practices with Final

If making a member variable `final` does not change the code algorithmically and does not cause syntax errors, **the programmer should always prefer to write their code as final**.

### When to Use Final for Member Variables

```java
public class BestPracticeExample {
    // Always prefer final when possible
    private final String name;        // Good: immutable after construction
    private final int id;             // Good: immutable after construction
    private final List<String> items; // Good: reference is final
    
    // Only non-final when mutation is required
    private int counter;              // OK: needs to change
    private double balance;           // OK: needs to change
    
    public BestPracticeExample(String name, int id) {
        this.name = name;
        this.id = id;
        this.items = new ArrayList<>();
    }
    
    // Final reference doesn't prevent collection modification
    public void addItem(String item) {
        items.add(item); // OK: modifying contents, not reference
    }
    
    // This would be impossible with final
    // public void setItems(List<String> newItems) {
    //     this.items = newItems; // ERROR: Cannot reassign final field
    // }
}
```

## Immutable vs Mutable Class Versions

Some classes may have both **mutable** and **immutable** versions depending on the situation. Not all classes require this, but domain requirements may necessitate both versions.

### Naming Convention
When both versions exist:
- **Immutable version**: Named directly (e.g., `Point`)
- **Mutable version**: Prefixed with "Mutable" (e.g., `MutablePoint`)

### Example: Immutable Point Class

```java
import static java.lang.Math.*;

public class Point {
    private final double m_x;
    private final double m_y;
    
    private static Point create(double a, double b) {
        return new Point(a, b);
    }
    
    private Point(double x, double y) {
        m_x = x;
        m_y = y;
    }
    
    public static Point createCartesian(double x, double y) {
        return create(x, y);
    }
    
    public static Point createPolar(double r, double theta) {
        return create(r * cos(theta), r * sin(theta));
    }
    
    public double getX() {
        return m_x;
    }
    
    public double getY() {
        return m_y;
    }
    
    public double euclideanDistance() {
        return euclideanDistance(0, 0);
    }
    
    public double euclideanDistance(Point other) {
        return euclideanDistance(other.m_x, other.m_y);
    }
    
    public double euclideanDistance(double x, double y) {
        return sqrt(pow(m_x - x, 2) + pow(m_y - y, 2));
    }
    
    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", m_x, m_y);
    }
}
```

### Example: Mutable Point Class

```java
import static java.lang.Math.*;

public class MutablePoint {
    private double m_x;
    private double m_y;
    
    private static MutablePoint create(double a, double b) {
        return new MutablePoint(a, b);
    }
    
    private MutablePoint(double x, double y) {
        m_x = x;
        m_y = y;
    }
    
    public static MutablePoint createCartesian(double x, double y) {
        return create(x, y);
    }
    
    public static MutablePoint createPolar(double r, double theta) {
        return create(r * cos(theta), r * sin(theta));
    }
    
    public double getX() {
        return m_x;
    }
    
    public void setX(double x) {
        m_x = x;
    }
    
    public double getY() {
        return m_y;
    }
    
    public void setY(double y) {
        m_y = y;
    }
    
    public double euclideanDistance() {
        return euclideanDistance(0, 0);
    }
    
    public double euclideanDistance(MutablePoint other) {
        return euclideanDistance(other.m_x, other.m_y);
    }
    
    public double euclideanDistance(double x, double y) {
        return sqrt(pow(m_x - x, 2) + pow(m_y - y, 2));
    }
    
    public void offset(double dxy) {
        offset(dxy, dxy);
    }
    
    public void offset(double dx, double dy) {
        m_x += dx;
        m_y += dy;
    }
    
    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", m_x, m_y);
    }
}
```

### Usage Comparison

```java
public class PointUsageExample {
    public static void main(String[] args) {
        // Immutable Point - cannot change after creation
        Point immutablePoint = Point.createCartesian(3.0, 4.0);
        System.out.println("Immutable: " + immutablePoint);
        
        // To "move" an immutable point, create a new one
        Point movedPoint = Point.createCartesian(
            immutablePoint.getX() + 1, 
            immutablePoint.getY() + 1
        );
        System.out.println("Moved immutable: " + movedPoint);
        
        // Mutable Point - can be modified after creation
        MutablePoint mutablePoint = MutablePoint.createCartesian(3.0, 4.0);
        System.out.println("Mutable: " + mutablePoint);
        
        // Modify existing mutable point
        mutablePoint.offset(1, 1);
        System.out.println("Offset mutable: " + mutablePoint);
        
        mutablePoint.setX(10);
        mutablePoint.setY(20);
        System.out.println("Modified mutable: " + mutablePoint);
    }
}
```

### When to Use Each Version

**Use Immutable (Point) when:**
- Thread safety is important
- Objects are used as map keys
- Functional programming style is preferred
- State should not change after creation

**Use Mutable (MutablePoint) when:**
- Performance is critical (avoid object creation)
- Frequent modifications are needed
- Working with algorithms that modify points in place
- Memory usage needs to be optimized

## Summary

- Final variables can only be assigned once
- Final member variables must be initialized at declaration, in constructors, or in initializer blocks
- Static final constants should follow ALL_CAPS naming convention
- Be careful with final references to mutable objects like arrays
- Use defensive copying or index-based access to protect internal state
- Always prefer final when it doesn't restrict necessary functionality
- Consider providing both mutable and immutable versions for domain classes
- Follow naming conventions: immutable classes get direct names, mutable classes get "Mutable" prefix
