/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author Andre
 */
public interface ProductoDAO {
    
    public String addProductos(Producto producto);
    public String editProducto (int cantidad,int idproducto);
    public List<Producto> mostrarProductos();
    public Producto getProductoById(int id);
}

