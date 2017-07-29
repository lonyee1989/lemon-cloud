package cn.lemon.fileservice.service.disk;

import cn.lemon.fileservice.service.IFileClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 本地磁盘存储服务
 * Created by lonyee on 2017/4/13.
 */
@Service("diskClient")
public class DiskClient implements IFileClient {
    /**
     * 上传文件
     */
    @Override
    public String uploadFile(byte[] fileBytes, String ext) throws IOException {
        return null;
    }

    /**
     * 查看下载文件
     */
    @Override
    public byte[] downloadFile(String filePath) throws IOException {
        return new byte[0];
    }

    /**
     * 查看下载压缩文件 等比缩放
     */
    @Override
    public byte[] downloadThumb(String filePath, int size) throws IOException {
        return new byte[0];
    }

    /**
     * 查看下载压缩文件 传入height==0 为等比缩放，width，width都大于0 为裁剪缩放
     */
    @Override
    public byte[] downloadThumb(String filePath, int width, int height) throws IOException {
        return new byte[0];
    }
}
