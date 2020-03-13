package checker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import config.Config;
import servlet.DoSql;

/**
 * @author mengyuantan
 */
public class NameChecker {
    private boolean isPass = false;
    private String errorMsg;

    public void checkName(String name){
        if (name.isEmpty()) {
            isPass = false;
            errorMsg = ErrorMessage.noNameError;
        } else {
            List<String> verifiedNameList = getVerifiedName();
            if (verifiedNameList.contains(name)) {
                isPass = true;
            } else {
                isPass = false;
                errorMsg = ErrorMessage.notVerifiedNameError;
            }
        }
    }

    private List<String> getVerifiedName() {
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

    public boolean isPass() {
        return isPass;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
