package servlet;

import java.io.IOException;
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
class GetSqlResult {
    List<List<String>> getSqlResult(String sql){

        List<List<String>> lists = new ArrayList<>();

        try {
            PreparedStatement stmt;

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

        return lists;
    }
}
