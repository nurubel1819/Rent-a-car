package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class customer_login_form extends JFrame {
    private JPanel login_panel;
    private JTextField tf_email;
    private JButton backButton;
    private JButton loginButton;
    private JButton clearButton;
    private JPasswordField pf_password;
    private JButton newCustomerRegistrationButton;

    public customer_login_form() {

    this.setContentPane(login_panel);
    this.setVisible(true);
    this.setSize(600,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            customer_login_form.this.dispose();
            main_class_and_form ob_main = new main_class_and_form();
        }
    });
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //code login button
            String email,password;
            email = tf_email.getText();
            password = String.valueOf(pf_password.getPassword());

            if(email.isEmpty() || password.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Email or password is empty");
            }
            else
            {
                final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
                final String db_username = "root";
                final String db_password = "Rubel1234@";

                try {
                    Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
                    Statement stmt = conn.createStatement();
                    String sql;
                    sql = "SELECT * FROM customer_info WHERE email = '"+email+"';";
                    ResultSet data = stmt.executeQuery(sql);
                    int i=0;
                    while (data.next())
                    {
                        i++;
                        String d_email = data.getString("email");
                        String d_password = data.getString("password");
                        int d_customer_id = data.getInt("customer_id");
                        if(d_email.equals(email) && d_password.equals(password))
                        {
                            JOptionPane.showMessageDialog(null,"Login successfully");

                            //remember login information
                            String sql_login_info = "INSERT INTO login_info(customer_id)\n" +
                                    "VALUES("+d_customer_id+");";
                            stmt.executeUpdate(sql_login_info);
                            customer_login_form.this.dispose();
                            vehicle_view ob = new vehicle_view();
                            ob.view("SELECT * FROM car_info;");
                            break;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Wrong email or password","",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if(i==0) JOptionPane.showMessageDialog(null,"This email not found in database","",JOptionPane.ERROR_MESSAGE);
                    stmt.close();
                    conn.close();

                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Login failed","Status",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    });
    clearButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // code clear button
            tf_email.setText("");
            pf_password.setText("");
        }
    });
        newCustomerRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer_login_form.this.dispose();
                customer_registration_form ob = new customer_registration_form();
            }
        });
    }
}
