package libs.math.geometry.test;

import libs.math.geometry.Point;

public class PointTest {
    public static void main(String[] args)
    {
        Point pc = Point.createCartesian(100, 100);
        Point pp = Point.createPolar(100, 100);

        System.out.println(pc.toString());
        System.out.println(pp.toString());
    }
}
