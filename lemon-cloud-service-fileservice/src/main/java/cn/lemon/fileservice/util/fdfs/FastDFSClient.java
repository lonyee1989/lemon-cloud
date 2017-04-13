package cn.lemon.fileservice.util.fdfs;

import cn.lemon.fileservice.util.IFileClient;
import cn.lemon.framework.utils.ImageUtil;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * FDFS客户端
 * Created by lonyee on 2017/4/13.
 */
@Service("fastDFSClient")
public class FastDFSClient implements IFileClient {
    static Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    /**
     * 上传文件
     */
    @Override
    public String uploadFile(byte[] fileBytes, String ext) throws IOException {
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = tracker.getStoreStorage(trackerServer);
        String fileId = null;
        try{
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            fileId = client.upload_file1(fileBytes, ext, null);
        } catch (MyException e) {
            logger.error("fastdfs文件传输失败, {}", e.getMessage());
            throw new IllegalArgumentException("fastdfs文件传输失败");
        } finally{
            returnResource(trackerServer, storageServer);
        }
        if (fileId == null) {
            throw new IllegalArgumentException("fastdfs文件传输失败");
        }
        return fileId;
    }

    /**
     * 查看下载文件
     */
    @Override
    public byte[] downloadFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            return null;
        }
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = tracker.getStoreStorage(trackerServer);
        byte[] files = null;
        try{
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            files = client.download_file1(filePath);
        } catch (MyException e) {
            logger.error("下载资源文件失败, {}", e.getMessage());
            return null;
        } finally{
            returnResource(trackerServer, storageServer);
        }
        return files;
    }

    /**
     * 查看下载压缩文件 等比缩放
     */
    @Override
    public byte[] downloadThumb(String filePath, int size) throws IOException {
        return this.downloadThumb(filePath, size, 0);
    }

    /**
     * 查看下载压缩文件 传入height==0 为等比缩放，width，width都大于0 为裁剪缩放
     */
    @Override
    public byte[] downloadThumb(String filePath, int width, int height) throws IOException {
        if (filePath == null || filePath.isEmpty() || (width<=0 && height<=0)) {
            throw new IllegalArgumentException("非法请求参数方式");
        }

        String fileParts[] = filePath.split("\\.");
        if (fileParts.length < 2) {
            throw new IllegalArgumentException("文件不包含扩展名");
        }
        String ext = fileParts[1];

        String prefixName = String.format("_%d%s", width, height>0? "x"+ height: "");
        String thumbFileName = String.format("%s%s.%s", fileParts[0], prefixName, ext);

        //请求缩略图
        byte thumbBytes[] = this.downloadFile(thumbFileName);
        if (thumbBytes!=null) {
            return thumbBytes;
        }
        //缩略图不存在，下载原文件
        byte originBytes[]  = this.downloadFile(filePath);
        if (originBytes == null) {
            //原文件不存在
            throw new NullPointerException("找不到图片文件");
        }
        ByteArrayInputStream in = new ByteArrayInputStream(originBytes);
        try {
            BufferedImage imgIn = ImageIO.read(in);
            BufferedImage imgOut = null;
            if (height<=0) {
                //按比例缩放
                imgOut = ImageUtil.getScaleZoom(imgIn, width);
            } else {
                //默认按比例裁剪
                imgOut = ImageUtil.getScaleCutscale(imgIn, width, height);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                ImageIO.write(imgOut, ext, out);
                byte buffer[] = out.toByteArray();
                TrackerClient tracker = new TrackerClient();
                TrackerServer trackerServer = tracker.getConnection();
                StorageServer storageServer = tracker.getStoreStorage(trackerServer);
                try{
                    StorageClient1 client = new StorageClient1(trackerServer, storageServer);
                    NameValuePair metaList[] = client.get_metadata1(filePath);
                    client.upload_file1(filePath, prefixName, buffer, ext, metaList);
                } catch (MyException e) {
                    logger.error("上传缩放资源文件失败, {}", e.getMessage());
                    return null;
                } finally{
                    returnResource(trackerServer, storageServer);
                }
                return buffer;
            }finally{
                out.close();
                imgOut.flush();
                imgIn.flush();
            }
        }finally{
            in.close();
        }
    }


    private void returnResource(TrackerServer trackerServer, StorageServer storageServer){
        if (storageServer!=null){
            try{
                storageServer.close();
            }catch(Exception e){
                logger.debug("storageServer 资源释放异常."+ e.getMessage());
            }
        }
        if (trackerServer!=null){
            try{
                trackerServer.close();
            }catch(Exception e){
                logger.debug("storageServer 资源释放异常."+ e.getMessage());
            }
        }
    }
}
