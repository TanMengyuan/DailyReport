package servlet;

import config.Config;
import org.apache.log4j.Logger;
import servlet.helper.BuildResponse;
import servlet.helper.DoSql;
import servlet.helper.GetSqlResult;
import servlet.helper.IpUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mengyuantan
 */
@WebServlet(name = "TodayReportServlet", urlPatterns = {"/TodayReport"})
public class TodayReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String sql;
        List<List<String>> lists = new ArrayList<>();

        IpUtils ipUtils = new IpUtils();
        String ipAddress = ipUtils.getVisitorIp(req);
        Logger log = Logger.getLogger(TodayReportServlet.class);
        log.info(String.format("IP: \"%s\", get today report.", ipAddress));

        sql = "SELECT * FROM person WHERE to_days(submission_date) = to_days(now());";

        try {
            GetSqlResult getSqlResult = new GetSqlResult();
            lists = getSqlResult.getSqlResult(sql);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter= new SimpleDateFormat(Config.dateFormat);
        Date currentTime = new Date(System.currentTimeMillis());
        String date = formatter.format(currentTime);

        try {
            BuildResponse buildResponse = new BuildResponse();
            StringBuilder result = buildResponse.buildResponse(date, lists);
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
