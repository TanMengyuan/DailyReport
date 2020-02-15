package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String databaseName = "info";
            String userName = "root";
            String password = "asd19941016";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);

            PreparedStatement stmt;

            sql = "SELECT * FROM person WHERE to_days(submission_date) = to_days(now());";

            stmt = conn.prepareStatement(sql);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                String name = rs.getString("name");
                String fever = rs.getString("fever");
                String contact = rs.getString("contact");
                String report = rs.getString("report");
                String others = rs.getString("others");
                String submissionDate = rs.getString("submission_date");
                List<String> line = Arrays.asList(name, fever, contact, report, others, submissionDate);
                lists.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        printWriter.println("<html><body><table width:'100%'>");
        printWriter.println("<tr>" +
                "<th>姓名</th>" +
                "<th>是否有发热症状</th>" +
                "<th>是否与疫区人员接触</th>" +
                "<th>工作简报</th>" +
                "<th>其他</th>" +
                "<th>提交时间</th>" +
                "</tr>");
        for (List<String> line: lists) {
            printWriter.println("<tr>");
            for (String s: line) {
                printWriter.println("<td word-break:break-all>" + s + "</td>");
            }
            printWriter.println("</tr>");
        }
        printWriter.println("</table></body></html>");
    }
}
