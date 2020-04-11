package servlet.helper;

import org.apache.poi.ss.formula.ptg.AreaPtg;

import java.text.ParseException;
import java.util.List;

/**
 * @author mengyuantan
 */
public class BuildResponse {
    public StringBuilder buildReportResponse(String date, List<List<String>> lists) throws ParseException {
        DateHelper dateHelper = new DateHelper();
        PointDate pointDate = dateHelper.getPointDate(date);
        ScheduleDate scheduleDate = dateHelper.getScheduleDate(date);

        StringBuilder result = new StringBuilder();
        result.append("<html><body><table border=\"1\" align=\"center\" " +
                "cellspacing=\"0\" style=\"table-layout: fixed\">");
        result.append(String.format("<tr><h3 align=\"center\">%s 汇总结果</h3></tr>",
                date));
        result.append(String.format("<tr><h5 align=\"center\">今日负责人：%s</h3></tr>",
                scheduleDate.getPrincipal()));
        result.append("<tr>" +
                "<th width=\"5%\">姓名</th>" +
                "<th width=\"8%\">是否有发热症状</th>" +
                "<th width=\"8%\">是否与疫区人员接触</th>" +
                "<th width=\"15%\">工作简报</th>" +
                "<th width=\"15%\">其他</th>" +
                "<th width=\"8%\">提交（修改）时间</th>" +
                "</tr>");
        for (List<String> line: lists) {
            result.append("<tr>");
            for (int i = 0; i < line.size(); i++) {
                String content = line.get(i);
                if (i == 1 || i == 2) {
                    if ("是".equals(line.get(i))) {
                        content = "<font color=red>" + content + "</font>";
                    }
                }
                result.append("<td style=\"word-wrap: break-word\">")
                        .append(content).append("</td>");
            }
            result.append("</tr>");
        }
        result.append("</table>");

        result.append(String.format(
                "<form role=\"form\" method=\"post\" action=\"HistoryReport\">" +
                        "<p align=\"center\" style=\"height: 40px\">" +
                        "<button type=\"submit\" name=\"date\" value=\"%1$s\">前一日</button>" +
                        "&nbsp;&nbsp;" +
                        "<button type=\"submit\" name=\"date\" value=\"%2$s\">后一日</button>" +
                        "</p></form>", pointDate.getYesterday(), pointDate.getTomorrow()));
        result.append(String.format(
                "<form role=\"form\" method=\"post\" action=\"Export\" target=\"_blank\">" +
                "<p align=\"center\" style=\"height: 40px\">" +
                "<button type=\"submit\" name=\"date\" value=\"%s\">导出为Excel</button>" +
                "</p></form>" +
                "</body></html>", pointDate.getToday()));
        return result;
    }

    public StringBuilder buildScheduleResponse(List<PersonInfo> list) {
        StringBuilder result = new StringBuilder();
        result.append("<html><body><table border=\"1\" " +
                "cellspacing=\"0\" style=\"table-layout: fixed\">");
        result.append("<tr><h3>每日收集信息负责人</h3></tr>");
        result.append("<tr>" +
                "<th width=\"50%\">时间</th>" +
                "<th width=\"50%\">负责人</th>" +
                "</tr>");
        for (PersonInfo personInfo: list) {
            result.append(String.format(
                    "<tr><td style=\"word-wrap: break-word\">%1$s</td>" +
                            "<td style=\"word-wrap: break-word\">%2$s</td></tr>",
                    personInfo.getDate(), personInfo.getName()));
        }
        result.append("</table></body></html>");

        return result;
    }

}
