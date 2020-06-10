/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionDAO;

import DAO.ProductoDAO;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;
import util.BaseDatos;

/**
 *
 * @author Andre
 */
public class ProductoDAOImplementacion implements ProductoDAO{

    private PreparedStatement statement;
    private Connection conexion;
    BaseDatos db = new BaseDatos();
    
    String respuesta = "";
    
    public static void main(String[] args) {
        ProductoDAOImplementacion da = new ProductoDAOImplementacion();
        List<Producto> datos = new ArrayList();
        datos = da.mostrarProductos();
        for (Producto dato : datos) {
            
            System.out.println(dato.getPro_nombre());
            System.out.println(dato.getIdproducto());
            
        }
    }
    @Override
    public String addProductos(Producto producto) {
        String sql = "INSERT INTO producto (pro_descripcion,pro_nombre,pro_marca,pro_codigobarra,pro_cantidad_disponible,pro_precio)VALUES ( ? , ? , ? , ? , ?,?)";
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setString(1, producto.getPro_descripcion());
            statement.setString(2, producto.getPro_nombre());
            statement.setString(3,producto.getPro_marca());
            statement.setString(4, producto.getPro_codigobarra());
            statement.setInt(5,producto.getPro_cantidad_disponible());
            statement.setInt(6,producto.getPro_precio());
           
            
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
    public List<Producto> mostrarProductos() {
        ArrayList<Producto> datos = new ArrayList();
        
        
        
        try {
            String sql = "SELECT * FROM producto";
            statement = db.getConexion().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                Producto producto = new Producto();                
                producto.setIdproducto(rs.getInt("idproducto"));
                producto.setPro_descripcion(rs.getString("pro_descripcion"));
                producto.setPro_nombre(rs.getString("pro_nombre"));
                producto.setPro_marca(rs.getString("pro_marca"));
                producto.setPro_codigobarra(rs.getString("pro_codigobarra"));
                producto.setPro_cantidad_disponible(rs.getInt("pro_cantidad_disponible"));
                producto.setPro_precio(rs.getInt("pro_precio"));

                
                datos.add(producto);
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
        
        
    }

    @Override
    public String editProducto(int cantidad,int idproducto) {
        String sql = "UPDATE producto SET pro_cantidad_disponible= ? WHERE idproducto= ?";
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1,cantidad);
            statement.setInt(2, idproducto);           
           
            
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
    public Producto getProductoById(int id) {
         Producto datos = new Producto();
        
        
        
        try {
            String sql = "SELECT * FROM producto WHERE idproducto = ?";
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                             
                datos.setIdproducto(rs.getInt("idproducto"));
                datos.setPro_descripcion(rs.getString("pro_descripcion"));
                datos.setPro_nombre(rs.getString("pro_nombre"));
                datos.setPro_marca(rs.getString("pro_marca"));
                datos.setPro_codigobarra(rs.getString("pro_codigobarra"));
                datos.setPro_cantidad_disponible(rs.getInt("pro_cantidad_disponible"));
                datos.setPro_precio(rs.getInt("pro_precio"));

                
                
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    
}
