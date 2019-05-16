package de.esgmobility.guicomponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class StartScreen {
   
    
    private final String img = "C:\\Users\\ZARV\\Desktop\\NetBeansProjects\\LearnSwing\\src\\img\\file_xml.png";
    private final int x;
    private final int y;
    private final JFrame frm;
    
    public StartScreen(int y, int x) {
        
        this.x = x;
        this.y = y;
        
        frm = new JFrame("XML Tool");
        frm.setIconImage(new ImageIcon(img).getImage());
        frm.setBounds(x,y,380,220);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setResizable(false);
        
        JLabel lbl = new JLabel("Welcome to XML Tool", JLabel.CENTER);
        lbl.setBounds(10,10,330,110);
        
        
        JButton btn = new JButton("Login");
        btn.setBounds(45, 100, 90, 30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frm.setVisible(false);
                new LogInScreen(x, y);
            }
        });
        
        JButton btn1 = new JButton("End");
        btn1.setBounds(210, 100, 90, 30);
        btn1.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        
        frm.setLayout(null);
        frm.add(lbl);
        frm.add(btn);
        frm.add(btn1);
        frm.setVisible(true);
        
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        new StartScreen(400, 250);
    }

    
    
}
