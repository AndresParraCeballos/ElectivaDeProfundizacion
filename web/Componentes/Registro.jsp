<%-- 
    Document   : Registro
    Created on : May 31, 2020, 10:25:00 PM
    Author     : Andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="formregistro" action="RegistroUsuarioControlador" method="POST">
           
            <div class="form-group">
                <input class="form-control" type="text" id="nombres"  name="nombres" placeholder="Nombres"><br>
                <input class="form-control" type="text" id="apellidos" name="apellidos" placeholder="Apellidos"><br>
                <select class="form-control" name="tipouser" id="tipouser">
                    <option value="UsuarioCorriente">Corriente</option>
                    <option value="UsuarioVendedor">Vendedor</option>
              </select><br>
              <input class="form-control" type="text" name="telefono" id="telefono" placeholder="Telefono"><br>
              <input class="form-control" type="text" name="identificacion" id="identificacion" placeholder="Numero de cedula"><br>
            </div>
            
            <div class="form-group">
              <label for="email">Email address</label>
              <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp">
              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" name="password" id="password">
            </div>
            
            <button type="submit" id="btnRegistro" class="btn btn-primary">Submit</button>
      </form>
    </body>
</html>
