<%--
  Created by IntelliJ IDEA.
  User: exepu
  Date: 18-12-2024
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Inicio de sesión</title>
</head>
<body>
<c:if test="${not empty error}">
    <div style="color: red; font-weight: bold;">
            ${error}
    </div>
</c:if>
<h1>Iniciar Sesion</h1>
<a href="crearUsuario" style="color: blue;">Regístrate</a>
<form action="iniciarSesion" method="post">
    <fieldset>
        <legend>Inicio de sesion</legend>
        <label for="username">Nombre de usuario:</label>
        <input type="text" name="username" id="username" required/>
        <br>
        <label for="password">Contraseña:</label>
        <input type="password" name="password" id="password" required/>
        <br>
        <button type="submit">Entrar</button>
        </fieldset>
    </fieldset>
</form>

</body>
</html>
