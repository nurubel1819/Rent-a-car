package All_class_and_frem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class admin_see_all_car extends JFrame {

    public void see_all_car()
    {
        Font font = new Font("Arial black",Font.BOLD,15);

        //create table for data view
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(font);
        table.setBackground(Color.CYAN);
        String[] columnNames = {"car_id","category","fuel","brand","cost","mileage","top_speed","power","sit"};
        model.setColumnIdentifiers(columnNames);


        //all data variable
        String d_car_id;
        String d_category;
        String d_fuel;
        String d_brand;
        String d_cost;
        String d_mileage;
        String d_top_speed;
        String d_power;
        String d_sit;

        //data load from database
        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM car_info;";
            ResultSet data = stmt.executeQuery(sql);
            while (data.next())
            {
                d_car_id = data.getString("car_id");
                d_category = data.getString("category");
                d_fuel = data.getString("fuel");
                d_brand = data.getString("brand");
                d_cost = data.getString("cost");
                d_mileage = data.getString("mileage");
                d_top_speed = data.getString("top_speed");
                d_power = data.getString("power");
                d_sit = data.getString("sit");

                model.addRow(new Object[]{d_car_id,d_category,d_fuel,d_brand,d_cost,d_mileage,d_top_speed,d_power,d_sit});
            }
            stmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error Data can't load","Status",JOptionPane.ERROR_MESSAGE);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JButton btn = new JButton("Back to admin page");
        btn.setFont(font);
        btn.setForeground(Color.white);
        btn.setBackground(Color.BLUE);

        JButton delete_button = new JButton("Delete");
        delete_button.setFont(font);
        delete_button.setForeground(Color.white);
        delete_button.setBackground(Color.red);

        JPanel pa = new JPanel();
        pa.add(btn);
        pa.add(delete_button);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_page ob = new admin_page();
                admin_see_all_car.this.dispose();
            }
        });

        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> deleted_item = new Vector<>();
                int[] selected_row = table.getSelectedRows();
                for (int index : selected_row) {
                    deleted_item.add(model.getValueAt(index, 0).toString());
                }

                String sql_delete = "hi";

                if(deleted_item.size()==1)
                {
                    sql_delete = "DELETE FROM car_info\n" +
                            "WHERE car_id = "+deleted_item.get(0)+";";
                }
                else  if(deleted_item.size()>1)
                {
                    sql_delete = "DELETE FROM car_info\n" +
                            "WHERE car_id IN (";
                    for(int i=0;i<deleted_item.size();i++)
                    {
                        if(i+1==deleted_item.size())
                        {
                            sql_delete+=deleted_item.get(i)+");";
                        }
                        else
                        {
                            sql_delete+= deleted_item.get(i)+ ",";
                        }
                    }
                }
                System.out.println(sql_delete);
                //data load from database
                final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
                final String db_username = "root";
                final String db_password = "";

                try {
                    Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
                    Statement stmt = conn.createStatement();
                    if(sql_delete.equals("hi"))
                        JOptionPane.showMessageDialog(null,"please select item","delete status",JOptionPane.ERROR_MESSAGE);
                    else
                    {
                        stmt.executeUpdate(sql_delete);
                        admin_see_all_car.this.dispose();
                        admin_see_all_car ob = new admin_see_all_car();
                        ob.see_all_car();
                        JOptionPane.showMessageDialog(null,"delete successfully","Delete status",JOptionPane.INFORMATION_MESSAGE);
                    }
                    stmt.close();
                    conn.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Error Data can't deleted in database","Status",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(pa);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        this.getContentPane().add(panel);
        this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,600);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        admin_see_all_car ob = new admin_see_all_car();
        ob.see_all_car();
    }
}
