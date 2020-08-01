/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import ControladorJPA.UsuarioJpaController;
import Entidades.Usuario;
import java.util.List;

/**
 *
 * @author Andre
 */
public class principal {
    
    public static void main(String[] args) {
        UsuarioJpaController udercon = new UsuarioJpaController();
        Object o[] = null;
        List<Usuario> ListU = udercon.findUsuarioEntities();
        for (int i = 0; i < ListU.size(); i++) {
            System.out.println(" " + ListU.get(i).getUserNombres());
        }
    }
}
