package All_class_and_frem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class new_car_upload extends JFrame{
    private JPanel main_panel;
    private JComboBox cb_category;
    private JComboBox cb_fuel;
    private JComboBox cb_brand;
    private JButton btn_image;
    private JTextField tf_cost;
    private JTextField tf_mileage;
    private JTextField tf_speed;
    private JTextField tf_power;
    private JTextField tf_sit;
    private JButton backToAdminPageButton;
    private JButton uploadThisInformationButton;
    private JButton clearButton;
    public String image_path;
public new_car_upload() {



    this.setContentPane(main_panel);
    this.setSize(1000,600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

    backToAdminPageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ///code for back button
            admin_page ob = new admin_page();
            new_car_upload.this.dispose();
        }
    });
    btn_image.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // code for selection image
            String image_location = "image path";
            JFileChooser jfc = new JFileChooser();
            if(jfc.showOpenDialog(null)==0)
            {
                image_location = jfc.getSelectedFile().getAbsolutePath();
                btn_image.setText("Image has been selected");
                btn_image.setBackground(Color.BLUE);
            }
            else JOptionPane.showMessageDialog(null,"Can't select any file","file selection info",JOptionPane.ERROR_MESSAGE);
            System.out.println(image_location);
            image_path = image_location;
        }
    });

    uploadThisInformationButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //code for submit button
            String category = cb_category.getSelectedItem().toString();
            String fuel = cb_fuel.getSelectedItem().toString();
            String brand = cb_brand.getSelectedItem().toString();
            String cost = tf_cost.getText();
            String mileage = tf_mileage.getText();
            String top_speed = tf_speed.getText();
            String power = tf_power.getText();
            String sit = tf_sit.getText();

            if(category.isEmpty()||fuel.isEmpty()||brand.isEmpty()||cost.isEmpty()||mileage.isEmpty()|| top_speed.isEmpty()||power.isEmpty()||sit.isEmpty()||image_path.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"please fill up all fields","informaion",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
                final String db_username = "root";
                final String db_password = "";

                try {
                    Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
                    Statement stmt = conn.createStatement();

                    String sql = "INSERT INTO car_info (category,fuel,brand,cost,mileage,top_speed,power,sit,image) VALUES(?,?,?,?,?,?,?,?,?);";

                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,category);
                    ps.setString(2,fuel);
                    ps.setString(3,brand);
                    ps.setString(4,cost);
                    ps.setString(5,mileage);
                    ps.setString(6,top_speed);
                    ps.setString(7,power);
                    ps.setString(8,sit);
                    ps.setString(9,image_path);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null,"update this data in server successfully");
                    stmt.close();
                    conn.close();
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Error, can't update this data in server","",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    });
    clearButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tf_cost.setText("");
            tf_mileage.setText("");
            tf_power.setText("");
            tf_sit.setText("");
            tf_speed.setText("");
            image_path = "";
            btn_image.setText("select image");
            btn_image.setBackground(Color.black);
        }
    });
}

    public static void main(String[] args) {
        new_car_upload ob = new new_car_upload();
    }
}
