package com.image.next;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class MergeImageResizer
{   
  
    public static void main(String[] args)
    {
    	
    	Path tSourceImage = Paths.get("src/com/image/data/Org.jpg");
       
    	try {
	        BufferedImage original;
				original = ImageIO.read(tSourceImage.toFile());
	        
	        int width = original.getWidth();
	        int height = original.getHeight();
	       
	        File resized_org = new File("src/com/image/data/resized_org.jpg");
	       
	        int type = (original.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage resized = getScaledInstance(original, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, "BEST_FIT");   
	        
	        BufferedImage finalImg  = new BufferedImage(width, height, type);
	        Graphics2D g = (Graphics2D)finalImg.getGraphics();  
	        g.setColor(Color.WHITE);  
	        g.fillRect(0, 0, finalImg.getWidth(), finalImg.getHeight()); 
	        
	        int x = 0;
	        int y = 0;
	        if(finalImg.getWidth()-resized.getWidth()!=0){
	            x = (finalImg.getWidth()-resized.getWidth())/2;
	        }
	        if(finalImg.getHeight()-resized.getHeight()!=0){
	            y = (finalImg.getHeight()-resized.getHeight())/2;
	        }
	        
	        g.drawImage(resized, x, y, resized.getWidth(), resized.getHeight(), null);
	        // draw other things on g  
	        g.dispose();  
	        
	        ImageIO.write(resized, "jpg", resized_org);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	try {
	        BufferedImage original;
				original = ImageIO.read(tSourceImage.toFile());
	        
	        int width = 70;
	        int height = 105;
	       
	        File destFile_70x105 = new File("src/com/image/data/resized_70x105.jpg");
	       
	        int type = (original.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage resized = getScaledInstance(original, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, "BEST_FIT");   
	        
	        BufferedImage finalImg  = new BufferedImage(width, height, type);
	        Graphics2D g = (Graphics2D)finalImg.getGraphics();  
	        g.setColor(Color.WHITE);  
	        g.fillRect(0, 0, finalImg.getWidth(), finalImg.getHeight()); 
	        
	        int x = 0;
	        int y = 0;
	        if(finalImg.getWidth()-resized.getWidth()!=0){
	            x = (finalImg.getWidth()-resized.getWidth())/2;
	        }
	        if(finalImg.getHeight()-resized.getHeight()!=0){
	            y = (finalImg.getHeight()-resized.getHeight())/2;
	        }
	        
	        g.drawImage(resized, x, y, resized.getWidth(), resized.getHeight(), null);
	        // draw other things on g  
	        g.dispose();  
	        
	        ImageIO.write(resized, "jpg", destFile_70x105);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	try {
	        BufferedImage original;
				original = ImageIO.read(tSourceImage.toFile());
	        
	        int width = 90;
	        int height = 135;
	       
	        File destFile_90x135 = new File("src/com/image/data/resized_90x135.jpg");
	       
	        int type = (original.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage resized = getScaledInstance(original, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, "BEST_FIT");   
	        
	        BufferedImage finalImg  = new BufferedImage(width, height, type);
	        Graphics2D g = (Graphics2D)finalImg.getGraphics();  
	        g.setColor(Color.WHITE);  
	        g.fillRect(0, 0, finalImg.getWidth(), finalImg.getHeight()); 
	        
	        int x = 0;
	        int y = 0;
	        if(finalImg.getWidth()-resized.getWidth()!=0){
	            x = (finalImg.getWidth()-resized.getWidth())/2;
	        }
	        if(finalImg.getHeight()-resized.getHeight()!=0){
	            y = (finalImg.getHeight()-resized.getHeight())/2;
	        }
	        
	        g.drawImage(resized, x, y, resized.getWidth(), resized.getHeight(), null);
	        // draw other things on g  
	        g.dispose();  
	        
	        ImageIO.write(resized, "jpg", destFile_90x135);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	try {
	        BufferedImage original;
				original = ImageIO.read(tSourceImage.toFile());
	        
	        int width = 170;
	        int height = 255;
	       
	        File destFile_170x255 = new File("src/com/image/data/resized_170x255.jpg");
	       
	        int type = (original.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage resized = getScaledInstance(original, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, "BEST_FIT");   
	        
	        BufferedImage finalImg  = new BufferedImage(width, height, type);
	        Graphics2D g = (Graphics2D)finalImg.getGraphics();  
	        g.setColor(Color.WHITE);  
	        g.fillRect(0, 0, finalImg.getWidth(), finalImg.getHeight()); 
	        
	        int x = 0;
	        int y = 0;
	        if(finalImg.getWidth()-resized.getWidth()!=0){
	            x = (finalImg.getWidth()-resized.getWidth())/2;
	        }
	        if(finalImg.getHeight()-resized.getHeight()!=0){
	            y = (finalImg.getHeight()-resized.getHeight())/2;
	        }
	        
	        g.drawImage(resized, x, y, resized.getWidth(), resized.getHeight(), null);
	        // draw other things on g  
	        g.dispose();  
	        
	        ImageIO.write(resized, "jpg", destFile_170x255);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	try {
	        BufferedImage original;
				original = ImageIO.read(tSourceImage.toFile());
	        
	        int width = 260;
	        int height = 390;
	       
	        File destFile_260x390 = new File("src/com/image/data/resized_260x390.jpg");
	       
	        int type = (original.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage resized = getScaledInstance(original, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, "BEST_FIT");   
	        
	        BufferedImage finalImg  = new BufferedImage(width, height, type);
	        Graphics2D g = (Graphics2D)finalImg.getGraphics();  
	        g.setColor(Color.WHITE);  
	        g.fillRect(0, 0, finalImg.getWidth(), finalImg.getHeight()); 
	        
	        int x = 0;
	        int y = 0;
	        if(finalImg.getWidth()-resized.getWidth()!=0){
	            x = (finalImg.getWidth()-resized.getWidth())/2;
	        }
	        if(finalImg.getHeight()-resized.getHeight()!=0){
	            y = (finalImg.getHeight()-resized.getHeight())/2;
	        }
	        
	        g.drawImage(resized, x, y, resized.getWidth(), resized.getHeight(), null);
	        // draw other things on g  
	        g.dispose();  
	        
	        ImageIO.write(resized, "jpg", destFile_260x390);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	try {
	        BufferedImage original;
				original = ImageIO.read(tSourceImage.toFile());
	        
	        int width = 375;
	        int height = 562;
	       
	        File destFile_375x562 = new File("src/com/image/data/resized_375x562.jpg");
	       
	        int type = (original.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage resized = getScaledInstance(original, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, "BEST_FIT");   
	        
	        BufferedImage finalImg  = new BufferedImage(width, height, type);
	        Graphics2D g = (Graphics2D)finalImg.getGraphics();  
	        g.setColor(Color.WHITE);  
	        g.fillRect(0, 0, finalImg.getWidth(), finalImg.getHeight()); 
	        
	        int x = 0;
	        int y = 0;
	        if(finalImg.getWidth()-resized.getWidth()!=0){
	            x = (finalImg.getWidth()-resized.getWidth())/2;
	        }
	        if(finalImg.getHeight()-resized.getHeight()!=0){
	            y = (finalImg.getHeight()-resized.getHeight())/2;
	        }
	        
	        g.drawImage(resized, x, y, resized.getWidth(), resized.getHeight(), null);
	        // draw other things on g  
	        g.dispose();  
	        
	        ImageIO.write(resized, "jpg", destFile_375x562);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }   
    
    /**  
     * Convenience method that returns a scaled instance of the  
     * provided {@code BufferedImage}.  
     *  
     * @param img the original image to be scaled  
     * @param targetWidth the desired width of the scaled instance,  
     *    in pixels  
     * @param targetHeight the desired height of the scaled instance,  
     *    in pixels  
     * @param hint one of the rendering hints that corresponds to  
     *    {@code RenderingHints.KEY_INTERPOLATION} (e.g.  
     *    {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},  
     *    {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},  
     *    {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})  
     * @param higherQuality if true, this method will use a multi-step  
     *    scaling technique that provides higher quality than the usual  
     *    one-step technique (only useful in downscaling cases, where  
     *    {@code targetWidth} or {@code targetHeight} is  
     *    smaller than the original dimensions, and generally only when  
     *    the {@code BILINEAR} hint is specified)  
     * @return a scaled version of the original {@code BufferedImage}  
     */  
    public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, String resize_type)   
    {   
        try{
            //backup the targetWidth and targetHeight for the CROP method.   
            int originalTargetWidth = targetWidth;   
            int originalTargetHeight = targetHeight;   
               
            //Before resizing, we fist need to determine the height and width of the to be created image.   
               
            //First get the height and the width of the original image as doubles.   
            double sourceHeight = img.getHeight();   
            double sourceWidth  = img.getWidth();   
               
            //get the max height and width as doubles for calculation purposes.   
            double heightD = targetHeight;   
            double widthD  = targetWidth;   
               
            //calculate the ratio for scaling the height   
            double ratio = heightD/sourceHeight;   
               
            if(resize_type.equals("BEST_FIT")){   
                //if the original width divided by the ratio is bigger than the targetwidth, the ratio of the width should be used to calculate the intended height;   
                if(((int)Math.round(sourceWidth * ratio)) > targetWidth) {   
                    ratio = widthD/sourceWidth;   
                    targetHeight = (int)Math.round(sourceHeight * ratio);   
                } else {   
                    targetWidth = (int)Math.round(sourceWidth * ratio);   
                }          
            } else if(resize_type.equals("CROP")) {   
                //when using crop, the image is first resized to the size where the max ratio is used as opposed to the min ration for the BEST_FIT method   
                if(((int)Math.round(sourceWidth * ratio)) > targetWidth) {   
                    targetWidth = (int)Math.round(sourceWidth * ratio);   
                } else {   
                    ratio = widthD/sourceWidth;   
                    targetHeight = (int)Math.round(sourceHeight * ratio);   
                }   
            }   
               
               
            int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;   
            BufferedImage ret = (BufferedImage)img;   
            int w, h;   
            // Use multi-step technique: start with original size, then   
            // scale down in multiple passes with drawImage()   
            // until the target size is reached   
            w = img.getWidth();   
            h = img.getHeight();   

            do {   
                if (w > targetWidth) {   
                    w /= 4;   
                    if (w < targetWidth) {   
                        w = targetWidth;   
                    }   
                } else {
                    w = targetWidth;
                }
      
                if (h > targetHeight) {   
                    h /= 4;   
                    if (h < targetHeight) {   
                        h = targetHeight;   
                    }   
                } else {
                    h = targetHeight;
                }
      
                BufferedImage tmp = new BufferedImage(w, h, type);   
                Graphics2D g2 = tmp.createGraphics();   
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);   
                g2.drawImage(ret, 0, 0, w, h, null);   
                g2.dispose();
      
                ret = tmp;   
            } while (w != targetWidth || h != targetHeight);   

            if(resize_type.equals("CROP")) {   
             
                if(targetHeight > originalTargetHeight) {   
                    int yoffset = (targetHeight-originalTargetHeight)/2;   
                    ret = ret.getSubimage(0,yoffset, originalTargetWidth, originalTargetHeight);    
                }   
                if(targetWidth > originalTargetWidth) {   
                     int xoffset = (targetWidth-originalTargetWidth)/2;   
                     ret = ret.getSubimage(xoffset,0, originalTargetWidth, originalTargetHeight);   
                }   
            }   
            return ret;   
        }
        catch(Exception e){
            return null;
        }
    }   
  
}
