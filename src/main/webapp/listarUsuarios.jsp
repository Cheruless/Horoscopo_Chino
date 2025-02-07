<%--
  Created by IntelliJ IDEA.
  User: exepu
  Date: 18-12-2024
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Listar usuarios</title>
</head>
<body>

<h1>Listar usuarios</h1>
<a href="<%=request.getContextPath()%>">Volver</a>
<c:choose>
    <c:when test="${not empty listaUsuarios}">

        <c:forEach items="${listaUsuarios}" var="usuario">
            <fieldset>
                <legend
                        style="font-size: 150%; font-weight: bold; color: #000000;">${usuario.getNombre()}
                </legend>
                <p>Username: ${usuario.getUsername()}</p>
                <p>Email: ${usuario.getEmail()}</p>
                <p>Fecha de nacimiento: <fmt:formatDate value="${usuario.getFechaNacimiento()}" pattern="dd/MM/yyyy"/>
                </p>
                <p>Animal chino: ${usuario.getAnimal()}</p>
            </fieldset>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty error}">
            <div style="color: red; font-weight: bold;">
                    ${error}
            </div>
        </c:if>
    </c:otherwise>
</c:choose>

</body>
</html>
