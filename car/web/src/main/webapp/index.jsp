<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <shiro:user>
        欢迎,<shiro:principal/> &nbsp;&nbsp;<a href="/logout">点击退出</a>
    </shiro:user>

    <shiro:guest>
        <a href="/login/login.jsp">点击登录</a>
    </shiro:guest>
</body>
</html>