package de.esgmobility.loginprocess;
import de.esgmobility.dndtest.*;
import javax.swing.SwingUtilities;

public class LoginProcess {
    private final static String LOGIN = "admin";
    private final static String PSWD = "admin";

    public LoginProcess() {
    }
    
    public static boolean validate(String login, char[] pswd) {
        if(login.equals(LOGIN) && new String(pswd).equals(PSWD)) {
                System.out.println("Login successful");
                SwingUtilities.invokeLater(() -> {
                    new ShapeMover();
                });
                return true;
        } else {
            System.out.println("Incorrect password, try again");
            return false;
        }
    }
}
