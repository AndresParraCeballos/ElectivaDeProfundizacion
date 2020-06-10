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
public class Producto {
    
    private int idproducto;
    private String pro_descripcion;
    private String pro_nombre;
    private String pro_marca;
    private String pro_codigobarra;
    private int pro_cantidad_disponible;
    private int pro_precio;

    public int getPro_precio() {
        return pro_precio;
    }

    public void setPro_precio(int pro_precio) {
        this.pro_precio = pro_precio;
    }
    

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getPro_descripcion() {
        return pro_descripcion;
    }

    public void setPro_descripcion(String pro_descripcion) {
        this.pro_descripcion = pro_descripcion;
    }

    public String getPro_nombre() {
        return pro_nombre;
    }

    public void setPro_nombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public String getPro_marca() {
        return pro_marca;
    }

    public void setPro_marca(String pro_marca) {
        this.pro_marca = pro_marca;
    }

    public String getPro_codigobarra() {
        return pro_codigobarra;
    }

    public void setPro_codigobarra(String pro_codigobarra) {
        this.pro_codigobarra = pro_codigobarra;
    }

    public int getPro_cantidad_disponible() {
        return pro_cantidad_disponible;
    }

    public void setPro_cantidad_disponible(int pro_cantidad_disponible) {
        this.pro_cantidad_disponible = pro_cantidad_disponible;
    }
    
    
    
}
