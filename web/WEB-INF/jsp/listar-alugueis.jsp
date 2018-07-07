<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: IgoR
  Date: 14/06/2018
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de alugueis</title>
</head>
<body>
<c:import url="menu.jsp" />

<c:forEach items="${alugueis}" var="alu">
    <ul>
        <li><c:out value="${alu.carro.marca}" /></li>
        <li><c:out value="${alu.usuario.nome}" /></li>
        <li><a href="delete-aluguel.priv?id=${alu.id}">Deletar</a></li>
        <li><a href="edit-aluguel.priv?id=${alu.id}">Editar</a></li>
    </ul>
</c:forEach>
</body>
</html>
