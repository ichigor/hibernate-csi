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
    <title>Cadastrar Usuario</title>
</head>
<body>
    <form action="cria-usuario.html" method="post">
        <p>Nome:</p>
        <input type="text" name="nome">
        <p>Login:</p>
        <input type="text" name="login">
        <p>Senha:</p>
        <input type="password" name="senha">
        <button type="submit"> Cadastrar </button>
    </form>
</body>
</html>
