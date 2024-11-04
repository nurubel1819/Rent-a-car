package All_class_and_frem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class bkash extends JFrame {
    private JPanel main_panel;
    private JLabel bkash_logo;
    private JLabel L_total_amount;
    private JButton btn_make_payment;
    private JLabel L_name;
    private JLabel L_email;
    private JLabel L_phone;
    private JLabel L_duration;

    public void bkash_payment(int customer_id,int rent_duration,int total_amount,int car_id)
    {
        //connect database
        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);

            Statement stmt = conn.createStatement();

            String sql_customer;
            sql_customer = "SELECT * FROM customer_info WHERE customer_id = "+customer_id+";";
            ResultSet data_customer = stmt.executeQuery(sql_customer);
            data_customer.next();

            String d_customer_name = data_customer.getString("name");
            String d_customer_email = data_customer.getString("email");
            String d_customer_phone = data_customer.getString("phone");
            String d_customer_address = data_customer.getString("address");

            L_name.setText("Customer Name : "+d_customer_name);
            L_email.setText("Customer Email : "+d_customer_email);
            L_phone.setText("Customer Phone : "+d_customer_phone);
            L_total_amount.setText("Total Amount = "+Integer.toString(total_amount));
            L_duration.setText("Rent Duration : "+Integer.toString(rent_duration)+" Day");

            stmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Order failed","Status",JOptionPane.ERROR_MESSAGE);
        }

        btn_make_payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);

                    Statement stmt = conn.createStatement();

                    String sql_order;
                    sql_order = "INSERT INTO order_info(car_id,customer_id,rent_duration,total_cost) VALUES(?,?,?,?);";
                    PreparedStatement ps = conn.prepareStatement(sql_order);
                    ps.setString(1,Integer.toString(car_id));
                    ps.setString(2,Integer.toString(customer_id));
                    ps.setString(3,Integer.toString(rent_duration));
                    ps.setString(4,Integer.toString(total_amount));
                    ps.executeUpdate();

                    bkash.this.dispose();
                    JOptionPane.showMessageDialog(null,"Your payment and order upload successfully","Status",JOptionPane.INFORMATION_MESSAGE);

                    stmt.close();
                    conn.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Order failed","Status",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.setContentPane(main_panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,400);
        this.setVisible(true);
    }
}
