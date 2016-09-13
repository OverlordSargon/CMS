package com.zaico.cms.utility;

import com.zaico.cms.entities.Schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 05.09.2016.
 */
public class DaySchedule {

    public static List<Schedule> scheduleList(String beginTime, String endTime, String breakHour) throws ParseException{
        List<Schedule> list = new ArrayList<Schedule>();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Calendar time = Calendar.getInstance();
        Date begin = timeFormat.parse(beginTime);
        Date end = timeFormat.parse(endTime);
        Date pause = timeFormat.parse(breakHour);
        time.setTime(begin);
        while (time.getTime().before(end)) {
            String flag = "F";
            if ( time.getTime().compareTo(pause) == 0 ) {
                flag = "P";
            }
            int interval = time.get(Calendar.HOUR_OF_DAY)+1;
            Schedule schedule = new Schedule(interval,flag);
            list.add(schedule);
            time.add(Calendar.HOUR_OF_DAY,1);
        }
        return list;
    }

}
