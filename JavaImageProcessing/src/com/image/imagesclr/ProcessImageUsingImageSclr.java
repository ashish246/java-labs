package com.image.imagesclr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class ProcessImageUsingImageSclr {

	public static void main(String[] args) {

		Path tSourceImage = Paths.get("src/com/image/data/Org.jpg");
		
		//BufferedImage img = ImageIO.read(new File("c:/img/test.jpg"));
		
		try {
			BufferedImage img= ImageIO.read(tSourceImage.toFile());
			
			/*BufferedImage resized_org = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, img.getWidth(), img.getHeight());			
			BufferedImage resized_70x105 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 70, 105);
			BufferedImage resized_90x135 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 90, 135);
			BufferedImage resized_170x255 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 170, 255);
			BufferedImage resized_260x390 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 260, 390);
			BufferedImage resized_375x562 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 375, 562);*/
			
			System.out.println("img.getHeight(): "+img.getHeight());
			System.out.println("img.getWidth(): "+img.getWidth());
			
			/*BufferedImage resized_org = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, img.getWidth(), img.getHeight(), Scalr.OP_ANTIALIAS);			
			BufferedImage resized_70x105 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 70, 105, Scalr.OP_ANTIALIAS);
			BufferedImage resized_90x135 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 90, 135, Scalr.OP_ANTIALIAS);
			BufferedImage resized_170x255 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 170, 255, Scalr.OP_ANTIALIAS);
			BufferedImage resized_260x390 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 260, 390, Scalr.OP_ANTIALIAS);
			BufferedImage resized_375x562 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 375, 562, Scalr.OP_ANTIALIAS);
			*/ 
			
			BufferedImage resized_org = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, img.getWidth(), img.getHeight(), Scalr.OP_BRIGHTER);			
			BufferedImage resized_70x105 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 70, 105, Scalr.OP_BRIGHTER);
			BufferedImage resized_90x135 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 90, 135, Scalr.OP_BRIGHTER);
			BufferedImage resized_170x255 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 170, 255, Scalr.OP_BRIGHTER);
			BufferedImage resized_260x390 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 260, 390, Scalr.OP_BRIGHTER);
			BufferedImage resized_375x562 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 375, 562, Scalr.OP_BRIGHTER);
			
			/*BufferedImage resized_org = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, img.getWidth(), img.getHeight(), Scalr.OP_GRAYSCALE);			
			BufferedImage resized_70x105 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 70, 105, Scalr.OP_GRAYSCALE);
			BufferedImage resized_90x135 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 90, 135, Scalr.OP_GRAYSCALE);
			BufferedImage resized_170x255 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 170, 255, Scalr.OP_GRAYSCALE);
			BufferedImage resized_260x390 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 260, 390, Scalr.OP_GRAYSCALE);
			BufferedImage resized_375x562 = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Mode.AUTOMATIC, 375, 562, Scalr.OP_GRAYSCALE);*/
			
			
			File destFile_og = new File("src/com/image/data/resized_org.jpg");			   
			ImageIO.write(resized_org, "jpg", destFile_og);
			File destFile_70x105 = new File("src/com/image/data/resized_70x105.jpg");			   
			ImageIO.write(resized_70x105, "jpg", destFile_70x105);
			File destFile_90x135 = new File("src/com/image/data/resized_90x135.jpg");			   
			ImageIO.write(resized_90x135, "jpg", destFile_90x135);
			File destFile_170x255 = new File("src/com/image/data/resized_170x255.jpg");			   
			ImageIO.write(resized_170x255, "jpg", destFile_170x255);
			File destFile_260x390 = new File("src/com/image/data/resized_260x390.jpg");			   
			ImageIO.write(resized_260x390, "jpg", destFile_260x390);
			File destFile_375x562 = new File("src/com/image/data/resized_375x562.jpg");			   
			ImageIO.write(resized_375x562, "jpg", destFile_375x562);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		System.out.println("Done resizing");
	}

}
