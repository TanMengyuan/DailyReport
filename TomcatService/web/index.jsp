<%--
  Created by IntelliJ IDEA.
  User: mengyuantan
  Date: 2020/2/13
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

  <head>
      <script language="javascript" type="text/javascript"
              src="My97DatePicker/WdatePicker.js">
      </script>
      <title>每日信息汇报</title>
  </head>

  <body>
  <div style="text-align: center;">
      <h1 align="center">每日信息汇报 v1.5.2</h1>
      <form action="SubmitReport" method="post">
        <table align="center">
            <tr>
                <td align="right" width="50%">姓名：</td>
                <td width="50%"><label>
                  <input type="text" name="name">
                </label></td>
            </tr>
            <tr>
                <td align="right" width="50%">是否有发热症状：</td>
                <td width="50%"><label>
                  <input type="radio" value="是" name="fever"/> 是
                  <input type="radio" value="否" name="fever" checked="checked"/> 否
                </label></td>
            </tr>
            <tr>
                <td align="right" width="50%">是否与疫区人员接触：</td>
                <td width="50%"><label>
                  <input type="radio" value="是" name="contact"/> 是
                  <input type="radio" value="否" name="contact" checked="checked"/> 否
                </label></td>
            </tr>
            <tr>
                <td align="right" width="50%">工作简报：</td>
                <td width="50%"><label>
                  <input type="text" name="report">
                </label></td>
            </tr>
            <tr>
                <td align="right" width="50%">其他：</td>
                <td width="50%"><label>
                  <input type="text" name="others">
                </label></td>
            </tr>
            <tr style="height: 40px">
                <td align="center" colspan="2" style="height: 40px">
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>
      </form>

      <h3>获取当日信息及安排表</h3>

      <form action="TodayReport" method="get" target="_blank">
          <p align="center" style="height: 40px">
              <input type="submit" value="获取当日汇总信息">
          </p>
      </form>

      <form action="Schedule" method="get" target="_blank">
          <p align="center" style="height: 40px">
              <input type="submit" value="获取每日汇总安排表">
          </p>
      </form>

      <form role="form" method="get" action="Export" target="_blank">
          <p align="center" style="height: 40px">
              <input type="submit" value="将当日信息导出至Excel">
          </p>
      </form>


      <h3>查询历史信息</h3>

      <form action="HistoryReport" method="post" target="_blank">
          <table align="center" width="85%">
              <tr>
                  <label>
                      <input class="Wdate" type="text" name="date" readonly
                             onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                  </label>
                  <span>  </span>
              </tr>
              <tr>
                  <input type="submit" value="查询">
              </tr>
          </table>
      </form>

      <br>
      <br>

      <span style="color: dimgray; ">
          <p style="align-content: center">
              Power by
              <a href="https://github.com/TanMengyuan" target="_blank">
                  TanMengyuan
              </a> <br>
            Source Code:
              <a href="https://github.com/TanMengyuan/DailyReport" target="_blank">
                  https://github.com/TanMengyuan/DailyReport
              </a>
          </p>
      </span>

  </div>
  </body>

</html>
