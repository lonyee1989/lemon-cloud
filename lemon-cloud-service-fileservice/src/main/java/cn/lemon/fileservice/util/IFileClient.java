package cn.lemon.fileservice.util;

import java.io.IOException;

/**
 * 文件操作接口
 * Created by lonyee on 2017/4/13.
 */
public interface IFileClient {
    /**
     * 上传文件
     */
    String uploadFile(byte fileBytes[], String ext) throws IOException;
    /**
     * 查看下载文件
     */
    byte[] downloadFile(String filePath) throws IOException;
    /**
     * 查看下载压缩文件 等比缩放
     */
    byte[] downloadThumb(String filePath, int size) throws IOException;
    /**
     * 查看下载压缩文件 传入height==0 为等比缩放，width，width都大于0 为裁剪缩放
     */
    byte[] downloadThumb(String filePath, int width, int height) throws IOException;
}
