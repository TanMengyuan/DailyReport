package servlet.helper;

import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author mengyuantan
 */
public class DoSql {
    public ResultSet doSql(String sql) {
        ResultSet rs = null;

        try {
            PreparedStatement stmt;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + Config.databaseName +
                            "?useUnicode=true&" +
                            "characterEncoding=utf8&" +
                            "serverTimezone=Asia/Shanghai",
                    Config.userName, Config.password);
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            rs = stmt.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void doSqlBatch(List<String> sqlList) {
        try {
            PreparedStatement stmt;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + Config.databaseName +
                            "?useUnicode=true&" +
                            "characterEncoding=utf8&" +
                            "serverTimezone=Asia/Shanghai",
                    Config.userName, Config.password);
            for (String sql : sqlList) {
                stmt = conn.prepareStatement(sql);
                stmt.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
