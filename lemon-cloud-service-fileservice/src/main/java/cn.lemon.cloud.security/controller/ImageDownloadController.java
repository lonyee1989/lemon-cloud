package cn.lemon.cloud.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lonyee on 2017/4/12.
 */
@RequestMapping("/img")
@RestController
public class ImageDownloadController {

    /**
     * 查看源图片文件
     */
    @RequestMapping(name = "/{fileName}.{ext}", method = { RequestMethod.GET })
    public void view(HttpServletRequest request, HttpServletResponse response, String fileName, String ext) {

    }

    /**
     * 查看等比缩放图片
     */
    @RequestMapping(name = "/{fileName}.{ext}_{s:0-9}", method = { RequestMethod.GET })
    public void zoom(HttpServletRequest request, HttpServletResponse response, String fileName, String ext, Integer s) {

    }

    /**
     * 查看缩放裁剪图片，依据最小边裁剪
     */
    @RequestMapping(name = "/{fileName}.{ext}_{w:0-9}x{h:0-9}", method = { RequestMethod.GET })
    public void crop(HttpServletRequest request, HttpServletResponse response, String fileName, String ext, Integer w, Integer h) {

    }
}
