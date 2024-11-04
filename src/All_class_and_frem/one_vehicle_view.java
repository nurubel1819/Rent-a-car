package All_class_and_frem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class one_vehicle_view extends JFrame {


    public JPanel one_vehicle(String car_id)
    {
        JPanel vehicle_panel = new JPanel();
        vehicle_panel.setLayout(new BoxLayout(vehicle_panel,BoxLayout.Y_AXIS));

        Font font = new Font("Arial",Font.BOLD,24);

        //data load from database
        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        //all data variable
        String d_category = "hi";
        String d_fuel = "hi";
        String d_brand = "hi";
        String d_cost = "hi";
        String d_mileage = "hi";
        String d_top_speed = "hi";
        String d_power = "hi";
        String d_sit = "hi";
        String d_imagae_path = "image location";

        String sql_login_info;
        int login_customer_id = -1;

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);

            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM car_info\n" +
                    "WHERE car_id = "+car_id+";";
            sql_login_info = "SELECT * FROM login_info;";
            ResultSet data_login_info = stmt.executeQuery(sql_login_info);
            data_login_info.next();
            login_customer_id = data_login_info.getInt("customer_id");
            ResultSet data = stmt.executeQuery(sql);
            while (data.next())
            {
                d_category = data.getString("category");
                d_fuel = data.getString("fuel");
                d_brand = data.getString("brand");
                d_cost = data.getString("cost");
                d_mileage = data.getString("mileage");
                d_top_speed = data.getString("top_speed");
                d_power = data.getString("power");
                d_sit = data.getString("sit");
                d_imagae_path = data.getString("image");

            }
            stmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error Data can't load","Status",JOptionPane.ERROR_MESSAGE);
        }

        image_class ob_image = new image_class();
        JLabel final_image = new JLabel(ob_image.resize_image(d_imagae_path));

        JPanel image_panel = new JPanel();
        image_panel.add(final_image);
        image_panel.setBackground(Color.red);
        vehicle_panel.add(image_panel);

        //vehicle_panel.add(final_image);

        ///category fuel brand
        JPanel cfb = new JPanel();
        cfb.setLayout(new FlowLayout());

        JLabel category = new JLabel();
        JLabel fuel = new JLabel();
        JLabel brand = new JLabel();

        category.setFont(font);
        fuel.setFont(font);
        brand.setFont(font);

        category.setText("Category : "+d_category+",");
        fuel.setText("  Fuel type : "+d_fuel+",");
        brand.setText("  Brand : "+d_brand);

        cfb.add(category);
        cfb.add(fuel);
        cfb.add(brand);
        cfb.setBackground(Color.CYAN);
        vehicle_panel.add(cfb);

        ///mileage speed sit
        JPanel mss = new JPanel();
        mss.setLayout(new FlowLayout());

        JLabel mileage = new JLabel();
        JLabel speed = new JLabel();
        JLabel sit = new JLabel();

        mileage.setFont(font);
        speed.setFont(font);
        sit.setFont(font);

        mileage.setText("Mileage : "+d_mileage+",");
        speed.setText("  Top speed : "+d_top_speed+",");
        sit.setText("  Total sit : "+d_sit+",");

        mss.add(mileage);
        mss.add(speed);
        mss.add(sit);
        mss.setBackground(Color.green);
        vehicle_panel.add(mss);

        //power cost per day
        JPanel pc = new JPanel();
        pc.setLayout(new FlowLayout());

        JLabel power = new JLabel();
        JLabel cost = new JLabel();

        power.setFont(font);
        cost.setFont(font);

        power.setText("Power HP : "+d_power+",     ");
        cost.setText("Cost per day : "+d_cost);

        pc.add(power);
        pc.add(cost);
        pc.setBackground(Color.CYAN);
        vehicle_panel.add(pc);

        //order button
        JPanel button_panel = new JPanel();
        JButton btn_order = new JButton();
        btn_order.setFont(font);
        btn_order.setText("Order this vehicle");
        btn_order.setBackground(Color.BLUE);
        btn_order.setForeground(Color.white);

        // order table update data upload
        int finalLogin_customer_id = login_customer_id;
        btn_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order_confirm_page ob = new order_confirm_page();
                ob.confirm(finalLogin_customer_id,Integer.parseInt(car_id));
            }
        });

        button_panel.add(btn_order);
        vehicle_panel.add(button_panel);

        vehicle_panel.setPreferredSize(new Dimension(620,700));

        return vehicle_panel;
    }
}
