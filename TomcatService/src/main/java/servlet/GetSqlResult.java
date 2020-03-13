package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import config.Config;

/**
 * @author mengyuantan
 */
public class GetSqlResult {
    public List<List<String>> getSqlResult(String sql){

        List<List<String>> lists = new ArrayList<>();

        try {
            DoSql doSql = new DoSql();
            ResultSet rs = doSql.doSql(sql);

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
