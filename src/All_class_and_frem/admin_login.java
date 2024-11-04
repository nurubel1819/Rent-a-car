package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin_login extends JFrame {
    private JPanel admin_login_panel;
    private JTextField tf_username;
    private JTextField tf_password;
    private JButton backButton;
    private JButton login_button;
    private JButton clearButton;
public admin_login() {

    this.setContentPane(admin_login_panel);
    this.setVisible(true);
    this.setSize(600,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    login_button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = tf_username.getText();
            String password = tf_password.getText();

            if(username.equals("admin") && password.equals("1234"))//admin username and password
            {
                JOptionPane.showMessageDialog(null, "Admin Login successful");
                admin_login.this.dispose();
                admin_page ob_admin = new admin_page();
            }
            else JOptionPane.showMessageDialog(null,"Wrong username or password","Admin login information",JOptionPane.ERROR_MESSAGE);
        }
    });
    clearButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tf_username.setText("");
            tf_password.setText("");
        }
    });
    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            admin_login.this.dispose();
            main_class_and_form ob = new main_class_and_form();
        }
    });
}
}
