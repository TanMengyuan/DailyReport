package servlet;

import config.Config;
import org.apache.log4j.Logger;
import servlet.helper.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mengyuantan
 */
@WebServlet(name = "ScheduleServlet", urlPatterns = {"/Schedule"})
public class ScheduleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        SimpleDateFormat formatter= new SimpleDateFormat(Config.dateFormat);
        Date currentTime = new Date(System.currentTimeMillis());
        String date = formatter.format(currentTime);

        IpUtils ipUtils = new IpUtils();
        String ipAddress = ipUtils.getVisitorIp(req);
        Logger log = Logger.getLogger(ScheduleServlet.class);
        log.info(String.format("IP: \"%1$s\", get schedule.", ipAddress));

        DateHelper dateHelper = new DateHelper();
        ScheduleDate scheduleDate;
        try {
            scheduleDate = dateHelper.getScheduleDate(date);
            int roundSize = Config.NAME_LIST.size();
            List<PersonInfo> scheduleList = new ArrayList<>();
            String curDate = scheduleDate.getDateStart();
            for (int i = 0; i < Config.shownScheduleTimes * roundSize; i++) {
                PersonInfo personInfo = new PersonInfo();
                personInfo.setDate(curDate);
                personInfo.setName((String) Config.NAME_LIST.get(i % roundSize));
                scheduleList.add(personInfo);
                curDate = dateHelper.getTomorrow(curDate);
            }
            BuildResponse buildResponse = new BuildResponse();
            StringBuilder result = buildResponse.buildScheduleResponse(scheduleList);
            printWriter.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
}