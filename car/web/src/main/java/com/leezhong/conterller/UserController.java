package com.leezhong.conterller;

import com.leezhong.domain.User;
import com.leezhong.dto.JsonRes;
import com.leezhong.dto.UserLoginInfo;
import com.leezhong.service.UserService;
import com.leezhong.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(AuthenticationException.class)
    public @ResponseBody JsonRes handlShiroExecption(AuthenticationException e){
        JsonRes jsonRes = new JsonRes(-1,e.getMessage(),"账号或密码错误");
        return jsonRes;
    }

    @RequestMapping("/login/login.action")
    public @ResponseBody JsonRes login(UserLoginInfo info){
        if(StringUtils.isBlank(info.getUsername())||StringUtils.isBlank(info.getPassword())){
            throw new AuthenticationException("用户名或密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(info.getUsername(), info.getPassword());
        if(info.getRememberMe()!=null){
           token.setRememberMe(info.getRememberMe());
        }
        subject.login(token);
        return new JsonRes(1,"操作成功",info.getBackurl());
    }
    @RequestMapping("/login/logout.action")
    public @ResponseBody JsonRes logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }
        return new JsonRes(1,"操作成功");
    }


    @RequestMapping("/login/reg.action")
    @ResponseBody
    public JsonRes reg(User user, HttpServletRequest request){
        JsonRes res;
        if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
            res = new JsonRes(-1,"操作失败:账号或密码不能为空");
            return res;
        }
        int userId = userService.createUser(user);
        if(userId<=0){
            res = new JsonRes(-1,"操作失败:数据库异常");
        }
        return new JsonRes(1,"操作成功");
    }


}
