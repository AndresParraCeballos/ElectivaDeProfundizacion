/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Andre
 */
public class BaseDatos {
    
    
    
    private String usuario = "root";
    private String contraseña = "";
    private String host = "localhost";
    private String port = "3306";
    private String database = "tienda";
    private String classname = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://"+host+":"+port+"/"+database; 
    
    private Connection con;
    
    public BaseDatos(){
        try {
            Class.forName(classname);
            con = DriverManager.getConnection(url,usuario, contraseña);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR"+e);
        }catch (SQLException w){
            System.err.println("ERROR"+w);
        }
       
    }
    
     public Connection getConexion(){
            return con;
        }
     
     
  
   
    
}
