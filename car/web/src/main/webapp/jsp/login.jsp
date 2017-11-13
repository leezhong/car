﻿<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>登录页</title>

	<link href="../static/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="../static/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="../static/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="../static/plugins/checkbix/css/checkbix.min.css" rel="stylesheet"/>
	<link href="../static/css/login.css" rel="stylesheet"/>
</head>
<body>
<shiro:guest>
	<div id="login-window">
		<div class="input-group m-b-20">
			<span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
			<div class="fg-line">
				<input id="username" type="text" class="form-control" name="username" placeholder="帐号" required autofocus value="admin">
			</div>
		</div>
		<div class="input-group m-b-20">
			<span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
			<div class="fg-line">
				<input id="password" type="password" class="form-control" name="password" placeholder="密码" required value="123456">
			</div>
		</div>
		<div class="clearfix">
		</div>
		<div class="checkbox">
			<input id="rememberMe" type="checkbox" class="checkbix" data-text="自动登录" name="rememberMe">
		</div>
		<a id="login-bt" href="javascript:;" class="waves-effect waves-button waves-float"><i class="zmdi zmdi-arrow-forward"></i></a>
	</div>
</shiro:guest>
<shiro:user>
	您好,<shiro:principal property="username"/>,您已登录.
</shiro:user>
<script src="../static/plugins/jquery.1.12.4.min.js"></script>
<script src="../static/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="../static/plugins/waves-0.7.5/waves.min.js"></script>
<script src="../static/plugins/checkbix/js/checkbix.min.js"></script>

<script src="../static/js/login.js"></script>
<script type="text/javascript">
	Checkbix.init();
</script>
</body>
</html>