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
public class Factura {
    
    private int idfactura;
    private String fecha;
    private int id_usuariovendedor;
    private int id_usuariocomprador;
    private int formaspagos_idformaspagos;

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_usuariovendedor() {
        return id_usuariovendedor;
    }

    public void setId_usuariovendedor(int id_usuariovendedor) {
        this.id_usuariovendedor = id_usuariovendedor;
    }

    public int getId_usuariocomprador() {
        return id_usuariocomprador;
    }

    public void setId_usuariocomprador(int id_usuariocomprador) {
        this.id_usuariocomprador = id_usuariocomprador;
    }

    public int getFormaspagos_idformaspagos() {
        return formaspagos_idformaspagos;
    }

    public void setFormaspagos_idformaspagos(int formaspagos_idformaspagos) {
        this.formaspagos_idformaspagos = formaspagos_idformaspagos;
    }
    
    
    
    
}
