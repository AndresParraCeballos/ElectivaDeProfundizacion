/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "productos_de_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosDeFactura.findAll", query = "SELECT p FROM ProductosDeFactura p")
    , @NamedQuery(name = "ProductosDeFactura.findByIdproductosDeFactura", query = "SELECT p FROM ProductosDeFactura p WHERE p.idproductosDeFactura = :idproductosDeFactura")
    , @NamedQuery(name = "ProductosDeFactura.findByProfacCantidad", query = "SELECT p FROM ProductosDeFactura p WHERE p.profacCantidad = :profacCantidad")
    , @NamedQuery(name = "ProductosDeFactura.findByProfaEstado", query = "SELECT p FROM ProductosDeFactura p WHERE p.profaEstado = :profaEstado")})
public class ProductosDeFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductos_de_factura")
    private Integer idproductosDeFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profac_cantidad")
    private int profacCantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "profa_estado")
    private String profaEstado;
    @JoinColumn(name = "factura_idfactura", referencedColumnName = "idfactura")
    @ManyToOne(optional = false)
    private Factura facturaIdfactura;
    @JoinColumn(name = "producto_idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto productoIdproducto;

    public ProductosDeFactura() {
    }

    public ProductosDeFactura(Integer idproductosDeFactura) {
        this.idproductosDeFactura = idproductosDeFactura;
    }

    public ProductosDeFactura(Integer idproductosDeFactura, int profacCantidad, String profaEstado) {
        this.idproductosDeFactura = idproductosDeFactura;
        this.profacCantidad = profacCantidad;
        this.profaEstado = profaEstado;
    }

    public Integer getIdproductosDeFactura() {
        return idproductosDeFactura;
    }

    public void setIdproductosDeFactura(Integer idproductosDeFactura) {
        this.idproductosDeFactura = idproductosDeFactura;
    }

    public int getProfacCantidad() {
        return profacCantidad;
    }

    public void setProfacCantidad(int profacCantidad) {
        this.profacCantidad = profacCantidad;
    }

    public String getProfaEstado() {
        return profaEstado;
    }

    public void setProfaEstado(String profaEstado) {
        this.profaEstado = profaEstado;
    }

    public Factura getFacturaIdfactura() {
        return facturaIdfactura;
    }

    public void setFacturaIdfactura(Factura facturaIdfactura) {
        this.facturaIdfactura = facturaIdfactura;
    }

    public Producto getProductoIdproducto() {
        return productoIdproducto;
    }

    public void setProductoIdproducto(Producto productoIdproducto) {
        this.productoIdproducto = productoIdproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductosDeFactura != null ? idproductosDeFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosDeFactura)) {
            return false;
        }
        ProductosDeFactura other = (ProductosDeFactura) object;
        if ((this.idproductosDeFactura == null && other.idproductosDeFactura != null) || (this.idproductosDeFactura != null && !this.idproductosDeFactura.equals(other.idproductosDeFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductosDeFactura[ idproductosDeFactura=" + idproductosDeFactura + " ]";
    }
    
}
