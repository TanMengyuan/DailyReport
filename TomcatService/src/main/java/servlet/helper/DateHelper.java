package servlet.helper;

import config.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author mengyuantan
 */
public class DateHelper {

    public PointDate getPointDate(String today) throws ParseException {

        PointDate pointDate = new PointDate();
        pointDate.setToday(today);

        SimpleDateFormat formatter = new SimpleDateFormat(Config.dateFormat);
        Date date = formatter.parse(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, 1);
        pointDate.setTomorrow(formatter.format(calendar.getTime()));

        calendar.add(Calendar.DATE, -2);
        pointDate.setYesterday(formatter.format(calendar.getTime()));


        return pointDate;
    }

    public ScheduleDate getScheduleDate(String today) throws ParseException {

        ScheduleDate scheduleDate = new ScheduleDate();
        scheduleDate.setToday(today);

        SimpleDateFormat formatter = new SimpleDateFormat(Config.dateFormat);
        Date dateToday = formatter.parse(today);
        Date dateOriginal = formatter.parse(Config.startTime);
        int roundSize = Config.NAME_LIST.size();

        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(dateToday);

        Calendar calendarOriginal = Calendar.getInstance();
        calendarOriginal.setTime(dateOriginal);

        long diffDaysLong = (calendarToday.getTimeInMillis() -
                calendarOriginal.getTimeInMillis()) /
                (1000 * 60 * 60 * 24);
        int diffDaysInt = (int) diffDaysLong;
        int remainDays = diffDaysInt % roundSize;
        if (remainDays < 0) {
            remainDays += roundSize;
        }

        scheduleDate.setRemainDays(remainDays);
        scheduleDate.setPrincipal((String) Config.NAME_LIST.get(remainDays));

        calendarToday.add(Calendar.DATE, - remainDays);
        scheduleDate.setDateStart(formatter.format(calendarToday.getTime()));

        calendarToday.add(Calendar.DATE, 2 * roundSize - 1);
        scheduleDate.setDateEnd(formatter.format(calendarToday.getTime()));

        return scheduleDate;
    }

    public String getTomorrow(String today) throws ParseException  {
        String tomorrow;

        SimpleDateFormat formatter = new SimpleDateFormat(Config.dateFormat);
        Date date = formatter.parse(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        tomorrow = formatter.format(calendar.getTime());

        return tomorrow;
    }
}
