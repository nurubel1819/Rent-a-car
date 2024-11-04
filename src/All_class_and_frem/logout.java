package All_class_and_frem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class logout {

    public logout()
    {
        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);

            Statement stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM login_info;";
            stmt.executeUpdate(sql);

            main_class_and_form ob = new main_class_and_form();

            stmt.close();
            conn.close();

        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error database connection","Status",JOptionPane.ERROR_MESSAGE);
        }
    }
}
