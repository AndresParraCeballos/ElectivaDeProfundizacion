/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import implementacionDAO.ProductoDAOImplementacion;
import implementacionDAO.ProductosFacturaImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ProductosFactura;

/**
 *
 * @author Andre
 */
public class AddProductosaFactura extends HttpServlet {

    ProductosFacturaImpl dao;
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
            
            dao = new ProductosFacturaImpl();
            /* TODO output your page here. You may use following sample code. */
            int idFactura = Integer.parseInt(request.getParameter("idfactura"));
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int cantidadComprar = Integer.parseInt(request.getParameter("cantidadComprar"));
            int cantidadDisponible = Integer.parseInt(request.getParameter("cantidadDisponible"));
            int cambiar = cantidadDisponible - cantidadComprar;
            int idComprador = Integer.parseInt(request.getParameter("idComprador"));
            
            List<ProductosFactura> lista = new ArrayList();
            lista = dao.getTodos();
            List<Integer> idproductos = new ArrayList();
            
            for (ProductosFactura idproducto : lista) {
                idproductos.add(idproducto.getProducto_idproducto());
            }
            
            boolean restultado = false;
            
            ProductosFactura model =new ProductosFactura();
            model.setFactura_idfactura(idFactura);
            model.setProducto_idproducto(idProducto);
            model.setProfac_cantidad(cantidadComprar);
            int actualizar = model.getProfac_cantidad()+cantidadComprar;
            out.println("  " + actualizar);
            if (idproductos.contains(model.getProducto_idproducto())) {
                
               restultado = dao.editProductosFactura(actualizar, idProducto);
            }else{
            
             restultado= dao.addFacturaProductos(model);
            
            
            }
            if (!restultado) {
                ProductoDAOImplementacion impl = new ProductoDAOImplementacion();
                String respuesta = impl.editProducto(cambiar, idProducto);
                out.println("<script>alert('se añadio a tu factura');</script>");
                out.println("<script>window.location.href='pages/productos.jsp?idComprador=" + idComprador + "&idFactura=" + idFactura + "';</script>");
            
            }else{
                out.println("nokas");
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
