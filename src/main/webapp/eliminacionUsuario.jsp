<%--
  Created by IntelliJ IDEA.
  User: exepu
  Date: 18-12-2024
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Eliminacion de usuario</title>
</head>
<body>
<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

    <h1>Eliminacion de usuario</h1>
    <form action="eliminarUsuario" method="post">
        <label for="password">Confirme su contraseña</label>
        <input type="password" name="password" id="password" placeholder="Contraseña">
        <br>
        <input type="submit" value="Eliminar">
    </form>
</body>
</html>
