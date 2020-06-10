<%-- 
    Document   : MisFacturas
    Created on : Jun 9, 2020, 9:12:04 AM
    Author     : Andre
--%>

<%@page import="modelo.TipoPago"%>
<%@page import="implementacionDAO.TipoPagoImpl"%>
<%@page import="modelo.Factura"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="implementacionDAO.FacturaImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis facturas</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="../Componentes/navbar.jsp" />
        <%
            int idComprador =Integer.parseInt(request.getParameter("idusuario"));
            FacturaImpl impl = new FacturaImpl();
            List<Factura> datos = new ArrayList();
            datos = impl.getFactura(idComprador);
        %>
        <table class="table">
            
            <thead>
            <th>Id Factura<th>
            <th>Fecha de creada de la factura</th>
            <th>Descripción</th>
            <th>Acciones</th>
            </thead>
            <tbody>
            
            <%
                for(Factura datoFac : datos ){
                    int idFac = datoFac.getIdfactura();
                    String fecha = datoFac.getFecha();
                    int formaPago = datoFac.getFormaspagos_idformaspagos();
                    TipoPagoImpl dao = new TipoPagoImpl();
                    TipoPago tipo = new TipoPago(); 
                    tipo = dao.getTipoPagobyId(formaPago);
                    String tiponombre = tipo.getForpa_descipcion();
                    %>
                    <tr>
                        <td>
                            <%=idFac%>                            
                        </td>
                        
                        <td>                            
                            -
                        </td>
                        <td>                            
                            <%=fecha%>                            
                        </td>
                        <td>                            
                            <%=tiponombre%>
                        </td>
                        <td>
                            <a href="Factura.jsp?idfactura=<%=idFac%>&idComprador=<%=idComprador%>" class="btn btn-danger text-white">Ver factura</a>
                            <a class="btn btn-dark text-white">Eliminar Factura</a>
                        </td>
                            
                    </tr>
                    <%
                }
            %>
            <tbody>
        </table>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    
    </body>
</html>
