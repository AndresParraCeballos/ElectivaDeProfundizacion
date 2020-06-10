<%@page import="modelo.Usuario"%>
<%@page import="implementacionDAO.UsuarioDAOImplementacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.TipoPago"%>
<%@page import="java.util.List"%>
<%@page import="implementacionDAO.TipoPagoImpl"%>
<%
    String emailUser = request.getParameter("emailuser");
    UsuarioDAOImplementacion dao = new UsuarioDAOImplementacion();
    Usuario user = new Usuario();
    user = dao.getUsuarioByEmail(emailUser);
    int iduser = user.getId()+1;
%>
<h3 class="mt-3 text-center">CREAR FACTURA DE COMPRA</h3>
<form class="p-3 bg-dark w-100 p-5 m-auto rounded-lg" action="../crearfactura" method="POST">
    <h6 class="text-center text-white ">Ingresa que tipo de pago usarás</h6>
    <div class="input-group mb-3">
        
        <input type="number" name="iduser" hidden="true" value="<%=iduser%>">
        <div class="input-group-prepend">
          <label class="input-group-text" for="fpago">Seleccione</label>
        </div>
        
        
        <select class="custom-select" name="fpago" id="fpago">
            <%
                
            
                TipoPagoImpl tip = new TipoPagoImpl();
                List<TipoPago> datos = new ArrayList();
                datos = tip.getTiposdePagos();
                
                            for (TipoPago dato : datos) {
                                int id = dato.getIdformaspagos(); 
                                String nombre = dato.getForpa_descipcion();
                                String descripcion = dato.getForpa_estado();
                                
                                %>
                                <option value="<%=id%>"><%=nombre%></option>
                                
                                <%
                            }
                
            %>
          <option selected>Choose...</option>
          
        </select>
      </div>
    <input type="submit" class="btn btn-block btn-danger">
</form>