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
<c:import url="menu.jsp" />
<c:forEach items="${usuarios}" var="u">
    <ul>
        <li><c:out value="${u.nome}" />/ <c:out value="${u.login}" /></li>
        <li><a href="delete-usuario.adm?id=${u.id}">Deletar</a></li>
        <li><a href="edit-usuario.adm?id=${u.id}">Editar</a></li>
    </ul>
</c:forEach>
</body>
</html>
