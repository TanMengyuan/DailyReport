package checker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import servlet.helper.DoSql;
import servlet.helper.UserHelper;

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
            UserHelper userHelper = new UserHelper();
            List<String> verifiedNameList = userHelper.getVerifiedName();
            if (verifiedNameList.contains(name)) {
                isPass = true;
            } else {
                isPass = false;
                errorMsg = ErrorMessage.notVerifiedNameError;
            }
        }
    }


    public boolean isPass() {
        return isPass;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
