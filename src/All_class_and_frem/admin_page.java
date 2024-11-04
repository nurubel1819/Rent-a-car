package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin_page extends JFrame {
    private JPanel main_panel_admin;
    private JButton btn_car_info;
    private JButton seeAllVehiclesButton;
    private JButton seeAllCuotomerButton;
    private JButton seeOrderSummaryButton;
    private JButton back;
public admin_page() {

    this.setContentPane(main_panel_admin);
    this.setVisible(true);
    this.setSize(1000,700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    btn_car_info.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //btn car information
            new_car_upload ob = new new_car_upload();
            admin_page.this.dispose();
        }
    });
    back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            admin_page.this.dispose();
            main_class_and_form ob = new main_class_and_form();
        }
    });
    seeAllVehiclesButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //code admin_add_car_show
            admin_see_all_car ob = new admin_see_all_car();
            ob.see_all_car();
            admin_page.this.dispose();
        }
    });
    seeAllCuotomerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            admin_see_all_customer ob = new admin_see_all_customer();
            ob.see_all_customer();
            admin_page.this.dispose();
        }
    });
    seeOrderSummaryButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            admin_page.this.dispose();
            admin_see_all_order ob = new admin_see_all_order();
            ob.see_all_order();
        }
    });
}

    public static void main(String[] args) {
        admin_page ob = new admin_page();
    }
}
