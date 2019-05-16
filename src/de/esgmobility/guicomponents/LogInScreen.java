package de.esgmobility.guicomponents;
import de.esgmobility.loginprocess.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInScreen implements ActionListener {
    private final int x;
    private final int y;
    private final String filename = "C:\\Users\\ZARV\\Desktop\\NetBeansProjects\\LearnSwing\\src\\img\\login.png";
    private final String img = "C:\\Users\\ZARV\\Desktop\\NetBeansProjects\\LearnSwing\\src\\img\\file_xml.png";
    private final JFrame frame;
    private final JLabel logInName;
    private final JLabel logInPassword;
    private final JCheckBox check;
    private final JButton loginBtn;
    private final JTextField loginField;   
    private final JPasswordField passField;
            
    public LogInScreen(int x, int y) {
        this.x = x;
        this.y = y;
        
        frame = new JFrame("XML Tool");
        frame.setBounds(x, y, 380, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(img).getImage());
        frame.setResizable(false);
        
        logInName = new JLabel("Your Login name: ");
        logInName.setBounds(10, 10, 360, 10);
        
        loginField = new JTextField();
        loginField.setBounds(10, 30, 150, 20);
        
        logInPassword = new JLabel("Your Password: ");
        logInPassword.setBounds(10, 60, 310, 10);
        
        passField = new JPasswordField();
        passField.setBounds(10, 80, 150, 20);
        
        check = new JCheckBox("Show password", false);
        check.setBounds(10, 105, 200, 20);
        check.addActionListener(this);
        
        loginBtn = new JButton(new ImageIcon(filename));
        loginBtn.setBounds(10, 140, 50, 20);              
        loginBtn.addActionListener(this);
        
        frame.add(logInName);
        frame.add(logInPassword);
        frame.add(loginField);
        frame.add(passField);
        frame.add(check);
        frame.add(loginBtn);
        
        frame.getRootPane().setDefaultButton(loginBtn);
        frame.setLayout(null);
        frame.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBtn) {
            String login = loginField.getText();
            char[] pswd = passField.getPassword();
            if(LoginProcess.validate(login, pswd)) {
                frame.setVisible(false);
            }
        }
        
        if(e.getSource() == check) {
            if(check.isSelected()) {
                passField.setEchoChar((char)0);
            } else {
                passField.setEchoChar('*');
            }
        }
    }

    
}
