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
<a href="create-usuario.html">Cadastrar Usuario</a>
<a href="cadastrar-carro.html">Cadastrar Carros</a>
<a href="lista-usuarios.html">Listar Usuarios</a>
<a href="list-carros.html">Listar Carros</a>
<a href="list-alugueis.html">Listar Alugueis</a>
<form action="create-aluguel.html" method="post">
    <p>Data:</p>
    <input type="date" name="data">
    <p>Carro</p>
    <input type="text" name="carro">
    <button type="submit"> Cadastrar </button>
</form>
</body>
</html>
