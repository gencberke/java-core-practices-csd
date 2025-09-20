# OBJECT-ORIENTED DESIGN PRINCIPLES

**Object Oriented Design Principles** → **SOLID Principles**

## SOLID Principles

- **S** - Single Responsibility Principle (SRP)
- **O** - Open Closed Principle (OCP)
- **L** - Liskov Substitution Principle (LSP)
- **I** - Interface Segregation Principle (ISP)
- **D** - Dependency Inversion Principle (DIP)

> **Note:** These principles should not be thought of separately from each other.

## Single Responsibility Principle (SRP)

According to this principle, **each class should have a single responsibility**.

### Definitions:
- *"A module should be responsible to one, and only one, actor"*
- *"A class should have only one reason to change"*

### Example: SRP Violation

```java
// SRP Violation - Multiple responsibilities
public class Employee {
    private String name;
    private double salary;
    private String department;
    
    // Responsibility 1: Employee data management
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    
    // Responsibility 2: Salary calculation
    public double calculateAnnualSalary() {
        return salary * 12;
    }
    
    public double calculateBonus() {
        return salary * 0.1;
    }
    
    // Responsibility 3: Report generation
    public String generateReport() {
        return "Employee: " + name + 
               ", Annual Salary: " + calculateAnnualSalary() +
               ", Bonus: " + calculateBonus();
    }
    
    // Responsibility 4: Database operations
    public void saveToDatabase() {
        System.out.println("Saving employee to database: " + name);
    }
    
    public void deleteFromDatabase() {
        System.out.println("Deleting employee from database: " + name);
    }
}
```

### Example: SRP Implementation

```java
// SRP Implementation - Each class has single responsibility

// Responsibility 1: Employee data management
public class Employee {
    private String name;
    private double salary;
    private String department;
    
    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}

// Responsibility 2: Salary calculation
public class SalaryCalculator {
    public double calculateAnnualSalary(Employee employee) {
        return employee.getSalary() * 12;
    }
    
    public double calculateBonus(Employee employee) {
        return employee.getSalary() * 0.1;
    }
}

// Responsibility 3: Report generation
public class EmployeeReportGenerator {
    private SalaryCalculator calculator;
    
    public EmployeeReportGenerator() {
        this.calculator = new SalaryCalculator();
    }
    
    public String generateReport(Employee employee) {
        return "Employee: " + employee.getName() + 
               ", Annual Salary: " + calculator.calculateAnnualSalary(employee) +
               ", Bonus: " + calculator.calculateBonus(employee);
    }
}

// Responsibility 4: Database operations
public class EmployeeRepository {
    public void save(Employee employee) {
        System.out.println("Saving employee to database: " + employee.getName());
    }
    
    public void delete(Employee employee) {
        System.out.println("Deleting employee from database: " + employee.getName());
    }
    
    public Employee findByName(String name) {
        // Simulate database lookup
        return new Employee(name, 50000, "IT");
    }
}

// Usage example
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Employee employee = new Employee("John Doe", 60000, "Engineering");
        
        SalaryCalculator calculator = new SalaryCalculator();
        EmployeeReportGenerator reportGenerator = new EmployeeReportGenerator();
        EmployeeRepository repository = new EmployeeRepository();
        
        // Each class handles its own responsibility
        double bonus = calculator.calculateBonus(employee);
        String report = reportGenerator.generateReport(employee);
        repository.save(employee);
        
        System.out.println("Bonus: " + bonus);
        System.out.println(report);
    }
}
```

## Benefits of SOLID Principles

1. **Maintainability**: Code becomes easier to maintain and modify
2. **Flexibility**: System becomes more adaptable to changes
3. **Reusability**: Components can be reused in different contexts
4. **Testability**: Individual components are easier to test
5. **Scalability**: System can grow without becoming unmanageable

## Key Points

- **Single Responsibility**: Each class should have only one reason to change
- **Clear Separation**: Different concerns should be handled by different classes
- **Loose Coupling**: Classes should depend on abstractions, not concrete implementations
- **High Cohesion**: Related functionality should be grouped together

These principles form the foundation for writing clean, maintainable object-oriented code and lead naturally into design patterns.
