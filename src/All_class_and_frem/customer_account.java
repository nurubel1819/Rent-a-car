package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class customer_account extends JFrame{
    private JPanel main_panel;
    private JButton closeButton;
    private JButton update_button;
    private JButton change_passowrd_buton;
    private JTextField tf_name;
    private JTextField tf_email;
    private JTextField tf_phone;
    private JTextField tf_address;

    private String loign_id;
public customer_account() {

    //data load from database
    final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
    final String db_username = "root";
    final String db_password = "";


    try {
        Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
        Statement stmt = conn.createStatement();

        String sql_login = "SELECT * FROM login_info;";
        ResultSet data_login = stmt.executeQuery(sql_login);
        data_login.next();
        String customer_id = data_login.getString("customer_id");
        loign_id = customer_id;// Assign login_id

        String sql_customer;
        sql_customer = "SELECT * FROM customer_info\n" +
                "WHERE customer_id = "+customer_id+";";
        ResultSet data_customer = stmt.executeQuery(sql_customer);
        data_customer.next();
        tf_name.setText(data_customer.getString("name"));
        tf_email.setText(data_customer.getString("email"));
        tf_phone.setText(data_customer.getString("phone"));
        tf_address.setText(data_customer.getString("address"));

        stmt.close();
        conn.close();
    }catch (Exception ex){
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null,"Error Data can't show","Status",JOptionPane.ERROR_MESSAGE);
    }

    update_button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
                Statement stmt = conn.createStatement();

                String d_name = tf_name.getText();
                String d_emain = tf_email.getText();
                String d_phone = tf_phone.getText();
                String d_address = tf_address.getText();

                String sql_customer;
                sql_customer = "UPDATE customer_info\n" +
                        "SET name = \""+d_name+"\",email = \""+d_emain+"\",phone = \""+d_phone+"\",address = \""+d_address+"\"\n" +
                        "WHERE customer_id = "+loign_id+";";
                stmt.executeUpdate(sql_customer);

                customer_account.this.dispose();
                JOptionPane.showMessageDialog(null,"Information update successfully");

                stmt.close();
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error your information can't update","Status",JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    change_passowrd_buton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            customer_account.this.dispose();
            change_password ob = new change_password();
            ob.update_password(loign_id);

        }
    });


    closeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            customer_account.this.dispose();
        }
    });

    this.setContentPane(main_panel);
    this.setSize(800,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
}

    public static void main(String[] args) {
        customer_account ob = new customer_account();
    }
}
