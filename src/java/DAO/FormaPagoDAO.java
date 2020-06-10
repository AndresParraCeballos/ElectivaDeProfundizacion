/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.TipoPago;

/**
 *
 * @author Andre
 */
public interface FormaPagoDAO {
    
    public List<TipoPago> getTiposdePagos();
    public TipoPago getTipoPagobyId(int idTipoPago);
    
    
}
