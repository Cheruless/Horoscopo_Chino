<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<c:set var="usuario" value="${sessionScope.usuario}" />

<c:if test="${not empty mensaje}">
    <div style="color: red; font-weight: bold;">
            ${mensaje}
    </div>
</c:if>

<c:choose>
    <c:when test="${not empty usuario}">
        <h1>¿Qué deseas hacer, ${usuario.getNombre()}?</h1>
        <br>
        <a href="consultarHoroscopo">Conoce tu animal</a>
        <br>
        <a href="buscarUsuarios">Buscar usuarios</a>
        <br>
        <a href="modificarUsuario">Modificar datos</a>
        <br>
        <a href="eliminarUsuario">Eliminar cuenta</a>
        <br>
        <br>
        <a href="cerrarSesion">Cerrar sesión</a>
    </c:when>
    <c:otherwise>
        <h1>Bienvenido.</h1>
        <a href="crearUsuario">Crear usuario</a>
        <br>
        <a href="iniciarSesion">Iniciar sesión</a>
    </c:otherwise>
</c:choose>
</body>
</html>