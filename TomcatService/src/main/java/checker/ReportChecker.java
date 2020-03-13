package checker;

/**
 * @author mengyuantan
 */
public class ReportChecker {
    private Parameters parameters = new Parameters();
    private boolean isPass = false;
    private String errorMsg;

    public void checkReport(String report){
        if (report.length() < parameters.getReportMinLength() ||
                report.length() > parameters.getReportMaxLength()) {
            isPass = false;
            errorMsg = String.format("工作简报中内容不应超过%s字，请重新输入。",
                    parameters.getReportMaxLength());
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
