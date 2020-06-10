/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionDAO;

import DAO.FormaPagoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import modelo.TipoPago;
import util.BaseDatos;

/**
 *
 * @author Andre
 */
public class TipoPagoImpl implements FormaPagoDAO{

    private PreparedStatement statement;
    private Connection conexion;
    BaseDatos db = new BaseDatos();
    @Override
    public List<TipoPago> getTiposdePagos() {
        ArrayList<TipoPago> datos = new ArrayList();
        
        
        
        try {
            String sql = "SELECT * FROM formaspagos";
            statement = db.getConexion().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                TipoPago tipopago = new TipoPago();                
                tipopago.setIdformaspagos(rs.getInt("idformaspagos"));
                tipopago.setForpa_descipcion(rs.getString("forpa_descipcion"));
                tipopago.setForpa_estado(rs.getString("forpa_estado"));

                
                datos.add(tipopago);
                
                
                
            }
            
            
        } catch (SQLException ex) {
            
        }
        return datos;
    }

    @Override
    public TipoPago getTipoPagobyId(int idTipoPago) {
        TipoPago datos =  new TipoPago();
        try {
            String sql = "SELECT * FROM formaspagos WHERE idformaspagos = ?";
            statement = db.getConexion().prepareStatement(sql);
            statement.setInt(1,idTipoPago);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                    
                           
                datos.setIdformaspagos(rs.getInt("idformaspagos"));
                datos.setForpa_descipcion(rs.getString("forpa_descipcion"));
                datos.setForpa_estado(rs.getString("forpa_estado"));

                
                
                
                
                
            }
            
            
        } catch (SQLException ex) {
            
        }
        return datos;
    }
    
}
