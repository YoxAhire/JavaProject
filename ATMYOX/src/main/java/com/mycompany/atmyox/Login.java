
package com.mycompany.atmyox;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public  class Login extends JFrame implements ActionListener{

  JLabel l1,l2,l3;
  JTextField tf1;
  JPasswordField pf2;
  JButton b1,b2,b3;

  Login(){
  
  // move text to corner
  System.out.println("Inside Login() ");
  
   setFont(new Font("System",Font.BOLD,22));
   Font f = getFont();
   FontMetrics fm = getFontMetrics(f);
   int x = fm.stringWidth("aUTOMATED TELLER MACHINE");
   int y = fm.stringWidth(" ");
   int z = getWidth() - x;
   int w = z/y;
   String pad = "";
   //for (i=0; i!=w; i++) pad +=" ";
   pad = String.format("%"+w+"s",pad);
   setTitle(pad+"AUTOMATED TELLER MACHINE");
  
   l1 = new JLabel("Welcome To ATM");
   l1.setFont(new Font("Osward",Font.BOLD,30));

   l2 = new JLabel("Card NO:");
   l2.setFont(new Font("Raleway",Font.BOLD,20));

   l3 = new JLabel("PIN:");
   l3.setFont(new Font("Raleway",Font.BOLD,20));

   tf1 = new JTextField(15);
   pf2 = new JPasswordField(15);
  
   b1 = new JButton("SIGN IN");
   b1.setBackground(Color.BLACK);
   b1.setForeground(Color.WHITE);

   b2 = new JButton("CLEAR");
   b2.setBackground(Color.BLACK);
   b2.setForeground(Color.WHITE);

   b3 = new JButton("SIGN UP");
   b3.setBackground(Color.BLACK);
   b3.setForeground(Color.WHITE);

   setLayout(null);
  
   l1.setBounds(175,50,450,200);
   add(l1);
   
   l2.setBounds(125,150,375,200);
   add(l2);

   l3.setBounds(125,225,375,200);
   add(l3);

   
   tf1.setFont(new Font("Arial", Font.BOLD, 14));
   tf1.setBounds(300,235,230,30);
   add(tf1);

   pf2.setFont(new Font("Arial", Font.BOLD, 14));
   pf2.setBounds(300,300,100,30);
   add(pf2);

   b1.setFont(new Font("Arial", Font.BOLD, 14));
   b1.setBounds(300,400,100,30);
   add(b1);

   b2.setFont(new Font("Arial", Font.BOLD, 14));
   b2.setBounds(430,400,100,30);
   add(b2);
   
   b3.setFont(new Font("Arial", Font.BOLD, 14));
   b3.setBounds(300,450,230,30);
   add(b3);

   b1.addActionListener(this);
   b2.addActionListener(this);
   b3.addActionListener(this);

   getContentPane().setBackground(Color.WHITE);

   setSize(750,750);
   setLocation(500,200);
   setVisible(true);

   System.out.println("Outside Login() ");
  }  
    /**,l
     *
     * @param ae
     */
    public void actionPerformed(ActionEvent ae){
        
        System.out.println("Inside actionPerformed");
        try{ 
           System.out.println("Inside actionPerformed try block");
             
           if(ae.getSource() == b2){

             tf1.setText("");
             pf2.setText("");
            }
           else if(ae.getSource() == b3){
              
                    System.out.println("Inside code b3 has pressed");
                    new Signup().setVisible(true);
                    setVisible(false);
                    System.out.println("Outside code b3 has pressed");
          }
           else{
                   if((tf1.getText().isEmpty() || pf2.getPassword().equals("")) &&(ae.getSource() == b1) ){
                    
                       JOptionPane.showMessageDialog(null,"Please enter Card Number or Password");
  
                   }
                   else{
                               
                                System.out.println("code flow here = ");
                       
                                Conn c1 = new Conn();
                                
                               //Class.forName("com.mysql.cj.jdbc.Driver");  
                               //Connection con=DriverManager.getConnection("jdbc:mysql:///yogeshjavaproject","root","");  
  
                                String a = tf1.getText();
                                String b = pf2.getPassword().toString();
                                
                                System.out.println("a : "+a+""+"     b : "+b);
                                //String q = "select * from login where cardno = '"+a+"' and pin = '"+b+"' ";
                                //String q = "select * from login where cardno = ? and pin = ?";
                                String q = "select * from login where cardno = ?";
                                 
                                PreparedStatement st = c1.c2().prepareStatement(q);
                                st.setString(1,a);
                                //st.setString(2,b);
                                
                                //ResultSet rs = c1.s.executeQuery(q);
                                ResultSet rs = st.executeQuery();

                                if(ae.getSource() == b1){
                                  if(rs.next()){
                                    new Transactions().setVisible(true);
                                    setVisible(false);
                                   }else{

                                     JOptionPane.showMessageDialog(null,"Incorrect Card Number or Password");
                                   }
                                }

                        }
               
               
                    
           } 
           
    }catch(Exception e){
      
       e.printStackTrace();
       System.out.println("Error: "+e);
    }
    
  }
    
    
    public static void main(String[] args) {
        
        System.out.println("Inside Login main");
        new Login().setVisible(true);
    }
    
}
