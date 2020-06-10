<%@page import="implementacionDAO.FacturaImpl"%>
<%@page import="modelo.Factura"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<%@page import="implementacionDAO.UsuarioDAOImplementacion"%>
<h3 class="mt-3 text-center">SELECCIONAR FACTURA CREADA</h3>
<%
    String emailUser = request.getParameter("emailuser");
    UsuarioDAOImplementacion dao = new UsuarioDAOImplementacion();
    Usuario user = new Usuario();
    user = dao.getUsuarioByEmail(emailUser);
    int iduser = user.getId()+1;
    FacturaImpl daoFac = new FacturaImpl();
    List<Factura> datos = new ArrayList();
    datos = daoFac.getFactura(iduser);
%>
<form class="p-3 bg-dark w-100 p-5 m-auto rounded-lg" action="../FacturaCreada" method="POST">
    <h6 class="text-center text-white ">Selecciona una de tus facturas creadas</h6>
    <div class="input-group mb-3">
        
        <input type="number" name="iduser" hidden="true" value="<%=iduser%>">
        <div class="input-group-prepend">
          <label class="input-group-text" for="fpago">Seleccione</label>
        </div>       
        
        <select class="custom-select" name="facturasel" id="facturasel">
            
            <%
                for(Factura datoFac : datos ){
                    int idFac = datoFac.getIdfactura();
                    String fecha = datoFac.getFecha();
                    %>
                    <option value="<%=idFac %>"><%=fecha %></option>

                    <%
                }
            %>
          <option selected>Choose...</option>
          
        </select>
      </div>
    <input type="submit" class="btn btn-block btn-danger">
</form>