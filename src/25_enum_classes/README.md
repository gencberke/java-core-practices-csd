# ENUM CLASSES

**Enum (enumeration) classes** are User Defined Types (UDT). Enum classes have all the features of normal classes plus some special characteristics unique to them. They were added in Java 5.

## Problem with Basic Types

In the following demo example, while the GameObject class gives an idea about the color information and the parameter names of the move method, it does not provide information about what values the types can accept. These can be understood from documentation. Additionally, the values passed when calling the related methods can negatively affect the readability/comprehensibility of client code. In this sense, while setColor and move methods work correctly, they can be considered poorly designed in terms of readability/comprehensibility.

### Example 1: Poor Readability with Integer Values

```java
public class GameObjectBasicExample {
    public static void main(String[] args) {
        Random random = new Random();
        GameObject go1 = new GameObject("Player-1");
        GameObject go2 = new GameObject("Player-2");

        go1.setColor(0);  // What does 0 mean?
        go2.setColor(2);  // What does 2 mean?

        while (true) {
            int direction1 = random.nextInt(1, 5);
            int direction2 = random.nextInt(1, 5);

            go1.move(direction1);  // What does direction1 represent?
            go2.move(direction2);

            // Simulate delay
            try {
                Thread.sleep(random.nextLong(300, 900));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

class GameObject {
    private final String m_name;
    private int m_color;

    public GameObject(String name) {
        m_name = name;
    }

    public void move(int direction) {
        switch (direction) {
            case 1 -> System.out.printf("%s moves to right%n", m_name);
            case 2 -> System.out.printf("%s moves to top%n", m_name);
            case 3 -> System.out.printf("%s moves to left%n", m_name);
            case 4 -> System.out.printf("%s moves to bottom%n", m_name);
            default -> System.out.println("Invalid direction value");
        }
    }

    public void setColor(int color) {
        m_color = color;
    }

    public int getColor() {
        return m_color;
    }
}
```

### Example 2: Improved with String Values (Still Problematic)

```java
public class GameObjectStringExample {
    public static void main(String[] args) {
        String[] directions = {"", "right", "top", "left", "bottom"};
        Random random = new Random();
        GameObject go1 = new GameObject("Player-1");
        GameObject go2 = new GameObject("Player-2");

        go1.setColor("red");    // More readable
        go2.setColor("blue");   // More readable

        while (true) {
            int direction1 = random.nextInt(1, 5);
            int direction2 = random.nextInt(1, 5);

            go1.move(directions[direction1]);  // More readable
            go2.move(directions[direction2]);

            try {
                Thread.sleep(random.nextLong(300, 900));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

class GameObject {
    private final String m_name;
    private String m_color;

    public GameObject(String name) {
        m_name = name;
    }

    public void move(String direction) {
        switch (direction) {
            case "right" -> System.out.printf("%s moves to right%n", m_name);
            case "top" -> System.out.printf("%s moves to top%n", m_name);
            case "left" -> System.out.printf("%s moves to left%n", m_name);
            case "bottom" -> System.out.printf("%s moves to bottom%n", m_name);
            default -> System.out.println("Invalid direction value");
        }
    }

    public void setColor(String color) {
        m_color = color;
    }

    public String getColor() {
        return m_color;
    }
}
```

**Problems with String approach:**
- Higher chance of errors (typos)
- String comparison is slower than integer comparison (character by character)
- Method parameters still don't indicate possible values clearly

### Example 3: Constants for Better Readability

```java
public class GameObjectConstantsExample {
    public static void main(String[] args) {
        Random random = new Random();
        GameObject go1 = new GameObject("Player-1");
        GameObject go2 = new GameObject("Player-2");

        go1.setColor(Color.RED);    // Clear meaning
        go2.setColor(Color.BLUE);   // Clear meaning

        while (true) {
            int direction1 = random.nextInt(1, 5);
            int direction2 = random.nextInt(1, 5);

            go1.move(direction1);
            go2.move(direction2);

            try {
                Thread.sleep(random.nextLong(300, 900));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

// Utility classes with constants
class Direction {
    private Direction() {} // Prevent instantiation

    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int LEFT = 3;
    public static final int BOTTOM = 4;
}

class Color {
    private Color() {} // Prevent instantiation

    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int WHITE = 3;
    public static final int BLACK = 4;
}

class GameObject {
    private final String m_name;
    private int m_color;

    public GameObject(String name) {
        m_name = name;
    }

    public void move(int direction) {
        switch (direction) {
            case 1 -> System.out.printf("%s moves to right%n", m_name);
            case 2 -> System.out.printf("%s moves to top%n", m_name);
            case 3 -> System.out.printf("%s moves to left%n", m_name);
            case 4 -> System.out.printf("%s moves to bottom%n", m_name);
            default -> System.out.println("Invalid direction value");
        }
    }

    public void setColor(int color) {
        m_color = color;
    }

    public int getColor() {
        return m_color;
    }
}
```

## Enum Classes - The Solution

When Direction and Color classes are declared as enum classes, they become both easier to use and more powerful. Enum classes automatically provide the features shown above (and even more).

### Example 4: Using Enum Classes

```java
public class GameObjectEnumExample {
    public static void main(String[] args) {
        Direction[] directions = Direction.values();
        Random random = new Random();
        GameObject go1 = new GameObject("Player-1");
        GameObject go2 = new GameObject("Player-2");

        go1.setColor(Color.RED);
        go2.setColor(Color.BLUE);

        while (true) {
            int index1 = random.nextInt(0, 4);
            int index2 = random.nextInt(0, 4);

            go1.move(directions[index1]);
            go2.move(directions[index2]);

            try {
                Thread.sleep(random.nextLong(300, 900));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

// Enum declarations - much simpler!
enum Direction {
    RIGHT, TOP, LEFT, BOTTOM
}

enum Color {
    RED, GREEN, BLUE, WHITE, BLACK
}

class GameObject {
    private final String m_name;
    private Color m_color;

    public GameObject(String name) {
        m_name = name;
    }

    public void move(Direction direction) {
        if (direction == Direction.RIGHT)
            System.out.printf("%s moves to right%n", m_name);
        else if (direction == Direction.TOP)
            System.out.printf("%s moves to top%n", m_name);
        else if (direction == Direction.LEFT)
            System.out.printf("%s moves to left%n", m_name);
        else if (direction == Direction.BOTTOM)
            System.out.printf("%s moves to bottom%n", m_name);
        else
            System.out.println("Invalid direction value");
    }

    public void setColor(Color color) {
        m_color = color;
    }

    public Color getColor() {
        return m_color;
    }
}
```

## Enum Class Declaration

Enum classes are declared with the `enum` keyword. Names declared with commas between them inside an enum class are called **enum constants**. Each enum constant is a public, static, and final reference variable of the enum class type, and when created, each points to an object of their enum class type.

- Writing public, static, final, and type name for enum constants is invalid
- A semicolon can be placed after the last enum constant
- If the enum class contains only enum constants, the semicolon is not required
- If elements other than constants are added to the enum class, a semicolon is required

### Basic Enum Examples

```java
enum DayOfWeek {
    SUN, MON, TUE, WED, THU, FRI, SAT
}

enum Month {
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
}

enum DeviceStatus {
    OPEN, CLOSED, INDETERMINATE
}

enum MaritalStatus {
    SINGLE, MARRIED, DIVORCED
}

enum CardType {
    SPADE, CLUB, HEART, DIAMOND
}

enum CardValue {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KNAVE, QUEEN, KING, ACE
}

enum Visibility {
    VISIBLE, INVISIBLE, GONE
}
```

## Built-in Enum Methods

### ordinal() Method
The int value related to the declaration order of an enum constant within the enum is called the **ordinal value**. The ordinal value can be obtained with the enum class's `ordinal()` method. Ordinal values start from zero.

### values() Method
An enum class has a static `values()` method. This method returns an array reference of the relevant enum type where references to constants are stored sequentially according to their ordinal numbers. Each call to `values()` creates a new array reference.

### toString() Method
The enum class's `toString()` method returns the constant name that the relevant reference corresponds to.

```java
public class EnumBasicMethodsExample {
    public static void main(String[] args) {
        // Testing ordinal values
        System.out.println("Day ordinals:");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.printf("%s: %d%n", day.toString(), day.ordinal());
        }
        
        // Testing values() method
        Month[] months = Month.values();
        System.out.println("\nMonths array length: " + months.length);
        
        // Testing toString
        Month currentMonth = Month.JUN;
        System.out.println("Current month: " + currentMonth.toString());
    }
}
```

**Key Note:** An enum class and its enum constant elements are unchangeable, finite, and known.

## Adding Members to Enum Classes

We can add elements other than constants to an enum class. As mentioned, to do this we must put a semicolon after the last constant. Methods, data members, and constructors can be added to enum classes just like in regular classes.

### Constructor Rules for Enums
- If no constructor is added, the compiler creates a default parameterless constructor
- Constructors can **never** be public or protected
- Constructors must be private or no-modifier (both are equivalent for enums)
- General convention is to declare as no-modifier

### Important Notes about Enum Objects
- Objects of an enum type can never be created by the programmer anywhere
- The number of objects of an enum type equals the number of enum constants in that enum type
- Declaring a constant directly or putting empty parentheses () after the constant name means the object will be created by calling the default constructor

### Example 5: Enum with Default Constructor

```java
public class EnumConstructorExample {
    public static void main(String[] args) {
        Color color1 = Color.randomColor(new Random());
        System.out.println("-----------------------");
        Color color2 = Color.randomColor(new Random());

        System.out.println(color1.toString());
        System.out.println(color2.toString());
    }
}

enum Color {
    RED(), GREEN(), BLUE(), WHITE, BLACK; // RED() and WHITE are equivalent
    
    private static final Color[] VALUES = values();
    
    Color() {
        System.out.println("I am a default constructor");
    }

    public static Color randomColor(Random random) {
        return VALUES[random.nextInt(VALUES.length)];
    }
}
```

### Example 6: Enum with Parameterized Constructor - RGB Colors

```java
public class RGBColorExample {
    public static void main(String[] args) {
        for (Color color : Color.values()) {
            System.out.printf("%s: RGB(%d, %d, %d)%n", 
                            color, color.r, color.g, color.b);
        }
        
        Color randomColor = Color.randomColor(new Random());
        System.out.printf("Random color: %s with RGB(%d, %d, %d)%n",
                        randomColor, randomColor.r, randomColor.g, randomColor.b);
    }
}

enum Color {
    RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255), 
    WHITE(255, 255, 255), BLACK(0, 0, 0);
    
    private static final Color[] VALUES = values();
    public final int r, g, b;

    Color(int red, int green, int blue) {
        r = red;
        g = green;
        b = blue;
    }

    public static Color randomColor(Random random) {
        return VALUES[random.nextInt(VALUES.length)];
    }
}
```

### Example 7: Month Enum with Days

A logical application of adding members to enum could be the number of days in months:

```java
import java.util.Random;
import java.util.Scanner;

public class MonthDaysExample {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Input month as JAN, FEB, ..., DEC: ");
        Month month = Month.valueOf(scanner.nextLine());
        int year = random.nextInt(1900, 2100);

        System.out.printf("%d -> %s(%d days)%n", year, month.toString(), month.getDays(year));
    }
}

enum Month {
    JAN(31), FEB(28), MAR(31), APR(30), MAY(31), JUN(30),
    JUL(31), AUG(31), SEP(30), OCT(31), NOV(30), DEC(31);

    private final int M_DAYS;

    Month(int days) {
        M_DAYS = days;
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
    
    public int getDays(int year) {
        return ordinal() == 1 && isLeapYear(year) ? 29 : M_DAYS;
    }
}
```

## valueOf() Method

An enum class's static `valueOf()` method returns a reference to the enum constant with the same name as the text taken with its parameter. If no such constant exists, an exception is thrown.

```java
public class ValueOfExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a day (SUN, MON, TUE, WED, THU, FRI, SAT): ");
        try {
            DayOfWeek day = DayOfWeek.valueOf(scanner.nextLine());
            System.out.println("You entered: " + day);
            System.out.println("Ordinal value: " + day.ordinal());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid day entered!");
        }
    }
}
```

## Enum Reference Comparison

When enum references are compared with == or != operators, logical equality comparison is also performed. This means reference comparison can often be used as logical equality comparison. Equality comparison can also be done with the `equals()` method.

This is possible because the object being compared (e.g., FEB in the example above) can only be a single object - there is only one instance of that object. In this context, comparing objects doesn't make sense. This comparison is therefore directed at the member's content.

```java
import java.util.Random;
import java.util.Scanner;

public class EnumComparisonExample {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Month[] months = Month.values();

        System.out.print("Enter a number: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; ++i) {
            Month month1 = months[random.nextInt(months.length)];
            Month month2 = months[random.nextInt(months.length)];
            int year = random.nextInt(1900, 2101);

            System.out.printf("%d -> %s(%d days)%n", year, month1.toString(), month1.getDays(year));
            System.out.printf("%d -> %s(%d days)%n", year, month2.toString(), month2.getDays(year));
            
            // Both == and equals() work for enums
            System.out.println(month1 == month2 ? "Same month" : "Different months");
            System.out.println(month1.equals(month2) ? "Same month" : "Different months");
            System.out.println("--------------------------------------------------------");
        }
    }
}
```

## compareTo() Method

An enum class has a `compareTo()` method. For the call `a.compareTo(b)`, it returns the value `a.ordinal() - b.ordinal()`.

- If this value is negative: enum constant a comes before enum constant b
- If zero: they are the same constants  
- If positive: a comes after b

```java
public class EnumCompareToExample {
    public static void main(String[] args) {
        DayOfWeek day1 = DayOfWeek.MON;
        DayOfWeek day2 = DayOfWeek.FRI;
        
        int comparison = day1.compareTo(day2);
        System.out.printf("%s vs %s: %d%n", day1, day2, comparison);
        
        if (comparison < 0) {
            System.out.println(day1 + " comes before " + day2);
        } else if (comparison > 0) {
            System.out.println(day1 + " comes after " + day2);
        } else {
            System.out.println("Same day");
        }
    }
}
```

## Enums in Switch Statements

Enum constants are treated as constant expressions. Therefore, enum classes and constants can be used with switch statements and expressions.

```java
public class EnumSwitchExample {
    public static void main(String[] args) {
        DayOfWeek today = DayOfWeek.MON;
        
        // Traditional switch statement
        switch (today) {
            case MON:
                System.out.println("Start of work week");
                break;
            case TUE:
            case WED:
            case THU:
                System.out.println("Middle of work week");
                break;
            case FRI:
                System.out.println("End of work week");
                break;
            case SAT:
            case SUN:
                System.out.println("Weekend");
                break;
        }
        
        // Switch expression (Java 14+)
        String dayType = switch (today) {
            case MON, TUE, WED, THU, FRI -> "Weekday";
            case SAT, SUN -> "Weekend";
        };
        System.out.println("Day type: " + dayType);
    }
}
```

## Singleton Pattern with Enums

A singleton class with eager implementation can be written more simply and effectively with enum class. Unless lazy implementation is needed, the best singleton implementation in Java is done with enum.

### Traditional Singleton (Eager Implementation)

```java
class TraditionalSingleton {
    public static final TraditionalSingleton INSTANCE = new TraditionalSingleton();
    private int m_x;

    private TraditionalSingleton() {
    }

    public int getX() {
        return m_x;
    }

    public void setX(int x) {
        m_x = x;
    }

    public String toString() {
        return String.valueOf(m_x);
    }
}
```

### Enum-based Singleton (Recommended)

```java
public class EnumSingletonExample {
    public static void main(String[] args) {
        Singleton s1 = Singleton.INSTANCE;
        Singleton s2 = Singleton.INSTANCE;

        System.out.println("Same instance? " + (s1 == s2)); // true
        
        s1.setX(42);
        System.out.println("s1: " + s1); // 42
        System.out.println("s2: " + s2); // 42 (same object)
    }
}

enum Singleton {
    INSTANCE;

    private int m_x;

    public int getX() {
        return m_x;
    }

    public void setX(int x) {
        m_x = x;
    }

    public String toString() {
        return String.valueOf(m_x);
    }
}
```

## Performance Considerations

**Important Note:** Enum constants and the objects they point to are created and live until the program ends. This situation can be disadvantageous in terms of memory in some rare but important applications. For example, the memory capacity usage of a not very powerful (limited) Android device can be limited according to its capacity, and the created objects may not need to live continuously. In such rare but important cases, it may be appropriate to use int type values instead of using enum class. In this case, readability/comprehensibility is sacrificed for performance.

## Key Points

1. **Enum classes are UDTs** with special characteristics
2. **Enum constants are public, static, final** references
3. **Fixed number of objects** - equal to number of constants
4. **Built-in methods**: ordinal(), values(), toString(), valueOf()
5. **Can have constructors, methods, and fields** like regular classes
6. **Constructors must be private or no-modifier**
7. **Reference comparison works** for equality (== or equals())
8. **Can be used in switch statements**
9. **Best implementation for eager singleton pattern**
10. **Consider memory implications** in resource-constrained environments

Enums provide type safety, readability, and maintainability while preventing invalid values and making code more self-documenting.
