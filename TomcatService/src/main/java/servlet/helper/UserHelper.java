package servlet.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengyuantan
 */
public class UserHelper {

    public List<String> getVerifiedName() {
        List<String> verifiedNameList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM verified_name;";
            DoSql doSql = new DoSql();
            ResultSet rs = doSql.doSql(sql);

            while (rs.next()) {
                String verifiedName = rs.getString("verified_name");
                verifiedNameList.add(verifiedName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return verifiedNameList;
    }
}
