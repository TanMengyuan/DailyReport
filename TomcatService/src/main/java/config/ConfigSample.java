package config;

import java.util.Arrays;
import java.util.List;

/**
 * @author mengyuantan
 */
public class ConfigSample {
    public static String databaseName = "database_name";
    public static String userName = "your_username";
    public static String password = "your_password";
    public static String dateFormat = "yyyy-MM-dd";
    public final static List NAME_LIST =
            Arrays.asList(
                    "aaa", "bbb", "ccc", "ddd"
            );
    public static String startTime = "2020-04-09";
    public static int shownScheduleTimes = 2;
    public static String loginUser = "admin_user_name";
    public static String loginPassword = "admin_user_password";
    private static String loginScript = "/init?user=admin_user_name&password=admin_user_password";
}
