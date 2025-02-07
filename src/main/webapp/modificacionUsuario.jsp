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
    <title>Modificación de usuario</title>
</head>
<body>

<c:if test="${not empty error}">
    <div style="color: red; font-weight: bold;">
            ${error}
    </div>
</c:if>

<h1>Modificar usuario</h1>
<h2>Por favor, modifique lo que usted desee modificar. Deja vacío si no desea modificar.</h2>
<form action="<%=request.getContextPath()%>/modificarUsuario" method="post">
    <fieldset>
        <legend>Datos para modificar usuario</legend>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" placeholder="${usuario.getNombre()}">
        <br>
        <label for="email">Correo electrónico:</label>
        <input type="email" name="email" id="email" placeholder="${usuario.getEmail()}">
        <br>
        <label for="usuario">Nombre de usuario:</label>
        <input type="text" name="usuario" id="usuario" placeholder="${usuario.getUsername()}">
        <br>
        <label for="password">Contraseña actual:</label>
        <input type="password" name="password" id="password" required>
        <br>
        <label for="password-modificar">Nueva contraseña: </label>
        <input type="password" name="password-modificar" id="password-modificar">
        <br>
        <label for="password-modificar-confirm">Repita nueva contraseña: </label>
        <input type="password" name="password-modificar-confirm" id="password-modificar-confirm">
        <button type="submit">Modificar usuario</button>
    </fieldset>
</form>

</body>
</html>
