package All_class_and_frem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class image_class {

    public ImageIcon draw_image(String image_path)
    {
        Image orjinal_image = null;
        try {
            orjinal_image = ImageIO.read(new File(image_path));
        } catch (IOException e) {
            try {
                orjinal_image = ImageIO.read(new File("C:\\Users\\nurub\\OneDrive\\Documents\\programming code\\intellij Idea for code\\Car Rental Management System\\src\\All_image_and_icon\\Rubel_image.JPG"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        int image_width = 200;
        int image_height = 150;
        BufferedImage resize_image = new BufferedImage(image_width,image_height,BufferedImage.TYPE_4BYTE_ABGR_PRE);

        Graphics2D g = resize_image.createGraphics();
        g.drawImage(orjinal_image,0,0,image_width,image_height,null);
        g.dispose();

        ImageIcon img = new ImageIcon(resize_image);
        //JLabel final_image = new JLabel(img);
        return img;
    }

    public ImageIcon resize_image(String image_path)
    {
        ImageIcon icon = new ImageIcon(image_path);
        Image edit_image = icon.getImage();
        int image_width = 610;
        int image_height = 400;
        Image final_image = edit_image.getScaledInstance(image_width,image_height, Image.SCALE_SMOOTH);
        icon.setImage(final_image);
        return icon;
    }
}
