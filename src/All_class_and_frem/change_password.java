package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class change_password extends JFrame {
    private JPanel main_panel;
    private JTextField tf_current_password;
    private JTextField tf_new_password;
    private JTextField tf_confirm_password;
    private JButton closeButton;
    private JButton updatePasswordButton;

    public void update_password(String loign_id)
    {

        updatePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //data load from database
                final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
                final String db_username = "root";
                final String db_password = "";

                String input_password = tf_current_password.getText();

                if(tf_new_password.getText().equals(tf_confirm_password.getText()))
                {
                    try {
                        Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
                        Statement stmt = conn.createStatement();

                        String sql_customer_info = "SELECT * FROM customer_info\n" +
                                "WHERE customer_id = "+loign_id+";";
                        ResultSet data_customer = stmt.executeQuery(sql_customer_info);
                        data_customer.next();
                        String current_password = data_customer.getString("password");

                        if(input_password.equals(current_password))
                        {
                            String d_password = tf_new_password.getText();

                            String sql_update_password;
                            sql_update_password = "UPDATE customer_info\n" +
                                    "SET password = \""+d_password+"\"\n" +
                                    "WHERE customer_id = "+loign_id+";";
                            stmt.executeUpdate(sql_update_password);
                            change_password.this.dispose();
                            customer_account ob = new customer_account();
                            JOptionPane.showMessageDialog(null,"Your password change successfully");
                        }
                        else JOptionPane.showMessageDialog(null,"Current password does not match","status",JOptionPane.ERROR_MESSAGE);

                        stmt.close();
                        conn.close();
                    }catch (Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,"Error your password can't change","Status",JOptionPane.ERROR_MESSAGE);
                    }

                }
                else JOptionPane.showMessageDialog(null,"Confirm password does not match","Status",JOptionPane.ERROR_MESSAGE);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                change_password.this.dispose();
                customer_account ob = new customer_account();
            }
        });

        this.setContentPane(main_panel);
        this.setSize(600,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
