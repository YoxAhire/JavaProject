
package com.mycompany.atmyox;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    
    Withdrawl(){
        
            setFont(new Font("System",Font.BOLD,22));
            Font f = getFont();
            FontMetrics fm = getFontMetrics(f);
            int x = fm.stringWidth("WITHDRAWL:");
            int y = fm.stringWidth(" ");
            int z = getWidth() - x;
            int w = z/y;
            String pad = "";
            //for (i=0; i!=w; i++) pad +=" ";
            pad = String.format("%"+w+"s",pad);
            setTitle(pad+"WITHDRAWL");

            l1 = new JLabel("MAXIMUM DAILY WITHDRAWL");
            l1.setFont(new Font("System",Font.BOLD,35));
            
            l2 = new JLabel("IS RS 10,000 :");
            l2.setFont(new Font("System",Font.BOLD,22));
            
            l3 = new JLabel("PLEASE ENTER YOUR AMOUNT:");
            l3.setFont(new Font("System",Font.BOLD,14));
            
            l4 = new JLabel("ENTER PIN:");
            l4.setFont(new Font("System",Font.BOLD,14));
            
            t1 = new JTextField();
            t1.setFont(new Font("Raleway",Font.BOLD,14));
            
            t2 = new JTextField();
            t2.setFont(new Font("Raleway",Font.BOLD,14));
            
            
            b1 = new JButton("WITHDRAWL");
            b1.setFont(new Font("System",Font.BOLD,14));
            b1.setBackground(Color.BLACK);
            b1.setForeground(Color.WHITE);
           
            b2 = new JButton("BACK");
            b2.setFont(new Font("System",Font.BOLD,14));
            b2.setBackground(Color.BLACK);
            b2.setForeground(Color.WHITE);
           
            b3 = new JButton("EXIT");
            b3.setFont(new Font("System",Font.BOLD,14));
            b3.setBackground(Color.BLACK);
            b3.setForeground(Color.WHITE);
           
            setLayout(null);
            
            l4.setBounds(620, 10, 80, 30);
            add(l4);
            
            t2.setBounds(700, 10, 40, 30);
            add(t2);
            
            l1.setBounds(150, 150, 800, 60);
            add(l1);
           
            l2.setBounds(290, 210, 800, 60);
            add(l2);
            
            l3.setBounds(250, 260, 800, 60);
            add(l3);
           
            t1.setBounds(250, 300, 415, 50);
            add(t1);
           
            b1.setBounds(260, 380, 125, 50);
            add(b1);
           
            b2.setBounds(400, 380, 125, 50);
            add(b2);
           
            b3.setBounds(540, 380, 125, 50);
            add(b3);
           
            
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            
           getContentPane().setBackground(Color.WHITE);

           setSize(850,850);
           setLocation(500,90);
           setVisible(true);
          
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        
        try{
        
            String a = t1.getText();
            String b = t2.getText();
            
            if(e.getSource() == b1)
            {
               if(t1.getText().equals("")){
                 
                    JOptionPane.showMessageDialog(null,"Please enter the amount do you want to withdrawl");
               }
               else if(t1.getText().equals("") || t2.getText().equals(""))
               {
                   JOptionPane.showMessageDialog(null,"Confirm the right pin");
                    
               }
               else{
                      Conn c1 = new Conn();
                     
                            if(!t1.getText().isEmpty()){

                                System.out.println("Inside validation code ++");
                                String q0 = "select * from bank where pin = ?";

                                PreparedStatement st = c1.c2().prepareStatement(q0);
                                st.setString(1,b);

                                ResultSet rs = st.executeQuery();
                                double balance = 0;
                                
                                if(e.getSource() == b1){
                                    if(!rs.next()){

                                        JOptionPane.showMessageDialog(null,"Incorrect Card PIN");

                                     }
                                    else{
                                    
                                        String pin = rs.getString("pin");
                                        balance = rs.getDouble("balance");

                                        double d = Double.parseDouble(a);
                                        balance-=d;
                                         //String q1 = "insert into bank values('"+pin+"','"+d+"',null,'"+balance+"')";
                                         String q1 = "insert into bank values(?,?,?,?)";
                                         PreparedStatement st1 = c1.c2().prepareStatement(q1);
                                         st1.setString(1,pin);
                                         st1.setString(2,"");
                                         st1.setDouble(3,d);
                                         st1.setDouble(4,balance);

                                         st1.executeUpdate();
    
                                        JOptionPane.showMessageDialog(null,"Rs. "+a+" Debited Successfully");

                                        new Transactions().setVisible(true);
                                        setVisible(false);

                                    }
                                  }

                             }
                            
                      
                      /*if(error == false){
                          
                             System.out.println("Inside validation code ++");
                             String q1 = "select * from bank where pin = ?";

                             PreparedStatement st = c1.c2().prepareStatement(q1);
                             st.setString(1,b);

                             ResultSet rs = st.executeQuery();
                          
                            double balance = 0;
                            if(rs.next())
                            {
                               String pin = rs.getString("pin");
                               balance = rs.getDouble("balance");

                                double d = Double.parseDouble(a);
                                balance-=d;

                                //String q1 = "insert into bank values('"+pin+"','"+d+"',null,'"+balance+"')";
                                //c1.s.executeUpdate(q1);

                            }

                            JOptionPane.showMessageDialog(null,"Rs. "+a+" Debited Successfully");

                            new Transactions().setVisible(true);
                            setVisible(false);
                      }*/
               }
            }
            else if(e.getSource() == b2){
              
                new Transactions().setVisible(true);
                setVisible(false);
            
            }
            else if(e.getSource() == b3){
                System.exit(0);
            }
            
        }catch(Exception ex){
           
            ex.printStackTrace();
            System.out.println("error: "+ex);
        }
        
        
        
    }
    
    
    public static void main(String[] args) {
        
        new Withdrawl().setVisible(true);
    }
    
}
