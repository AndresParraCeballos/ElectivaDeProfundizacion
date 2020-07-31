/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto")
    , @NamedQuery(name = "Producto.findByProDescripcion", query = "SELECT p FROM Producto p WHERE p.proDescripcion = :proDescripcion")
    , @NamedQuery(name = "Producto.findByProNombre", query = "SELECT p FROM Producto p WHERE p.proNombre = :proNombre")
    , @NamedQuery(name = "Producto.findByProMarca", query = "SELECT p FROM Producto p WHERE p.proMarca = :proMarca")
    , @NamedQuery(name = "Producto.findByProCodigobarra", query = "SELECT p FROM Producto p WHERE p.proCodigobarra = :proCodigobarra")
    , @NamedQuery(name = "Producto.findByProCantidadDisponible", query = "SELECT p FROM Producto p WHERE p.proCantidadDisponible = :proCantidadDisponible")
    , @NamedQuery(name = "Producto.findByProPrecio", query = "SELECT p FROM Producto p WHERE p.proPrecio = :proPrecio")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pro_descripcion")
    private String proDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pro_nombre")
    private String proNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pro_marca")
    private String proMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pro_codigobarra")
    private String proCodigobarra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pro_cantidad_disponible")
    private int proCantidadDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pro_precio")
    private int proPrecio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoIdproducto")
    private Collection<ProductosDeFactura> productosDeFacturaCollection;

    public Producto() {
    }

    public Producto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Producto(Integer idproducto, String proDescripcion, String proNombre, String proMarca, String proCodigobarra, int proCantidadDisponible, int proPrecio) {
        this.idproducto = idproducto;
        this.proDescripcion = proDescripcion;
        this.proNombre = proNombre;
        this.proMarca = proMarca;
        this.proCodigobarra = proCodigobarra;
        this.proCantidadDisponible = proCantidadDisponible;
        this.proPrecio = proPrecio;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getProDescripcion() {
        return proDescripcion;
    }

    public void setProDescripcion(String proDescripcion) {
        this.proDescripcion = proDescripcion;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProMarca() {
        return proMarca;
    }

    public void setProMarca(String proMarca) {
        this.proMarca = proMarca;
    }

    public String getProCodigobarra() {
        return proCodigobarra;
    }

    public void setProCodigobarra(String proCodigobarra) {
        this.proCodigobarra = proCodigobarra;
    }

    public int getProCantidadDisponible() {
        return proCantidadDisponible;
    }

    public void setProCantidadDisponible(int proCantidadDisponible) {
        this.proCantidadDisponible = proCantidadDisponible;
    }

    public int getProPrecio() {
        return proPrecio;
    }

    public void setProPrecio(int proPrecio) {
        this.proPrecio = proPrecio;
    }

    @XmlTransient
    public Collection<ProductosDeFactura> getProductosDeFacturaCollection() {
        return productosDeFacturaCollection;
    }

    public void setProductosDeFacturaCollection(Collection<ProductosDeFactura> productosDeFacturaCollection) {
        this.productosDeFacturaCollection = productosDeFacturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Producto[ idproducto=" + idproducto + " ]";
    }
    
}
