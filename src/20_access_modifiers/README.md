# ACCESS MODIFIERS

Java has 4 access modifiers: `private`, `public`, `no-modifier`, `protected`

## Access Modifier Definitions

- **Public** → accessible from everywhere
- **Private** → not accessible from anywhere (only within the same class)
- **No-modifier** → acts like public for same package classes, like private for different package classes
- **Protected** → acts like public for same package classes, acts like private for different package classes (unless inheritance exists). If inheritance is used, protected members can be accessed

## Access Modifier Table

| Access Modifier | Same Class | Friendly Class | Different Package Class | Derived Class |
|----------------|------------|----------------|------------------------|---------------|
| **Public**     | yes        | yes            | yes                    | yes           |
| **Private**    | yes        | no             | no                     | no            |
| **No-modifier**| yes        | yes            | no                     | no            |
| **Protected**  | yes        | yes            | no                     | yes           |

> **Note:** Friendly class means other classes in the same package.

## Examples

### Example 1: Public Access Modifier

```java
package examples;

public class PublicExample {
    public int publicVariable = 10;
    
    public void publicMethod() {
        System.out.println("This method is accessible from everywhere");
    }
}

// Usage from different package
package different;
import examples.PublicExample;

public class TestPublic {
    public static void main(String[] args) {
        PublicExample obj = new PublicExample();
        obj.publicVariable = 20;  // Accessible
        obj.publicMethod();       // Accessible
    }
}
```

### Example 2: Private Access Modifier

```java
package examples;

public class PrivateExample {
    private int privateVariable = 10;
    
    private void privateMethod() {
        System.out.println("This method is only accessible within this class");
    }
    
    public void testPrivateAccess() {
        privateVariable = 20;  // Accessible within same class
        privateMethod();       // Accessible within same class
    }
}

public class TestPrivate {
    public static void main(String[] args) {
        PrivateExample obj = new PrivateExample();
        // obj.privateVariable = 20;  // ERROR: Not accessible
        // obj.privateMethod();       // ERROR: Not accessible
        obj.testPrivateAccess();      // OK: Public method
    }
}
```

### Example 3: No-modifier (Package-private)

```java
package examples;

class NoModifierExample {
    int packageVariable = 10;
    
    void packageMethod() {
        System.out.println("Accessible within same package");
    }
}

// Same package - accessible
class SamePackageTest {
    public static void main(String[] args) {
        NoModifierExample obj = new NoModifierExample();
        obj.packageVariable = 20;  // Accessible
        obj.packageMethod();       // Accessible
    }
}

// Different package
package different;
import examples.NoModifierExample;

public class DifferentPackageTest {
    public static void main(String[] args) {
        NoModifierExample obj = new NoModifierExample();
        // obj.packageVariable = 20;  // ERROR: Not accessible
        // obj.packageMethod();       // ERROR: Not accessible
    }
}
```

### Example 4: Protected Access Modifier

```java
package examples;

public class ParentClass {
    protected int protectedVariable = 10;
    
    protected void protectedMethod() {
        System.out.println("Protected method");
    }
}

// Same package
class SamePackageChild {
    public void test() {
        ParentClass parent = new ParentClass();
        parent.protectedVariable = 20;  // Accessible
        parent.protectedMethod();       // Accessible
    }
}

// Different package with inheritance
package different;
import examples.ParentClass;

public class ChildClass extends ParentClass {
    public void test() {
        protectedVariable = 30;  // Accessible through inheritance
        protectedMethod();       // Accessible through inheritance
    }
}

// Different package without inheritance
public class NonChildClass {
    public void test() {
        ParentClass parent = new ParentClass();
        // parent.protectedVariable = 20;  // ERROR: Not accessible
        // parent.protectedMethod();       // ERROR: Not accessible
    }
}
```

## Key Points

- **Public**: Use when you want the member to be accessible from anywhere
- **Private**: Use for internal implementation details that should not be exposed
- **No-modifier**: Use for package-level access, commonly used for helper classes
- **Protected**: Use when you want to allow access to subclasses but not to unrelated classes
- Always use the most restrictive access modifier that still allows your code to function properly
