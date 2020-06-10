/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.Producto;
import modelo.ProductosFactura;

/**
 *
 * @author Andre
 */
public interface ProductosFacturaDAO {
    public boolean addFacturaProductos(ProductosFactura producto);
    public boolean deleteFacturaProductos(int idFacturaProductos);
    public List<ProductosFactura> getFacturaProductos(int facturaid);
    public List<ProductosFactura> getTodos ();
    public boolean editProductosFactura (int profac_cantidad,int idProducto);
}
