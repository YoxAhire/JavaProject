
package com.mycompany.atmyox;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener {

    JPasswordField t1,t2,t3;
    JButton b1,b2;
    JLabel l1,l2,l3,l4;
    
    Pin(){
     
            setFont(new Font("System",Font.BOLD,22));
            Font f = getFont();
            FontMetrics fm = getFontMetrics(f);
            int x = fm.stringWidth("PIN Change:");
            int y = fm.stringWidth(" ");
            int z = getWidth() - x;
            int w = z/y;
            String pad = "";
            //for (i=0; i!=w; i++) pad +=" ";
            pad = String.format("%"+w+"s",pad);
            setTitle(pad+"PIN Change");

            l1 = new JLabel("Change Your Pin:");
            l1.setFont(new Font("System",Font.BOLD,35));
            
            l2 = new JLabel("Current Pin:");
            l2.setFont(new Font("System",Font.BOLD,22));
            
            l3 = new JLabel("New Pin:");
            l3.setFont(new Font("System",Font.BOLD,22));
            
            l4 = new JLabel("Re-Enter New Pin:");
            l4.setFont(new Font("System",Font.BOLD,22));
            
            t1 = new JPasswordField();
            t1.setFont(new Font("System",Font.BOLD,22));
            
            t2 = new  JPasswordField();
            t2.setFont(new Font("System",Font.BOLD,22));
            
            t3 = new  JPasswordField();
            t3.setFont(new Font("System",Font.BOLD,22));
            
            
            b1 = new JButton("SAVE");
            b1.setFont(new Font("System",Font.BOLD,18));
            b1.setBackground(Color.BLACK);
            b1.setForeground(Color.WHITE);
           
            b2 = new JButton("BACK");
            b2.setFont(new Font("System",Font.BOLD,18));
            b2.setBackground(Color.BLACK);
            b2.setForeground(Color.WHITE);

            b1.addActionListener(this);
            b2.addActionListener(this);

            setLayout(null);
            
            l1.setBounds(220, 130, 800, 60);
            add(l1);
            
            l2.setBounds(100, 240, 150, 30);
            add(l2);
            
            l3.setBounds(100, 300, 150, 40);
            add(l3);
            
            l4.setBounds(100, 360, 200, 40);
            add(l4);
            
            t1.setBounds(310, 240, 360, 40);
            add(t1);
            
            t2.setBounds(310, 300, 360, 40);
            add(t2);
            
            t3.setBounds(310, 360, 360, 40);
            add(t3);
            
            b1.setBounds(220, 460, 160, 50);
            add(b1);
            
            b2.setBounds(400, 460, 160, 50);
            add(b2);
            
           
           getContentPane().setBackground(Color.WHITE);

           setSize(850,850);
           setLocation(500,90);
           setVisible(true);
          
           
    }
    
    


        public void actionPerformed(ActionEvent ae) {

            try{
                  String a = t1.getPassword().toString();
                  String b = (String)t2.getPassword().toString();
                  String c = (String)t3.getPassword().toString();

                  if(ae.getSource() == b1)
                  {
                            if(t1.getPassword().equals("")){
                              JOptionPane.showMessageDialog(null,"Please enter the current PIN");
                            }
                            if(t2.getPassword().equals("")){
                              JOptionPane.showMessageDialog(null,"Please enter the New PIN");
                            }
                            if(t3.getPassword().equals("")){
                              JOptionPane.showMessageDialog(null,"Re-Enter the new PIN");
                            }

                           System.out.println("t1.getPassword():"+t1.getPassword());
                           System.out.println("t2.getPassword():"+t2.getPassword());
                           System.out.println("t3.getPassword():"+t3.getPassword());
                           
                           
                           if(t2.getPassword().equals(t3.getPassword())){
 
                               System.out.println("Inside pin change methode");
                               Conn c1 = new Conn();
                               //String q1 = "update bank set pin = '"+b+"' where pin = '"+a+"'";
                               //String q2 = "update login set pin = '"+b+"' where pin = '"+a+"'";
                               //String q3 = "update signup3 set pin = '"+b+"' where pin = '"+a+"'";

                               //c1.s.executeUpdate(q1);
                               //c1.s.executeUpdate(q2);
                               //c1.s.executeUpdate(q3);
                               
                               String q1 = "update bank set pin = ? where pin = ?";
                               String q2 = "update login set pin = ? where pin = ?";
                               String q3 = "update signup3 set pin = ? where pin = ?";
                               
                               PreparedStatement st1 = c1.c2().prepareStatement(q1);
                               st1.setString(1,b);
                               st1.setString(1,a);
                               st1.executeUpdate();
                               
                               PreparedStatement st2 = c1.c2().prepareStatement(q2);
                               st2.setString(1,b);
                               st2.setString(1,a);
                               st2.executeQuery();
                               
                               PreparedStatement st3 = c1.c2().prepareStatement(q3);
                               st3.setString(1,b);
                               st3.setString(1,a);
                               st3.executeQuery();

                               JOptionPane.showMessageDialog(null,"PIN change Successfully");
                               new Transactions().setVisible(true);
                               setVisible(false);
                           } 
                           else{

                               JOptionPane.showMessageDialog(null,"PIN Enter doesnt match");
                           }
                  }
                  else if(ae.getSource() == b2){

                       new Transactions().setVisible(true);
                       setVisible(false);

                  }

            }
            catch(Exception ex){

                ex.printStackTrace();
            }

        }
    
    

        public static void main(String[] args) {
        new Pin().setVisible(true);
    }
}
