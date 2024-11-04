package All_class_and_frem;

import javax.swing.*;
import javax.xml.stream.events.StartElement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Arrays;

public class customer_registration_form extends JFrame {
    private JPanel maian_panel_reg;
    private JTextField tf_name;
    private JTextField tf_email;
    private JTextField tf_phone;
    private JTextField tf_address;
    private JPasswordField tf_password;
    private JPasswordField tf_confarm_password;
    private JButton button_reg;
    private JButton button_cencel;
    private JButton button_back;

    public customer_registration_form() {

        tf_email.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    String d_email = tf_email.getText();
                    validation ob = new validation();
                    if(ob.email_is_valid(d_email))
                    {
                        tf_email.setBackground(Color.BLUE);
                        tf_email.setForeground(Color.white);
                    }
                    else
                    {
                        tf_email.setBackground(Color.red);
                        tf_email.setForeground(Color.white);
                    }

                }catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null,"Error email","status",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tf_password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String password = String.valueOf(tf_password.getPassword());
                validation ob = new validation();
                if(ob.password_is_strong(password))
                {
                    tf_password.setBackground(Color.blue);
                    tf_password.setForeground(Color.white);
                }
                else
                {
                    tf_password.setBackground(Color.red);
                    tf_password.setForeground(Color.white);
                }
            }
        });

    this.setContentPane(maian_panel_reg);
    this.setVisible(true);
    this.setSize(1000,600);
    //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //coe back button
                customer_registration_form.this.dispose();
                customer_login_form ob = new customer_login_form();
            }
        });
    button_reg.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // code for register
            new_registration();
        }
    });
    button_cencel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //code for cencel
            tf_name.setText("");
            tf_email.setText("");
            tf_phone.setText("");
            tf_address.setText("");
            tf_password.setText("");
            tf_confarm_password.setText("");
        }
    });
    }

    private void new_registration() {

        String name = tf_name.getText();
        String email = tf_email.getText();
        String phone = tf_phone.getText();
        String address = tf_address.getText();
        String password = String.valueOf(tf_password.getPassword());
        String confarm_password = String.valueOf(tf_confarm_password.getPassword());

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || confarm_password.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"please fill up all file","",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confarm_password))
        {
            JOptionPane.showMessageDialog(null,"password and confirm password are not equal","",JOptionPane.ERROR_MESSAGE);
            return;
        }

        validation ob = new validation();

        if(!ob.password_is_strong(String.valueOf(tf_password.getPassword())))
        {
            JOptionPane.showMessageDialog(null,"One capital latter,one small latter,one symbol");
            return;
        }
        if(!ob.email_is_valid(tf_email.getText()))
        {
            JOptionPane.showMessageDialog(null,"Email is not valid","status",JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
            Statement stmt = conn.createStatement();
            String sql_r = "SELECT * FROM customer_info WHERE email = '"+email+"';";
            ResultSet data = stmt.executeQuery(sql_r);
            if(data.next()) JOptionPane.showMessageDialog(null,"This email already has database,Enter another email","",JOptionPane.ERROR_MESSAGE);
            else
            {
                String sql = "INSERT INTO customer_info (name,email,phone,address,password) VALUES(?,?,?,?,?);";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,name);
                ps.setString(2,email);
                ps.setString(3,phone);
                ps.setString(4,address);
                ps.setString(5,password);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null,"Registration successfully");
                customer_registration_form.this.dispose();
                main_class_and_form ob_main = new main_class_and_form();
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error , Registration","",JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        customer_registration_form ob = new customer_registration_form();
    }
}
