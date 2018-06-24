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
    <title>Cadastrar Aluguel</title>
</head>
<body>
<c:import url="menu.jsp" />
    <form action="create-aluguel.priv" method="post">
        <p>Data:</p>
        <input type="date" name="data">
        <p>Carro</p>
        <input type="text" name="carro">
        <button type="submit"> Cadastrar </button>
    </form>
</body>
</html>
