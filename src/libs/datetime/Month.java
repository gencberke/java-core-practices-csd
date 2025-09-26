package libs.datetime;

public enum Month {
    JAN(31), FEB(28), MAR(31), APR(30), MAY(31), JUN(30), JUL(31), AUG(30),
    SEP(31), OCT(30), NOV(31), DEC(30);

    private final int M_DAYS;

    Month(int days) {
        M_DAYS = days;
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public int getDays(int year) {
        return ordinal() == 1 && isLeapYear(year) ? 29: M_DAYS;
    }

}
