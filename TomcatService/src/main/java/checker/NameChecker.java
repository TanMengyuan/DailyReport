package checker;

/**
 * @author mengyuantan
 */
public class NameChecker {
    private boolean isPass = false;
    private String errorMsg;

    public void checkName(String name){
        if (name.isEmpty()) {
            isPass = false;
            errorMsg = "姓名不能为空，请重新输入。";
        } else {
            isPass = true;
        }
    }

    public boolean isPass() {
        return isPass;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
