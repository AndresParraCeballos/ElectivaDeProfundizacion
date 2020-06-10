/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Andre
 */
public class ProductosFactura {
    
    private int idproductos_de_factura;
    private int factura_idfactura;
    private int producto_idproducto;
    private int profac_cantidad;
    private String profa_estado;

    public int getIdproductos_de_factura() {
        return idproductos_de_factura;
    }

    public void setIdproductos_de_factura(int idproductos_de_factura) {
        this.idproductos_de_factura = idproductos_de_factura;
    }

    public int getFactura_idfactura() {
        return factura_idfactura;
    }

    public void setFactura_idfactura(int factura_idfactura) {
        this.factura_idfactura = factura_idfactura;
    }

    public int getProducto_idproducto() {
        return producto_idproducto;
    }

    public void setProducto_idproducto(int producto_idproducto) {
        this.producto_idproducto = producto_idproducto;
    }

    public int getProfac_cantidad() {
        return profac_cantidad;
    }

    public void setProfac_cantidad(int profac_cantidad) {
        this.profac_cantidad = profac_cantidad;
    }

    public String getProfa_estado() {
        return profa_estado;
    }

    public void setProfa_estado(String profa_estado) {
        this.profa_estado = profa_estado;
    }
    
    
    
}
