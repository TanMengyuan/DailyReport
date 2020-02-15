package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author mengyuantan
 */
@WebServlet(name = "SubmitReportServlet", urlPatterns = {"/SubmitReport"})
public class SubmitReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String fever = req.getParameter("fever");
        String contact = req.getParameter("contact");
        String report = req.getParameter("report");
        String others = req.getParameter("others");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        int maxLength= 30;

        if (report.length() > maxLength || others.length() > maxLength) {
            printWriter.println("<html><body>");
            printWriter.println("工作简报或其他汇报中内容不应超过30字，请重新输入。");
            printWriter.println("</body></html>");
            return;
        }

        if (name.isEmpty()) {
            printWriter.println("<html><body>");
            printWriter.println("姓名不能为空，请重新输入。");
            printWriter.println("</body></html>");
            return;
        }


        String sql;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String databaseName = "info_demo";
            String userName = "root";
            String password = "asd19941016";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);

            PreparedStatement stmt;
            sql = "CREATE TABLE IF NOT EXISTS `person`" +
                    "(`id` INT UNSIGNED AUTO_INCREMENT," +
                    "`name` VARCHAR(40) NOT NULL," +
                    "`fever` VARCHAR(10) NOT NULL," +
                    "`contact` VARCHAR(10) NOT NULL," +
                    "`report` VARCHAR(200)," +
                    "`others` VARCHAR(200)," +
                    "`submission_date` TIMESTAMP," +
                    "PRIMARY KEY (`id`))" +
                    "ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt = conn.prepareStatement(sql);
            stmt.execute();

            sql = String.format("SELECT * FROM person " +
                    "WHERE name = '%s' AND " +
                    "to_days(submission_date) = to_days(now());",
                    name);
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
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
                                "submission_date = '%6$s'" +
                                "WHERE name = '%1s' AND " +
                                "to_days(submission_date) = to_days(now());",
                        name, fever, contact, report, others,
                        new java.sql.Timestamp(System.currentTimeMillis()));
            }

            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.execute();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        printWriter.println("<html><body>");
        printWriter.println(name + "，信息提交成功。");
        printWriter.println("</body></html>");
    }
}
