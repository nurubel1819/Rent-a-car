package All_class_and_frem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class search_vehicle extends JFrame {
    private JPanel panel_search;
    private JCheckBox privateCarCheckBox;
    private JCheckBox microbusCheckBox;
    private JCheckBox sportsCarCheckBox;
    private JCheckBox luxuryCarCheckBox;
    private JCheckBox electricCarCheckBox;
    private JCheckBox taxiCheckBox;
    private JCheckBox truckCheckBox;
    private JCheckBox pickupCheckBox;
    private JCheckBox petroleumCheckBox;
    private JCheckBox gasolineCheckBox;
    private JCheckBox dieselCheckBox;
    private JCheckBox naturalGasCheckBox;
    private JCheckBox electricityCheckBox;
    private JCheckBox hybridCheckBox;
    private JCheckBox toyotaCheckBox;
    private JCheckBox hondaCheckBox;
    private JCheckBox suzukiCheckBox;
    private JCheckBox tataCheckBox;
    private JCheckBox BMWCheckBox;
    private JCheckBox audiCheckBox;
    private JCheckBox ferrariCheckBox;
    private JCheckBox lamborghiniCheckBox;
    private JCheckBox teslaCheckBox;
    private JComboBox cb_mileage;
    private JComboBox cb_speed;
    private JComboBox cb_power;
    private JComboBox cb_sit;
    private JComboBox cb_cost;
    private JButton backToHomeButton;
    private JButton button2;
    private JButton logoutButton;
public search_vehicle() {

    button2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //

            Vector<String> category = new Vector<>();
            Vector<String> fuel = new Vector<>();
            Vector<String> brand = new Vector<>();

            if(privateCarCheckBox.isSelected()) category.add("Private Car");
            if(microbusCheckBox.isSelected()) category.add("Microbus");
            if(sportsCarCheckBox.isSelected()) category.add("Sports Car");
            if(luxuryCarCheckBox.isSelected()) category.add("Luxury Car");
            if(electricCarCheckBox.isSelected()) category.add("Electric Car");
            if(taxiCheckBox.isSelected()) category.add("Taxi");
            if(truckCheckBox.isSelected()) category.add("Truck");
            if(pickupCheckBox.isSelected()) category.add("Pickup");

            if(petroleumCheckBox.isSelected()) fuel.add("petroleum");
            if(gasolineCheckBox.isSelected()) fuel.add("Gasoline");
            if(dieselCheckBox.isSelected()) fuel.add("Diesel");
            if(naturalGasCheckBox.isSelected()) fuel.add("Natural Gas");
            if(electricityCheckBox.isSelected()) fuel.add("Electricity");
            if(hybridCheckBox.isSelected()) fuel.add("Hybrid");

            if(toyotaCheckBox.isSelected()) brand.add("Toyota");
            if(hondaCheckBox.isSelected()) brand.add("Honda");
            if(suzukiCheckBox.isSelected()) brand.add("Suzuki");
            if(tataCheckBox.isSelected()) brand.add("Tata");
            if(BMWCheckBox.isSelected()) brand.add("BMW");
            if(audiCheckBox.isSelected()) brand.add("Audi");
            if(ferrariCheckBox.isSelected()) brand.add("Ferrari");
            if(lamborghiniCheckBox.isSelected()) brand.add("Lamborghini");
            if(teslaCheckBox.isSelected()) brand.add("Tesla");

            String mileage = cb_mileage.getSelectedItem().toString() ;
            String speed = cb_speed.getSelectedItem().toString();
            String power = cb_power.getSelectedItem().toString();
            String sit = cb_sit.getSelectedItem().toString();
            String cost = cb_cost.getSelectedItem().toString();

            boolean where_detected = false;

            String sql = "SELECT * FROM car_info ";
            if(category.size()!=0)
            {
                sql+= "WHERE (category = \"";
                where_detected = true;
                for(int i=0;i<category.size();i++)
                {
                    sql +=category.get(i)+"\"";
                    if(i+1==category.size()) sql+=")";
                    else sql+= " or category = \"";
                }
            }
            if(fuel.size()!=0)
            {
                if(!where_detected)
                {
                    sql+="WHERE (fuel = \"";
                    where_detected = true;
                }
                else sql+= " and (fuel = \"";
                for(int i=0;i<fuel.size();i++)
                {
                    sql+= fuel.get(i)+"\"";
                    if(i+1==fuel.size()) sql+=")";
                    else sql+= " or fuel = \"";
                }
            }
            if(brand.size()!=0)
            {
                if(!where_detected)
                {
                    sql+= "WHERE (brand = \"";
                    where_detected = true;
                }
                else sql+= " and (brand = \"";
                for(int i=0;i<brand.size();i++)
                {
                    sql += brand.get(i)+"\"";
                    if(i+1==brand.size()) sql+=")";
                    else sql+= " or brand = \"";
                }
            }
            switch (mileage) {
                case "0-50" -> {
                    if (!where_detected) sql += "WHERE (mileage <= 50)";
                    else sql += " and (mileage <= 50)";
                }
                case "51-100" -> {
                    if (!where_detected) sql += "WHERE (mileage > 50 and mileage <=100)";
                    else sql += " and (mileage > 50 and mileage <=100)";
                }
                case "101-150" -> {
                    if (!where_detected) sql += "WHERE (mileage > 100 and mileage <=150)";
                    else sql += " and (mileage > 100 and mileage <=150)";
                }
                case "151-200" -> {
                    if (!where_detected) sql += "WHERE (mileage > 150 and mileage <=200)";
                    else sql += " and (mileage > 150 and mileage <=200)";
                }
            }
            switch (speed) {
                case "0-50" -> {
                    if (!where_detected) sql += "WHERE (top_speed <=50)";
                    else sql += " and (top_speed <=50)";
                }
                case "51-100" -> {
                    if (!where_detected) sql += "WHERE (top_speed >50 and top_speed <=100)";
                    else sql += " and (top_speed >50 and top_speed <=100)";
                }
                case "101-200" -> {
                    if (!where_detected) sql += "WHERE (top_speed >100 and top_speed <=200)";
                    else sql += " and (top_speed >100 and top_speed <=200)";
                }
                case "200-400" -> {
                    if (!where_detected) sql += "WHERE (top_speed >200 and top_speed <=400)";
                    else sql += " and (top_speed >200 and top_speed <=400)";
                }
            }
            switch (power) {
                case "0-40" -> {
                    if (!where_detected) sql += "WHERE (power <=40)";
                    else sql += " and (power <=40)";
                }
                case "41-100" -> {
                    if (!where_detected) sql += "WHERE (power >40 and power <=100)";
                    else sql += " and (power >40 and power <=100)";
                }
                case "101-200" -> {
                    if (!where_detected) sql += "WHERE (power >100 and power <=200)";
                    else sql += " and (power >100 and power <=200)";
                }
                case "201-500" -> {
                    if (!where_detected) sql += "WHERE (power >200 and power <=500)";
                    else sql += " and (power >200 and power <=500)";
                }
                case "501-1000" -> {
                    if (!where_detected) sql += "WHERE (power >500 and power <=1000)";
                    else sql += " and (power >500 and power <=1000)";
                }
            }
            switch (sit) {
                case "2-5" -> {
                    if (!where_detected) sql += "WHERE (sit >2 and sit <=5)";
                    else sql += " and (sit >2 and sit <=5)";
                }
                case "6-12" -> {
                    if (!where_detected) sql += "WHERE (sit >6 and sit <=12)";
                    else sql += " and (sit >6 and sit <=12)";
                }
                case "13-30" -> {
                    if (!where_detected) sql += "WHERE (sit >13 and sit <=30)";
                    else sql += " and (sit >13 and sit <=30)";
                }
            }
            switch (cost) {
                case "1,000" -> {
                    if (!where_detected) sql += "WHERE (cost <=1000)";
                    else sql += " and (cost <=1000)";
                }
                case "1,000-3,000" -> {
                    if (!where_detected) sql += "WHERE (cost >1000 and cost <=3000)";
                    else sql += " and (cost >1000 and cost <=3000)";
                }
                case "3,000-6,000" -> {
                    if (!where_detected) sql += "WHERE (cost >3000 and cost <=6000)";
                    else sql += " and (cost >3000 and cost <=6000)";
                }
                case "6,000-10,000" -> {
                    if (!where_detected) sql += "WHERE (cost >6000 and cost <=10000)";
                    else sql += " and (cost >6000 and cost <=10000)";
                }
                case "10,000-50,000" -> {
                    if (!where_detected) sql += "WHERE (cost >10000 and cost <=50000)";
                    else sql += " and (cost >10000 and cost <=50000)";
                }
            }
            sql += ";";

            vehicle_view ob = new vehicle_view();
            ob.view(sql);
            search_vehicle.this.dispose();
        }
    });

    this.setContentPane(panel_search);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1000,650);
    this.setVisible(true);

    logoutButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // code for logout
            search_vehicle.this.dispose();
            logout ob = new logout();

        }
    });
}

    public static void main(String[] args) {
        search_vehicle ob = new search_vehicle();
    }
}
