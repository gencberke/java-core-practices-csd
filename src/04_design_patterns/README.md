# DESIGN PATTERNS

As OOP advanced, some programmers began to make efforts to produce general solutions to certain problems. In this sense, 4 scientists known as **Gang of Four (GoF)** categorized a group of general problems along with their solutions. These types of general solutions are called **design patterns**.

## SINGLETON DESIGN PATTERN

Singleton is one of GoF's **creational** patterns.

### Definition
> "Let there be such a class that only one object is created from that class and the reference of that object is obtained whenever desired"

### Key Characteristics
- Only one instance of the class can exist
- Global access point to the instance
- Lazy initialization (object created when first requested)

## Implementation Example

```java
public class Singleton {
    // Static variable to hold the single instance
    private static Singleton instance;
    
    // Private constructor prevents external instantiation
    private Singleton() {
        // Initialization code here
        System.out.println("Singleton instance created");
    }
    
    // Public method to get the instance (lazy implementation)
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    // Example business method
    public void doSomething() {
        System.out.println("Singleton is doing something");
    }
}

// Usage example
public class SingletonDemo {
    public static void main(String[] args) {
        // Get the singleton instance
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        // Both references point to the same object
        System.out.println("Are both references the same? " + (s1 == s2));
        
        // Use the singleton
        s1.doSomething();
        s2.doSomething(); // Same object
    }
}
```

## Lazy Implementation

In this implementation, the related object is created on the **first getInstance call**. In this case, **the object is not created unless getInstance is called**. Therefore, this is also called **"lazy implementation"**.

### Benefits of Lazy Implementation:
- **Memory efficiency**: Object is created only when needed
- **Performance**: No unnecessary object creation at startup
- **Resource management**: Expensive resources are allocated only when required

## Real-World Example: Database Connection Manager

```java
public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private String connectionString;
    private boolean isConnected;
    
    // Private constructor
    private DatabaseConnectionManager() {
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
        this.isConnected = false;
        System.out.println("Database Connection Manager initialized");
    }
    
    // Thread-safe getInstance method
    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }
    
    public void connect() {
        if (!isConnected) {
            System.out.println("Connecting to database: " + connectionString);
            isConnected = true;
        } else {
            System.out.println("Already connected to database");
        }
    }
    
    public void disconnect() {
        if (isConnected) {
            System.out.println("Disconnecting from database");
            isConnected = false;
        } else {
            System.out.println("Not connected to database");
        }
    }
    
    public boolean isConnected() {
        return isConnected;
    }
}

// Usage
public class DatabaseDemo {
    public static void main(String[] args) {
        // Multiple parts of application get the same instance
        DatabaseConnectionManager db1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager db2 = DatabaseConnectionManager.getInstance();
        
        System.out.println("Same instance? " + (db1 == db2));
        
        db1.connect();
        db2.disconnect(); // Same object, so this affects db1 too
        
        System.out.println("DB1 connected? " + db1.isConnected());
        System.out.println("DB2 connected? " + db2.isConnected());
    }
}
```

## Application Configuration Example

```java
public class AppConfig {
    private static AppConfig instance;
    private String appName;
    private String version;
    private String environment;
    
    private AppConfig() {
        // Load configuration from file or environment
        this.appName = "MyApplication";
        this.version = "1.0.0";
        this.environment = "development";
        System.out.println("Configuration loaded");
    }
    
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
    
    public String getAppName() { return appName; }
    public String getVersion() { return version; }
    public String getEnvironment() { return environment; }
    
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    
    public String getFullInfo() {
        return appName + " v" + version + " (" + environment + ")";
    }
}

// Usage across different parts of application
public class AppConfigDemo {
    public static void main(String[] args) {
        // Different modules accessing the same configuration
        AppConfig config1 = AppConfig.getInstance();
        AppConfig config2 = AppConfig.getInstance();
        
        System.out.println("Config 1: " + config1.getFullInfo());
        System.out.println("Config 2: " + config2.getFullInfo());
        
        // Changing environment affects all references
        config1.setEnvironment("production");
        System.out.println("After change - Config 2: " + config2.getFullInfo());
    }
}
```

## When to Use Singleton

### Good Use Cases:
- **Configuration managers**: Application settings that need to be consistent
- **Logging systems**: Single point for logging across the application
- **Database connection pools**: Managing limited database connections
- **Caching mechanisms**: Single cache instance for the application

### Important Notes:
- Use singleton sparingly - it can make testing difficult
- Consider thread safety in multi-threaded applications
- Be careful about global state - it can lead to tight coupling
- The lazy implementation ensures the object is created only when needed

## Key Benefits

1. **Single Instance**: Guarantees only one instance exists
2. **Global Access**: Provides a global point of access
3. **Lazy Initialization**: Object created only when needed
4. **Memory Efficiency**: Saves memory by avoiding multiple instances
5. **Consistent State**: All parts of application work with same data
