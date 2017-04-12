package cn.lemon.cloud.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lonyee on 2017/4/12.
 */
@RequestMapping("/img")
@RestController
public class ImageUploadController {

    /**
     * 上传图片文件
     */
    @RequestMapping(name = "/upload", method = { RequestMethod.POST })
    public void upload(MultipartFile file) {

    }

    /**
     * 上传图片文件
     */
    @RequestMapping(name = "/upload/list", method = { RequestMethod.POST })
    public void upload(HttpServletRequest request) {

    }

    /**
     * 上传BASE64加密图片文件
     */
    @RequestMapping(name = "/upload/base64", method = { RequestMethod.POST })
    public void upload(String data, String fileName) {
        String ext = "";
    }
}
