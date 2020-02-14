<%--
  Created by IntelliJ IDEA.
  User: mengyuantan
  Date: 2020/2/13
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>每日信息汇报</title>
  </head>
  <body>
  <h1>每日信息汇报</h1>
  <form action="SubmitReport" method="post">
    <table>
        <tr>
            <td>姓名：</td>
            <td><label>
              <input type="text" name="name">
            </label></td>
        </tr>
        <tr>
            <td>是否有发热症状:</td>
            <td><label>
              <input type="radio" value="是" name="fever"/> 是
              <input type="radio" value="否" name="fever" checked="checked"/> 否
            </label></td>
        </tr>
        <tr>
            <td>是否与疫区人员接触:</td>
            <td><label>
              <input type="radio" value="是" name="contact"/> 是
              <input type="radio" value="否" name="contact" checked="checked"/> 否
            </label></td>
        </tr>
        <tr>
            <td>工作简报：</td>
            <td><label>
              <input type="text" name="report">
            </label></td>
        </tr>
        <tr>
            <td>其他：</td>
            <td><label>
              <input type="text" name="others">
            </label></td>
        </tr>
        <tr>
            <td>
                <input type="submit" style="width:120px;height:80px" value="提交">
            </td>
        </tr>
    </table>
  </form>
  <form action="TodayReport" method="post">
      <tr>
          <td>
              <input type="submit" style="width:120px;height:80px" value="获取当日汇总信息">
          </td>
      </tr>
  </form>
  </body>
</html>
