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
    <a href="create-usuario.html">Cadastrar Usuario</a>
    <a href="cadastrar-carro.html">Cadastrar Carros</a>
    <a href="cadastrar-aluguel.html">Cadastrar Aluguel</a>
    <a href="lista-usuarios.html">Listar Usuarios</a>
    <a href="list-alugueis.html">Listar Alugueis</a>
    <c:forEach items="${carros}" var="car">
        <ul>
            <li>${car.marca}</li>
            <li><a href="delete-carro.html?id=${car.id}">Deletar</a></li>
            <li><a href="edit-carro.html?id=${car.id}">Editar</a></li>
        </ul>
    </c:forEach>

</body>
</html>
