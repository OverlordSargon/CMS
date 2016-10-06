package com.zaico.cms.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nzaitsev on 06.10.2016.
 */
public class CheckFromTo {

    /**
     * Check if "FROM" hour less than "TO" hour
     * @param firstHour
     * @param lastHour
     * @throws ExceptionCMS
     */
    public static void checkHours(String firstHour, String lastHour) throws ExceptionCMS, ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date first = dateFormat.parse(firstHour);
            Date last = dateFormat.parse(lastHour);
            Calendar calendarFirst = Calendar.getInstance();
            calendarFirst.setTime(first);

            Calendar calendarLast = Calendar.getInstance();
            calendarLast.setTime(last);

            if ( calendarLast.getTime().getTime() < calendarFirst.getTime().getTime() ) {
                throw new ExceptionCMS("\"WORK TO\" less than \"WORK FROM\" ",ErrorCode.NO_REQUIRED_INTERVAL);
            }
    }

    /**
     * Check if "FROM" hour less than "TO" hour
     * @param firstDay
     * @param lastDay
     * @throws ExceptionCMS
     */
    public static void checkDays(String firstDay, String lastDay) throws ExceptionCMS, ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-y");
            Date first = dateFormat.parse(firstDay);
            Date last = dateFormat.parse(lastDay);
            Calendar calendarFirst = Calendar.getInstance();
            calendarFirst.setTime(first);

            Calendar calendarLast = Calendar.getInstance();
            calendarLast.setTime(last);

            if ( calendarLast.getTime().getTime() < calendarFirst.getTime().getTime() ) {
                throw new ExceptionCMS("\"END DATE\" less than \"BEGIN DATE\" ",ErrorCode.NO_REQUIRED_INTERVAL);
            }
    }


}
