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
}
