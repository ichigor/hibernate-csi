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
    <a href="cadastrar-carro.html">Cadastrar Carros</a>
    <a href="cadastrar-aluguel.html">Cadastrar Aluguel</a>
    <a href="lista-usuarios.html">Listar Usuarios</a>
    <a href="list-carros.html">Listar Carros</a>
    <a href="list-alugueis.html">Listar Alugueis</a>
    <%--<a href="list-carros.html">Listar Carros</a>--%>
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
