package com.image.next;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestPNGT
{
    public static void main(String[] args) throws IOException {
        FileInputStream item = new FileInputStream("F:/Videocon/Job/original_product_images/14000015143.png");
        String logo_name = System.currentTimeMillis() + ".png";
        File uploadedFile = new File("f:/www/static.appcanvas.com/" + logo_name);
        BufferedImage bi = ImageIO.read(item);
        Image img = bi.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        BufferedImage img_logo = new BufferedImage(125, 125, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img_logo.createGraphics();
        g.drawImage(img, 0, 0, null);
        if (g != null) {
            g.dispose();
        }
        uploadedFile.getParentFile().mkdirs();
        uploadedFile.createNewFile();
        ImageIO.write(img_logo, "png", uploadedFile);

    }
}
