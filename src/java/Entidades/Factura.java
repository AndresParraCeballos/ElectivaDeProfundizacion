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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByIdfactura", query = "SELECT f FROM Factura f WHERE f.idfactura = :idfactura")
    , @NamedQuery(name = "Factura.findByFacFecha", query = "SELECT f FROM Factura f WHERE f.facFecha = :facFecha")
    , @NamedQuery(name = "Factura.findByFacEstado", query = "SELECT f FROM Factura f WHERE f.facEstado = :facEstado")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfactura")
    private Integer idfactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fac_fecha")
    private String facFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "fac_estado")
    private String facEstado;
    @JoinColumn(name = "formaspagos_idformaspagos", referencedColumnName = "idformaspagos")
    @ManyToOne(optional = false)
    private Formaspagos formaspagosIdformaspagos;
    @JoinColumn(name = "id_usuariovendedor", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idUsuariovendedor;
    @JoinColumn(name = "id_usuariocomprador", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idUsuariocomprador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaIdfactura")
    private Collection<ProductosDeFactura> productosDeFacturaCollection;

    public Factura() {
    }

    public Factura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Factura(Integer idfactura, String facFecha, String facEstado) {
        this.idfactura = idfactura;
        this.facFecha = facFecha;
        this.facEstado = facEstado;
    }

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public String getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(String facFecha) {
        this.facFecha = facFecha;
    }

    public String getFacEstado() {
        return facEstado;
    }

    public void setFacEstado(String facEstado) {
        this.facEstado = facEstado;
    }

    public Formaspagos getFormaspagosIdformaspagos() {
        return formaspagosIdformaspagos;
    }

    public void setFormaspagosIdformaspagos(Formaspagos formaspagosIdformaspagos) {
        this.formaspagosIdformaspagos = formaspagosIdformaspagos;
    }

    public Usuario getIdUsuariovendedor() {
        return idUsuariovendedor;
    }

    public void setIdUsuariovendedor(Usuario idUsuariovendedor) {
        this.idUsuariovendedor = idUsuariovendedor;
    }

    public Usuario getIdUsuariocomprador() {
        return idUsuariocomprador;
    }

    public void setIdUsuariocomprador(Usuario idUsuariocomprador) {
        this.idUsuariocomprador = idUsuariocomprador;
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
        hash += (idfactura != null ? idfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idfactura == null && other.idfactura != null) || (this.idfactura != null && !this.idfactura.equals(other.idfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Factura[ idfactura=" + idfactura + " ]";
    }
    
}
