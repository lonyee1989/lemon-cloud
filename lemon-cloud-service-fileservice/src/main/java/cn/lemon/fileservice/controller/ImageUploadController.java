package cn.lemon.fileservice.controller;

import cn.lemon.fileservice.service.IFileClient;
import cn.lemon.framework.core.BasicController;
import cn.lemon.framework.encrypt.Base64Util;
import cn.lemon.framework.response.ResultMessage;
import cn.lemon.framework.response.ResultResponse;
import cn.lemon.framework.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * Created by lonyee on 2017/4/12.
 */
@RestController
@RequestMapping(value = "/img")
public class ImageUploadController extends BasicController {
    Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
    @Resource(name="fastDFSClient")
    private IFileClient fileClient;

    /**
     * 上传图片文件
     */
    @RequestMapping(value = "/upload", method = { RequestMethod.POST })
    public ResultResponse upload(MultipartFile file) {
        // 上传图片的方法
        try {
            // 获取扩展名
            String ext = FileUtil.getExtension(file.getOriginalFilename());
            String fileId = fileClient.uploadFile(file.getBytes(), ext);
            String fileName = fileId.split("\\.")[0];
            String fileNameBase64 = Base64Util.encode(fileName.getBytes("UTF8"));
            String encodeFileName = URLEncoder.encode(fileNameBase64, "UTF-8");
            String path = String.format("/img/%s.%s", encodeFileName, ext);
            logger.debug("upload image: "+ path);
            return resultResponse.success(path);
        } catch (Exception e) {
            logger.error("图片上传失败. {}", e.getMessage());
            return resultResponse.failure(ResultMessage.F4070);
        }

    }

    /**
     * 上传图片文件
     */
    @RequestMapping(value = "/upload/list", method = { RequestMethod.POST })
    public ResultResponse uploadList(HttpServletRequest request) {
        return null;
    }

    /**
     * 上传BASE64加密图片文件
     */
    @RequestMapping(value = "/upload/base64", method = { RequestMethod.POST })
    public ResultResponse uploadBase64(String data, String fileName) {
        // 上传图片的方法
        try {
            logger.debug("base64 image: "+ fileName);
            // 获取扩展名
            String ext = FileUtil.getExtension(fileName);
            String fileId = fileClient.uploadFile(Base64Util.decode(data.substring(data.indexOf(",")+1)), ext);
            String file = fileId.split("\\.")[0];
            String fileNameBase64 = Base64Util.encode(file.getBytes("UTF8"));
            String encodeFileName = URLEncoder.encode(fileNameBase64, "UTF-8");
            String path = String.format("/img/%s.%s", encodeFileName, ext);
            logger.debug("upload image: "+ path);
            return resultResponse.success(path);
        } catch (Exception e) {
            logger.error("图片上传失败. {}", e.getMessage());
            return resultResponse.failure(ResultMessage.F4070);
        }

    }
}
