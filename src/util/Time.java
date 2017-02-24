/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Sidi
 */
public final class Time {

    private static String pattern = "HH:mm:ss";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Time.pattern);

    public static String getPattern() {
        return Time.pattern;
    }

    public static void setPattern(String pattern) {
        Time.pattern = pattern;
        Time.formatter = DateTimeFormatter.ofPattern(Time.pattern);
    }

    public static String format(LocalTime time) {
        return (time == null ? null : Time.formatter.format(time));
    }

    public static LocalTime parse(String dateTimeString) {
        try {
            return Time.formatter.parse(dateTimeString, LocalTime::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isValid(String dateTimeString) {
        return parse(dateTimeString) != null;
    }

}
