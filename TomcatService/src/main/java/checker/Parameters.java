package checker;

/**
 * @author mengyuantan
 */
class Parameters {
    private int reportMaxLength = 50;
    private int reportMinLength = 0;
    private int otherMaxLength = 50;
    private int otherMinLength = 0;

    int getReportMaxLength() {
        return reportMaxLength;
    }

    public void setReportMaxLength(int reportMaxLength) {
        this.reportMaxLength = reportMaxLength;
    }

    int getReportMinLength() {
        return reportMinLength;
    }

    public void setReportMinLength(int reportMinLength) {
        this.reportMinLength = reportMinLength;
    }

    int getOtherMaxLength() {
        return otherMaxLength;
    }

    public void setOtherMaxLength(int otherMaxLength) {
        this.otherMaxLength = otherMaxLength;
    }

    int getOtherMinLength() {
        return otherMinLength;
    }

    public void setOtherMinLength(int otherMinLength) {
        this.otherMinLength = otherMinLength;
    }
}
