package com.zaico.cms.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 03.09.2016.
 */
public class WorkWeek {
    public static List<Date> getWorkDays(String beginDate, String endDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-y");
        Date begin = dateFormat.parse(beginDate);
        Date end = dateFormat.parse(endDate);
        List<Date> workDays = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(begin);
        cal2.setTime(end);
        cal2.add(Calendar.DAY_OF_MONTH,1);
        while (cal.getTime().before(cal2.getTime())) {
            if ((Calendar.SATURDAY != cal.get(Calendar.DAY_OF_WEEK))
                    &&(Calendar.SUNDAY != cal.get(Calendar.DAY_OF_WEEK)))
            {
                workDays.add(cal.getTime());
            }
            cal.add(Calendar.DATE, 1);
        }
        return workDays;
    }
}
