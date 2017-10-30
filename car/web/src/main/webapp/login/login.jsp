<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/30
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form  action="/login" method="post">
        登录<br/>
       用户名: <input type="text" name="username"/><br/>
       密码: <input type="text" name="password"/><br/>
        <input type="submit" value="点击登录"/>
    </form>
</body>
</html>
