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

    int getReportMinLength() {
        return reportMinLength;
    }


    int getOtherMaxLength() {
        return otherMaxLength;
    }


    int getOtherMinLength() {
        return otherMinLength;
    }
}
