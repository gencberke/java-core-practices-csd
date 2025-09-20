# ARRAYS (DIZILER)

Arrays are data structures where elements are of the same type and elements are stored consecutively in memory. Arrays are among the most fundamental data structures in programming. In fact, some data structures can be implemented using arrays.

In Java, arrays are created in the heap. That is, they are represented as classes. Arrays cannot be created in the stack in Java.

## Array Declaration and Creation

### Array Reference Declaration

Let T be a type name. To declare an array reference where each element is of type T:

```java
T[] arrayName;    // Preferred style
T arrayName[];    // C-style (not recommended)
```

The variable is a reference variable. No array has been created yet.

### Array Creation

Arrays are created using the `new` operator:

```java
new <type>[<size>];
```

The value in brackets is called the array's **length** (number of elements). In Java, array length doesn't need to be a constant expression. After an array is created, its length cannot be changed.

### Basic Array Example

```java
public class BasicArrayExample {
    public static void main(String[] args) {
        // Array reference declaration
        int[] numbers;
        
        // Array creation
        numbers = new int[5];  // Creates array of 5 integers
        
        // Accessing array elements using subscription operator []
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;
        
        // Displaying array elements
        System.out.println("Array elements:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("numbers[%d] = %d%n", i, numbers[i]);
        }
        
        System.out.println("Array length: " + numbers.length);
    }
}
```

## Array Indexing and Length

- Array elements are accessed using the **subscription operator []**
- **Indexing starts from 0**: First element is at index 0
- **Valid index range**: [0, length-1]
- **length property**: Returns the number of elements in the array
- **IndexOutOfBoundsException**: Thrown when accessing invalid indices

```java
public class ArrayIndexingExample {
    public static void main(String[] args) {
        double[] scores = new double[4];
        
        // Valid indices: 0, 1, 2, 3
        scores[0] = 85.5;
        scores[1] = 92.0;
        scores[2] = 78.3;
        scores[3] = 96.7;
        
        System.out.println("Scores:");
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("Score %d: %.1f%n", i + 1, scores[i]);
        }
        
        // This would cause IndexOutOfBoundsException
        // scores[4] = 100.0;  // ERROR: Index 4 is out of bounds
        // System.out.println(scores[-1]);  // ERROR: Negative index
    }
}
```

## Array Initialization

Arrays can be initialized with values at declaration time using braces `{}`.

### Array Initialization Syntax

```java
public class ArrayInitializationExample {
    public static void main(String[] args) {
        // Method 1: Declaration and initialization together
        int[] numbers1 = {1, 2, 3, 4, 5};
        
        // Method 2: Using new with initialization
        int[] numbers2 = new int[]{10, 20, 30, 40, 50};
        
        // Method 3: Separate declaration and initialization
        int[] numbers3;
        numbers3 = new int[]{100, 200, 300};
        
        // Method 4: Empty array
        int[] emptyArray = new int[]{};  // Zero-length array
        int[] alsoEmpty = new int[0];    // Also zero-length
        
        System.out.println("Numbers1 length: " + numbers1.length);
        System.out.println("Numbers2 length: " + numbers2.length);
        System.out.println("Numbers3 length: " + numbers3.length);
        System.out.println("Empty array length: " + emptyArray.length);
        
        // Trailing comma is allowed
        String[] names = {
            "Alice",
            "Bob",
            "Charlie",  // Trailing comma OK
        };
        
        System.out.println("Names: " + java.util.Arrays.toString(names));
    }
}
```

## Default Values in Arrays

When arrays are created without explicit initialization, elements get default values:

```java
public class ArrayDefaultValuesExample {
    public static void main(String[] args) {
        // Numeric types default to 0
        int[] integers = new int[3];
        double[] doubles = new double[3];
        
        // boolean defaults to false
        boolean[] booleans = new boolean[3];
        
        // char defaults to '\u0000' (null character)
        char[] characters = new char[3];
        
        // Reference types default to null
        String[] strings = new String[3];
        
        System.out.println("Default values:");
        System.out.println("int[0]: " + integers[0]);           // 0
        System.out.println("double[0]: " + doubles[0]);         // 0.0
        System.out.println("boolean[0]: " + booleans[0]);       // false
        System.out.println("char[0]: '" + characters[0] + "'"); // null character
        System.out.println("String[0]: " + strings[0]);         // null
    }
}
```

## Arrays as Method Parameters

Arrays can be passed to methods as parameters. Since arrays are objects, the reference is passed.

```java
public class ArrayParameterExample {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 8, 3, 9, 1, 7};
        
        System.out.println("Original array:");
        printArray(numbers);
        
        System.out.println("Sum: " + calculateSum(numbers));
        System.out.println("Average: " + calculateAverage(numbers));
        System.out.println("Maximum: " + findMaximum(numbers));
        
        // Method can modify array contents
        doubleValues(numbers);
        System.out.println("After doubling:");
        printArray(numbers);
    }
    
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }
    
    public static int calculateSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
    
    public static double calculateAverage(int[] arr) {
        if (arr.length == 0) return 0.0;
        return (double) calculateSum(arr) / arr.length;
    }
    
    public static int findMaximum(int[] arr) {
        if (arr.length == 0) return Integer.MIN_VALUE;
        
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    public static void doubleValues(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2;  // Modifies original array
        }
    }
}
```

## Methods Returning Arrays

Methods can create and return array references:

```java
public class ArrayReturnExample {
    public static void main(String[] args) {
        int[] evenNumbers = generateEvenNumbers(10);
        System.out.println("Even numbers: " + java.util.Arrays.toString(evenNumbers));
        
        int[] randomArray = generateRandomArray(5, 1, 100);
        System.out.println("Random array: " + java.util.Arrays.toString(randomArray));
        
        String[] words = {"hello", "world", "java", "programming"};
        String[] upperWords = convertToUpperCase(words);
        System.out.println("Original: " + java.util.Arrays.toString(words));
        System.out.println("Upper: " + java.util.Arrays.toString(upperWords));
    }
    
    public static int[] generateEvenNumbers(int count) {
        int[] evens = new int[count];
        for (int i = 0; i < count; i++) {
            evens[i] = (i + 1) * 2;  // 2, 4, 6, 8, ...
        }
        return evens;
    }
    
    public static int[] generateRandomArray(int size, int min, int max) {
        java.util.Random random = new java.util.Random();
        int[] array = new int[size];
        
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
    
    public static String[] convertToUpperCase(String[] original) {
        String[] result = new String[original.length];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].toUpperCase();
        }
        return result;
    }
}
```

## Array Sorting Algorithms

### Bubble Sort

```java
public class BubbleSortExample {
    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Original array:");
        printArray(numbers);
        
        bubbleSort(numbers);
        
        System.out.println("Sorted array:");
        printArray(numbers);
    }
    
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    public static void printArray(int[] arr) {
        System.out.println(java.util.Arrays.toString(arr));
    }
}
```

### Selection Sort

```java
public class SelectionSortExample {
    public static void main(String[] args) {
        int[] numbers = {64, 25, 12, 22, 11};
        
        System.out.println("Original array:");
        printArray(numbers);
        
        selectionSort(numbers);
        
        System.out.println("Sorted array:");
        printArray(numbers);
    }
    
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Find minimum element in remaining array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap minimum element with first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    public static void printArray(int[] arr) {
        System.out.println(java.util.Arrays.toString(arr));
    }
}
```

### Partition Algorithm

```java
public class PartitionExample {
    public static void main(String[] args) {
        int[] numbers = {3, 7, 2, 9, 4, 1, 8, 5};
        int threshold = 5;
        
        System.out.println("Original array:");
        printArray(numbers);
        
        int partitionIndex = partition(numbers, threshold);
        
        System.out.printf("After partitioning around %d:%n", threshold);
        printArray(numbers);
        System.out.printf("Partition index: %d%n", partitionIndex);
    }
    
    public static int partition(int[] arr, int threshold) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            // Move left pointer to find element >= threshold
            while (left <= right && arr[left] < threshold) {
                left++;
            }
            
            // Move right pointer to find element < threshold  
            while (left <= right && arr[right] >= threshold) {
                right--;
            }
            
            // Swap if both pointers found appropriate elements
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        
        return left;  // Index where partition occurs
    }
    
    public static void printArray(int[] arr) {
        System.out.println(java.util.Arrays.toString(arr));
    }
}
```

## Character Arrays and Strings

Character arrays have special relationship with String class:

```java
public class CharArrayExample {
    public static void main(String[] args) {
        // String to char array
        String text = "Hello World";
        char[] charArray = text.toCharArray();
        
        System.out.println("Original string: " + text);
        System.out.println("Char array: " + java.util.Arrays.toString(charArray));
        
        // Modify char array
        charArray[6] = 'w';  // Change 'W' to 'w'
        
        // Char array to String
        String modifiedText = new String(charArray);
        System.out.println("Modified string: " + modifiedText);
        
        // String constructor with char array and range
        String partial = new String(charArray, 0, 5);  // "Hello"
        System.out.println("Partial string: " + partial);
        
        // String.valueOf with char array
        String fromCharArray = String.valueOf(charArray);
        System.out.println("Using valueOf: " + fromCharArray);
        
        // Character manipulation
        reverseCharArray(charArray);
        System.out.println("Reversed: " + new String(charArray));
    }
    
    public static void reverseCharArray(char[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
```

## Reference Arrays (Object Arrays)

Arrays where each element is a reference to an object:

```java
public class ReferenceArrayExample {
    public static void main(String[] args) {
        // String array (reference array)
        String[] names = new String[3];
        
        // Initially all elements are null
        System.out.println("Initial values:");
        for (int i = 0; i < names.length; i++) {
            System.out.println("names[" + i + "] = " + names[i]);
        }
        
        // Assign string references
        names[0] = "Alice";
        names[1] = "Bob";
        names[2] = "Charlie";
        
        System.out.println("\\nAfter assignment:");
        for (String name : names) {
            System.out.println("Name: " + name);
        }
        
        // Array initialization with string literals
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        
        System.out.println("\\nColors:");
        for (int i = 0; i < colors.length; i++) {
            System.out.printf("Color %d: %s%n", i + 1, colors[i]);
        }
        
        // Person object array example
        Person[] people = new Person[2];
        people[0] = new Person("John", 25);
        people[1] = new Person("Jane", 30);
        
        System.out.println("\\nPeople:");
        for (Person person : people) {
            person.displayInfo();
        }
    }
}

class Person {
    public String name;
    public int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void displayInfo() {
        System.out.printf("Name: %s, Age: %d%n", name, age);
    }
}
```

## Array Utility Methods

### Practical Array Operations

```java
public class ArrayUtilsExample {
    public static void main(String[] args) {
        int[] numbers = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        
        // Various array operations
        System.out.println("Original: " + java.util.Arrays.toString(numbers));
        System.out.println("Sum: " + sum(numbers));
        System.out.println("Average: " + average(numbers));
        System.out.println("Min: " + min(numbers));
        System.out.println("Max: " + max(numbers));
        
        int target = 5;
        int index = linearSearch(numbers, target);
        System.out.printf("Index of %d: %d%n", target, index);
        
        int[] reversed = reverse(numbers);
        System.out.println("Reversed: " + java.util.Arrays.toString(reversed));
        
        int[] doubled = map(numbers, x -> x * 2);
        System.out.println("Doubled: " + java.util.Arrays.toString(doubled));
        
        int[] evens = filter(numbers, x -> x % 2 == 0);
        System.out.println("Even numbers: " + java.util.Arrays.toString(evens));
    }
    
    public static int sum(int[] arr) {
        int total = 0;
        for (int value : arr) {
            total += value;
        }
        return total;
    }
    
    public static double average(int[] arr) {
        return arr.length > 0 ? (double) sum(arr) / arr.length : 0.0;
    }
    
    public static int min(int[] arr) {
        if (arr.length == 0) return Integer.MAX_VALUE;
        int minimum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minimum) {
                minimum = arr[i];
            }
        }
        return minimum;
    }
    
    public static int max(int[] arr) {
        if (arr.length == 0) return Integer.MIN_VALUE;
        int maximum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maximum) {
                maximum = arr[i];
            }
        }
        return maximum;
    }
    
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;  // Not found
    }
    
    public static int[] reverse(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }
    
    // Simple functional-style operations
    public static int[] map(int[] arr, IntFunction mapper) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = mapper.apply(arr[i]);
        }
        return result;
    }
    
    public static int[] filter(int[] arr, IntPredicate predicate) {
        // Count matching elements first
        int count = 0;
        for (int value : arr) {
            if (predicate.test(value)) {
                count++;
            }
        }
        
        // Create result array
        int[] result = new int[count];
        int index = 0;
        for (int value : arr) {
            if (predicate.test(value)) {
                result[index++] = value;
            }
        }
        return result;
    }
    
    // Functional interfaces for operations
    interface IntFunction {
        int apply(int value);
    }
    
    interface IntPredicate {
        boolean test(int value);
    }
}
```

## String Processing with split()

The `split()` method is very useful for processing text data:

```java
public class StringSplitExample {
    public static void main(String[] args) {
        // CSV-like data processing
        String csvData = "John,25,Engineer,50000";
        String[] fields = csvData.split(",");
        
        System.out.println("CSV Fields:");
        for (int i = 0; i < fields.length; i++) {
            System.out.printf("Field %d: %s%n", i, fields[i]);
        }
        
        // Processing space-separated values
        String sentence = "Java is a powerful programming language";
        String[] words = sentence.split(" ");
        
        System.out.println("\\nWords in sentence:");
        for (String word : words) {
            System.out.println("- " + word);
        }
        
        // Multiple delimiters using regex
        String data = "apple,banana;cherry:date grape";
        String[] fruits = data.split("[,;: ]+");  // Split on comma, semicolon, colon, or space
        
        System.out.println("\\nFruits:");
        for (String fruit : fruits) {
            System.out.println("* " + fruit);
        }
        
        // Handling empty strings
        String textWithSpaces = "a,,b,c,,d";
        String[] parts1 = textWithSpaces.split(",");      // Includes empty strings
        String[] parts2 = textWithSpaces.split(",+");     // Skip empty strings
        
        System.out.println("\\nWith empty strings: " + java.util.Arrays.toString(parts1));
        System.out.println("Without empty strings: " + java.util.Arrays.toString(parts2));
    }
}
```

## Enhanced For Loop (For-Each)

The enhanced for loop makes array iteration more readable:

```java
public class EnhancedForLoopExample {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        String[] names = {"Alice", "Bob", "Charlie"};
        
        // Traditional for loop
        System.out.println("Traditional for loop:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers[" + i + "] = " + numbers[i]);
        }
        
        // Enhanced for loop (for-each)
        System.out.println("\\nEnhanced for loop:");
        for (int number : numbers) {
            System.out.println("Number: " + number);
        }
        
        // For-each with reference arrays
        System.out.println("\\nNames:");
        for (String name : names) {
            System.out.println("Hello, " + name);
        }
        
        // When you need index, use traditional loop
        System.out.println("\\nWith indices:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d: %s%n", i + 1, names[i]);
        }
        
        // For-each cannot modify array elements directly
        int[] values = {1, 2, 3, 4, 5};
        
        // This doesn't modify the array
        for (int value : values) {
            value *= 2;  // Only modifies local variable
        }
        System.out.println("After for-each attempt: " + java.util.Arrays.toString(values));
        
        // Use traditional loop to modify elements
        for (int i = 0; i < values.length; i++) {
            values[i] *= 2;  // Modifies array element
        }
        System.out.println("After traditional loop: " + java.util.Arrays.toString(values));
    }
}
```

## Key Points

1. **Arrays store elements of the same type** consecutively in memory
2. **Created in heap memory** using `new` operator
3. **Zero-based indexing**: First element at index 0
4. **Fixed size**: Length cannot be changed after creation
5. **Default values**: Automatic initialization with type-appropriate defaults
6. **Reference semantics**: Arrays are objects, variables hold references
7. **Method parameters**: Pass-by-reference allows modification
8. **Enhanced for loop**: Cleaner syntax for iteration
9. **String relationship**: Special integration with String class
10. **Sorting and searching**: Essential algorithms for array manipulation

Arrays are fundamental data structures that serve as building blocks for more complex data structures and algorithms in Java programming.
