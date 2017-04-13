package cn.lemon.framework.utils;

import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.common.io.Files;

public class ImageUtil {

	/**
	 * 按比例裁剪
	 * @param img
	 * @param targetWidth
	 * @param targetHeight
	 * @return
	 */
	public static BufferedImage getScaleCutscale(BufferedImage img, int targetWidth, int targetHeight) {
		int srcWidth = img.getWidth();
		int srcHeight = img.getHeight();
		//当超过图片最大宽高时候等比缩小所需要的图片规格
		while(targetWidth>srcWidth || targetHeight>srcHeight){
			double rt = (targetWidth>srcWidth)? ((double)targetWidth)/(srcWidth): ((double)targetHeight)/(srcHeight);
			targetWidth = (int)(((double)targetWidth)/rt);
			targetHeight = (int)(((double)targetHeight)/rt);
		}

		double rate1 = ((double)srcWidth)/(targetWidth);
		double rate2 = ((double)srcHeight)/(targetHeight);
        // 根据缩放比率大的进行缩放控制 
        double rate = rate1 < rate2 ? rate1 : rate2; 
        int newWidth = (int)(((double)srcWidth)/rate);
        int newHeight = (int)(((double)srcHeight)/rate);

        int x1 = newWidth/2 - targetWidth/2;
        int x2 = newWidth/2 + targetWidth/2;
        int y1 = newHeight/2 - targetHeight/2;
        int y2 = newHeight/2 + targetHeight/2;
		int imageType = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage tag = new BufferedImage(targetWidth, targetHeight, imageType);
	    Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_REPLICATE);
		tag.getGraphics().drawImage(scaledImage, 0, 0, targetWidth, targetHeight, x1, y1, x2, y2, null);
		
		return tag;
	}
	
	/**
	 * 按比例缩放
	 * @param img
	 * @return
	 */
	public static BufferedImage getScaleZoom(BufferedImage img, int targetSize) {
		int srcWidth = img.getWidth();
		int srcHeight = img.getHeight();
		int targetWidth = targetSize;
		int targetHeight = targetSize;
		if (targetSize>srcWidth || targetSize>srcHeight){
			targetWidth = srcWidth;
			targetHeight = srcHeight;
		}
		double rate1 = ((double)srcWidth)/targetWidth;
		double rate2 = ((double)srcHeight)/targetHeight;
        // 根据缩放比率大的进行缩放控制 
        double rate = rate1 > rate2 ? rate1 : rate2;
        int newWidth = (int)(((double)srcWidth)/rate);
        int newHeight = (int)(((double)srcHeight)/rate);
		int imageType = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage tag = new BufferedImage(newWidth, newHeight, imageType);
		//Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的优先级比速度高 生成的图片质量比较好 但速度慢
		Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_REPLICATE);
		tag.getGraphics().drawImage(scaledImage, 0, 0, null);
		
		return tag;
	}
	
	public static void copyCompressImage(String fileName, String newFileName) throws IOException {
		File file = new File(fileName);
		if (50*1024>=file.length()) {
			Files.copy(file, new File(newFileName));
			return;
		}
		BufferedImage imgIn = ImageIO.read(file);
		BufferedImage imgOut = ImageUtil.getScaleZoom(imgIn, 300);
		ImageIO.write(imgOut, "jpg",  new File(newFileName));
	}	
}
