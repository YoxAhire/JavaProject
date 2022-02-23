
package com.mycompany.atmyox;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener  {

    JLabel l1,l2;
    JButton b1,b2,b3,b4,b5,b6,b7,b8;
    JTextField t1;
    
    FastCash(){
                
        
            System.out.println("Inside FastCash()");
            
            setFont(new Font("System",Font.BOLD,22));
            Font f = getFont();
            FontMetrics fm = getFontMetrics(f);
            int x = fm.stringWidth("FAST CASH:");
            int y = fm.stringWidth(" ");
            int z = getWidth() - x;
            int w = z/y;
            String pad = "";
            //for (i=0; i!=w; i++) pad +=" ";
            pad = String.format("%"+w+"s",pad);
            setTitle(pad+"FAST CASH");

            l1 = new JLabel("SELECT WITHDRAWL AMOUNT:");
            l1.setFont(new Font("System",Font.BOLD,30));
            
            l2 = new JLabel("ENTER PIN:");
            l2.setFont(new Font("System",Font.BOLD,13));
            
            b1 = new JButton("Rs 100");
            b1.setFont(new Font("System",Font.BOLD,18));
            b1.setBackground(Color.BLACK);
            b1.setForeground(Color.WHITE);
           
            b2 = new JButton("Rs 500");
            b2.setFont(new Font("System",Font.BOLD,18));
            b2.setBackground(Color.BLACK);
            b2.setForeground(Color.WHITE);
           
            b3 = new JButton("Rs 1000");
            b3.setFont(new Font("System",Font.BOLD,18));
            b3.setBackground(Color.BLACK);
            b3.setForeground(Color.WHITE);
           
            b4 = new JButton("Rs 2000");
            b4.setFont(new Font("System",Font.BOLD,18));
            b4.setBackground(Color.BLACK);
            b4.setForeground(Color.WHITE);
           
            b5 = new JButton("Rs 5000");
            b5.setFont(new Font("System",Font.BOLD,18));
            b5.setBackground(Color.BLACK);
            b5.setForeground(Color.WHITE);
           
            b6 = new JButton("Rs 10000");
            b6.setFont(new Font("System",Font.BOLD,18));
            b6.setBackground(Color.BLACK);
            b6.setForeground(Color.WHITE);
           
            b7 = new JButton("BACK");
            b7.setFont(new Font("System",Font.BOLD,18));
            b7.setBackground(Color.BLACK);
            b7.setForeground(Color.WHITE);
           
            t1 = new JTextField();
            t1.setFont(new Font("Raleway",Font.BOLD,14));
            
            setLayout(null);
            
            l2.setBounds(600, 10, 60, 40);
            add(l2);
            
            t1.setBounds(710, 10, 60, 40);
            add(t1);
            
            l1.setBounds(100, 100, 700, 40);
            add(l1);
            
            b1.setBounds(40, 250, 300, 60);
            add(b1);
            
            b2.setBounds(440, 250, 300, 60);
            add(b2);
            
            b3.setBounds(40, 360, 300, 60);
            add(b3);
            
            b4.setBounds(440, 360, 300, 60);
            add(b4);
            
            b5.setBounds(40, 470, 300, 60);
            add(b5);
            
            b6.setBounds(440, 470, 300, 60);
            add(b6);
            
            b7.setBounds(240, 600, 300, 60);
            add(b7);
            
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b4.addActionListener(this);
            b5.addActionListener(this);
            b6.addActionListener(this);
            b7.addActionListener(this);
            
           getContentPane().setBackground(Color.WHITE);

           setSize(850,850);
           setLocation(500,90);
           setVisible(true);
          
        System.out.println("Outside FastCash()");    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        System.out.println("Inside actionPerformed()");
        try{
        
               String a = t1.getText();
               double balance = 0;
               boolean Error = false;
               //boolean Checkbalance = false;
               Conn c1 = new Conn();
               
               if(t1.getText().isEmpty()){
                        
                    Error = true;
                    JOptionPane.showMessageDialog(null,"Please Enter Pin");          
                }
               if(ae.getSource() == b1 && Error == false )
               {
                        System.out.println("Inside connection");
                       
                            String q = "select * from bank where pin  = ?";

                            PreparedStatement st = c1.c2().prepareStatement(q);
                            st.setString(1,a);

                            ResultSet rs = st.executeQuery();
                             if(rs.next()){

                                 System.out.println("Inside correct pin");

                                  String pin = rs.getString("pin");
                                  balance = rs.getDouble("balance");
                                  System.out.println("balance : "+balance);
                                   balance-=100;
                                   System.out.println("balance after 100 fast cash : "+balance);
                                   if(balance <= 0)
                                   {
                                    
                                     JOptionPane.showMessageDialog(null,"Account has not enough balance");
                                    
                                   }
                                   else{
                                        //String q1 = "insert into bank values('"+pin+"',null,100,'"+balance+"')";
                                        String q1 = "insert into bank values(?,?,?,?)";
                                        PreparedStatement st1 = c1.c2().prepareStatement(q1);
                                        st1.setString(1,a);
                                        st1.setString(2,"");
                                        st1.setDouble(3,100);
                                        st1.setDouble(4,balance);
                                        st1.executeUpdate();

                                        JOptionPane.showMessageDialog(null,"Rs. "+100+"  Debited Successfully");
                                        new Transactions().setVisible(true);
                                        setVisible(false);
                                   }

                               }
                         
               }
               else if(ae.getSource() == b2 && Error == false)
               {
                  String q = "select * from bank where pin  = ?";

                    PreparedStatement st = c1.c2().prepareStatement(q);
                    st.setString(1,a);

                    ResultSet rs = st.executeQuery();
                     if(rs.next()){

                         System.out.println("Inside correct pin");

                          String pin = rs.getString("pin");
                          balance = rs.getDouble("balance");

                           balance-=500;
                        
                        if(balance <= 0)
                        {
                                    
                            JOptionPane.showMessageDialog(null,"Account has not enough balance");
                                    
                        }
                        else{
                           //String q1 = "insert into bank values('"+pin+"',null,100,'"+balance+"')";
                         String q1 = "insert into bank values(?,?,?,?)";
                         PreparedStatement st1 = c1.c2().prepareStatement(q1);
                         st1.setString(1,a);
                         st1.setString(2,"");
                         st1.setDouble(3,500);
                         st1.setDouble(4,balance);
                         st1.executeUpdate();

                         JOptionPane.showMessageDialog(null,"Rs. "+500+"  Debited Successfully");
                         new Transactions().setVisible(true);
                         setVisible(false);

                        }  
                    } 

                   
               }
               else if(ae.getSource() == b3 && Error == false)
               {  
                   
                            String q = "select * from bank where pin  = ?";

                            PreparedStatement st = c1.c2().prepareStatement(q);
                            st.setString(1,a);

                            ResultSet rs = st.executeQuery();
                             if(rs.next()){

                                 System.out.println("Inside correct pin");

                                  String pin = rs.getString("pin");
                                  balance = rs.getDouble("balance");

                                   balance-=1000;
                               if(balance <= 0)
                               {
                                    
                                      JOptionPane.showMessageDialog(null,"Account has not enough balance");
                                    
                                }else{
                                        //String q1 = "insert into bank values('"+pin+"',null,100,'"+balance+"')";
                                      String q1 = "insert into bank values(?,?,?,?)";
                                      PreparedStatement st1 = c1.c2().prepareStatement(q1);
                                      st1.setString(1,a);
                                      st1.setString(2,"");
                                      st1.setDouble(3,1000);
                                      st1.setDouble(4,balance);
                                      st1.executeUpdate();

                                      JOptionPane.showMessageDialog(null,"Rs. "+1000+"  Debited Successfully");
                                      new Transactions().setVisible(true);
                                      setVisible(false);
                                }
                             
                       } 

               }
               else if(ae.getSource() == b4 && Error == false)
               {
                   
                            String q = "select * from bank where pin  = ?";

                             PreparedStatement st = c1.c2().prepareStatement(q);
                             st.setString(1,a);

                             ResultSet rs = st.executeQuery();
                              if(rs.next()){

                                  System.out.println("Inside correct pin");

                                   String pin = rs.getString("pin");
                                   balance = rs.getDouble("balance");

                                    balance-=2000;
                            
                          if(balance <= 0)
                            {
                                    
                                JOptionPane.showMessageDialog(null,"Account has not enough balance");
                                    
                            }
                            else{
                                    //String q1 = "insert into bank values('"+pin+"',null,100,'"+balance+"')";
                                  String q1 = "insert into bank values(?,?,?,?)";
                                  PreparedStatement st1 = c1.c2().prepareStatement(q1);
                                  st1.setString(1,a);
                                  st1.setString(2,"");
                                  st1.setDouble(3,2000);
                                  st1.setDouble(4,balance);
                                  st1.executeUpdate();

                                  JOptionPane.showMessageDialog(null,"Rs. "+2000+"  Debited Successfully");
                                  new Transactions().setVisible(true);
                                  setVisible(false);
                              }

                       } 

               }
               else if(ae.getSource() == b5 && Error == false)
               {
                   
                            String q = "select * from bank where pin  = ?";

                             PreparedStatement st = c1.c2().prepareStatement(q);
                             st.setString(1,a);

                             ResultSet rs = st.executeQuery();
                              if(rs.next()){

                                  System.out.println("Inside correct pin");

                                   String pin = rs.getString("pin");
                                   balance = rs.getDouble("balance");

                                    balance-=5000;
                            
                          if(balance <= 0)
                            {
                                    
                                JOptionPane.showMessageDialog(null,"Account has not enough balance");
                                    
                             }
                             else{ 
                                    //String q1 = "insert into bank values('"+pin+"',null,100,'"+balance+"')";
                                    String q1 = "insert into bank values(?,?,?,?)";
                                    PreparedStatement st1 = c1.c2().prepareStatement(q1);
                                    st1.setString(1,a);
                                    st1.setString(2,"");
                                    st1.setDouble(3,5000);
                                    st1.setDouble(4,balance);
                                    st1.executeUpdate();

                                    JOptionPane.showMessageDialog(null,"Rs. "+5000+"  Debited Successfully");
                                    new Transactions().setVisible(true);
                                    setVisible(false);

                              }
                       } 
 
               }
               else if(ae.getSource() == b6 && Error == false)
               {
                   
                            String q = "select * from bank where pin  = ?";

                            PreparedStatement st = c1.c2().prepareStatement(q);
                            st.setString(1,a);

                            ResultSet rs = st.executeQuery();
                             if(rs.next()){

                                 System.out.println("Inside correct pin");

                                  String pin = rs.getString("pin");
                                  balance = rs.getDouble("balance");

                                   balance-=10000;
                                
                            if(balance <= 0)
                             {

                               JOptionPane.showMessageDialog(null,"Account has not enough balance");

                              }
                              else{ 
                                        //String q1 = "insert into bank values('"+pin+"',null,100,'"+balance+"')";
                                      String q1 = "insert into bank values(?,?,?,?)";
                                      PreparedStatement st1 = c1.c2().prepareStatement(q1);
                                      st1.setString(1,a);
                                      st1.setString(2,"");
                                      st1.setDouble(3,10000);
                                      st1.setDouble(4,balance);
                                      st1.executeUpdate();

                                      JOptionPane.showMessageDialog(null,"Rs. "+10000+"  Debited Successfully");
                                      new Transactions().setVisible(true);
                                      setVisible(false);

                             }
                       } 

               }
               else if(ae.getSource() == b7 && Error == false)
               {   
                  new Transactions().setVisible(true);
                  setVisible(false);
               }
            
        }catch(Exception ex){
          ex.printStackTrace();
          System.out.println("error: "+ex);
        }
        
        System.out.println("outside actionperformed()");
    }
    
    public static void main(String[] args) {
        new FastCash().setVisible(true);
        System.out.println("Inside fast Cash main");
    }
}
