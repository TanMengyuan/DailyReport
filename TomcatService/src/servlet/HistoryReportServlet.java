package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
@WebServlet(name = "HistoryReportServlet", urlPatterns = {"/HistoryReport"})
public class HistoryReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // todo: 固定表格的宽度
        req.setCharacterEncoding("UTF-8");
        String date = new String(req.getParameter("date").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

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

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String databaseName = "info";
            String userName = "root";
            String password = "asd19941016";
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + databaseName +
                            "?useUnicode=true&" +
                            "characterEncoding=utf8&" +
                            "serverTimezone=Asia/Shanghai",
                    userName, password);

            PreparedStatement stmt;

            sql = String.format("SELECT * FROM person " +
                    "WHERE to_days(submission_date) = to_days('%s');", date);

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
                List<String> line = Arrays.asList(
                        name, fever, contact, report, others, submissionDate);
                lists.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        StringBuilder result = new StringBuilder();
        result.append("<html><body><center><table border=\"1\" cellspacing=\"0\" width:'100%'>");
        result.append(String.format("<h3>%s 汇总结果</h3>", date));
        result.append("<tr>" +
                "<th>姓名</th>" +
                "<th>是否有发热症状</th>" +
                "<th>是否与疫区人员接触</th>" +
                "<th>工作简报</th>" +
                "<th>其他</th>" +
                "<th>提交（修改）时间</th>" +
                "</tr>");
        for (List<String> line: lists) {
            result.append("<tr>");
            for (int i = 0; i < line.size(); i++) {
                String content = line.get(i);
                if (i == 1 || i == 2) {
                    if ("是".equals(line.get(i))) {
                        content = "<font color=red>" + content + "</font>";
                    }
                }
                result.append("<td word-break:break-all>").append(content).append("</td>");
            }
            result.append("</tr>");
        }
        result.append("</table></center></body></html>");
        printWriter.println(result);
    }
}
