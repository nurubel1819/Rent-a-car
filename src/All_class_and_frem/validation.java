package All_class_and_frem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validation {
    public boolean email_is_valid(String email)
    {
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean password_is_strong(String password)
    {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void main(String[] args) {
        validation ob = new validation();
        String e = "nurubel@12gmailo.m";
        String p = "Rubel1234";
        System.out.println("Email = "+ob.email_is_valid(e));
        System.out.println("password : "+ob.password_is_strong(p));
    }
}
