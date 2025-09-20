# PACKAGES AND IMPORT STATEMENTS

A Java program generally consists of packages. Java codes are contained in files that are generally compiled with a `.java` extension and are called **compilation units** or **translation units**. In Java, each User Defined Type (UDT) can be thought of as a separate compilation unit.

## Package Details

### Compilation and Byte Code

- **javac (Java compiler)** generates a `.class` file for each UDT (byte code generation)
- These files are called **"byte code (BC)"**
- Even if UDTs are in the same compilation unit, separate BC is generated for each one
- A UDT can be in any package; UDTs in a project don't need to be in the same package

### Directory Structure Requirements

- For a `.class` file's UDT to be usable from another compilation unit, it must be in a directory with the same name as the package the UDT belongs to
- Java IDEs generally do this automatically - they generate BC in the appropriate place
- There's no such requirement for compilation units, but most Java IDEs maintain this requirement for compilation units as well

### Package Access Rules

- A UDT can be accessed from outside its package using package name and dot operator
- For a UDT to be accessible from outside its package, it must be declared as **public**
- UDTs that are not declared public (accessible only from within their own package) are called **"friendly"** or **"internal"**

## Basic Package Structure

### Package Declaration

```java
// File: src/com/company/math/Calculator.java
package com.company.math;

public class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
}
```

### Using Classes from Other Packages

```java
// File: src/com/company/app/Application.java
package com.company.app;

public class Application {
    public static void main(String[] args) {
        // Using fully qualified class name
        int result1 = com.company.math.Calculator.add(10, 20);
        System.out.println("Addition result: " + result1);
        
        int result2 = com.company.math.Calculator.multiply(5, 6);
        System.out.println("Multiplication result: " + result2);
    }
}
```

## Import Declarations

Import declarations are **NOT** for including libraries. Import declarations are designed to reduce qualification and make code more concise.

### Types of Import Declarations

#### 1. Import Single Type Declaration (Import without asterisk)

```java
// File: Application.java
package com.company.app;

import com.company.math.Calculator;  // Import single class

public class Application {
    public static void main(String[] args) {
        // Now we can use Calculator directly without full qualification
        int result1 = Calculator.add(10, 20);
        int result2 = Calculator.multiply(5, 6);
        
        System.out.println("Addition: " + result1);
        System.out.println("Multiplication: " + result2);
    }
}
```

#### 2. Import On Demand Declaration (Import with asterisk)

```java
package com.company.app;

import com.company.math.*;  // Import all public classes from the package

public class Application {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        MathUtils utils = new MathUtils();  // Assuming this class exists in math package
        
        // Use classes without qualification
        int sum = Calculator.add(15, 25);
        System.out.println("Sum: " + sum);
    }
}
```

### Import Declaration Rules

**Common Properties of Import Declarations:**
- Import declarations must come after package declaration and before all other declarations
- Multiple import declarations are valid
- Import declarations are valid for **unqualified names only**
- Import declarations are valid for the compilation unit they are declared in

#### Ambiguity in Import Declarations

```java
package com.example;

import java.util.Date;
import java.sql.Date;  // ERROR: Ambiguous import

public class DateExample {
    public static void main(String[] args) {
        // Which Date class? Compilation error!
        // Date date = new Date();
        
        // Solution: Use fully qualified names
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
    }
}
```

#### Resolving Ambiguity

```java
package com.example;

import java.util.Date;  // Import one
// Don't import java.sql.Date

public class DateExample {
    public static void main(String[] args) {
        Date utilDate = new Date();  // java.util.Date
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());  // Fully qualified
    }
}
```

## Import Static Declarations

Import static declarations were added to Java 5. These declarations also come in two groups:

#### 1. Import Static Single Member Declaration

```java
package com.example;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.System.out;

public class ImportStaticExample {
    public static void main(String[] args) {
        // Use static members without class qualification
        double radius = 5.0;
        double area = PI * radius * radius;  // Instead of Math.PI
        double diagonal = sqrt(2) * radius;   // Instead of Math.sqrt
        
        out.println("Area: " + area);        // Instead of System.out
        out.println("Diagonal: " + diagonal);
    }
}
```

#### 2. Import Static On Demand Declaration

```java
package com.example;

import static java.lang.Math.*;  // Import all static members from Math class

public class MathExample {
    public static void main(String[] args) {
        double a = 3.0;
        double b = 4.0;
        
        // Use Math methods without qualification
        double hypotenuse = sqrt(pow(a, 2) + pow(b, 2));
        double area = PI * pow(min(a, b), 2);
        
        System.out.println("Hypotenuse: " + hypotenuse);
        System.out.println("Circle area: " + area);
    }
}
```

## Package Hierarchy and Sub-packages

### Creating Sub-packages

```java
// File: com/company/math/basic/BasicCalculator.java
package com.company.math.basic;

public class BasicCalculator {
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int subtract(int a, int b) {
        return a - b;
    }
}
```

```java
// File: com/company/math/advanced/AdvancedCalculator.java
package com.company.math.advanced;

public class AdvancedCalculator {
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    public static double logarithm(double value) {
        return Math.log(value);
    }
}
```

### Using Sub-packages

```java
package com.company.app;

import com.company.math.basic.BasicCalculator;
import com.company.math.advanced.AdvancedCalculator;

public class MathApplication {
    public static void main(String[] args) {
        // Basic operations
        int sum = BasicCalculator.add(10, 5);
        int difference = BasicCalculator.subtract(10, 5);
        
        // Advanced operations
        double power = AdvancedCalculator.power(2, 8);
        double log = AdvancedCalculator.logarithm(Math.E);
        
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("2^8 = " + power);
        System.out.println("ln(e) = " + log);
    }
}
```

**Important Note**: Sub-packages are still different packages. A parent package does not include its sub-packages. They are only hierarchically organized.

## Name Lookup Rules

### Unqualified Name Lookup

When the compiler sees an unqualified name, it searches in this order:

1. **Method scope**: Searches upward in the method (including method parameters)
2. **Class scope**: Searches throughout the class (all static and non-static members)
3. **Package scope**: Searches in the same package
4. **Import on demand declarations**: Searches in all packages specified with `import package.*;`

```java
package com.example;

import java.util.*;
import java.io.*;

public class NameLookupExample {
    private static String classField = "Class field";
    
    public static void main(String[] args) {
        String localVar = "Local variable";
        
        // 1. Finds local variable first
        System.out.println(localVar);
        
        // 2. Finds class field
        System.out.println(classField);
        
        // 3. Finds in import declarations
        List<String> list = new ArrayList<>();  // java.util.List, java.util.ArrayList
        
        testMethod("parameter value");
    }
    
    public static void testMethod(String parameter) {
        // parameter is found in method scope
        System.out.println("Parameter: " + parameter);
        
        // classField is found in class scope
        System.out.println("Class field: " + classField);
    }
}
```

### Qualified Name Lookup

For qualified names (names with dot operator):

1. **Class name on left**: Searches within that class and its superclasses
2. **Reference variable on left**: Searches in the type of that reference
3. **Package name on left**: Searches within that package

```java
package com.example;

public class QualifiedLookupExample {
    public static void main(String[] args) {
        // Qualified with class name
        String result = Math.abs(-10) + "";
        
        // Qualified with package name
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        // Qualified with reference variable
        String text = "Hello";
        int length = text.length();  // text.length() - reference.method()
    }
}
```

## Practical Package Organization

### Domain-based Package Naming

```java
// Company domain: example.com
// Base package: com.example

// Core business logic
package com.example.business;

// Data access layer
package com.example.data;

// User interface
package com.example.ui;

// Utilities
package com.example.util;

// Configuration
package com.example.config;
```

### Example Project Structure

```
src/
├── com/
│   └── example/
│       ├── business/
│       │   ├── CustomerService.java
│       │   └── OrderService.java
│       ├── data/
│       │   ├── CustomerRepository.java
│       │   └── OrderRepository.java
│       ├── ui/
│       │   └── ApplicationUI.java
│       └── util/
│           ├── DateUtils.java
│           └── StringUtils.java
```

### Business Layer Example

```java
// File: com/example/business/CustomerService.java
package com.example.business;

import com.example.data.CustomerRepository;
import com.example.util.StringUtils;

public class CustomerService {
    public static boolean isValidCustomer(String name, String email) {
        // Use utility method
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(email)) {
            return false;
        }
        
        // Use data layer
        return CustomerRepository.customerExists(email);
    }
    
    public static void createCustomer(String name, String email) {
        if (isValidCustomer(name, email)) {
            CustomerRepository.saveCustomer(name, email);
            System.out.println("Customer created successfully");
        } else {
            System.out.println("Invalid customer data");
        }
    }
}
```

### Utility Layer Example

```java
// File: com/example/util/StringUtils.java
package com.example.util;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
```

## Java Standard Library Packages

### Common Java Packages

```java
package com.example;

// java.lang - automatically imported
import java.util.*;     // Collections, Date, etc.
import java.io.*;       // Input/Output operations
import java.net.*;      // Networking
import java.text.*;     // Text formatting
import java.time.*;     // Date/Time API (Java 8+)

public class StandardLibraryExample {
    public static void main(String[] args) {
        // java.lang (no import needed)
        String text = "Hello World";
        System.out.println(text);
        
        // java.util
        List<String> list = new ArrayList<>();
        Date date = new Date();
        
        // java.time (Java 8+)
        LocalDateTime now = LocalDateTime.now();
        
        // java.text
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        
        System.out.println("Current time: " + now);
        System.out.println("Formatted number: " + formatter.format(1234.567));
    }
}
```

## Default Package and Limitations

### Problems with Default Package

```java
// File in default package (no package declaration)
public class DefaultPackageClass {
    public static void sayHello() {
        System.out.println("Hello from default package");
    }
}
```

```java
// File in named package - CANNOT import from default package
package com.example;

// import DefaultPackageClass;  // ERROR: Cannot import from default package

public class NamedPackageClass {
    public static void main(String[] args) {
        // Cannot access DefaultPackageClass from here
        // DefaultPackageClass.sayHello();  // ERROR
        
        System.out.println("This class is in a named package");
    }
}
```

**Why avoid default package:**
- Cannot be imported by classes in named packages
- Not suitable for real applications
- Makes code organization difficult
- Against Java best practices

## Best Practices

### 1. Package Naming Conventions

```java
// GOOD: Reverse domain name
package com.company.project.module;

// AVOID: Generic names
package utilities;
package helpers;
```

### 2. Import Organization

```java
package com.example;

// 1. Java standard library imports
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// 2. Third-party library imports
import org.apache.commons.lang3.StringUtils;

// 3. Application imports
import com.example.business.CustomerService;
import com.example.util.DateUtils;

public class WellOrganizedClass {
    // Class implementation
}
```

### 3. Minimize Import Scope

```java
// GOOD: Import specific classes
import java.util.ArrayList;
import java.util.HashMap;

// ACCEPTABLE: Import on demand when many classes used
import java.util.*;

// AVOID: Mixing specific and on-demand imports from same package
import java.util.List;
import java.util.*;  // Redundant
```

### 4. Use Static Imports Judiciously

```java
// GOOD: For frequently used constants/methods
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

// AVOID: Overusing static imports (reduces readability)
import static java.lang.System.*;
import static java.util.Collections.*;
```

## Key Points

1. **Packages organize related classes and prevent naming conflicts**
2. **Package declaration must be first in the file**
3. **Import declarations come after package, before class declarations**
4. **Public classes can be accessed from other packages**
5. **Friendly classes are accessible only within their package**
6. **Import statements don't include libraries - they reduce qualification**
7. **Static imports allow unqualified access to static members**
8. **Avoid default package in real applications**
9. **Use reverse domain naming convention**
10. **Organize imports logically and minimize scope**

Understanding packages and imports is crucial for organizing large Java applications and using the vast Java standard library effectively.
