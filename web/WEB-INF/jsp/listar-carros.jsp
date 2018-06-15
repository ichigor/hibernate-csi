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
    <title>Lista de carros</title>
</head>
<body>
    <a href="cadastrar-carro.html">Cadastrar Carros</a>
    <c:forEach items="${carros}" var="car">
        <ul>
            <li>${car.marca}</li>
            <li><a href="delete-carro.html?id=${car.id}">Deletar</a></li>
            <li><a href="edit-carro.html?id=${car.id}">Editar</a></li>
        </ul>
    </c:forEach>

</body>
</html>
