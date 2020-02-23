package servlet;


import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mengyuantan
 */
public class ExportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date(System.currentTimeMillis());
        String date = formatter.format(currentTime);

        String sql;
        List<List<String>> lists = new ArrayList<>();

        sql = "SELECT * FROM person WHERE to_days(submission_date) = to_days(now());";

        try {
            GetSqlResult getSqlResult = new GetSqlResult();
            lists = getSqlResult.getSqlResult(sql);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        if (lists.size() > 0) {
            //2. 创建一个excel工作簿
            String fileName = String.format("%s汇总信息.xlsx", date);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            XSSFWorkbook wb = new XSSFWorkbook();
            //3. 创建sheet页,设置表格样式
            XSSFSheet sheet = wb.createSheet("sheet1");
            sheet.setDefaultRowHeight((short) (2 * 256));
            sheet.setColumnWidth(0, 2500);
            sheet.setColumnWidth(1, 2500);
            sheet.setColumnWidth(2, 2500);
            sheet.setColumnWidth(3, 2500);
            sheet.setColumnWidth(4, 2500);
            XSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 16);
            //4. 在sheet中创建 行/单元格,向单元格中添加数据
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(String.format("%s汇总信息", date));
            CellRangeAddress region = new CellRangeAddress(0, 0, 0, 4);
            sheet.addMergedRegion(region);

            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue("姓名");
            cell = row.createCell(1);
            cell.setCellValue("是否有发热症状");
            cell = row.createCell(2);
            cell.setCellValue("是否与疫区人员接触");
            cell = row.createCell(3);
            cell.setCellValue("工作简报");
            cell = row.createCell(4);
            cell.setCellValue("其他");
            XSSFRow rows;
            XSSFCell cells;
            /*插入数据*/
            for (int i = 0; i < lists.size(); i++) {
                List<String> line = lists.get(i);
                rows = sheet.createRow(i + 2);
                for (int j = 0; j < line.size() - 1; j ++) {
                    cells = rows.createCell(j);
                    cells.setCellValue(line.get(j));
                }
            }
            //5. 控制台写入数据
            try {
                OutputStream out = resp.getOutputStream();
                wb.write(out);
                out.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
}
