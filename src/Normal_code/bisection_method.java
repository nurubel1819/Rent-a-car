package Normal_code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bisection_method {
    private JPanel panel1;
    private JTextField tf_lower;
    private JTextField tf_upper;
    private JButton btn_calclate;
    private JLabel l_ans;

    public double bisetcion(double a)
    {
        double ans;
        ans = (a*a*a)+(2*a*a)-a+8;
        return ans;
    }

public bisection_method() {

        Frame frame = new JFrame();
        frame.setSize(1000,600);
        frame.add(panel1);
        frame.setVisible(true);

        btn_calclate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                double a=0,b=0,f_a,f_b,f_c;
                double c=0;
                try {
                    a = Double.parseDouble(tf_lower.getText());
                    b = Double.parseDouble(tf_upper.getText());
                }catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null,"Enter valid number in text field","Status",JOptionPane.ERROR_MESSAGE);
                }
                for(int i=0;i<=10;i++)
                {
                    panel1.add(l_ans);
                    frame.add(panel1);

                    try {
                        Thread.sleep(500); // Sleep for the specified time
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }


                    f_a = bisetcion(a);
                    f_b = bisetcion(b);
                    c = (a+b)/2;
                    l_ans.setText("Root is = "+Double.toString(c));
                    f_c = bisetcion(c);
                    if(f_a*f_c>0) a = c;
                    else b = c;
                }
            }
        });

}

    public static void main(String[] args) {
        bisection_method ob = new bisection_method();
    }
}
