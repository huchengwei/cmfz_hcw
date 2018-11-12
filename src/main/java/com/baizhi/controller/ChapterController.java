package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/add")
    public @ResponseBody Map<String,Object> add(HttpServletRequest request, Chapter chapter, MultipartFile pic){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            String path = request.getSession().getServletContext().getRealPath("/album/files");
            String downPath= UUID.randomUUID().toString()+pic.getOriginalFilename();
            File file=new File(path,downPath);
            pic.transferTo(file);

            Double size =(double)pic.getSize();

            MP3File mp3 = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader=(MP3AudioHeader) mp3.getAudioHeader();
            int length = audioHeader.getTrackLength();

            chapter.setDownPath("/album/files/"+downPath);
            chapter.setBulk(size);
            chapter.setDuration(length);
            chapterService.add(chapter);
            map.put("success","添加成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("error","添加失败！！！");
        }
        return map;
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response, String fileName,String openStyle){
        FileInputStream si=null;
        ServletOutputStream os=null;
        try {
            String[] f=fileName.split("/");
            String name=f[3];
            String path = request.getSession().getServletContext().getRealPath("/album/files");
            si= new FileInputStream(new File(path,name));
            response.setHeader("content-disposition", openStyle+";fileName="+URLEncoder.encode(name,"UTF-8"));
            os = response.getOutputStream();
            IOUtils.copy(si, os);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(si);
            IOUtils.closeQuietly(os);
        }
    }
}
