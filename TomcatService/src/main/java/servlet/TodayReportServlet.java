package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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

        sql = "SELECT * FROM person WHERE to_days(submission_date) = to_days(now());";

        try {
            GetSqlResult getSqlResult = new GetSqlResult();
            lists = getSqlResult.getSqlResult(sql);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date(System.currentTimeMillis());
        String date = formatter.format(currentTime);

        BuildResponse buildResponse = new BuildResponse();
        StringBuilder result = buildResponse.buildResponse(date, lists);
        printWriter.println(result);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
}
