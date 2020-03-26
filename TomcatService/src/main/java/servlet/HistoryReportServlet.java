package servlet;

import servlet.helper.BuildResponse;
import servlet.helper.GetSqlResult;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengyuantan
 */
@WebServlet(name = "HistoryReportServlet", urlPatterns = {"/HistoryReport"})
public class HistoryReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        String date = new String(req.getParameter("date").
                getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        resp.setContentType("text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String sql;
        List<List<String>> lists = new ArrayList<>();

        if (date.isEmpty()) {
            printWriter.println("<html><body>");
            printWriter.println("查询日期为空，请选择查询日期。");
            printWriter.println("</body></html>");
            return;
        }

        sql = String.format("SELECT * FROM person " +
                "WHERE to_days(submission_date) = to_days('%s');", date);

        try {
            GetSqlResult getSqlResult = new GetSqlResult();
            lists = getSqlResult.getSqlResult(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BuildResponse buildResponse = new BuildResponse();
            StringBuilder result = buildResponse.buildResponse(date, lists);
            printWriter.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
