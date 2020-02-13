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
    <form>
      姓名：
      <br/>
      <label><input type="text" name="name"></label>
      <br/>
      是否有发热症状:
      <br/>
      <label>
        <input type="radio" value="是" name="fever"/> 是
        <input type="radio" value="否" name="fever" checked="checked"/> 否
      </label>
      <br/>
      是否与疫区人员接触:
      <br/>
      <label>
        <input type="radio" value="是" name="contact"/> 是
        <input type="radio" value="否" name="contact" checked="checked"/> 否
      </label>
      <br/>
      工作简报：
      <br/>
      <label><input type="text" name="report"></label>
      <br/>
      其他：
      <br/>
      <label><input type="text" name="others"></label>
      <br/>
      <button type="submit" name="submit">提交</button>
      <br/>
    </form>
  </body>
</html>
