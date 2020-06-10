/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionDAO;

import DAO.ProductosFacturaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import modelo.ProductosFactura;
import util.BaseDatos;

/**
 *
 * @author Andre
 */
public class ProductosFacturaImpl implements ProductosFacturaDAO{

    private Connection conexion;
    BaseDatos db = new BaseDatos();
    
    boolean respuesta =false;
    private PreparedStatement statement;
    
    
    @Override
    public boolean addFacturaProductos(ProductosFactura FacturaProductos) {
        String sql = "INSERT INTO productos_de_factura (factura_idfactura,producto_idproducto,profac_cantidad)VALUES ( ? , ? , ? )";
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1, FacturaProductos.getFactura_idfactura());
            statement.setInt(2, FacturaProductos.getProducto_idproducto());
            statement.setInt(3, FacturaProductos.getProfac_cantidad());
 
           
            
            int fila = statement.executeUpdate();
            conexion.commit();
            statement.close();
            respuesta = true;
            conexion.close();
            
            
        } catch (Exception e) {
            respuesta = false;
        }finally{
            
        }
        return respuesta;
    }

    @Override
    public boolean deleteFacturaProductos(int producto_idproducto) {
        String sql = "DELETE FROM productos_de_factura WHERE producto_idproducto = ? ";
        int res = 0;
        boolean ress = false;
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1, producto_idproducto);
            res = statement.executeUpdate();
            if (res==1) {
                ress=true;
            }else{
                ress = false;
            }
            
        }catch(Exception e){
            
        }
        return ress;
        
    }

    @Override
    public List<ProductosFactura> getFacturaProductos(int facturaid) {
        ArrayList<ProductosFactura> datos = new ArrayList();
        
        
        
        try {
            String sql = "SELECT * FROM productos_de_factura WHERE factura_idfactura = ?";
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1, facturaid);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                ProductosFactura producto = new ProductosFactura();                
                producto.setIdproductos_de_factura(rs.getInt("idproductos_de_factura"));
                producto.setFactura_idfactura(rs.getInt("factura_idfactura"));
                producto.setProducto_idproducto(rs.getInt("producto_idproducto"));
                producto.setProfac_cantidad(rs.getInt("profac_cantidad"));
                producto.setProfa_estado(rs.getString("profa_estado"));
                

                
                datos.add(producto);
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    @Override
    public List<ProductosFactura> getTodos() {
        ArrayList<ProductosFactura> datos = new ArrayList();
        
        
        
        try {
            String sql = "SELECT * FROM productos_de_factura ";
            statement = db.getConexion().prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                ProductosFactura producto = new ProductosFactura();                
                producto.setIdproductos_de_factura(rs.getInt("idproductos_de_factura"));
                producto.setFactura_idfactura(rs.getInt("factura_idfactura"));
                producto.setProducto_idproducto(rs.getInt("producto_idproducto"));
                producto.setProfac_cantidad(rs.getInt("profac_cantidad"));
                producto.setProfa_estado(rs.getString("profa_estado"));
                

                
                datos.add(producto);
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    @Override
    public boolean editProductosFactura(int profac_cantidad,int idProducto) {
        String sql = "UPDATE productos_de_factura SET profac_cantidad = ? WHERE producto_idproducto= ?";
        boolean respuesta = false;
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1,profac_cantidad);
            statement.setInt(2, idProducto);           
           
            
            int fila = statement.executeUpdate();
            conexion.commit();
            statement.close();
            
            conexion.close();
            if (fila != 0) {
                respuesta = true;
            }
            
            
        } catch (Exception e) {
        }finally{
            
        }
        return respuesta;
    }
    
    
}
