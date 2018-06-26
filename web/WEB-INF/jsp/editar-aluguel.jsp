<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: IgoR
  Date: 21/06/2018
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Aluguel</title>
</head>
<body>
<c:import url="menu.jsp" />
<c:forEach items="${carros}" var="car">
    <ul>
        <li>${car.marca}</li>
        <li><a href="create-aluguel.priv?id=${car.id}">Alugar</a></li>
    </ul>
</c:forEach>
</body>
</html>
