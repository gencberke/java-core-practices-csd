# STRING OPERATIONS AND STRING CLASS

In programming, a collection of characters or generally texts is called a **"string"**. In Java, the most basic class for string operations is the **String** class in the `java.lang` package. There are other classes in JavaSE that perform string operations, but they are in a supporting position to String.

## Important Notes

- **Classes in `java.lang` package** can be used directly without any import declaration
- **Immutable class**: If the content of an object (roughly non-static data members) cannot be changed by the programmer using the class after the object is created, such classes are called "immutable classes"
- **String literals**: When the compiler sees a string literal and sees this string literal for the first time, it roughly generates code like: "create a string type object and bring the characters in the string literal to a form that can be held by this string object and give the reference of the relevant string object"

## Basic String Creation and Usage

### String Literals and Objects

```java
public class BasicStringExample {
    public static void main(String[] args) {
        // String literals create String objects
        String greeting = "Hello World";
        String name = "Java";
        
        // String objects can be used with print methods
        System.out.println(greeting);
        System.out.println("Programming language: " + name);
        
        // printf with %s format specifier
        System.out.printf("Learning %s programming%n", name);
        
        // String concatenation
        String message = greeting + " from " + name;
        System.out.println(message);
    }
}
```

### Reading Strings from Keyboard

```java
import java.util.Scanner;

public class StringInputExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();  // Reads entire line including spaces
        
        System.out.print("Enter your city: ");
        String city = scanner.nextLine();
        
        System.out.printf("Hello %s from %s!%n", name, city);
        
        // Note: nextLine() does not include the enter character
    }
}
```

## String Indexing and Length

Strings can be thought of as arrays of characters, where each character has an index starting from 0.

### charAt() and length()

```java
public class StringIndexingExample {
    public static void main(String[] args) {
        String text = "Java Programming";
        
        // length() - returns number of characters
        System.out.println("Length: " + text.length());
        
        // charAt() - returns character at specific index
        System.out.println("First character: " + text.charAt(0));
        System.out.println("Last character: " + text.charAt(text.length() - 1));
        
        // Printing all characters
        System.out.println("All characters:");
        for (int i = 0; i < text.length(); i++) {
            System.out.println("Index " + i + ": " + text.charAt(i));
        }
        
        // IndexOutOfBoundsException if index is invalid
        // System.out.println(text.charAt(100)); // Would throw exception
    }
}
```

## String Immutability

String is an immutable class. Methods that appear to modify strings actually return new String objects.

### Case Conversion Methods

```java
public class StringCaseExample {
    public static void main(String[] args) {
        String original = "Hello World";
        
        // toLowerCase() - returns new string with all lowercase
        String lowercase = original.toLowerCase();
        System.out.println("Original: " + original);
        System.out.println("Lowercase: " + lowercase);
        
        // toUpperCase() - returns new string with all uppercase
        String uppercase = original.toUpperCase();
        System.out.println("Uppercase: " + uppercase);
        
        // Original string is unchanged (immutable)
        System.out.println("Original after operations: " + original);
        
        // Characters without case equivalents remain the same
        String mixed = "Hello123!@#";
        System.out.println("Mixed to upper: " + mixed.toUpperCase());
    }
}
```

## String Storage and Reference Comparison

### String Interning

```java
public class StringInternExample {
    public static void main(String[] args) {
        // Identical string literals share the same object (interning)
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = "World";
        
        // Reference comparison (== compares addresses)
        System.out.println("str1 == str2: " + (str1 == str2));  // true (same object)
        System.out.println("str1 == str3: " + (str1 == str3));  // false (different objects)
        
        // Content comparison (equals compares content)
        System.out.println("str1.equals(str2): " + str1.equals(str2));  // true
        
        // Different ways to create strings
        String str4 = new String("Hello");  // Creates new object
        System.out.println("str1 == str4: " + (str1 == str4));      // false (different objects)
        System.out.println("str1.equals(str4): " + str1.equals(str4)); // true (same content)
    }
}
```

## String Comparison

### equals() and equalsIgnoreCase()

```java
public class StringComparisonExample {
    public static void main(String[] args) {
        String str1 = "Java";
        String str2 = "java";
        String str3 = "JAVA";
        String str4 = "Java";
        
        // Case-sensitive comparison
        System.out.println("str1.equals(str2): " + str1.equals(str2));  // false
        System.out.println("str1.equals(str4): " + str1.equals(str4));  // true
        
        // Case-insensitive comparison
        System.out.println("str1.equalsIgnoreCase(str2): " + str1.equalsIgnoreCase(str2));  // true
        System.out.println("str1.equalsIgnoreCase(str3): " + str1.equalsIgnoreCase(str3));  // true
        
        // Always use equals() for content comparison, not ==
        // == compares references (addresses), not content
    }
}
```

### compareTo() Method

```java
public class StringCompareToExample {
    public static void main(String[] args) {
        String a = "apple";
        String b = "banana";
        String c = "apple";
        
        // compareTo returns:
        // negative if a < b lexicographically
        // zero if a equals b
        // positive if a > b lexicographically
        
        int result1 = a.compareTo(b);
        int result2 = a.compareTo(c);
        int result3 = b.compareTo(a);
        
        System.out.println("apple.compareTo(banana): " + result1);  // negative
        System.out.println("apple.compareTo(apple): " + result2);   // zero
        System.out.println("banana.compareTo(apple): " + result3);  // positive
        
        // Practical usage
        if (a.compareTo(b) < 0) {
            System.out.println(a + " comes before " + b);
        } else if (a.compareTo(b) > 0) {
            System.out.println(a + " comes after " + b);
        } else {
            System.out.println(a + " and " + b + " are equal");
        }
    }
}
```

## String Concatenation

### Using + Operator

```java
public class StringConcatenationExample {
    public static void main(String[] args) {
        String firstName = "John";
        String lastName = "Doe";
        
        // String concatenation with +
        String fullName = firstName + " " + lastName;
        System.out.println("Full name: " + fullName);
        
        // Concatenation with other types
        int age = 25;
        String info = "Name: " + firstName + ", Age: " + age;
        System.out.println(info);
        
        // When one operand is String, others are converted to String
        String result = "Result: " + 10 + 20;     // "Result: 1020"
        String result2 = "Result: " + (10 + 20);  // "Result: 30"
        System.out.println(result);
        System.out.println(result2);
    }
}
```

### concat() Method

```java
public class StringConcatMethodExample {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "World";
        
        // concat() method
        String result = str1.concat(" ").concat(str2);
        System.out.println("Concatenated: " + result);
        
        // Chaining concat calls
        String greeting = "Good"
                .concat(" ")
                .concat("Morning")
                .concat("!");
        System.out.println(greeting);
        
        // Note: concat() only works with String arguments
        // Use + operator for mixing types
    }
}
```

## Empty and Blank Strings

### isEmpty() and isBlank()

```java
public class EmptyBlankStringExample {
    public static void main(String[] args) {
        String empty = "";
        String spaces = "   ";
        String text = "Hello";
        String nullString = null;
        
        // isEmpty() - checks if length is 0
        System.out.println("empty.isEmpty(): " + empty.isEmpty());      // true
        System.out.println("spaces.isEmpty(): " + spaces.isEmpty());    // false
        System.out.println("text.isEmpty(): " + text.isEmpty());        // false
        
        // isBlank() - Java 11+ - checks if empty or only whitespace
        System.out.println("empty.isBlank(): " + empty.isBlank());      // true
        System.out.println("spaces.isBlank(): " + spaces.isBlank());    // true
        System.out.println("text.isBlank(): " + text.isBlank());        // false
        
        // Be careful with null strings
        // System.out.println(nullString.isEmpty()); // Would throw NullPointerException
        
        // Safe way to check
        if (nullString != null && !nullString.isEmpty()) {
            System.out.println("String is not empty");
        }
    }
}
```

## Substring Operations

### substring() Methods

```java
public class SubstringExample {
    public static void main(String[] args) {
        String text = "Java Programming";
        
        // substring(beginIndex) - from index to end
        String fromIndex = text.substring(5);  // "Programming"
        System.out.println("From index 5: " + fromIndex);
        
        // substring(beginIndex, endIndex) - from begin to end-1
        String middle = text.substring(5, 11);  // "Progra"
        System.out.println("From 5 to 11: " + middle);
        
        // Special case: substring(length) returns empty string
        String emptyResult = text.substring(text.length());
        System.out.println("From end: '" + emptyResult + "'");
        
        // substring(0, 0) returns empty string
        String emptyFromStart = text.substring(0, 0);
        System.out.println("From 0 to 0: '" + emptyFromStart + "'");
        
        // Extracting first and last characters
        if (!text.isEmpty()) {
            String firstChar = text.substring(0, 1);
            String lastChar = text.substring(text.length() - 1);
            System.out.println("First: " + firstChar + ", Last: " + lastChar);
        }
    }
}
```

## String Searching

### indexOf() Methods

```java
public class StringSearchExample {
    public static void main(String[] args) {
        String text = "Java is great, Java is powerful";
        
        // indexOf(char) - find first occurrence of character
        int firstA = text.indexOf('a');
        System.out.println("First 'a' at index: " + firstA);
        
        // indexOf(char, fromIndex) - find from specific index
        int nextA = text.indexOf('a', firstA + 1);
        System.out.println("Next 'a' at index: " + nextA);
        
        // indexOf(String) - find first occurrence of substring
        int javaIndex = text.indexOf("Java");
        System.out.println("First 'Java' at index: " + javaIndex);
        
        // indexOf(String, fromIndex) - find substring from specific index
        int nextJava = text.indexOf("Java", javaIndex + 1);
        System.out.println("Next 'Java' at index: " + nextJava);
        
        // Returns -1 if not found
        int notFound = text.indexOf("Python");
        System.out.println("'Python' found at: " + notFound);  // -1
        
        // lastIndexOf() - search from end
        int lastA = text.lastIndexOf('a');
        System.out.println("Last 'a' at index: " + lastA);
    }
}
```

### Practical Search Example

```java
public class WordCountExample {
    public static void main(String[] args) {
        String text = "Java is fun, Java is powerful, Java is everywhere";
        String searchWord = "Java";
        
        int count = 0;
        int index = 0;
        
        while ((index = text.indexOf(searchWord, index)) != -1) {
            count++;
            index += searchWord.length();  // Move past found word
        }
        
        System.out.printf("'%s' appears %d times in the text%n", searchWord, count);
    }
}
```

## Character Operations with Character Class

### Character Testing Methods

```java
public class CharacterOperationsExample {
    public static void main(String[] args) {
        String text = "Hello World 123!";
        
        System.out.println("Character analysis of: " + text);
        
        int letters = 0, digits = 0, whitespaces = 0, others = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            
            if (Character.isLetter(ch)) {
                letters++;
            } else if (Character.isDigit(ch)) {
                digits++;
            } else if (Character.isWhitespace(ch)) {
                whitespaces++;
            } else {
                others++;
            }
        }
        
        System.out.println("Letters: " + letters);
        System.out.println("Digits: " + digits);
        System.out.println("Whitespaces: " + whitespaces);
        System.out.println("Others: " + others);
    }
}
```

## String Trimming

### trim(), strip(), stripLeading(), stripTrailing()

```java
public class StringTrimmingExample {
    public static void main(String[] args) {
        String textWithSpaces = "   Hello World   ";
        String textWithUnicode = "\u2000\u2001Hello World\u2000\u2001";  // Unicode spaces
        
        System.out.println("Original: '" + textWithSpaces + "'");
        
        // trim() - removes ASCII whitespace (\\u0000 to \\u0020)
        String trimmed = textWithSpaces.trim();
        System.out.println("trim(): '" + trimmed + "'");
        
        // strip() - Java 11+ - removes all Unicode whitespace
        String stripped = textWithSpaces.strip();
        System.out.println("strip(): '" + stripped + "'");
        
        // stripLeading() - Java 11+ - removes leading whitespace
        String stripLeading = textWithSpaces.stripLeading();
        System.out.println("stripLeading(): '" + stripLeading + "'");
        
        // stripTrailing() - Java 11+ - removes trailing whitespace
        String stripTrailing = textWithSpaces.stripTrailing();
        System.out.println("stripTrailing(): '" + stripTrailing + "'");
        
        // Difference with Unicode spaces
        System.out.println("\\nUnicode spaces example:");
        System.out.println("trim(): '" + textWithUnicode.trim() + "'");      // May not remove all
        System.out.println("strip(): '" + textWithUnicode.strip() + "'");    // Removes all whitespace
    }
}
```

## StringBuilder for Efficient String Building

When building strings with many concatenations, StringBuilder is more efficient than using + operator repeatedly.

### Basic StringBuilder Usage

```java
public class StringBuilderExample {
    public static void main(String[] args) {
        // StringBuilder for efficient string building
        StringBuilder sb = new StringBuilder();
        
        // append() methods for different types
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        sb.append(" - ");
        sb.append(2024);
        sb.append(" - ");
        sb.append(3.14);
        
        // toString() to get final String
        String result = sb.toString();
        System.out.println("Result: " + result);
        
        // StringBuilder with initial capacity
        StringBuilder sbWithCapacity = new StringBuilder(100);
        sbWithCapacity.append("Initial text");
        
        // StringBuilder with initial string
        StringBuilder sbWithString = new StringBuilder("Starting text");
        sbWithString.append(" - appended");
        System.out.println("With initial string: " + sbWithString.toString());
    }
}
```

### StringBuilder Methods

```java
public class StringBuilderMethodsExample {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello World");
        
        // setCharAt() - modify character at specific index
        sb.setCharAt(6, 'w');  // Change 'W' to 'w'
        System.out.println("After setCharAt: " + sb);
        
        // reverse() - reverse the string
        StringBuilder reversed = new StringBuilder("Hello").reverse();
        System.out.println("Reversed: " + reversed);
        
        // insert() - insert at specific position
        sb.insert(5, " Beautiful");
        System.out.println("After insert: " + sb);
        
        // delete() - remove characters in range
        sb.delete(5, 15);  // Remove " Beautiful"
        System.out.println("After delete: " + sb);
        
        // Method chaining (fluent interface)
        String chained = new StringBuilder()
                .append("Java")
                .append(" is")
                .append(" awesome")
                .reverse()
                .toString();
        System.out.println("Chained operations: " + chained);
    }
}
```

## String Factory Methods

### valueOf() Methods

```java
public class StringValueOfExample {
    public static void main(String[] args) {
        // valueOf() factory methods convert various types to String
        
        int number = 42;
        double decimal = 3.14159;
        boolean flag = true;
        char character = 'A';
        
        String strFromInt = String.valueOf(number);
        String strFromDouble = String.valueOf(decimal);
        String strFromBoolean = String.valueOf(flag);
        String strFromChar = String.valueOf(character);
        
        System.out.println("From int: " + strFromInt);
        System.out.println("From double: " + strFromDouble);
        System.out.println("From boolean: " + strFromBoolean);
        System.out.println("From char: " + strFromChar);
        
        // valueOf with char array
        char[] charArray = {'H', 'e', 'l', 'l', 'o'};
        String strFromCharArray = String.valueOf(charArray);
        System.out.println("From char array: " + strFromCharArray);
        
        // valueOf with char array and range
        String strFromCharRange = String.valueOf(charArray, 1, 3);  // "ell"
        System.out.println("From char range: " + strFromCharRange);
    }
}
```

## Advanced String Operations

### contains() Method

```java
public class StringContainsExample {
    public static void main(String[] args) {
        String email = "user@example.com";
        String website = "https://www.example.com";
        
        // contains() - check if string contains substring
        if (email.contains("@")) {
            System.out.println("Valid email format");
        }
        
        if (website.contains("https://")) {
            System.out.println("Secure website");
        }
        
        // Case-sensitive search
        String text = "Java Programming";
        System.out.println("Contains 'java': " + text.contains("java"));      // false
        System.out.println("Contains 'Java': " + text.contains("Java"));      // true
        
        // For case-insensitive search, convert to lowercase
        System.out.println("Contains 'java' (ignore case): " + 
                          text.toLowerCase().contains("java".toLowerCase()));  // true
    }
}
```

### startsWith() and endsWith()

```java
public class StringStartsEndsExample {
    public static void main(String[] args) {
        String filename = "document.pdf";
        String url = "https://www.example.com";
        String email = "user@gmail.com";
        
        // startsWith() - check if string starts with prefix
        if (url.startsWith("https://")) {
            System.out.println("Secure URL");
        } else if (url.startsWith("http://")) {
            System.out.println("Non-secure URL");
        }
        
        // endsWith() - check if string ends with suffix
        if (filename.endsWith(".pdf")) {
            System.out.println("PDF file");
        } else if (filename.endsWith(".txt")) {
            System.out.println("Text file");
        }
        
        if (email.endsWith("@gmail.com")) {
            System.out.println("Gmail account");
        }
        
        // Multiple checks
        String[] imageExtensions = {".jpg", ".png", ".gif", ".bmp"};
        String imageFile = "photo.jpg";
        
        boolean isImage = false;
        for (String ext : imageExtensions) {
            if (imageFile.endsWith(ext)) {
                isImage = true;
                break;
            }
        }
        
        System.out.println(imageFile + " is image: " + isImage);
    }
}
```

### replace() Methods

```java
public class StringReplaceExample {
    public static void main(String[] args) {
        String text = "Hello World! Hello Java!";
        
        // replace(char, char) - replace all occurrences of character
        String replacedChar = text.replace('l', 'L');
        System.out.println("Replace 'l' with 'L': " + replacedChar);
        
        // replace(CharSequence, CharSequence) - replace all occurrences of substring
        String replacedWord = text.replace("Hello", "Hi");
        System.out.println("Replace 'Hello' with 'Hi': " + replacedWord);
        
        // replaceFirst() - replace only first occurrence
        String replaceFirst = text.replaceFirst("Hello", "Hi");
        System.out.println("Replace first 'Hello': " + replaceFirst);
        
        // Practical examples
        String phoneNumber = "123-456-7890";
        String cleanPhone = phoneNumber.replace("-", "");
        System.out.println("Clean phone: " + cleanPhone);
        
        String path = "C:\\Users\\John\\Documents";
        String unixPath = path.replace("\\", "/");
        System.out.println("Unix path: " + unixPath);
    }
}
```

## Practical String Applications

### Simple Text Processor

```java
public class TextProcessor {
    public static void main(String[] args) {
        String text = "  Java is a powerful programming language. Java is platform-independent.  ";
        
        System.out.println("Original text: '" + text + "'");
        
        // Clean and process text
        String processed = text.strip()                    // Remove leading/trailing spaces
                              .toLowerCase()               // Convert to lowercase
                              .replace("java", "Python"); // Replace Java with Python
        
        System.out.println("Processed: '" + processed + "'");
        
        // Count words
        String[] words = processed.split("\\\\s+");
        System.out.println("Word count: " + words.length);
        
        // Find longest word
        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        System.out.println("Longest word: " + longestWord);
    }
}
```

## Key Points

1. **String class is immutable** - methods return new objects
2. **Use equals() for content comparison**, not ==
3. **String literals are interned** for memory efficiency
4. **StringBuilder is efficient** for multiple concatenations
5. **charAt() and length()** provide character-level access
6. **indexOf() and lastIndexOf()** search for substrings
7. **trim() and strip()** remove whitespace
8. **startsWith() and endsWith()** check prefixes and suffixes
9. **contains() checks for substring presence**
10. **valueOf() factory methods** convert other types to String

String operations are fundamental to text processing and form the basis for many programming tasks involving user input, file processing, and data manipulation.
