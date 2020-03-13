package servlet;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import entity.BoldStyle;
import entity.PersonEntity;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mengyuantan
 */
@WebServlet(name = "ExportServlet", urlPatterns = {"/Export"})
public class ExportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        String date;

        if (req.getParameter("date") != null) {
            date = new String(req.getParameter("date").
                    getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date currentTime = new Date(System.currentTimeMillis());
            date = formatter.format(currentTime);
        }

        String sql;
        List<List<String>> lists = new ArrayList<>();

        sql = String.format("SELECT * FROM person " +
                "WHERE to_days(submission_date) = to_days('%s');", date);

        try {
            GetSqlResult getSqlResult = new GetSqlResult();
            lists = getSqlResult.getSqlResult(sql);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        List<PersonEntity> personEntityList = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            List<String> line = lists.get(i);
            PersonEntity personEntity = new PersonEntity();
            personEntity.setId(i);
            personEntity.setName(line.get(0));
            personEntity.setIsFever(line.get(1));
            personEntity.setIsContact(line.get(2));
            personEntity.setReport(line.get(3));
            personEntity.setOthers(line.get(4));
            personEntityList.add(personEntity);
        }

        String fileName = String.format("%s汇总信息.xlsx", date);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        String title = String.format("%s 汇总信息", date);

        ExportParams exportParams = new ExportParams(title, "sheet1",
                ExcelType.XSSF);
        exportParams.setHeaderHeight(12D);
        exportParams.setStyle(BoldStyle.class);
        Workbook workbook = ExcelExportUtil.exportExcel(
                exportParams,
                PersonEntity.class,
                personEntityList);

        try {
            OutputStream out = resp.getOutputStream();
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
}
