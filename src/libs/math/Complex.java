package libs.math;

import static java.lang.Math.sqrt;

public class Complex {
    public double real;
    public double imag;

    private static Complex add(double re1, double im1, double re2, double im2)
    {
        return new Complex(re1 + re2, im1 + im2);
    }

    private static Complex subtract(double re1, double im1, double re2, double im2)
    {
        return add(re1, im1, -re2, -im2);
    }

    public Complex()
    {
    }

    public Complex(double a)
    {
        real = a;
    }

    public Complex(double a, double b)
    {
        real = a;
        imag = b;
    }

    public static Complex add(double val, Complex z)
    {
        return add(val, 0, z.real, z.imag);
    }

    public Complex add(Complex other)
    {
        return add(real, imag, other.real, other.imag);
    }

    public Complex add(double val)
    {
        return add(real, imag, val, 0);
    }

    public static Complex subtract(double val, Complex z)
    {
        return subtract(val, 0, z.real, z.imag);
    }

    public Complex subtract(Complex other)
    {
        return subtract(real, imag, other.real, other.imag);
    }

    public Complex subtract(double val)
    {
        return subtract(real, imag, val, 0);
    }

    public void inc(double val)
    {
        real += val;
    }

    public void inc()
    {
        inc(1);
    }

    public void dec(double val)
    {
        inc(-val);
    }

    public void dec()
    {
        dec(1);
    }

    public Complex getConjugate()
    {
        return new Complex(real, -imag);
    }

    public double getNorm()
    {
        return sqrt(real * real + imag * imag);
    }

    public double getLength()
    {
        return getNorm();
    }

    public String toString()
    {
        return "(%.2f, %.2f)".formatted(real, imag);
    }
}