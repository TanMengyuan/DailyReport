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
              src="My97DatePicker/WdatePicker.js"></script>
      <title>每日信息汇报</title>
  </head>

  <body>
  <div style="text-align: center;">
      <h1 align="center">每日信息汇报 v1.2</h1>
      <form action="SubmitReport" method="post">
        <table align="center" width="85%">
            <tr>
                <td align="right" width="45%">姓名：</td>
                <td width="55%"><label>
                  <input type="text" name="name">
                </label></td>
            </tr>
            <tr>
                <td align="right" width="45%">是否有发热症状：</td>
                <td width="55%"><label>
                  <input type="radio" value="是" name="fever"/> 是
                  <input type="radio" value="否" name="fever" checked="checked"/> 否
                </label></td>
            </tr>
            <tr>
                <td align="right" width="45%">是否与疫区人员接触：</td>
                <td width="55%"><label>
                  <input type="radio" value="是" name="contact"/> 是
                  <input type="radio" value="否" name="contact" checked="checked"/> 否
                </label></td>
            </tr>
            <tr>
                <td align="right" width="45%">工作简报：</td>
                <td width="55%"><label>
                  <input type="text" name="report">
                </label></td>
            </tr>
            <tr>
                <td align="right" width="45%">其他：</td>
                <td width="55%"><label>
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

      <h3>获取当日信息</h3>

      <form action="TodayReport" method="post">
          <p align="center" style="height: 40px">
              <input type="submit" value="获取当日汇总信息">
          </p>
      </form>

      <h3>根据日期查询历史信息</h3>

      <form action="HistoryReport" method="post">
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

  </div>
  </body>

</html>
