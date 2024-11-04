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

public class admin_see_all_customer extends JFrame {

    public void see_all_customer()
    {

        // create font
        Font font = new Font("Arial black",Font.BOLD,15);
        //create table for data view
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(font);
        table.setBackground(Color.CYAN);
        String[] columnNames = {"customer_id","name","email","phone","address","password"};
        model.setColumnIdentifiers(columnNames);

        String d_customer_id;
        String d_name;
        String d_email;
        String d_phone;
        String d_address;
        String d_password;

        //data load from database
        final String db_car_URL = "jdbc:mysql://localhost/car_dbms?serverTimezone=UTC";
        final String db_username = "root";
        final String db_password = "";

        try {
            Connection conn = DriverManager.getConnection(db_car_URL,db_username,db_password);
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customer_info;";
            ResultSet data = stmt.executeQuery(sql);
            while (data.next())
            {
                d_customer_id = data.getString("customer_id");
                d_name = data.getString("name");
                d_email = data.getString("email");
                d_phone = data.getString("phone");
                d_address = data.getString("address");
                d_password = data.getString("password");

                model.addRow(new Object[]{d_customer_id,d_name,d_email,d_phone,d_address,d_password});
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

        JButton btn_delete = new JButton("Delete");
        btn_delete.setFont(font);
        btn_delete.setForeground(Color.white);
        btn_delete.setBackground(Color.red);

        JPanel pa = new JPanel();
        pa.add(btn);
        pa.add(btn_delete);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_page ob = new admin_page();
                admin_see_all_customer.this.dispose();
            }
        });

        btn_delete.addActionListener(new ActionListener() {
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
                    sql_delete = "DELETE FROM customer_info\n" +
                            "WHERE customer_id = "+deleted_item.get(0)+";";
                }
                else  if(deleted_item.size()>1)
                {
                    sql_delete = "DELETE FROM customer_info\n" +
                            "WHERE customer_id IN (";
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
                        admin_see_all_customer.this.dispose();
                        admin_see_all_customer ob = new admin_see_all_customer();
                        ob.see_all_customer();
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
        admin_see_all_customer ob = new admin_see_all_customer();
        ob.see_all_customer();
    }
}
