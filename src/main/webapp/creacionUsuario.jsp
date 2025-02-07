<%--
  Created by IntelliJ IDEA.
  User: exepu
  Date: 18-12-2024
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Creación de usuario</title>
</head>
<body>
<c:if test="${not empty error}">
    <div style="color: red; font-weight: bold;">
            ${error}
    </div>
</c:if>

    <h1>Crear usuario</h1>

<form action="<%=request.getContextPath()%>/crearUsuario" method="post">
    <fieldset>
        <legend>Datos para crear tu usuario</legend>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required>
        <br>
        <label for="email">Correo electrónico:</label>
        <input type="email" name="email" id="email" required>
        <br>
        <label for="usuario">Nombre de usuario:</label>
        <input type="text" name="usuario" id="usuario" required>
        <br>
        <label for="fecha-nacimiento">Fecha de Nacimiento:</label>
        <input type="date" name="fecha-nacimiento" id="fecha-nacimiento" required>
        <br>
        <label for="password">Contraseña:</label>
        <input type="password" name="password" id="password" required>
        <br>
        <label for="password-confirmacion">Repita contraseña: </label>
        <input type="password" name="password-confirmacion" id="password-confirmacion" required>
        <br>
        <button type="submit">Crear usuario</button>
    </fieldset>
</form>

</body>
</html>
