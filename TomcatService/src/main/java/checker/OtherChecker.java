package checker;

/**
 * @author mengyuantan
 */
public class OtherChecker {
    private Parameters parameters = new Parameters();
    private boolean isPass = false;
    private String errorMsg;

    public void checkOther(String other){
        if (other.length() < parameters.getOtherMinLength() ||
                other.length() > parameters.getOtherMaxLength()) {
            isPass = false;
            errorMsg = String.format("其他汇报中内容不应超过%s字，请重新输入。",
                    parameters.getOtherMaxLength());
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
