package All_class_and_frem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class vehicle_view extends JFrame {

    public JPanel marge_tow_panel(String first_car,String second_car)
    {
        JPanel tow_panel = new JPanel();
        tow_panel.setLayout(new FlowLayout());

        one_vehicle_view ob = new one_vehicle_view();

        if(second_car == "null")
        {
            JPanel first_panel = ob.one_vehicle(first_car);
            tow_panel.add(first_panel);
            return tow_panel;
        }
        else
        {
            JPanel first_panel = ob.one_vehicle(first_car);
            tow_panel.add(first_panel);

            JPanel second_panel = ob.one_vehicle(second_car);
            tow_panel.add(second_panel);
            return tow_panel;
        }
    }

    public void view(String sql)
    {
        JPanel panel_view = new JPanel();
        panel_view.setLayout(new BoxLayout(panel_view,BoxLayout.Y_AXIS));


        //data all car
        Vector<String> all_car_id = new Vector<>();

        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);

            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            while (data.next()) all_car_id.add(data.getString("car_id"));
            stmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error exception","Status",JOptionPane.ERROR_MESSAGE);
        }

        //back button
        JPanel filter_logout_panel = new JPanel();
        JButton btn_filter = new JButton("Filter vehicle");

        Font font = new Font("Arial Black",Font.BOLD,20);
        btn_filter.setFont(font);
        btn_filter.setForeground(Color.white);
        btn_filter.setBackground(Color.BLUE);

        btn_filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicle_view.this.dispose();
                search_vehicle ob = new search_vehicle();
            }
        });

        JButton btn_logout = new JButton("Logout");
        btn_logout.setFont(font);
        btn_logout.setForeground(Color.white);
        btn_logout.setBackground(Color.red);

        JButton account_btn = new JButton("My account");
        account_btn.setFont(font);
        account_btn.setForeground(Color.white);
        account_btn.setBackground(Color.ORANGE);

        JButton home_button = new JButton("Home");
        home_button.setFont(font);
        home_button.setForeground(Color.white);
        home_button.setBackground(Color.blue);

        home_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicle_view.this.dispose();
                main_class_and_form ob = new main_class_and_form();
            }
        });

        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicle_view.this.dispose();
                logout ob = new logout();
            }
        });

        account_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer_account ob = new customer_account();

            }
        });

        filter_logout_panel.add(btn_filter);
        filter_logout_panel.add(btn_logout);
        filter_logout_panel.add(account_btn);
        filter_logout_panel.add(home_button);
        panel_view.add(filter_logout_panel);

        //panel_view.add(marge_tow_panel());

        if(all_car_id.size()==0)
        {
            btn_filter.setBackground(Color.red);
            JPanel panel_no_car = new JPanel();
            JLabel no_car = new JLabel();
            no_car.setText("Dear customer this item are not available right now");
            no_car.setFont(font);
            panel_no_car.add(no_car);
            panel_view.add(panel_no_car);
        }
        else if(all_car_id.size()%2==0)
        {
            for(int i=0;i<all_car_id.size();i+=2)
            {
                panel_view.add(marge_tow_panel(all_car_id.get(i),all_car_id.get(i+1)));
            }
        }
        else
        {
            int pair = all_car_id.size()/2;
            int index=0;
            while (pair!=0)
            {
                pair--;
                panel_view.add(marge_tow_panel(all_car_id.get(index),all_car_id.get(index+1)));
                index+=2;
            }
            panel_view.add(marge_tow_panel(all_car_id.get(index),"null"));
        }
        JScrollPane scrollPane = new JScrollPane(panel_view);
        scrollPane.setForeground(Color.CYAN);

        this.getContentPane().add(scrollPane);
        this.setSize(1300,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
