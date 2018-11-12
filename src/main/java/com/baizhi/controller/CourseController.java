package com.baizhi.controller;

import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findByPage")
    public @ResponseBody Map<String ,Object> findByPage(Integer page, Integer rows){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Course> courses = courseService.findByPage(page, rows);
        Integer count = courseService.findCount();
        map.put("total",count);
        map.put("rows",courses);
        return map;
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,Object> add(Course course){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            courseService.add(course);
            map.put("success","添加成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("error","添加失败！！！");
        }
        return map;
    }

    @RequestMapping("/delete")
    public @ResponseBody void delete(String[] ids){
        courseService.removeAll(ids);
    }
}
