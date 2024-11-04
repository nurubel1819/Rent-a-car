package All_class_and_frem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class create_all_table_in_database { //database name = car_dbms
    public create_all_table_in_database()
    {
        try
        {
            final String db_link = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
            final String db_username = "root";
            final String db_password = "Rubel1234@";

            Connection conn = DriverManager.getConnection(db_link,db_username,db_password);
            Statement stmt = conn.createStatement();

            String create_customer_info_table;
            String create_order_table;
            String create_car_info_table;
            String create_login_info;

            create_customer_info_table = "CREATE TABLE IF NOT EXISTS customer_info (customer_id INT( 10 ) NOT NULL PRIMARY KEY AUTO_INCREMENT, \n" +
                    "name VARCHAR(200) NOT NULL, \n" +
                    "email VARCHAR(200) NOT NULL UNIQUE, \n" +
                    "phone VARCHAR(200) NOT NULL, \n" +
                    "address VARCHAR(200)NOT NULL, \n" +
                    "password VARCHAR(200) NOT NULL); ";
            create_order_table = "CREATE TABLE if NOT EXISTS order_info(\n" +
                    "order_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "car_id INT NOT NULL,\n" +
                    "customer_id INT NOT NULL,\n" +
                    "rent_duration varchar(20) NOT NULL,\n" +
                    "total_cost varchar (20) NOT NULL,\n" +
                    "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                    "order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                    ");";
            create_car_info_table = "CREATE TABLE if NOT EXISTS car_info (car_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, \n" +
                    "category VARCHAR(20) NOT NULL,\n" +
                    "fuel VARCHAR(50) NOT NULL," +
                    "brand VARCHAR(50) NOT NULL,\n" +
                    "cost VARCHAR(20) NOt NULL,\n" +
                    "mileage VARCHAR(20) NOT NULL," +
                    "top_speed VARCHAR(20) NOT NULL,\n" +
                    "power VARCHAR(30)," +
                    "sit VARCHAR(10),\n" +
                    "image varchar(200) not null);";
            create_login_info = "CREATE TABLE if NOT EXISTS login_info(\n" +
                    "customer_id INT \n" +
                    ");";

            stmt.executeUpdate(create_customer_info_table);
            stmt.executeUpdate(create_car_info_table);
            stmt.executeUpdate(create_order_table);
            stmt.executeUpdate(create_login_info);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null,"All table create successfully","Status",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e,"Status",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        create_all_table_in_database ob_create_table = new create_all_table_in_database();
    }
}

