package servlet;

import checker.NameChecker;
import checker.OtherChecker;
import checker.ReportChecker;
import org.apache.log4j.Logger;
import servlet.helper.DoSql;
import servlet.helper.IpUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * @author mengyuantan
 */
@WebServlet(name = "SubmitReportServlet", urlPatterns = {"/SubmitReport"})
public class SubmitReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        String name = new String(req.getParameter("name").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String fever = new String(req.getParameter("fever").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String contact = new String(req.getParameter("contact").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String report = new String(req.getParameter("report").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String others = new String(req.getParameter("others").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        name = name.replace("'", "\\'");
        fever = fever.replace("'", "\\'");
        contact = contact.replace("'", "\\'");
        report = report.replace("'", "\\'");
        others = others.replace("'", "\\'");

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();

        NameChecker nameChecker = new NameChecker();
        ReportChecker reportChecker = new ReportChecker();
        OtherChecker otherChecker = new OtherChecker();

        // todo: 信息校验的过程可以尝试整合
        nameChecker.checkName(name);
        reportChecker.checkReport(report);
        otherChecker.checkOther(others);

        if (!nameChecker.isPass()) {
            printWriter.println("<html><body>");
            printWriter.println(nameChecker.getErrorMsg());
            printWriter.println("</body></html>");
            return;
        }

        if (!reportChecker.isPass()) {
            printWriter.println("<html><body>");
            printWriter.println(reportChecker.getErrorMsg());
            printWriter.println("</body></html>");
            return;
        }

        if (!otherChecker.isPass()) {
            printWriter.println("<html><body>");
            printWriter.println(otherChecker.getErrorMsg());
            printWriter.println("</body></html>");
            return;
        }

        String sql;
        DoSql doSql = new DoSql();

        try {
            sql = String.format("SELECT * FROM person " +
                    "WHERE name = '%s' AND " +
                    "to_days(submission_date) = to_days(now());",
                    name);
            ResultSet resultSet = doSql.doSql(sql);
            if (!resultSet.next()) {
                sql = String.format("INSERT INTO person " +
                                "(name, fever, contact, report, others, submission_date) VALUES " +
                                "('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', '%6$s');",
                        name, fever, contact, report, others,
                        new java.sql.Timestamp(System.currentTimeMillis()));
            } else {
                sql = String.format("UPDATE person SET " +
                                "fever = '%2$s', " +
                                "contact = '%3$s', " +
                                "report = '%4$s', " +
                                "others = '%5$s', " +
                                "submission_date = '%6$s' " +
                                "WHERE name = '%1$s' AND " +
                                "to_days(submission_date) = to_days(now());",
                        name, fever, contact, report, others,
                        new java.sql.Timestamp(System.currentTimeMillis()));
            }


            IpUtils ipUtils = new IpUtils();
            String ipAddress = ipUtils.getVisitorIp(req);
            Logger log = Logger.getLogger(SubmitReportServlet.class);
            log.info(String.format("IP: \"%1$s\", name: \"%2$s\", SQL: \"%3$s\", submit report.",
                    ipAddress, name, sql));

            doSql.doSql(sql);

            printWriter.println("<html><body>");
            printWriter.println(name + "，信息提交成功。");
            printWriter.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
}
