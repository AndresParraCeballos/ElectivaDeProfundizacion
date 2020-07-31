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
@Table(name = "formaspagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formaspagos.findAll", query = "SELECT f FROM Formaspagos f")
    , @NamedQuery(name = "Formaspagos.findByIdformaspagos", query = "SELECT f FROM Formaspagos f WHERE f.idformaspagos = :idformaspagos")
    , @NamedQuery(name = "Formaspagos.findByForpaDescipcion", query = "SELECT f FROM Formaspagos f WHERE f.forpaDescipcion = :forpaDescipcion")
    , @NamedQuery(name = "Formaspagos.findByForpaEstado", query = "SELECT f FROM Formaspagos f WHERE f.forpaEstado = :forpaEstado")})
public class Formaspagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idformaspagos")
    private Integer idformaspagos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "forpa_descipcion")
    private String forpaDescipcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "forpa_estado")
    private String forpaEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaspagosIdformaspagos")
    private Collection<Factura> facturaCollection;

    public Formaspagos() {
    }

    public Formaspagos(Integer idformaspagos) {
        this.idformaspagos = idformaspagos;
    }

    public Formaspagos(Integer idformaspagos, String forpaDescipcion, String forpaEstado) {
        this.idformaspagos = idformaspagos;
        this.forpaDescipcion = forpaDescipcion;
        this.forpaEstado = forpaEstado;
    }

    public Integer getIdformaspagos() {
        return idformaspagos;
    }

    public void setIdformaspagos(Integer idformaspagos) {
        this.idformaspagos = idformaspagos;
    }

    public String getForpaDescipcion() {
        return forpaDescipcion;
    }

    public void setForpaDescipcion(String forpaDescipcion) {
        this.forpaDescipcion = forpaDescipcion;
    }

    public String getForpaEstado() {
        return forpaEstado;
    }

    public void setForpaEstado(String forpaEstado) {
        this.forpaEstado = forpaEstado;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformaspagos != null ? idformaspagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formaspagos)) {
            return false;
        }
        Formaspagos other = (Formaspagos) object;
        if ((this.idformaspagos == null && other.idformaspagos != null) || (this.idformaspagos != null && !this.idformaspagos.equals(other.idformaspagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Formaspagos[ idformaspagos=" + idformaspagos + " ]";
    }
    
}
