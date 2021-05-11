package com.example.abstractionizer.login.jwt2.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static Date addMinutesToDate(Date date, Integer minutes){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }
}
