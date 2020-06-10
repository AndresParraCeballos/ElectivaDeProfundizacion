/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import modelo.Estados;
import modelo.Usuario;
import util.BaseDatos;
import DAO.UsuarioDAO;

/**
 *
 * @author Andre
 */


public class UsuarioDAOImplementacion  implements UsuarioDAO{
    private PreparedStatement statement;
    private Connection conexion;
    BaseDatos db = new BaseDatos();
    String respuesta = "";
    
   
    @Override
    
    
    public String GuardarUsuario(Usuario cliente) {
        String sql = "INSERT INTO usuario (user_nombres,user_apellidos,user_tipo,user_telefono,user_identificacion,user_email,user_password)VALUES ( ? , ? , ? , ? , ? , ? , ?  )";
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setString(1, cliente.getNombres());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3,cliente.getTipo());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5,cliente.getCedula());
            statement.setString(6, cliente.getEmail());
            statement.setString(7, cliente.getPassword());
            
            int fila = statement.executeUpdate();
            conexion.commit();
            statement.close();
            respuesta = "Se registraron "+ fila + " nuevo elemento ";
            conexion.close();
            
            
        } catch (Exception e) {
        }finally{
            
        }
        return respuesta;
        
    } 

    @Override
    public boolean login(String usuario, String contraseña) {
        ResultSet rs  = null;
        try {
            String sql  ="select * from usuario where user_email = ? AND  user_password = ?";
            statement = db.getConexion().prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setString(2, contraseña);
            rs = statement.executeQuery();  
            if (rs.absolute(1)) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {
        ResultSet rs  = null;
        Usuario user = new Usuario();
        try {
            String sql  ="select * from usuario where user_email = ?";
            statement = db.getConexion().prepareStatement(sql);
            statement.setString(1, email);
            rs = statement.executeQuery();  
            user.setId(rs.getInt("idusuario"));
            user.setApellidos(rs.getString("user_apellidos"));
            user.setNombres(rs.getString("user_nombres"));
            user.setTipo("user_tipo");
            user.setTelefono("user_telefono");
            user.setCedula(rs.getString("user_identificacion"));
            user.setEmail(rs.getString("user_email"));
            user.setPassword(rs.getString("user_password"));
            
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public Usuario getUsuarioById(int id) {
       ResultSet rs  = null;
        Usuario user = new Usuario();
        try {
            String sql  ="select * from usuario where idusuario = ?";
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();  
            user.setId(rs.getInt("idusuario"));
            user.setApellidos(rs.getString("user_apellidos"));
            user.setNombres(rs.getString("user_nombres"));
            user.setTipo("user_tipo");
            user.setTelefono("user_telefono");
            user.setCedula(rs.getString("user_identificacion"));
            user.setEmail(rs.getString("user_email"));
            user.setPassword(rs.getString("user_password"));
            
        } catch (Exception e) {
        }
        return user;
    }
    
    



    
}
