<%-- 
    Document   : productos
    Created on : Jun 1, 2020, 10:27:38 AM
    Author     : Andre
--%>

<%@page import="modelo.Factura"%>
<%@page import="implementacionDAO.FacturaImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="implementacionDAO.ProductoDAOImplementacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <%
            int idFactura = 0;
            ProductoDAOImplementacion dao = new ProductoDAOImplementacion();
            List<Producto> datos = new ArrayList();
            datos = dao.mostrarProductos();
            int idComprador =Integer.parseInt(request.getParameter("idComprador"));
            if (request.getParameter("idFactura") !=null) {
                    idFactura =Integer.parseInt(request.getParameter("idFactura"));
                }else{
                idFactura = 0;
            }
            FacturaImpl daoFactura = new FacturaImpl();
            List<Factura> datosFactura = new ArrayList();
            datosFactura = daoFactura.getFactura(idComprador);
        %>
    </head>
    <body>
         <jsp:include page="../Componentes/navbar.jsp" />  
         <h4 class="mt-3 text-center">Productos</h4><br>
         <a class="btn btn-dark text-white btn-block" href="MisFacturas.jsp?idusuario=<%=idComprador%>">Ver mis facturas</a>
        <table class="table mt-3" border="1" cellpadding="2">
            <thead>
                <tr>
                    <th>Id prodcuto</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Marca</th>
                    <th>Codigo de Barra</th>
                    <th>Cantidad disponible</th>
                    <th>Precio </th>
                    <th> Agregar a factura</th>
                    
                </tr>
            </thead>
            <tbody>
                
                    <%
                        
                        try {
                           
                            
                            
                       


                            for (Producto dato : datos) {
                                int id = dato.getIdproducto(); 
                                String nombre = dato.getPro_nombre();
                                String descripcion = dato.getPro_descripcion();
                                String marca = dato.getPro_marca();
                                String codigobarra = dato.getPro_codigobarra();
                                int cantidad = dato.getPro_cantidad_disponible();
                                String precio = "$ " + dato.getPro_precio();
                                %>
                                <tr>
                                <td><%=id%></td>
                                <td><%=nombre %></td>
                                <td><%=descripcion %></td>
                                <td><%=marca %></td>
                                <td><%=codigobarra %></td>
                                <td><%=cantidad %></td>
                                <td><%=precio%></td>
                                <td><form action="../AddProductosaFactura" method="POST">
                                        <input type="number" hidden="true" id="idComprador" name="idComprador" value="<%=idComprador%>">
                                        <input type="number" hidden="true" id="idProducto" name="idProducto" value="<%=id%>">
                                        <input type="number" hidden="true" value="<%=idComprador%>">
                                        <input type="number" hidden="true" name="cantidadDisponible" id="cantidadDisponible" value="<%=cantidad %>">
                                        <select class="custom-select" name="cantidadComprar" id="cantidadComprar">
                                            <%
                                                for(int i = 1 ; i <= cantidad; i++){
                                                    %>
                                                    <option  value="<%=i %>"><%=i %></option>
                                                    <%
                                                }
                                            %>
            
                                            <option selected>Cantidad...</option>

                                          </select>
                                            <select class="custom-select" name="idfactura" id="idfactura">
                                                <option >Seleccione Factura...</option>
                                            <%
                                                for(Factura datoFac : datosFactura ){
                                                    int idFac = datoFac.getIdfactura();
                                                    String fecha = datoFac.getFecha();
                                                    %>
                                                    <option <%if (idFactura != 0) {
                                                            if (idFac == idFactura) {
                                                                    %>selected<%
                                                                }
                                                        }
                                                        %> value="<%=idFac %>"><%=fecha %></option>
                                                        
                                                    <%
                                                }
                                            %>
            
                                            

                                          </select>
                                            <button type="submit" class="btn btn-danger btn-block text-white"> Añadir </button>
                                    </form>
                                </td>
                                </tr>
                                
                                <%
                            }

                               %>
                                
                               <tr>
                                    <td colspan="8">
                                        <h5 class="text-center">No hay mas resultados </h5>
                                    </td>
                                </tr>
                                <%

                                
                            
                        
                                
                            } catch (NullPointerException e) {
                                %>
                                <td colspan="7" class="text-center">No  hay resultados</td>
                                
                                <%
                            

                            }
                        
                        
                    %>
                    
                    
               
            </tbody>
        </table>

    </body>
</html>
