
$(function() {
    console.log('--------登录-------');
    // 点击退出登录
    $('#logout_bt').click(function() {
        console.log('点击退出登录')
        logout();
    });
});
function logout(){
   if( confirm('是否确认退出登录?'))(
       $.ajax({
           url: '/user/login/logout.action',
           type: 'POST',
           success:function(){
               alert('退出成功!')
               location.href="/jsp/index.jsp";
               return;
           },
           error:function(){
               alert('操作失败!');
               return;
           }
       })
   )

}

