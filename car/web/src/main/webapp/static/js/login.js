$(function() {
	// Waves初始化
	Waves.displayEffect();
	// 输入框获取焦点后出现下划线
	$('.form-control').focus(function() {
		$(this).parent().addClass('fg-toggled');
	}).blur(function() {
		$(this).parent().removeClass('fg-toggled');
	});
});
Checkbix.init();
$(function() {
	console.log('--------登录-------');
	// 点击登录按钮
	$('#login-bt').click(function() {
		console.log('点击登录按钮')
		login();
	});
	// 回车事件
	$('#username, #password').keypress(function (event) {
		if (13 == event.keyCode) {
			login();
		}
	});
});
// 登录
function login() {
    console.log('触发登录事件')
	$.ajax({
		url: '/user/login/login.action',
		type: 'POST',
		data: {
			username: $('#username').val(),
			password: $('#password').val(),
			rememberMe: $('#rememberMe').is(':checked'),
			backurl: '/jsp/index.jsp'
		},
		beforeSend: function() {

		},
		success: function(json){
			console.log('登录成功',json)
			if (json.resCode == 1) {
				location.href = json.data;
			} else {
				alert(json.data);
				// if (10101 == json.code) {
				// 	$('#username').focus();
				// }
				// if (10102 == json.code) {
				// 	$('#password').focus();
				// }
                $('#password').focus();
			}
		},
		error: function(error){
            console.log('登录失败')
			console.log(error);
		}
	});
}