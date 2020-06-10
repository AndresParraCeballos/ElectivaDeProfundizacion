/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.Factura;
import modelo.Usuario;

/**
 *
 * @author Andre
 */
public interface FacturaDAO {
    
    public boolean GuardarFactura(Factura factura);
    public List<Factura> getFactura(int usuarioComprador);
    public Factura getFacturaById(int idfactura);
    public boolean editFactura(int factura);
    public boolean CancelarFactura(int factura);
}
