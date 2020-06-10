/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionDAO;

import DAO.FacturaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import modelo.Factura;
import modelo.Producto;
import modelo.Usuario;
import util.BaseDatos;

/**
 *
 * @author Andre
 */
public class FacturaImpl implements FacturaDAO{

    
    private Connection conexion;
    BaseDatos db = new BaseDatos();
    
    boolean respuesta =false;
    private PreparedStatement statement;
    
    RequestDispatcher rd = null;
    
    
    @Override
    public boolean GuardarFactura(Factura factura) {
        String sql = "INSERT INTO factura (fac_fecha,id_usuariovendedor,id_usuariocomprador,formaspagos_idformaspagos)VALUES ( ? , ? , ? , ?   )";
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setString(1, factura.getFecha());
            statement.setInt(2, factura.getId_usuariovendedor());
            statement.setInt(3, factura.getId_usuariocomprador());
            statement.setInt(4, factura.getFormaspagos_idformaspagos());
           
            
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
    public List<Factura> getFactura(int usuarioComprador) {
        ArrayList<Factura> datos = new ArrayList();
        try {
            String sql = "SELECT * FROM factura WHERE id_usuariocomprador = ? AND fac_estado = 'X'";
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1, usuarioComprador);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                Factura factura = new Factura();  
                factura.setIdfactura(rs.getInt("idfactura"));
                factura.setFecha(rs.getString("fac_fecha"));
                factura.setFormaspagos_idformaspagos(rs.getInt("formaspagos_idformaspagos"));
                factura.setId_usuariocomprador(rs.getInt("id_usuariovendedor"));
                factura.setId_usuariocomprador(rs.getInt("id_usuariocomprador"));
                

                
                datos.add(factura);
                
                
                
            }
            
            
        } catch (SQLException ex) {
            
        }
        return datos;
    }

    @Override
    public Factura getFacturaById(int idfactura) {
        Factura datos = new Factura();
        try {
            String sql = "SELECT * FROM factura WHERE 	idfactura = ?";
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1, idfactura);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                 
                datos.setIdfactura(rs.getInt("idfactura"));
                datos.setFecha(rs.getString("fac_fecha"));
                datos.setFormaspagos_idformaspagos(rs.getInt("formaspagos_idformaspagos"));
                datos.setId_usuariocomprador(rs.getInt("id_usuariovendedor"));
                datos.setId_usuariocomprador(rs.getInt("id_usuariocomprador"));    
            }
            
            
        } catch (SQLException ex) {
            
        }
        return datos;
    }

    @Override
    public boolean editFactura(int factura) {
        String sql = "UPDATE factura SET fac_estado= 'L' WHERE idfactura= ?";
        boolean res = false;
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1,factura);
                      
           
            
            int fila = statement.executeUpdate();
            conexion.commit();
            statement.close();
            
            conexion.close();
            
            if (fila != 0) {
                res = true;
            }else{
                res = false;
            }
            
            
        } catch (Exception e) {
        }finally{
            
        }
        return respuesta;
    }

    @Override
    public boolean CancelarFactura(int factura) {
        String sql = "UPDATE factura SET fac_estado= '/' WHERE idfactura= ?";
        boolean res = false;
        try {
           
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1,factura);
                      
           
            
            int fila = statement.executeUpdate();
            conexion.commit();
            statement.close();
            
            conexion.close();
            
            if (fila != 0) {
                res = true;
            }else{
                res = false;
            }
            
            
        } catch (Exception e) {
        }finally{
            
        }
        return respuesta;
    }
    
}
