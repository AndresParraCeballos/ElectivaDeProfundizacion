<%-- 
    Document   : Factura
    Created on : Jun 9, 2020, 12:37:46 PM
    Author     : Andre
--%>

<%@page import="implementacionDAO.ProductoDAOImplementacion"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ProductosFactura"%>
<%@page import="java.util.List"%>
<%@page import="implementacionDAO.ProductosFacturaImpl"%>
<%@page import="modelo.Usuario"%>
<%@page import="implementacionDAO.UsuarioDAOImplementacion"%>
<%@page import="modelo.Factura"%>
<%@page import="implementacionDAO.FacturaImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="bg-secondary">
        <jsp:include page="../Componentes/navbar.jsp" />
        <%
            
            int idComprador = Integer.parseInt(request.getParameter("idComprador"));
            
            UsuarioDAOImplementacion daoUser = new UsuarioDAOImplementacion();
            Usuario user = new Usuario();
            user = daoUser.getUsuarioById(idComprador);
            String nameUser = user.getNombres() + " " + user.getApellidos();
            String email = user.getEmail();
            int idFactura =Integer.parseInt(request.getParameter("idfactura"));
            FacturaImpl impl = new FacturaImpl();
            Factura fac = new Factura();
            fac = impl.getFacturaById(idFactura);
            String fechafac = fac.getFecha();
        
        %>
        <div class="container mt-3 bg-white">
            <div class="row border">
                <div class="col ">
                    <p><%=idFactura%></p>
                </div>
                <div class="col ">
                    <p><%=fechafac%></p>
                </div>
                <div class="col ">
                    <p><%=nameUser%></p>
                </div>
            </div>
             <div class="row border">
                <div class="col ">
                    <h3 class="text-center">Productos</h3>
                    <table class="table">
                        <thead>
                        <th>Cantidad </th>
                        <th>
                            Nombre del producto
                        </th>
                        <th>
                            Marca del producto
                        </th>
                        <th>
                            Codigo de barra
                        </th>
                        <th>Precio del producto
                        </th>
                        <th>Acción</th>
                        </thead>
                        <tbody>
                            
                        
                    
                    <%
                        ProductosFacturaImpl daoimpl = new ProductosFacturaImpl();
                        List<ProductosFactura> datosProductosFactura = new ArrayList();
                        List<Integer> idsproductos = new ArrayList();
                        List<Producto> productos = new ArrayList();
                        datosProductosFactura = daoimpl.getFacturaProductos(idFactura);
                        int precioTotal =  0;
                        for (ProductosFactura elem : datosProductosFactura ) {
                                int cantidad = elem.getProfac_cantidad();
                                ProductoDAOImplementacion dao = new ProductoDAOImplementacion();
                                Producto pro = new Producto();
                                pro = dao.getProductoById(elem.getProducto_idproducto());
                                idsproductos.add(elem.getProducto_idproducto());
                                int id = pro.getIdproducto();
                                String pronombre = pro.getPro_nombre();
                                String marca = pro.getPro_marca();
                                String barra = pro.getPro_codigobarra();
                                int precio = pro.getPro_precio();
                                
                                precioTotal += cantidad*precio; 
                                %>
                                        <tr>
                                            <td>
                                                <%=cantidad%> 
                                            </td>
                                        <td>
                                            <%=pronombre%>
                                        </td>
                                        <td>
                                            <%=marca%>
                                        </td>
                                        <td>
                                            <%=barra%>
                                        </td>
                                        <td>
                                            <%=precio%> c/u
                                        </td>
                                        <td>
                                            <form name="formdelete" action="../EliminarProductoFactura" method="POST">
                                                <input type="number" hidden="true" id="cantidad" name="cantidad" value="<%=cantidad%>">
                                                <input type="number" hidden="true" id="producto_idproducto" name="producto_idproducto" value="<%=id%>">
                                                <input type="number" hidden="true" id="idFactura" name="idFactura" value="<%=idFactura%>">
                                                <input type="number" hidden="true" id="idComprador" name="idComprador" value="<%=idComprador%>">
                                                <button type="submit" class="btn btn-danger text-white">Eliminar de factura</button>
                                            </form>

                                        
                                <%
                                
                            }
                       
                        
                       
                            
                                
                            
                    %>
                                        <tr>
                                        <td colspan="6"> 
                                            <h4 class="text-center">Precio total: <%=precioTotal%></h4>
                                        </td>
                                        </tr>
                    
                    </tbody>
                    </table>
                </div>
                    
            </div>
                    
        </div>
                    
        <div class="container">
            <div class="row">
            <div class="col">
                <form action="../CancelarFactura" method="POST">
                    <input type="number" hidden="true" id="idFactura" name="idFactura" value="<%=idFactura%>">
                    <input class="btn btn-danger btn-block" type="submit" value="Eliminar factura y continuar comprando" />
                </form>
            </div>
            <div class="col">
                <form action="../CompletarFactura" method="POST">
                    <input type="text" hidden="true" id="email" name="email" value="<%=email%>">
                    <input type="number" hidden="true" id="idFactura" name="idFactura" value="<%=idFactura%>">
                    <input class="btn btn-danger btn-block" type="submit" value="Completar factura y continuar comprando" />
                </form>
            </div>
        </div>
                    <div class="row mt-3">
                        <div class="col">
                            <a class="text-white btn btn-danger btn-block" href="productos.jsp?idComprador=<%=idComprador%>&idFactura=<%=idFactura%>">Seguir comprando </a>
                        </div>
                    </div>
        </div>
    </body>
</html>
