package sample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaskDate {
    private String date;
    private int year;
    private int month;
    private int day;

    public TaskDate(String date) {
        this.date = date;
        this.year = Integer.parseInt(date.substring(0,4));
        this.month = Integer.parseInt(date.substring(5,7))-1;
        this.day = Integer.parseInt(date.substring(8,10));
    }

    public TaskDate(){

    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getTodayDateString(){
        return new GregorianCalendar().toZonedDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
    }

    public GregorianCalendar getTodayPlusSevenDayDate(){
        GregorianCalendar weekCalendar = new GregorianCalendar();//Получаю сегодняшнее число
        weekCalendar.add(Calendar.DAY_OF_MONTH,7);//Добавляю к сегодняшнему числу 7 дней
        return weekCalendar;
    }

    public String getTodayPlusSevenDayToString(){
        return getTodayPlusSevenDayDate().toZonedDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
    }
}
