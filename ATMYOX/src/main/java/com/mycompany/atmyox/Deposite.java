
package com.mycompany.atmyox;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Deposite extends JFrame implements ActionListener {

    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    
    Deposite(){
        
         setFont(new Font("System",Font.BOLD,22));
            Font f = getFont();
            FontMetrics fm = getFontMetrics(f);
            int x = fm.stringWidth("DEPOSITE:");
            int y = fm.stringWidth(" ");
            int z = getWidth() - x;
            int w = z/y;
            String pad = "";
            //for (i=0; i!=w; i++) pad +=" ";
            pad = String.format("%"+w+"s",pad);
            setTitle(pad+"DEPOSITE");

            l1 = new JLabel("ENTER THE AMOUNT YOU WANT :");
            l1.setFont(new Font("System",Font.BOLD,35));
            
            l2 = new JLabel("To Deposite :");
            l2.setFont(new Font("System",Font.BOLD,25));
            
            l3 = new JLabel("Enter Pin :");
            l3.setFont(new Font("System",Font.BOLD,14));
            
            t1 = new JTextField();
            t1.setFont(new Font("Raleway",Font.BOLD,14));
            
            t2 = new JTextField();
            t2.setFont(new Font("Raleway",Font.BOLD,14));
            
            
            b1 = new JButton("DEPOSITE");
            b1.setFont(new Font("System",Font.BOLD,18));
            b1.setBackground(Color.BLACK);
            b1.setForeground(Color.WHITE);
           
            b2 = new JButton("BACK");
            b2.setFont(new Font("System",Font.BOLD,18));
            b2.setBackground(Color.BLACK);
            b2.setForeground(Color.WHITE);
           
            b3 = new JButton("EXIT");
            b3.setFont(new Font("System",Font.BOLD,18));
            b3.setBackground(Color.BLACK);
            b3.setForeground(Color.WHITE);
           
            setLayout(null);
            
            l3.setBounds(620, 10, 80, 30);
            add(l3);
            
            t2.setBounds(700, 10, 40, 30);
            add(t2);
            
            l1.setBounds(150, 150, 800, 60);
            add(l1);
           
            l2.setBounds(260, 210, 800, 60);
            add(l2);
           
            t1.setBounds(260, 300, 265, 40);
            add(t1);
           
            b1.setBounds(260, 380, 125, 50);
            add(b1);
           
            b2.setBounds(400, 380, 125, 50);
            add(b2);
           
            b3.setBounds(330, 440, 125, 50);
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
            System.out.println("String a ++"+a);
            System.out.println("String b ++"+b);
            
            
            if(e.getSource() == b1)
            {
               if(t1.getText().equals("")){
                 
                    JOptionPane.showMessageDialog(null,"Please enter the amount do you want to deposite");
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

                                if(e.getSource() == b1){
                                    if(!rs.next()){
                                        JOptionPane.showMessageDialog(null,"Incorrect Card PIN");

                                     }
                                    else{
                            
                                            //ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+b+"'");
                                            System.out.println("Inside code ++");
                                            String q5 = "select * from bank where pin = ?";

                                            PreparedStatement st5 = c1.c2().prepareStatement(q5);
                                            st5.setString(1,b);

                                             ResultSet rs1 = st5.executeQuery();
                                             System.out.println("ResultSet rs : "+ rs1);


                                            double balance = 100;

                                            if(rs1.next())
                                            {  
                                               System.out.println("Inside rs1.next()  : ");
                                               String pin = rs1.getString("pin");
                                               System.out.println("String pin : "+ pin);
                                               balance = rs1.getDouble("balance");
                                               System.out.println("balance = : "+ balance);

                                                double d = Double.parseDouble(a);
                                                System.out.println("double d : "+ d);
                                                balance+=d;
                                                System.out.println("balance += : "+ d);
                                                //String q1 = "insert into bank values('"+pin+"','"+d+"',null,'"+balance+"')";
                                                //c1.s.executeUpdate(q1);

                                               String q1 = "insert into bank values(?,?,?,?)";        
                                               PreparedStatement st1 = c1.c2().prepareStatement(q1);
                                               System.out.println("PreparedStatement st1 : "+ st1);
                                               st1.setString(1,pin);
                                               st1.setDouble(2,d);
                                               st1.setString(3,"");
                                               st1.setDouble(4,balance);

                                               st1.executeUpdate();
                                               System.out.println("query executed : ");
                                             }

                                            JOptionPane.showMessageDialog(null,"Rs. "+a+" Deposited successfully");

                                            new Transactions().setVisible(true);
                                            setVisible(false);
                    
                                    }
                                    
                                  }

                             }   
                     
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
        
        new Deposite().setVisible(true);
    }
    
}
