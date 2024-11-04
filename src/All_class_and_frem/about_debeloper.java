package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class about_debeloper extends JFrame {
    private JPanel main_panel;
    private JButton backToHomeButton;
public about_debeloper() {

    this.setContentPane(main_panel);
    this.setSize(1000,600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

    backToHomeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            about_debeloper.this.dispose();
            main_class_and_form ob = new main_class_and_form();
        }
    });
}
}
