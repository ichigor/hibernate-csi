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
<a href="create-usuario.html">Cadastrar Usuario</a>
<a href="cadastrar-carro.html">Cadastrar Carros</a>
<a href="cadastrar-aluguel.html">Cadastrar Aluguel</a>
<a href="lista-usuarios.html">Listar Usuarios</a>
<a href="list-carros.html">Listar Carros</a>

<c:forEach items="${alugueis}" var="alu">
    <ul>
        <li>${alu}</li>
        <li><a href="delete-aluguel.html?id=${alu.id}">Deletar</a></li>
        <li><a href="edit-aluguel.html?id=${alu.id}">Editar</a></li>
    </ul>
</c:forEach>
</body>
</html>
