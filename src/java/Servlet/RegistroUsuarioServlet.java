/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorJPA.UsuarioJpaController;
import DAO.UsuarioDAO;
import implementacionDAO.UsuarioDAOImplementacion;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author Andre
 */
public class RegistroUsuarioServlet extends HttpServlet {
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UsuarioDAOImplementacion dao = new UsuarioDAOImplementacion();
            Usuario persona = new Usuario();
            
            List<Usuario> datos = new ArrayList<>();
            String respuesta="";
            RequestDispatcher rd = null;
            
            try {
                
                    persona.setNombres(request.getParameter("nombres"));
                    persona.setApellidos(request.getParameter("apellidos"));
                    persona.setTipo(request.getParameter("tipouser"));
                    persona.setTelefono(request.getParameter("telefono"));
                    persona.setCedula(request.getParameter("identificacion"));
                    persona.setEmail(request.getParameter("email"));
                    persona.setPassword(request.getParameter("password"));
                    respuesta = dao.GuardarUsuario(persona);
                    System.out.println(respuesta);
                    request.setAttribute("respuesta", respuesta);
                    out.println("<script>alert('Se registro correctamente');</script>");
                    out.println("<script>window.location.href='index.jsp';</script>");
                    
                
                
                rd = request.getRequestDispatcher("index.jsp");
            } catch (Exception e) {
                out.println("<script>alert('No se registro correctamente');</script>");
                out.println("<script>window.location.href='index.jsp';</script>");
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
