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
  <script src='https://www.google.com/recaptcha/api.js'></script>
  <body>
  <a href="list-carros.html">Listar Carros</a>
  <form action="login.html" method="post">
    <p>Login:</p>
    <input type="text" name="login">
    <p>Senha</p>
    <input type="password" name="senha">
    <button type="submit"> Login </button>
    <div class="g-recaptcha" data-sitekey="6LdBI2IUAAAAAIn_TmZp4rZXGMnh6VfT0layhhqI"></div>
  </form>
  <a href="create-usuario.html">Cadastrar-se</a>
  </body>
</html>
