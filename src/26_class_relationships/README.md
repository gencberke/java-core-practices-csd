# CLASS RELATIONSHIPS

When coding a project using OOP (Object-Oriented Programming), the first step is to identify the entities in the domain and represent them as classes. This process is called **transformation**. After identifying classes and their relationships, the implementation phase begins.

For example, in a shopping system application, concepts like customer, product, and order are identified as classes. Relationships are conceptualized between objects and implemented through classes.

**UML (Unified Modeling Language)** is the universal language used to represent these relationships visually.

## Dependency

**Dependency** is the situation where certain code must exist for other code to be written or compiled. For instance, when a method requires another method to function, that's a dependency.

In OOP, dependency applies to classes (and all User-Defined Types). For example, when a class has a data member of another class type, this creates a dependency relationship.

## Types of Class Relationships

Between two classes, either **one** of the following relationships exists, or **none** at all:

### 1. Composition (Has-A)

**A has a B** relationship requires both of these conditions to be satisfied:

* The B object belonging to A cannot be used by any other object
* The B object's lifetime begins and ends almost exactly with the A object's lifetime

In this relationship, the B object is used by its owner A object whenever needed. This type of usage is called **whole usage**.

**UML Representation:** A filled diamond on the owner side pointing to the component.

#### Example: Composition

```java
// Car HAS-A Engine (Composition)
// Engine cannot exist without Car
// Engine's lifetime is bound to Car's lifetime

class Engine {
    public void start() {
        System.out.println("Engine started");
    }
    
    public void stop() {
        System.out.println("Engine stopped");
    }
}

class Car {
    private final Engine engine; // Composition - engine is part of car
    
    public Car() {
        this.engine = new Engine(); // Engine created when Car is created
    }
    
    public void startCar() {
        System.out.println("Starting car...");
        engine.start();
    }
    
    public void stopCar() {
        engine.stop();
        System.out.println("Car stopped");
    }
    // When Car is destroyed, Engine is also destroyed
}
```

**Key Points:**
* Engine object is created inside Car's constructor
* Engine is marked as `final` - cannot be replaced
* Engine cannot be shared with other Car objects
* When Car object is garbage collected, Engine is also collected

### 2. Aggregation (Holds-A)

**A holds a B** relationship is a whole usage relationship where **at least one** of the composition rules is generally not met. The contained object can exist independently and may be shared.

**UML Representation:** A hollow diamond on the container side pointing to the contained object.

#### Example: Aggregation

```java
// Department HOLDS-A Employee (Aggregation)
// Employee can exist without Department
// Employee can work in multiple departments

class Employee {
    private String name;
    private int id;
    
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
}

class Department {
    private String departmentName;
    private Employee[] employees; // Aggregation - employees exist independently
    
    public Department(String departmentName, Employee[] employees) {
        this.departmentName = departmentName;
        this.employees = employees; // Receiving existing employees
    }
    
    public void listEmployees() {
        System.out.println("Employees in " + departmentName + ":");
        for (Employee emp : employees) {
            System.out.println("- " + emp.getName() + " (ID: " + emp.getId() + ")");
        }
    }
}

// Usage example:
class App {
    public static void main(String[] args) {
        // Employees created independently
        Employee emp1 = new Employee("John Doe", 101);
        Employee emp2 = new Employee("Jane Smith", 102);
        
        Employee[] techTeam = {emp1, emp2};
        Department tech = new Department("Technology", techTeam);
        
        // Employees still exist even if department is removed
    }
}
```

**Key Points:**
* Employee objects are created outside and passed to Department
* Employees can exist before and after Department
* Multiple Departments can share the same Employee references
* Department does not control Employee lifecycle

### 3. Association

An **association** relationship occurs when an A object uses a B object **when needed**, but not as a whole. This is called **partial usage**.

**UML Representation:** A simple line connecting the two classes.

#### Example: Association

```java
// Student uses Library (Association)
// Student doesn't own or hold Library
// Student just uses Library services when needed

class Library {
    private String name;
    
    public Library(String name) {
        this.name = name;
    }
    
    public void borrowBook(String studentName, String bookTitle) {
        System.out.println(studentName + " borrowed '" + bookTitle + "' from " + name);
    }
    
    public void returnBook(String studentName, String bookTitle) {
        System.out.println(studentName + " returned '" + bookTitle + "' to " + name);
    }
}

class Student {
    private String name;
    private int studentId;
    
    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }
    
    // Association - Student uses Library when needed
    public void borrowBookFrom(Library library, String bookTitle) {
        library.borrowBook(name, bookTitle);
    }
    
    public void returnBookTo(Library library, String bookTitle) {
        library.returnBook(name, bookTitle);
    }
}

// Usage example:
class App {
    public static void main(String[] args) {
        Library cityLibrary = new Library("City Library");
        Student student = new Student("Alice Johnson", 2024001);
        
        // Student uses library temporarily
        student.borrowBookFrom(cityLibrary, "Clean Code");
        student.returnBookTo(cityLibrary, "Clean Code");
        
        // Student and Library exist independently
    }
}
```

**Key Points:**
* Student doesn't hold a Library reference as a member variable
* Library is passed as a method parameter when needed
* Both objects exist completely independently
* No ownership or containment relationship

### 4. Inheritance (Is-A)

**Inheritance** was adopted from biology into programming. In biology, inheritance is the transfer of parent's characteristics to the child.

**Important Notes:**

For the first three relationships (Composition, Aggregation, Association), Java doesn't provide a special syntax. These relationships are implemented using general Java rules according to their definitions.

However, **inheritance has special language support in Java** with the `extends` keyword and specific rules:
* Subclass inherits all non-private members from superclass
* Subclass can override methods to provide specific behavior
* Java supports single inheritance (a class can extend only one class)
* Inheritance creates an "is-a" relationship (Dog is-a Animal)
* Used for code reusability and polymorphism

Inheritance will be covered in detail in a separate topic.

## Comprehensive Demo: Race Application

The following demo demonstrates all relationship types in a racing application context.

**Class Diagram Overview:**
* `Car` **has-a** `Engine` (Composition)
* `Car` **holds-a** `Driver` (Aggregation)
* `Plane` **has-a** `Engine[]` (Composition)
* `Plane` **holds-a** `Pilot[]` (Aggregation)

```java
class App {
    public static void main(String[] args) {
        DemoRaceApp.run();
    }
}

class DemoRaceApp {
    public static void run() {
        // Create driver independently (Aggregation)
        Driver driver = new Driver();
        driver.setName("Arman Koca");
        driver.setRating(100);

        // Car has engine (Composition) and holds driver (Aggregation)
        Car car = new Car(driver);
        car.run();

        // Create pilots independently (Aggregation)
        Pilot[] pilots = {
            new Pilot(1, 10000, "Elif Kılıç"),
            new Pilot(2, 50000, "Günay Akıncı"),
            new Pilot(3, 2500, "İsmail Enes Doğru")
        };
        
        // Plane has engines (Composition) and holds pilots (Aggregation)
        Plane plane = new Plane(4, pilots);
        plane.fly();
    }
}

class Plane {
    private final Engine[] m_engines; // Composition
    private Pilot[] m_pilots;         // Aggregation

    public Plane(int engineCount, Pilot[] pilots) {
        // Engines created here - composition
        m_engines = new Engine[engineCount];
        for (int i = 0; i < engineCount; ++i) {
            m_engines[i] = new Engine();
        }
        
        // Pilots received from outside - aggregation
        m_pilots = pilots;
    }

    private void startEngines() {
        for (Engine e : m_engines) {
            e.startEngine();
        }
    }

    private void accelerateEngines() {
        for (Engine e : m_engines) {
            e.accelerateEngine();
        }
    }

    private void slowEngines() {
        for (Engine e : m_engines) {
            e.slowEngine();
        }
    }

    private void stopEngines() {
        for (Engine e : m_engines) {
            e.stopEngine();
        }
    }

    public void fly() {
        // Display pilot information
        for (Pilot pilot : m_pilots) {
            System.out.printf("%d, %s, %d%n", 
                pilot.getTitle(), 
                pilot.getName(), 
                pilot.getFlightDuration());
        }

        startEngines();
        accelerateEngines();
        System.out.println("Flying...");
        slowEngines();
        stopEngines();
    }
}

class Car {
    private final Engine m_engine; // Composition
    private Driver m_driver;       // Aggregation

    public Car(Driver driver) {
        // Engine created here - composition
        m_engine = new Engine();
        // Driver received from outside - aggregation
        m_driver = driver;
    }

    public Driver getDriver() {
        return m_driver;
    }

    public void setDriver(Driver driver) {
        m_driver = driver;
    }

    public void brake() {
        m_engine.slowEngine();
    }

    public void run() {
        System.out.printf("Driver: %s, Rating: %d%n", 
            m_driver.getName(), 
            m_driver.getRating());
        
        m_engine.startEngine();
        m_engine.accelerateEngine();
        System.out.println("Running...");
        brake();
        m_engine.stopEngine();
    }
}

class Pilot {
    private int m_title;
    private int m_flightDuration;
    private String m_name;

    public Pilot(int title, int flightDuration, String name) {
        m_title = title;
        m_flightDuration = flightDuration;
        m_name = name;
    }

    public int getTitle() {
        return m_title;
    }

    public void setTitle(int title) {
        m_title = title;
    }

    public int getFlightDuration() {
        return m_flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        m_flightDuration = flightDuration;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }
}

class Driver {
    private String m_name;
    private int m_rating;

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public int getRating() {
        return m_rating;
    }

    public void setRating(int rating) {
        m_rating = rating;
    }
}

class Engine {
    public void startEngine() {
        System.out.println("Start Engine");
    }

    public void accelerateEngine() {
        System.out.println("Accelerate Engine");
    }

    public void slowEngine() {
        System.out.println("Slow Engine");
    }

    public void stopEngine() {
        System.out.println("Stop Engine");
    }
}
```

## Key Takeaways

**Composition vs Aggregation vs Association:**
* **Composition:** Strong ownership, lifetime dependency (Car-Engine)
* **Aggregation:** Weak ownership, independent lifetime (Department-Employee)
* **Association:** No ownership, temporary usage (Student-Library)

**Important Notes:**

1. **No Special Java Syntax:** Composition, Aggregation, and Association don't have dedicated keywords in Java. They are implemented using design principles and coding patterns.

2. **Inheritance is Different:** Unlike the other three relationships, inheritance has explicit language support with the `extends` keyword and special rules in Java.

3. **Special Cases:** Two classes may have a dependency without fitting into any of these four relationship categories. These are special cases and don't affect the general classification. Examples will be provided in advanced topics.

**Design Principle:** Always choose the loosest coupling that meets your requirements:
* Use Association when you just need to use an object
* Use Aggregation when you need to hold a reference but not own the object
* Use Composition when you need complete ownership and lifecycle control
