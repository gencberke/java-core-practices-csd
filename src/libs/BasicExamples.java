/*------------------ FINAL KEYWORD ------------------------------------------- FINAL KEYWORD --------------------------------- */

/*
package libs;
public class BasicExamples {

    public static void main(String[] args) {
        Window w = new Window();

        w.setVisibility(Visibility.GONE);
    }
}

class Window {
    public void setVisibility(int value) {

        switch (value) {
            case Visibility.VISIBLE -> System.out.println("visible");
            case Visibility.INVISIBLE -> System.out.println("invisible");
            case Visibility.GONE -> System.out.println("gone");
        }

    }
}

class Visibility {
    public static final int VISIBLE = 1;
    public static final int INVISIBLE = 0;
    public static final int GONE = 2;
}
*/

// Wrong Example
/*
package libs;

class BasicExamples {
    public static void main(String[] args)
    {
        final Sample s = new Sample(20);

        s.x = 10;
    }
}

class Sample {
    public int x;

    public Sample(int a)
    {
        x = a;
    }
}
 */

/*------------------ ENUM CLASSES --------------------------------------------- ENUM CLASSES ------------------------------- */
// WITHOUT ENUM CLASS -----------------------
/*
package libs;
import java.util.Arrays;
import java.util.Random;

class BasicExamples {
    public static void main(String[] args)
    {
        DemoGameApp.run(args);
    }
}

class DemoGameApp {
    public static void run(String [] args)
    {
        Direction [] directions = Direction.values();
        Random random = new Random();
        GameObject go1 = new GameObject("Player-1");
        GameObject go2 = new GameObject("Player-2");

        go1.setColor(Color.RED);
        go2.setColor(Color.BLUE);

        while (true) {
            int index1 = random.nextInt(0, 4);
            int index2 = random.nextInt(0, 4);
            //...
            go1.move(directions[index1]);
            go2.move(directions[index2]);

            if (index1 == index2)
                break;
        }
    }
}

class Direction {
    private final int m_ordinal;

    private Direction(int ordinal)
    {
        m_ordinal = ordinal;
    }

    private static final Direction [] m_values = {new Direction(0), new Direction(1), new Direction(2)
            ,new Direction(3)};

    public static final Direction RIGHT = m_values[0];
    public static final Direction TOP = m_values[1];
    public static final Direction LEFT = m_values[2];
    public static final Direction BOTTOM = m_values[3];

    public static Direction[] values() {
        return Arrays.copyOf(m_values, m_values.length);
    }

    public int getOrdinal() {
        return m_ordinal;
    }
}


class Color {
    private final int m_ordinal;

    private Color(int ordinal)
    {
        m_ordinal = ordinal;
    }

    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int WHITE = 3;
    public static final int BLACK = 4;

    public int getOrdinal() {
        return m_ordinal;
    }
}

class GameObject {
    private final String m_name;
    private int m_color;
    //...

    public GameObject(String name)
    {
        m_name = name;
    }

    public void move(Direction direction)
    {
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

    public void setColor(int color)
    {
        //...
        m_color = color;
    }

    public int getColor()
    {
        return m_color;
    }

    //...
}
 */

// WITH ENUM CLASS -----------------------
/*
package libs;
import java.util.Arrays;
import java.util.Random;

class BasicExamples {
    public static void main(String[] args)
    {
        DemoGameApp.run(args);
    }
}

class DemoGameApp {
    public static void run(String [] args)
    {
        Direction [] directions = Direction.values();
        Random random = new Random();
        GameObject go1 = new GameObject("Player-1");
        GameObject go2 = new GameObject("Player-2");

        go1.setColor(1);
        go2.setColor(2);

        while (true) {
            int index1 = random.nextInt(0, 4);
            int index2 = random.nextInt(0, 4);
            //...
            go1.move(directions[index1]);
            go2.move(directions[index2]);

            if (index1 == index2)
                break;
        }
    }
}

enum Direction {
    RIGHT, TOP, LEFT, BOTTOM
}


enum Color {
   RED, GREEN, WHITE, BLUE
}

class GameObject {
    private final String m_name;
    private int m_color;
    //...

    public GameObject(String name)
    {
        m_name = name;
    }

    public void move(Direction direction)
    {
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

    public void setColor(int color)
    {
        //...
        m_color = color;
    }

    public int getColor()
    {
        return m_color;
    }

    //...
}
 */

// ADDING ANOTHER MEMBERS TO ENUM CLASS ----------------------
/*
package libs;
import java.util.Random;
import java.util.Scanner;

class BasicExamples {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner kb = new Scanner(System.in);

        System.out.println("enter value");
        int count = kb.nextInt();

        for (int i = 0; i < count; i++) {
            Color color = Color.randomColor(random);

            System.out.printf("%s[r = %d, g = %d, b = %d]%n", color.toString(), color.r, color.g, color.b);
        }
    }
}

enum Color {
    RED(255,0,0), GREEN(0,255,0), BLUE(0,0,255), WHITE(0,0,255), BLACK(0,0,0);
    private static final Color [] VALUES = values();
    public final int r, g, b;

    Color(int x, int y, int z) {
        r = x;
        g = y;
        b = z;
    }

    public static Color randomColor(Random random) {
        return VALUES[random.nextInt(VALUES.length)];
    }
}
 */

// VALUE OF METHOD ENUM ----------------------
/*
package libs;
import libs.datetime.Month;

import java.util.Random;
import java.util.Scanner;

class BasicExamples {
    public static void main(String[] args)
    {
        Random random = new Random();
        Scanner kb = new Scanner(System.in);
        System.out.print("Input month as JAN, FEB, ..., DEC:");
        Month month = Month.valueOf(kb.nextLine());
        int year = random.nextInt(1900, 2100);

        System.out.printf("%d -> %s(%d)%n", year, month.toString(), month.getDays(year));
    }
}
 */

//package libs;
//
//public class BasicExamples {
//    public static void main(String[] args) {
//
//    }
//}



















