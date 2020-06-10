/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.ws.rs.core.Response;
import modelo.Usuario;

/**
 *
 * @author Andre
 */
public interface UsuarioDAO {
    
    public String GuardarUsuario(Usuario cliente);    
    public boolean login(String usuario, String contraseña);
    public Usuario getUsuarioByEmail (String email);
    public Usuario getUsuarioById (int id);
}
