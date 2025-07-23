package egovframework.dnworks.cmm.util;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import egovframework.com.cmm.service.EgovProperties;

public class ImageUtil 
{
	private static String SystemPath 	= EgovProperties.getProperty("Globals.SystemPath");
	private static String UploadPath 	= EgovProperties.getProperty("Globals.UploadPath");
	private static String NoImage 		= EgovProperties.getProperty("Globals.noImage");
	
	public static String getImgTag(String voWebDir, String filename, int maxWidth, String altText) throws Exception {
		String phyFilePath = String.format("%s/%s", voWebDir, filename);
		return getImgTag(phyFilePath, maxWidth, altText);
	}
	public static String getImgTag(String filePath, int maxWidth, String altText) throws Exception {
		String strStyle = String.format("style=\"width:%dpx\"", maxWidth);
		if(maxWidth == 0) strStyle = "";
		
		String rtnVal = String.format("<img src=\"%s\" alt=\"이미지 없음\" class=\"no_img\" %s />", NoImage, strStyle);
		String phyFilePath = String.format("%s%s%s", SystemPath, UploadPath, filePath);
		String imagePath= String.format("%s%s", UploadPath, filePath);
		
		File file = new File(phyFilePath);
		if(file.isFile() && !phyFilePath.equals("")) {
			int[] size = getSizeImage(phyFilePath, maxWidth);
			strStyle = "";
			if(maxWidth > 0) {
				strStyle = String.format("style=\"width:%dpx; height:%dpx;\"", size[0], size[1]);
			}
			rtnVal = String.format("<img src=\"%s\" alt=\"%s\" %s />", imagePath, altText, strStyle);
		}
		return rtnVal;
	}
	
	public static String getImgSrc(String voWebDir, String filename) throws Exception {
		String rtnVal = NoImage;
		String phyFilePath = String.format("%s%s%s/%s", SystemPath, UploadPath, voWebDir, filename);
		String imagePath= String.format("%s%s/%s", UploadPath, voWebDir, filename);
		
		File file = new File(phyFilePath);
		if(file.isFile() && !phyFilePath.equals("")) {
			rtnVal = imagePath;
		}
		return rtnVal;
	}
	
	public static String getImgTagStyle(String voWebDir, String filename, String altText, String style) throws Exception {
		String phyFilePath = String.format("%s/%s", voWebDir, filename);
		return getImgTagStyle(phyFilePath, altText, style);
	}
	public static String getImgTagStyle(String filePath, String altText, String style) throws Exception {
		String rtnVal = String.format("<img src=\"%s\" alt=\"이미지 없음\" class=\"no_img\" %s />", NoImage, style);
		String phyFilePath = String.format("%s%s%s", SystemPath, UploadPath, filePath);
		String imagePath= String.format("%s%s", UploadPath, filePath);
		
		File file = new File(phyFilePath);
		if(file.isFile()) {	
			rtnVal = String.format("<img src=\"%s\" alt=\"%s\" %s />", imagePath, altText, style);
		}
		return rtnVal;
	}
	
	public static int[] getSizeImage(String imageFilePath, int maxWidth) throws Exception {
		int[] size = {0, 0};
		int imgW = 0;
		int imgH = 0;
	
		File file = new File(imageFilePath);
		if(file.isFile()) {
			Image img = new ImageIcon(imageFilePath).getImage();
			imgW = img.getWidth(null);
			imgH = img.getHeight(null);
			
			if (imgW > maxWidth) {
                imgH = (imgH * maxWidth) / imgW;
                imgW = maxWidth;
            }
		}
	
		size[0] = imgW;
		size[1] = imgH;
		
		return size;
	}
}
