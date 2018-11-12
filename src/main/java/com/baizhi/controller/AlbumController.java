package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/findByPage")
    public @ResponseBody Map<String,Object> findByPage(Integer page,Integer rows){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Album> albums = albumService.findByPage(page, rows);
        Integer count = albumService.findCount();
        map.put("total",count);
        map.put("rows",albums);
        return map;
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,Object> add(HttpServletRequest request, MultipartFile pic,Album album){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            String path = request.getSession().getServletContext().getRealPath("/album/imgs");
            String coverPic= UUID.randomUUID().toString()+pic.getOriginalFilename();
            pic.transferTo(new File(path,coverPic));
            album.setCoverPic("/album/imgs/"+coverPic);
            albumService.add(album);
            map.put("success","添加成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("error","添加失败！！！");
        }
        return map;
    }
}
