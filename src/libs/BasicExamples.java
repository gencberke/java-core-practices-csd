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

/*------------------ CLASS RELATIONSHIPS --------------------------------------------- CLASS RELATIONSHIPS ------------------------------- */

// Composition, Aggregation, Association examples ----------------------
/*
package libs;

import java.util.Arrays;

class App {
    public static void main(String[] args) {
        DemoRaceApp.run();
    }
}

class DemoRaceApp {
    public static void run() {
        Driver driver = new Driver();

        driver.setName("Berke Genç");
        driver.setRating(100);

        Car car = new Car(driver);

        car.run();

        Pilot [] pilots = {new Pilot(1, 10000, "Berke Genç"), new Pilot(2, 50000, "Elif Çalışkan"),
                new Pilot(3, 2500, "Emirhan Güroğlu")};
        Plane plane = new Plane(4, pilots);

        plane.fly();
    }
}

class Plane {
    private Engine [] m_engine;
    private Pilot [] m_pilot;

    public Plane(int n, Pilot[] m_pilot) {
        m_engine = new Engine[n];
        this.m_pilot = m_pilot;

        for (int i = 0; i < n; ++i)
            m_engine[i] = new Engine();
    }

    private void startEngines()
    {
        for (Engine e : m_engine)
            e.startEngine();
    }

    private void accelerateEngines()
    {
        for (Engine e : m_engine)
            e.accelerateEngine();
    }

    private void slowEngines()
    {
        for (Engine e : m_engine)
            e.slowEngine();
    }

    private void stopEngines()
    {
        for (Engine e : m_engine)
            e.stopEngine();
    }

    public void fly() {
        for (Pilot pilot : m_pilot) {
            System.out.printf("%s named pilot flys for %d duration with title of %d\n", pilot.getM_name(), pilot.getM_flightDuration(),
                    pilot.getM_flightDuration());


        }
    }

}

class Car {
    private final Engine m_engine;
    private Driver m_driver;

    public Car(Driver driver)
    {
        m_engine = new Engine();
        m_driver = driver;
    }

    public Driver getDriver()
    {
        return m_driver;
    }

    public void setDriver(Driver driver)
    {
        m_driver = driver;
    }

    public void brake()
    {
        m_engine.slowEngine();
    }

    public void run()
    {
        System.out.printf("Driver:%s, %d%n", m_driver.getName(), m_driver.getRating());
        m_engine.startEngine();
        m_engine.accelerateEngine();

        //...
        System.out.println("running");

        brake();
        m_engine.stopEngine();
    }

}

class Pilot {
    private int m_title;
    private int m_flightDuration;
    private String m_name;

    public Pilot(int m_title, int m_flightDuration, String m_name) {
        this.m_title = m_title;
        this.m_flightDuration = m_flightDuration;
        this.m_name = m_name;
    }

    public int getM_title() {
        return m_title;
    }

    public void setM_title(int m_title) {
        this.m_title = m_title;
    }

    public int getM_flightDuration() {
        return m_flightDuration;
    }

    public void setM_flightDuration(int m_flightDuration) {
        this.m_flightDuration = m_flightDuration;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

}

class Driver {
    private String m_name;
    private int m_rating;

    //...

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public int getRating()
    {
        return m_rating;
    }

    public void setRating(int rating)
    {
        m_rating = rating;
    }

}


class Engine {
    public void startEngine() {
        System.out.println("engine started");
    }

    public void stopEngine() {
        System.out.println("engine stopped");
    }

    public void slowEngine() {
        System.out.println("engine slowed");
    }

    public void accelerateEngine() {
        System.out.println("engine accelerated");
    }

}

*/

/*
package libs;

import java.util.Arrays;

class App {
    public static void main(String[] args) {
        DemoTaxiApp.run();
    }
}

class DemoTaxiApp {
    public static void run() {
        Driver driver = new Driver("Berke", 100);
        Taxi taxi = new Taxi(driver, "34 ZBU 79");

        Client client1 = new Client("elfclskn", "Elif Çalışkan");
        Client client2 = new Client("pacii", "Emirhan Guroglu");

        taxi.take(client1);
        taxi.take(client2);
    }
}

class Taxi {
    private final String plate;
    private Driver driver;

    public Taxi(String plate) {
        this.plate = plate;
    }

    public Taxi(Driver driver, String plate) {
        this.driver = driver;
        this.plate = plate;
    }

    public void take(Client client) {
        System.out.printf("TAXİ: %s, DRIVER: %s, RATING: %d\n", this.plate, driver.getM_name(), driver.getM_rating());
        System.out.printf("CLIENT: %s\n", client.getM_username());
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getPlate() {
        return plate;
    }

}

class Client {
    private String m_username;
    private String m_name;

    public Client(String m_username, String m_name) {
        this.m_username = m_username;
        this.m_name = m_name;
    }

    public String getM_username() {
        return m_username;
    }

    public void setM_username(String m_username) {
        this.m_username = m_username;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }
}

class Driver {
    private String m_name;
    private int m_rating;

    public Driver(String m_name, int m_rating) {
        this.m_name = m_name;
        this.m_rating = m_rating;
    }

    public Driver() {
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public int getM_rating() {
        return m_rating;
    }

    public void setM_rating(int m_rating) {
        this.m_rating = m_rating;
    }
}
 */














