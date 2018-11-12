package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/findOne")
    public String findOne(HttpServletRequest request,Admin admin,String enCode){
        Object validationCode = request.getSession().getAttribute("validationCode");
        if(validationCode.equals(enCode)){
            Admin admin1 = adminService.findOne(admin);
            if(admin1!=null){
                request.getSession().setAttribute("admin",admin1);
                return "redirect:/main/main.jsp";
            }else{
                return "redirect:/login.jsp";
            }
        }else{
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login.jsp";
    }

    @RequestMapping("/updatePassword")
    public @ResponseBody Map<String,Object> updatePassword(HttpServletRequest request, String oldPassword, String newPassword){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            Admin admin1=(Admin) request.getSession().getAttribute("admin");
            if(admin1.getPassword().equals(oldPassword)){
                admin1.setPassword(newPassword);
                adminService.motify(admin1);
                map.put("success","修改成功！！！");
            }else{
                map.put("success","旧密码不正确！！！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("error","修改失败");
        }

        return map;
    }
}
