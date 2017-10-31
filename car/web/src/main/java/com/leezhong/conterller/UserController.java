package com.leezhong.conterller;

import com.leezhong.domain.User;
import com.leezhong.dto.JsonRes;
import com.leezhong.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(AuthenticationException.class)
    public @ResponseBody JsonRes handlShiroExecption(AuthenticationException e){
        JsonRes jsonRes = new JsonRes(-1,e.getMessage());
        return jsonRes;
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }
        return "login/login";
    }


    @RequestMapping("/reg")
    @ResponseBody
    public JsonRes reg(User user, HttpServletRequest request){

        System.out.println(user);

        System.out.println(request.getParameter("username"));
        JsonRes jsonRes = new JsonRes();
        return jsonRes;
    }


}
