import java.util.*;

public class Test_lng {

    private static Locale locale         = null;
    private static ResourceBundle bundle = null;


    public static void main (String args[]) {
	locale = new Locale("fr","FR");
        bundle = ResourceBundle.getBundle("LNG", locale);
        System.out.println(bundle.getString("1Test"));
        System.out.println(bundle.getString("2Test"));
    }

} // Test_lng
