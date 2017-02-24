/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Sidi
 */
public final class Date {

    private static String pattern = "dd/MM/yyyy";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Date.pattern);

    public static String getPattern() {
        return Date.pattern;
    }

    public static void setPattern(String pattern) {
        Date.pattern = pattern;
        Date.formatter = DateTimeFormatter.ofPattern(Date.pattern);
    }

    public static String format(LocalDate date) {
        return (date == null ? null : Date.formatter.format(date));
    }

    public static LocalDate parse(String dateTimeString) {
        try {
            return Date.formatter.parse(dateTimeString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isValid(String dateTimeString) {
        return parse(dateTimeString) != null;
    }
    
    public static String dayOfWeek(int day) {
        DayOfWeek dayOfWeek = DayOfWeek.of(day);
        String dayString = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
        return dayString;
    }

}
