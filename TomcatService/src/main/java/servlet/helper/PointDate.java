package servlet.helper;

/**
 * @author mengyuantan
 */
class PointDate {

    private String today;
    private String yesterday;
    private String tomorrow;

    String getToday() {
        return today;
    }

    void setToday(String today) {
        this.today = today;
    }

    String getYesterday() {
        return yesterday;
    }

    void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    String getTomorrow() {
        return tomorrow;
    }

    void setTomorrow(String tomorrow) {
        this.tomorrow = tomorrow;
    }
}
