package edu.pdx.cs410J.huidong;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import static java.lang.Character.getType;


public class Try {
    public static void main(String[] args) throws ParseException {
        Object number = 1;
        LocalTime t1 = LocalTime.of(7, 30);
        LocalTime t2 = t1.minus(2, ChronoUnit.HOURS);
        System.out.println(t2);
        System.exit(0);
    }
}
