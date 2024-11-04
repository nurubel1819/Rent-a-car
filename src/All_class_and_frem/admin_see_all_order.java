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
import java.util.Vector;

public class admin_see_all_order extends JFrame {
    public void see_all_order()
    {
        // create font
        Font font = new Font("Arial black",Font.BOLD,15);
        //create table for data view
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(font);
        table.setBackground(Color.CYAN);

        String[] columnNames = {"order_id","car_id","customer_id","rent_duration","total_cost","order_date","order_time"};
        model.setColumnIdentifiers(columnNames);

        String d_order_id;
        String d_car_id;
        String d_customer_id;
        String d_rent_duration;
        String d_total_cost;
        String d_order_date;
        String d_order_time;

        //data load from database
        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM order_info;";
            ResultSet data = stmt.executeQuery(sql);
            while (data.next())
            {
                d_order_id = data.getString("order_id");
                d_car_id = data.getString("car_id");
                d_customer_id = data.getString("customer_id");
                d_rent_duration = data.getString("rent_duration");
                d_total_cost = data.getString("total_cost");
                d_order_date = data.getString("order_date");
                d_order_time = data.getString("order_time");

                model.addRow(new Object[]{d_order_id,d_car_id,d_car_id,d_rent_duration,d_total_cost,d_order_date,d_order_time});
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
        btn.setBackground(Color.blue);

        JButton delete_btn = new JButton("Delete");
        btn.setFont(font);
        delete_btn.setBackground(Color.red);
        delete_btn.setForeground(Color.white);

        JPanel pa = new JPanel();
        pa.add(btn);
        pa.add(delete_btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_page ob = new admin_page();
                admin_see_all_order.this.dispose();
            }
        });

        delete_btn.addActionListener(new ActionListener() {
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
                    sql_delete = "DELETE FROM order_info\n" +
                            "WHERE order_id = "+deleted_item.get(0)+";";
                }
                else  if(deleted_item.size()>1)
                {
                    sql_delete = "DELETE FROM order_info\n" +
                            "WHERE order_id IN (";
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
                        admin_see_all_order.this.dispose();
                        admin_see_all_order ob = new admin_see_all_order();
                        ob.see_all_order();
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
}
