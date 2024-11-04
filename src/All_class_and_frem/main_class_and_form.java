package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main_class_and_form extends JFrame {
    private JPanel panel_main_class;
    private JButton button_admin;
    private JButton button_customer_login;
    private JButton aboutDeveloperButton;

    public main_class_and_form() {
    this.setContentPane(panel_main_class);
    this.setVisible(true);
    this.setSize(1000,600);
    //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    button_customer_login.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
            final String db_username = "root";
            final String db_password = "";

            try {
                Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);

                Statement stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM login_info;";
                ResultSet data = stmt.executeQuery(sql);
                int i=0;
                while (data.next())
                {
                    i++;
                    int customer_id = data.getInt("customer_id");
                    vehicle_view ob = new vehicle_view();
                    ob.view("SELECT * FROM car_info;");
                    main_class_and_form.this.dispose();
                }
                if(i==0)
                {
                    customer_login_form ob = new customer_login_form();
                    main_class_and_form.this.dispose();
                }

                stmt.close();
                conn.close();

            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Login failed","Status",JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    button_admin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            main_class_and_form.this.dispose();
            admin_login ob_admin = new admin_login();
        }
    });
    aboutDeveloperButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            main_class_and_form.this.dispose();
            about_debeloper ob = new about_debeloper();
        }
    });
}

    public static void main(String[] args) {

    main_class_and_form ob = new main_class_and_form();
    }
}
