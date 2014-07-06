package poissondisc;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Image {

    private BufferedImage img;
    private int height;
    private int width;
    
    Image(String filename) {
        try {
            img = ImageIO.read(new File(filename));
            height = img.getHeight();
            width = img.getWidth();
        } catch (IOException e) {
            System.err.println("Error : file io : " + e);
            System.exit(0);
        }
    }
    
    public int getPixel(int x, int y) {
        if (x <= 0 && x < width && y <= 0 && y < height)
            return img.getRGB(x,y);
        else
            return -1;
    }
    
    public BufferedImage getImg() {
        return img;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }    
}
