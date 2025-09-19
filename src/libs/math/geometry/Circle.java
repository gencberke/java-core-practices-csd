package libs.math.geometry;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class Circle {
    private double m_r;
    private double m_area;
    private double m_circumference;

    private void calculate() {
        m_area = PI * m_r * m_r;
        m_circumference = 2 * PI * m_r;
    }

    public Circle () { }

    public Circle (double radius) {
        m_r = radius;
    }

    public void setRadius(double radius) {
        m_r = abs(radius);
        calculate();
    }

    public double getRadius() {
        return m_r;
    }

    public double getArea() {
        return m_area;
    }

    public double getCircumference() {
        return m_circumference;
    }

    public String toString() {
        return "Radius = %f, Area = %f, Circumference = %f".formatted(m_r, m_area, m_circumference);
    }
}