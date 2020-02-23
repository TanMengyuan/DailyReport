import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import servlet.GetSqlResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {

    @Test
    public void test() {

        String sql;
        List<List<String>> lists = new ArrayList<>();

        sql = "SELECT * FROM person WHERE to_days(submission_date) = to_days(now());";

        try {
            GetSqlResult getSqlResult = new GetSqlResult();
            lists = getSqlResult.getSqlResult(sql);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("sheet1");
        System.out.println("test workbook");
    }

}
