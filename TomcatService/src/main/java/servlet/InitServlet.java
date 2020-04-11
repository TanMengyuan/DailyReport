package servlet;

import config.Config;
import servlet.helper.DoSql;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengyuantan
 */
@WebServlet(name = "InitServlet", urlPatterns = {"/init"})
public class InitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter("user");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();

        if (!user.equals(Config.loginUser) || !password.equals(Config.loginPassword)) {
            printWriter.println("<html><body>");
            printWriter.println("用户名或密码错误。");
            printWriter.println("</body></html>");
            return;
        }

        String sql;
        DoSql doSql = new DoSql();

        List<String> sqlList = new ArrayList<>();
        sqlList.add(String.format("TRUNCATE TABLE `%1$s`.`schedule`", Config.databaseName));
        for (int order = 0; order < Config.NAME_LIST.size(); order++) {
            sql = String.format(
                    "INSERT IGNORE INTO `%1$s`.`schedule` (`order`, `name`) " +
                            "VALUES ('%2$s', '%3$s');",
                    Config.databaseName, order + 1, Config.NAME_LIST.get(order));
            sqlList.add(sql);
        }
        doSql.doSqlBatch(sqlList);

        printWriter.println("<html><body>");
        printWriter.println("初始化完成。");
        printWriter.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
}
