<%-- 
    Document   : Login
    Created on : May 31, 2020, 10:21:26 PM
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
        <form action="login" method="POST">
        <div class="form-group">
          <label for="email">Correo electronico</label>
          <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp">
          
        </div>
        <div class="form-group">
          <label for="password">Contraseña</label>
          <input type="password" class="form-control" name="password" id="password">
        </div>
        
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
    </body>
</html>
