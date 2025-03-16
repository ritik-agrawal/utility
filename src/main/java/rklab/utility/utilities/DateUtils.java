package rklab.utility.utilities;

import lombok.experimental.UtilityClass;

import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class DateUtils {

    public static Date getDateAfterNMinutes(int n){
        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, n);
        return calendar.getTime();
    }

    public static Date getDateAfterNDays(int n){
        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }

    public static long getUnixTime(Date date){
        var time = date.getTime();
        return time / 1000L;
    }

}
