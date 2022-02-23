
package com.mycompany.atmyox;

import java.sql.*;

public class Conn {
     
  Connection con;
  PreparedStatement s;

  public Conn(){
   
      
   /*try{
          
        
       //com.mysql.jdbc.Driver
       //Class.forName("com.mysql.cj.jdbc.Driver");
       //c = DriverManager.getConnection("jdbc:mysql:///yogeshjavaproject","root","");
      

    }catch(Exception e){
      
      e.printStackTrace();
      System.out.println("Inside Yogesh Conn = > "+e);
      
    }

  }*/
  }  
   
  public Connection c2( ) {
       
       try{
                Class.forName("com.mysql.cj.jdbc.Driver");  
                con = DriverManager.getConnection("jdbc:mysql:///yogeshjavaproject","root","");
       
       }catch( Exception e){
           e.printStackTrace();
       }
         
       return con;
   }
    
   
   
}
