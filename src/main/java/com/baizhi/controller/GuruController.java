package com.baizhi.controller;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;

    @RequestMapping("/findByPage")
    public @ResponseBody Map<String ,Object> findByPage(Integer page,Integer rows){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Guru> gurus = guruService.findByPage(page, rows);
        Integer count = guruService.findCount();
        map.put("total",count);
        map.put("rows",gurus);
        return map;
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,Object> add(Guru guru, MultipartFile pic, HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            String path = request.getSession().getServletContext().getRealPath("/guru/imgs");
            String img= UUID.randomUUID().toString()+pic.getOriginalFilename();
            pic.transferTo(new File(path,img));
            guru.setHeadPic("/guru/imgs/"+img);
            guruService.add(guru);
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
        guruService.removeAll(ids);
    }
}
