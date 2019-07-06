

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saief
 */
public  class connection {

  public Connection   conn;  


    public connection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/MeFclvGBL6","MeFclvGBL6","Nqge4cy7yR");
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    }

  

       
    
    
    
    
   
      
            
         

