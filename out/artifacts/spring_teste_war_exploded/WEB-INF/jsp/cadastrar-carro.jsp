<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: IgoR
  Date: 14/06/2018
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cadastrar Carro</title>
    </head>
    <body>
    <c:import url="menu.jsp" />
        <form action="create-carro.adm" method="post">
            <input type="hidden" value="${token}" name="token" />
            <p>Marca:</p>
            <input type="text" name="marca">
            <p>Ano</p>
            <input type="number" name="ano">
            <p>Placa</p>
            <input type="text" name="placa">
            <button type="submit"> Cadastrar </button>
        </form>
    </body>
</html>
