package com.leezhong.conterller;

import com.github.pagehelper.PageInfo;
import com.leezhong.domain.Test;
import com.leezhong.dto.JsonRes;
import com.leezhong.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/toTest")
    public String toTest(){
        int id = 1;
        Test tetsById = testService.getTetsById(id);
//        System.out.println("test---"+tetsById.getName());
        return "test";
    }


    @RequestMapping("/testjson")
    @ResponseBody
    public JsonRes getJsonTest(Test test){
        System.out.println(test);

        return new JsonRes(1,"success");
    }

    @RequestMapping("/testip")
    @ResponseBody
    public JsonRes getIP(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        return new JsonRes(1,"success");
    }

    @RequestMapping("/testip2")
    @ResponseBody
    public String getIP2(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        return "13245646";
    }

}
