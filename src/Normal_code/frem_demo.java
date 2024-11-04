package Normal_code;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class frem_demo {
    public frem_demo()
    {
        // Create a new JFrame
        JFrame frame = new JFrame("JFrame With Background");

        // Load the background image
        Image image = new ImageIcon("C:\\Users\\nurub\\OneDrive\\Documents\\programming code\\intellij Idea for code\\Car Rental Management System\\src\\All_image_practic\\car.png").getImage();

        // Create a new JLabel and set its icon to the background image
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        JButton button = new JButton("ruble");
        backgroundLabel.add(button);

        // Add the JLabel to the content pane of the JFrame
        frame.getContentPane().add(backgroundLabel);


        // Set the size of the JFrame and make it visible
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frem_demo ob = new frem_demo();
    }
}
