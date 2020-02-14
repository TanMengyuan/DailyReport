package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author mengyuantan
 */
@WebServlet(name = "TodayReportServlet", urlPatterns = {"/TodayReport"})
public class TodayReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><body>");
        printWriter.println("Today Report.");
        printWriter.println("</body></html>");
    }
}
