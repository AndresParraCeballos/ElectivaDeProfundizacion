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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Usuario.findByUserNombres", query = "SELECT u FROM Usuario u WHERE u.userNombres = :userNombres")
    , @NamedQuery(name = "Usuario.findByUserApellidos", query = "SELECT u FROM Usuario u WHERE u.userApellidos = :userApellidos")
    , @NamedQuery(name = "Usuario.findByUserTipo", query = "SELECT u FROM Usuario u WHERE u.userTipo = :userTipo")
    , @NamedQuery(name = "Usuario.findByUserTelefono", query = "SELECT u FROM Usuario u WHERE u.userTelefono = :userTelefono")
    , @NamedQuery(name = "Usuario.findByUserIdentificacion", query = "SELECT u FROM Usuario u WHERE u.userIdentificacion = :userIdentificacion")
    , @NamedQuery(name = "Usuario.findByUserEmail", query = "SELECT u FROM Usuario u WHERE u.userEmail = :userEmail")
    , @NamedQuery(name = "Usuario.findByUserPassword", query = "SELECT u FROM Usuario u WHERE u.userPassword = :userPassword")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_nombres")
    private String userNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_apellidos")
    private String userApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_tipo")
    private String userTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_telefono")
    private String userTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_identificacion")
    private String userIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_email")
    private String userEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_password")
    private String userPassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuariovendedor")
    private Collection<Factura> facturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuariocomprador")
    private Collection<Factura> facturaCollection1;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String userNombres, String userApellidos, String userTipo, String userTelefono, String userIdentificacion, String userEmail, String userPassword) {
        this.idusuario = idusuario;
        this.userNombres = userNombres;
        this.userApellidos = userApellidos;
        this.userTipo = userTipo;
        this.userTelefono = userTelefono;
        this.userIdentificacion = userIdentificacion;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUserNombres() {
        return userNombres;
    }

    public void setUserNombres(String userNombres) {
        this.userNombres = userNombres;
    }

    public String getUserApellidos() {
        return userApellidos;
    }

    public void setUserApellidos(String userApellidos) {
        this.userApellidos = userApellidos;
    }

    public String getUserTipo() {
        return userTipo;
    }

    public void setUserTipo(String userTipo) {
        this.userTipo = userTipo;
    }

    public String getUserTelefono() {
        return userTelefono;
    }

    public void setUserTelefono(String userTelefono) {
        this.userTelefono = userTelefono;
    }

    public String getUserIdentificacion() {
        return userIdentificacion;
    }

    public void setUserIdentificacion(String userIdentificacion) {
        this.userIdentificacion = userIdentificacion;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection1() {
        return facturaCollection1;
    }

    public void setFacturaCollection1(Collection<Factura> facturaCollection1) {
        this.facturaCollection1 = facturaCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
