package Operations;

import java.util.*;

/**
 * DateOperation contiene todas las operaciones que nuestro programa podrá realizar con una Date
 * @author Pol Barco Martínez
 */

public class DateOperation {

    /**
     * Indica el dia de una fecha
     * @param date fecha a tratar
     * @return el dia de la "date"
     */
    public int Day (Date date) {
        int day;
        day = date.getDate();
        return day;
    }

    /**
     * Indica el mes de una fecha
     * @param date fecha a tratar
     * @return el nombre del mes de "date"
     */
    public String Month (Date date) {
        int month;
        month = date.getMonth();
        return whatMonth(month-1);
    }

    /**
     * Indica el año de una fecha
     * @param date fecha a tratar
     * @return el año de la "date"
     */
    public int Year (Date date) {
        int year;
        year = date.getYear();
        return year-1;
    }

    /**
     * Indica el dia de la semana de una fecha
     * @param date fecha a tratar
     * @return el nombre del día de la semana de "date"
     */
    public String DayOfWeek (Date date) {
        int day = Day(date);
        int year = Year(date) - 1900;
        int month = date.getMonth() - 1;

        Date date2 = new Date(year, month, day);

        int dayOfWeek = date2.getDay();
        return whatDayOfWeek(dayOfWeek);
    }

    /**
     * Indica el nombre del mes
     * @param month valor a tratar
     * @return el nombre del  mes de "month"
     */
    private String whatMonth (int month) {
        String m = "";
        if (month == 0) m = "January";
        else if (month == 1) m = "February";
        else if (month == 2) m = "March";
        else if (month == 3) m = "April";
        else if (month == 4) m = "May";
        else if (month == 5) m = "June";
        else if (month == 6) m = "July";
        else if (month == 7) m = "August";
        else if (month == 8) m = "September";
        else if (month == 9) m = "October";
        else if (month == 10) m = "November";
        else m = "December";
        return m;
    }

    /**
     * Indica el nombre del día de la semana
     * @param dayOfWeek valor a tratar
     * @return el nombre del día de la semana de "dayOfWeek"
     */
    private String whatDayOfWeek (int dayOfWeek) {
        String dayWeek = "";
        if (dayOfWeek == 0) dayWeek = "Monday";
        else if (dayOfWeek == 1) dayWeek = "Tuesday";
        else if (dayOfWeek == 2) dayWeek = "Wednesday";
        else if (dayOfWeek == 3) dayWeek = "Thursday";
        else if (dayOfWeek == 4) dayWeek = "Friday";
        else if (dayOfWeek == 5) dayWeek = "Saturday";
        else if (dayOfWeek == 6) dayWeek = "Sunday";
        return dayWeek;
    }
}

