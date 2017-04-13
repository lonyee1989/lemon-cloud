package cn.lemon.fileservice.controller;

import cn.lemon.fileservice.util.IFileClient;
import cn.lemon.framework.BasicController;
import cn.lemon.framework.encrypt.Base64Util;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by lonyee on 2017/4/12.
 */
@RequestMapping(value = "/img")
@RestController
public class ImageDownloadController extends BasicController {

    private static Map<String, String> contentTypeMap = Maps.newConcurrentMap();
    @Resource(name="fastDFSClient")
    private IFileClient fileClient;

    /**
     * 查看源图片文件
     */
    @RequestMapping(value = "/{fileEncodeName:[^_.]+}.{ext:jpg|jpeg|png|bmp|gif}", method = { RequestMethod.GET })
    public void view(HttpServletResponse response, @PathVariable("fileEncodeName") String fileEncodeName, @PathVariable("ext") String ext) {
        try {
            logger.debug("view image: {}.{}", fileEncodeName, ext);
            response.setContentType(getImageContentType(ext.toLowerCase()));
            String fileNameDecodeBase64 = new String(Base64Util.decode(fileEncodeName),"UTF-8");
            String fileName = URLDecoder.decode(fileNameDecodeBase64, "UTF-8");
            String fileId = String.format("%s.%s", fileName, ext.toLowerCase());
            byte image[] = fileClient.downloadFile(fileId);
            response.getOutputStream().write(image);
            response.getOutputStream().flush();
        } catch (Exception e) {
            logger.error("查看图片[{}]异常. {}", fileEncodeName, e.getMessage());
        }

    }

    /**
     * 查看等比缩放图片
     */
    @RequestMapping(value = "/{fileEncodeName:[^._]+}.{ext:jpg|jpeg|png|bmp|gif}_{size:[0-9]+}", method = { RequestMethod.GET })
    public void zoom(HttpServletResponse response, @PathVariable("fileEncodeName") String fileEncodeName, @PathVariable("size") int size, @PathVariable("ext") String ext) {
        try {
            logger.debug("view image: {}.{}", fileEncodeName, ext);
            response.setContentType(getImageContentType(ext.toLowerCase()));
            if (size>0) {
                String fileNameDecodeBase64 = new String(Base64Util.decode(fileEncodeName),"UTF-8");
                String fileName = URLDecoder.decode(fileNameDecodeBase64, "UTF-8");
                String fileId = String.format("%s.%s", fileName, ext.toLowerCase());
                byte[] thumb = fileClient.downloadThumb(fileId, size);
                response.getOutputStream().write(thumb);
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            logger.error("查看图片[{}]异常. {}", fileEncodeName, e.getMessage());
        }
    }

    /**
     * 查看缩放裁剪图片，依据最小边裁剪
     */
    @RequestMapping(value = "/{fileEncodeName:[^._]+}.{ext:jpg|jpeg|png|bmp|gif}_{width:[0-9]+}x{height:[0-9]+}", method = { RequestMethod.GET })
    public void crop(HttpServletResponse response, @PathVariable("fileEncodeName") String fileEncodeName, @PathVariable("width") int width, @PathVariable("height") int height, @PathVariable("ext") String ext) {
        try {
            logger.debug("view image: {}.{}", fileEncodeName, ext);
            response.setContentType(getImageContentType(ext.toLowerCase()));
            if (width>0 && height>0) {
                String fileNameDecodeBase64 = new String(Base64Util.decode(fileEncodeName),"UTF-8");
                String fileName = URLDecoder.decode(fileNameDecodeBase64, "UTF-8");
                String fileId = String.format("%s.%s", fileName, ext.toLowerCase());
                byte[] thumb = fileClient.downloadThumb(fileId, width, height);
                response.getOutputStream().write(thumb);
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            logger.error("查看图片[{}]异常. {}", fileEncodeName, e.getMessage());
        }
    }

    private String getImageContentType(String ext) {
        if (contentTypeMap.size()==0) {
            contentTypeMap.put("png", "image/png");
            contentTypeMap.put("jpg", "image/jpeg");
            contentTypeMap.put("jpeg", "image/jpeg");
            contentTypeMap.put("bmp", "image/bmp");
            contentTypeMap.put("gif", "image/gif");
        }
        String contentType = contentTypeMap.containsKey(ext)? contentTypeMap.get(ext): "image/jpeg";
        return contentType;
    }
}
