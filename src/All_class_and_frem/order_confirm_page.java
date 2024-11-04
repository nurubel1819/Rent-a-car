package All_class_and_frem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class order_confirm_page extends JFrame {
    private JPanel car_info_panel;
    private JPanel customre_info_panel;
    private JPanel order_confirm_panel;
    private JLabel car_image;
    private JLabel L_category;
    private JLabel L_fuel;
    private JLabel L_brand;
    private JLabel L_mileage;
    private JLabel L_top_speed;
    private JLabel L_power;
    private JLabel L_sit;
    private JLabel L_cost;
    private JLabel L_name;
    private JLabel L_email;
    private JLabel L_phone;
    private JLabel L_address;
    private JTextField tf_duration;
    private JLabel L_total_cost;
    private JButton btn_confirm;
    private JButton btn_cancel;



public void confirm(int customer_id,int car_id)
{
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

        String sql_car;
        sql_car = "SELECT * FROM car_info  WHERE car_id = "+car_id+";";
        ResultSet data_car = stmt.executeQuery(sql_car);
        data_car.next();

        String d_car_category = data_car.getString("category");
        String d_car_fuel = data_car.getString("fuel");
        String d_car_brand = data_car.getString("brand");
        String d_car_cost = data_car.getString("cost");
        String d_car_mileage = data_car.getString("mileage");
        String d_car_speed = data_car.getString("top_speed");
        String d_car_power = data_car.getString("power");
        String d_car_sit = data_car.getString("sit");
        String d_car_image = data_car.getString("image");


        ///image insert
        Image orjinal_image = null;
        try {
            orjinal_image = ImageIO.read(new File(d_car_image));
        } catch (IOException e) {
            try {
                orjinal_image = ImageIO.read(new File("C:\\Users\\nurub\\OneDrive\\Documents\\programming code\\intellij Idea for code\\Car Rental Management System\\src\\All_image_and_icon\\Rubel_image.JPG"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        int image_width = 610;
        int image_height = 400;
        BufferedImage resize_image = new BufferedImage(image_width,image_height,BufferedImage.TYPE_4BYTE_ABGR_PRE);

        Graphics2D g = resize_image.createGraphics();
        g.drawImage(orjinal_image,0,0,image_width,image_height,null);
        g.dispose();

        ImageIcon img = new ImageIcon(resize_image);
        //JLabel final_image = new JLabel(img);
        car_image.setIcon(img);

        //set all information
        //set car information
        L_category.setText("Category : "+d_car_category);
        L_fuel.setText("Fuel : "+d_car_fuel);
        L_brand.setText("Brand : "+d_car_brand);
        L_cost.setText("Cost per day : "+d_car_cost);
        L_mileage.setText("Mileage : "+d_car_mileage);
        L_top_speed.setText("Top speed : "+d_car_speed);
        L_power.setText("Power (HP) : "+d_car_power);
        L_sit.setText("Total sit : "+d_car_sit);

        //set customer information
        L_name.setText("Name : "+d_customer_name);
        L_email.setText("Email : "+d_customer_email);
        L_phone.setText("Phone : "+d_customer_phone);
        L_address.setText("Address : "+d_customer_address);

        tf_duration.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    int duration = Integer.parseInt(tf_duration.getText());
                    int total_cost = duration * Integer.parseInt(d_car_cost);
                    if(tf_duration.getText().isEmpty()) total_cost = 0;
                    L_total_cost.setText(Integer.toString(total_cost)+ " TK");
                } catch (Exception exception)
                {
                    L_total_cost.setText("0");
                    JOptionPane.showMessageDialog(null,"Enter valid integer in time duration field","Status",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //order confirm button
        btn_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int duration = Integer.parseInt(tf_duration.getText());
                    int total_cost = duration * Integer.parseInt(d_car_cost);
                    order_confirm_page.this.dispose();
                    payment ob = new payment();
                    ob.payment_method(customer_id,duration,total_cost,car_id);
                } catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null,"Order confirm field","Status",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order_confirm_page.this.dispose();
            }
        });

        stmt.close();
        conn.close();

    }catch (Exception ex){
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null,"Order failed","Status",JOptionPane.ERROR_MESSAGE);
    }



    this.setContentPane(order_confirm_panel);
    this.setSize(1020,700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
}

    public static void main(String[] args) {
        order_confirm_page ob = new order_confirm_page();
        ob.confirm(1,1);
    }
}
