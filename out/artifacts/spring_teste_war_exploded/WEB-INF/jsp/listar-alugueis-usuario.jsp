<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: IgoR
  Date: 26/06/2018
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alugueis do Usuario</title>
</head>
<body>
<c:import url="menu.jsp" />

<c:forEach items="${usuario.alugueis}" var="user">
    <ul>
        <li><c:out value="${user.carro.marca}" /></li>
        <li><a href="delete-aluguel.priv?id=${user.id}">Deletar</a></li>
        <li><a href="edit-aluguel.priv?id=${user.id}">Editar</a></li>
    </ul>
</c:forEach>
</body>
</html>
