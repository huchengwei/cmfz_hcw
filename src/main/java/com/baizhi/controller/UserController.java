package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(User user){
        userService.add(user);
        return "";
    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        User user1 = userService.findOne(user);
        if(user1!=null){
            request.getSession().setAttribute("user",user1);
            return "成功";
        }else {
            return "失败";
        }
    }

    @RequestMapping("/update")
    public String update(User user,HttpServletRequest request){
        User user1=(User)request.getSession().getAttribute("user");
        user.setId(user1.getId());
        userService.motify(user);
        return "";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(String newPassword,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        user.setPassword(newPassword);
        userService.motifyPassword(user);
        return "";
    }
}
