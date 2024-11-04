package Normal_code;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class simulation {

    public simulation()
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(2);
        queue.add(4);
        queue.add(12);
        queue.add(3);
        queue.add(0);

        System.out.println(queue.peek());
        queue.remove();
        queue.remove();
        queue.remove();
        System.out.println(queue.peek());

        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new FlowLayout());
        Font font = new Font("Arial black",Font.BOLD,24);
        JLabel label = new JLabel();
        label.setForeground(Color.blue);

        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        for(int i=0;i<10;i++)
        {
            label.setText(Integer.toString(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            panel.add(label);
            frame.add(panel);
        }
    }

    public static void main(String[] args) {
        simulation ob = new simulation();
    }
}
