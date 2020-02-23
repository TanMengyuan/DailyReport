package servlet;

import java.util.List;

class BuildResponse {

    StringBuilder buildResponse(String date, List<List<String>> lists) {
        StringBuilder result = new StringBuilder();
        result.append("<html><body><table border=\"1\" align=\"center\" " +
                "cellspacing=\"0\" width=\"85%\" style=\"table-layout: fixed\">");
        result.append(String.format("<tr><h3 align=\"center\">%s 汇总结果</h3></tr>",
                date));
        result.append("<tr>" +
                "<th>姓名</th>" +
                "<th>是否有发热症状</th>" +
                "<th>是否与疫区人员接触</th>" +
                "<th>工作简报</th>" +
                "<th>其他</th>" +
                "<th>提交（修改）时间</th>" +
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
                result.append("<td style=\"word-wrap: break-word\">").append(content).append("</td>");
            }
            result.append("</tr>");
        }
        result.append("</table></body></html>");
        return result;
    }
}
