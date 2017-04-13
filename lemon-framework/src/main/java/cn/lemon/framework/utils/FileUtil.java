package cn.lemon.framework.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

public class FileUtil {
	static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
     * 下载文件到本地
     *
     * @param urlString 被下载的文件地址
     * @param filename 本地文件名
     */
	public static void download(String urlString, String filename) throws IOException {
		logger.info("urlString:{}", urlString);
		makeDirs(urlString);
	    URL url = new URL(urlString);
	    URLConnection con = url.openConnection();
	    InputStream is = con.getInputStream();
	    byte[] bs = new byte[1024];  //1K的数据缓冲
	    OutputStream os = new FileOutputStream(filename);
	    try{
		    int len;
		    while ((len = is.read(bs)) != -1) {
		      os.write(bs, 0, len);
		    }
	    }catch(IOException ex){
			logger.error("download file error. {}", urlString);
			throw new IOException(ex);
	    }finally {
		    os.close();
		    is.close();
	    }
	}
	
	public static void copyFile(String fileName, String newFileName) throws IOException {
		Files.copy(new File(fileName), new File(newFileName));
	}
	
	public static boolean makeDirs(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }
        String filePath = fileName.substring(0, fileName.lastIndexOf("/"));        
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }
	
	public static void deleteDirs(String pathname) {
		if (pathname == null || pathname.isEmpty()) {
            return;
        }
		File path = new File(pathname);
		deleteDirs(path);
	}

	public static String getExtension(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			return "";
		}
		String ext =fileName.substring(fileName.lastIndexOf(".")+1);
		return ext.toLowerCase();
	}
	
	public static void deleteDirs(File path) {
        if (!path.exists())  
            return;  
        if (path.isFile()) {  
            path.delete();  
            return;  
        }  
        File[] files = path.listFiles();  
        for (File file: files) {  
        	deleteDirs(file);
        }
        path.delete();
    }
}
