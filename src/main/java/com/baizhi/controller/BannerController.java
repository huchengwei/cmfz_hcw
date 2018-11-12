package com.baizhi.controller;


import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/findByPage")
    public @ResponseBody Map<String,Object> findByPage(Integer page, Integer rows){
        List<Banner> banners = bannerService.findByPage(page, rows);
        Integer count = bannerService.findCount();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",count);
        map.put("rows",banners);
        return map;
    }

    @RequestMapping("/delete")
    public @ResponseBody void delete(String[] ids){
        bannerService.removeAll(ids);
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,Object> delete(Banner banner, MultipartFile imagePath, HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            String path = request.getSession().getServletContext().getRealPath("/banner/imgs");
            String image=UUID.randomUUID().toString()+imagePath.getOriginalFilename();
            imagePath.transferTo(new File(path,image));
            banner.setImgPath("/banner/imgs/"+image);
            bannerService.add(banner);
            map.put("success","添加成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("error","添加失败！！！");
        }
        return map;
    }
}
