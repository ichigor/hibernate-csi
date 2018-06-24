<%--
  Created by IntelliJ IDEA.
  User: cpol
  Date: 22/05/2017
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <a href="list-carros.html">Listar Carros</a>
  <form action="login.html" method="post">
    <p>Login:</p>
    <input type="text" name="login">
    <p>Senha</p>
    <input type="password" name="senha">
    <button type="submit"> Login </button>
  </form>
  <a href="create-usuario.html">Cadastrar-se</a>
  </body>
</html>
