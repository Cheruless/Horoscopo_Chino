<%--
  Created by IntelliJ IDEA.
  User: exepu
  Date: 18-12-2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Consulta de horóscopo chino</title>
</head>
<body>
<a href="<%=request.getContextPath()%>">Volver</a>
<h1 style="text-align: center">Conoce a tu animal del horóscopo chino</h1>
<h2 style="text-align: center">Tu animal es <i>${animalUsuario}</i>.</h2>
</body>
</html>
