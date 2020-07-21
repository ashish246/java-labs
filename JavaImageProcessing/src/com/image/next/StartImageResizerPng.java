package com.image.next;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

public class StartImageResizerPng
{   
    /**
                 * The properties file
                 */
    static Properties properties = new Properties();
    static FileWriter logFileWriter = null;
    /**  
    * @param args  
    */  
    public static void main(String[] args)
    {
        try
        {
            properties.load(new FileReader(new File("F:/Videocon/LatestAWSWorkspace/ImageResizer/javasource/com/eperium/images/image_resizing.properties")));
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        
        // Determine whether to print messages to the console. 
        boolean logToConsole = false;
        String logToConsoleProperty = properties.getProperty("log_to_console");
        if(logToConsoleProperty.equals("true")) logToConsole = true;
        if (logToConsole) System.out.println("Starting...");
        
        Date date = new Date();   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        File logFile = new File(properties.getProperty("logfile_location") + "resize-" + sdf.format(date) + ".log");   
        try
        {   
            logFileWriter = new FileWriter(logFile, true);   
            //get the directory where the original files are located.
            File original_dir = new File(properties.getProperty("base_image_dir") + properties.getProperty("original_image_folder"));
            File error_dir = new File(original_dir.getAbsolutePath()+ "/error");
            if(!error_dir.exists()){
                error_dir.mkdir();
            }
            File processed_dir = new File(original_dir.getAbsolutePath()+ "/processed");
            if(!processed_dir.exists()){
                processed_dir.mkdir();
            }
            String[] fileNames = original_dir.list();
            String[] resize_sizes = properties.getProperty("resize_sizes").split(",");
            System.out.println("Lenght of the string " + resize_sizes.length);
            if (logToConsole) System.out.println(String.valueOf(fileNames.length) + " file(s) found.");
            boolean error = false;
            for(int i=0;i < fileNames.length;i++)
            {
                error = false;
                File originalFile = new File(original_dir.getAbsolutePath()+ "/" + fileNames[i]);
                if(originalFile.isFile()){
                    final String fileExtension = fileNames[i].substring(fileNames[i].indexOf(".") +1);
                    if(fileExtension.equals("png") || fileExtension.equals("jpg"))
                    {
                        /**
                        * Get The original Image
                        */
                        if (logToConsole) System.out.println("Processing file: " + fileNames[i]);
                        try
                        {
                            BufferedImage original = ImageIO.read(originalFile);
                            for(int t=0;t<resize_sizes.length;t++)
                            {
                                File targetFile = new File(properties.getProperty("base_image_dir") + "/" + resize_sizes[t] + "/" + originalFile.getName());
                                int width = Integer.parseInt(properties.getProperty(resize_sizes[t] + ".resize.width"));
                                int height = Integer.parseInt(properties.getProperty(resize_sizes[t] + ".resize.height"));
                                Image img = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                                BufferedImage img_logo = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
                                Graphics2D g = img_logo.createGraphics();
                                g.drawImage(img, 0, 0, null);
                                if (g != null) {
                                    g.dispose();
                                }
                                ImageIO.write(img_logo, "png", targetFile);
                                
                                //String resizeSizeFlag = resize_sizes[t].substring(0, 1).toLowerCase();
                               
                                //targetFileName = targetFileName.substring(0,targetFileName.indexOf(".png"))+".png";
                                /** only resize the file if the targetfile does not exist or is older then the original file*/
                                    
                                    // draw other things on g  
                                    /*if(resized != null){
                                    
                                        ImageIO.write(resized, "jpg", targetFile);
                                    }*/
   
                            }
                        }
                        catch(javax.imageio.IIOException exc) // Handle unparseable image file, so it does not break the batch.
                        {
                            exc.printStackTrace();
                            logEvent(("File " + fileNames[i] + " failed: " + exc.getMessage()), logFileWriter);
                            error = true;
                        }
                        catch(Exception e) // Handle any other exception so it does not break the batch.
                        {
                            e.printStackTrace();
                            logEvent(("File " + fileNames[i] + " failed: " + e.getMessage()), logFileWriter);
                            error = true;
                        }
                    }
                    else
                    {   
                        logEvent("Skipped file " + fileNames[i], logFileWriter);   
                        logFileWriter.flush();   
                        error = true;
                    }   
                    try{
                        if(error){
                            if(originalFile!=null){
                                File errorFile = new File(original_dir.getAbsolutePath()+ "/error/" + originalFile.getName());
                                if(errorFile.exists()){
                                    errorFile.delete();
                                }
                                originalFile.renameTo(errorFile);
                            }
                        }
                        else{
                            File processedFile = new File(original_dir.getAbsolutePath()+ "/processed/" + originalFile.getName());
                            if(processedFile.exists()){
                                processedFile.delete();
                            }
                            originalFile.renameTo(processedFile);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }   
            logFileWriter.flush();   
            logFileWriter.close();   
        }
        catch (IOException e)
        {   
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
    public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, String resize_type, FileWriter logFileWriter, String fileName)   
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
               
            //calculate the ration for scaling the height   
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
                    w /= 2;   
                    if (w < targetWidth) {   
                        w = targetWidth;   
                    }   
                } else {
                    w = targetWidth;
                }
      
                if (h > targetHeight) {   
                    h /= 2;   
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
            logEvent(("File " + fileName + " failed: " + e.getMessage()), logFileWriter);
            return null;
        }
    }   
       
    private static void logEvent(String logMessage, FileWriter logFileWriter)
    {   
        try
        {   
            logFileWriter.write(logMessage+"\n");   
        }
        catch (IOException e)
        {
            //nothin   
        }   
    }   
  
}
