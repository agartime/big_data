package com.agartime.utad.util;

import com.agartime.utad.writables.DateTimeWritable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by antoniogonzalezartime on 31/12/13.
 */
public final class DateUtils {

    public static final String defaultYear = "2013";

    public static Date getDate(String month, String day, String hour, String minute, String second) {
        Date date;
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");
            date = parseFormat.parse(month+" "+day+" "+hour+":"+minute+":"+second+" "+defaultYear);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static Date getDate(int month, int day, int hour, int minute, int second) {
        Date date;
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("MM dd HH:mm:ss yyyy");
            date = parseFormat.parse(month+" "+day+" "+hour+":"+minute+":"+second+" "+defaultYear);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static DateTimeWritable getDateTimeWritable(Date date) {
        short year = Short.parseShort(defaultYear);
        SimpleDateFormat parseFormat = new SimpleDateFormat("MM");
        byte month = Byte.parseByte(parseFormat.format(date));
        parseFormat = new SimpleDateFormat("dd");
        byte day = Byte.parseByte(parseFormat.format(date));
        parseFormat = new SimpleDateFormat("HH");
        byte hour = Byte.parseByte(parseFormat.format(date));
        parseFormat = new SimpleDateFormat("mm");
        byte minute = Byte.parseByte(parseFormat.format(date));
        parseFormat = new SimpleDateFormat("ss");
        byte second = Byte.parseByte(parseFormat.format(date));
        return new DateTimeWritable(year,month,day,hour,minute,second);
    }

    public static String getCurrentDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        return sdf.format(d);
    }

}
