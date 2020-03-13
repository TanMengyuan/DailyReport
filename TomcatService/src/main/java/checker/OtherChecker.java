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
            errorMsg = String.format(ErrorMessage.otherError,
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
