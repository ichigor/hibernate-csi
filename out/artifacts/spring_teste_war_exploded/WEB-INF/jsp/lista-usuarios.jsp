<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: cpol
  Date: 31/05/2017
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de usuarios</title>
</head>
<body>
<a href="create-usuario.html">Cadastrar Usuario</a>
<a href="cadastrar-carro.html">Cadastrar Carros</a>
<a href="cadastrar-aluguel.html">Cadastrar Aluguel</a>
<a href="list-carros.html">Listar Carros</a>
<a href="list-alugueis.html">Listar Alugueis</a>
<c:forEach items="${usuarios}" var="u">
    <ul>
        <li>${u.nome} / ${u.login} / ${u.senha}</li>
        <li><a href="delete-usuario.html?id=${u.id}">Deletar</a></li>
        <li><a href="edit-usuario.html?id=${u.id}">Editar</a></li>
    </ul>
    <%--<ul>--%>
        <%--<c:forEach items="${u.logs}" var="l" >--%>
            <%--<li>alterou ${l.idObjeto}@${l.classe} em <fmt:formatDate value="${l.dataHora}" pattern="dd/MM/yyyy 'às' HH:mm"/> </li>--%>
        <%--</c:forEach>--%>
    <%--</ul>--%>
</c:forEach>
</body>
</html>
