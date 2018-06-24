<%--
  Created by IntelliJ IDEA.
  User: IgoR
  Date: 21/06/2018
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar usuario</title>
</head>
<body>
<a href="cadastrar-carro.html">Cadastrar Carros</a>
<a href="cadastrar-aluguel.html">Cadastrar Aluguel</a>
<a href="lista-usuarios.html">Listar Usuarios</a>
<a href="list-carros.html">Listar Carros</a>
<a href="list-alugueis.html">Listar Alugueis</a>

<form action="cria-usuario.html" method="post">
    <input type="hidden" name ="id" value="${usuario.id}" />
    <p>Nome:</p>
    <input type="text" name="nome" value="${usuario.nome}">
    <p>Login:</p>
    <input type="text" name="login" value="${usuario.login}">
    <p>Senha:</p>
    <input type="password" name="senha">
    <button type="submit"> Editar </button>
</form>

</body>
</html>
