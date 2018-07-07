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
<form action="editar-aluguel.priv" method="post">
    <input type="hidden" name ="id" value="${aluguel.id}" />
    <select name="carroId">
        <c:forEach items="${carros}" var="car">
            <c:if test="${car.alugado != true}" >
                <option value="${car.id}"><c:out value="${car.marca}" /></option>
            </c:if>
        </c:forEach>
    </select>
    <button type="submit"> Editar </button>
</form>
</body>
</html>
