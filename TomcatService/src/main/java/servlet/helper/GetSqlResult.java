package servlet.helper;

import servlet.helper.DoSql;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mengyuantan
 */
public class GetSqlResult {
    public List<List<String>> getSqlResult(String sql){

        List<List<String>> lists = new ArrayList<>();

        try {
            DoSql doSql = new DoSql();
            ResultSet rs = doSql.doSql(sql);

            List<String> finishedName = new ArrayList<>();

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
                finishedName.add(name);
            }

            UserHelper userHelper = new UserHelper();
            List<String> verifiedNameList = userHelper.getVerifiedName();

            for (String name: verifiedNameList) {
                if (!finishedName.contains(name)) {
                    List<String> line = Arrays.asList(
                            name, "", "", "", "", "");
                    lists.add(line);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return lists;
    }
}
