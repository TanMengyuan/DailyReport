import config.Config;
import servlet.helper.PointDate;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UnitTest {

    @Test
    private void Test(){
        System.out.println(String.format("UPDATE person SET " +
                "fever = '%2$s', " +
                "contact = '%3$s', " +
                "report = '%4$s', " +
                "others = '%5$s', " +
                "submission_date = '%6$s'" +
                "WHERE name = '%6$s' AND " +
                "to_days(submission_date) = to_days(now());",
                "1s", "2s", "3s", "4s", "5s", "6s"));
        System.out.println(String.format("%6s", "abc"));
    }

    @Test
    public void getDay() {
        String day = "2018-03-19";
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        System.out.println(dayAfter);
    }

    @Test
    public void getPreviousAndNextDay() throws ParseException {

        String today = "2020-03-02";
        PointDate pointDate = new PointDate();
        pointDate.setToday(today);

        SimpleDateFormat formatter = new SimpleDateFormat(Config.dateFormat);
        Date date = formatter.parse(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.DATE, -2);
        System.out.println(calendar.getTime());


    }
}
