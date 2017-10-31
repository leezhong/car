<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/31
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <table border="1">
        <form id="regForm" method="post">
            <tr>
                <td colspan="2">注册用户</td>
            </tr>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="button" id="_submit" onclick="_subForm()" value="注册"/>&nbsp;<input type="reset" value="重置"/></td>
            </tr>
        </form>
    </table>
</body>

<script src="/static/js/jquery-3.2.1.min.js"></script>
<script>
    function _subForm(){
        var _formData =  new FormData(document.getElementById("regForm"));
        $.ajax({
            url:"/login/reg",
            type:"post",
            data:_formData,
            processData:false,
            contentType:false,
            success:function(data){
                console.log('success')
                console.log(data)
            },
            err:function(data){
                console.log('err')
                console.log(data)
            }
        })
    }
</script>
</html>
