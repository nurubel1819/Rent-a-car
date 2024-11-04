package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment extends JFrame{
    private JPanel payment_panel;
    private JLabel l_total_tk;
    private JButton BRACBankButton;
    private JButton islamiBankButton;
    private JButton btn_bkash;
    private JButton btn_nogod;
    private JButton btn_roket;
    private JButton sonaliBankButton;

    public void payment_method(int customer_id , int rant_duration , int total_cost,int car_id)
    {
        l_total_tk.setText("Payable amount = "+total_cost);

        btn_bkash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment.this.dispose();
                bkash ob = new bkash();
                ob.bkash_payment(customer_id,rant_duration,total_cost,car_id);
            }
        });

        this.setContentPane(payment_panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        payment ob = new payment();
        ob.payment_method(10,20,30,40);
    }
}
