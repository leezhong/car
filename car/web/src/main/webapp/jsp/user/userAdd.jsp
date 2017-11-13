<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/13
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增用户</title>
    <script src="/static/plugins/bootstrap-3.3.0/css/bootstrap.min.css"></script>
    <script src="/static/plugins/jquery.1.12.4.min.js"></script>
</head>
<body>
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" placeholder="请输入用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" placeholder="请输入密码">
            </div>
        </div>
        <div class="form-group">
            <label for="realname" class="col-sm-2 control-label">真实姓名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="realname" placeholder="请输入真实姓名">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" id="reg_bt" class="btn btn-default">添加用户</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="reset"  class="btn btn-default">重置信息</button>
            </div>
        </div>
    </form>
</body>
</html>
<script>
    $(document).ready(function(){
        $('#reg_bt').click(function () {
            $.ajax({
                url:'/user/login/reg.action',
                type:'POST',
                data:{
                    username:$("#username").val(),
                    password:$("#password").val(),
                },
                success:function(data){
                    console.log('注册返回成功')
                    if(data.resCode==1){
                        alert('新增用户成功!')
                        location.reload();
                        return;
                    }
                },
                error:function(data){
                    console.log('访问注册接口失败!')
                    return;
                }

            })
        })
    })
</script>
