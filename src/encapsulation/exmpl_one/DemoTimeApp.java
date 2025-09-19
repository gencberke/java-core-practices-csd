package encapsulation.exmpl_one;

public class DemoTimeApp {
    public static void main(String[] args) {
        Time t = new Time(19, 20, 58);
        System.out.printf("%02d:%02d:%02d\n", t.getM_hour(), t.getM_minute(), t.getM_second());
        t.setM_hour(20);
        t.setM_hour(21);
        t.setM_second(59);
        System.out.printf("%02d:%02d:%02d\n", t.getM_hour(), t.getM_minute(), t.getM_second());
    }
}

class Time {
    private int m_hour;
    private int m_minute;
    private int m_second;

    public Time (int hour, int minute, int second) {
        m_hour = hour;
        m_minute = minute;
        m_second = second;
    }

    public int getM_hour() {
        return m_hour;
    }

    public void setM_hour(int value) {
        m_hour = value;
    }

    public int getM_minute() {
        return m_minute;
    }

    public void setM_minute(int m_minute) {
        this.m_minute = m_minute;
    }

    public int getM_second() {
        return m_second;
    }

    public void setM_second(int m_second) {
        this.m_second = m_second;
    }
}