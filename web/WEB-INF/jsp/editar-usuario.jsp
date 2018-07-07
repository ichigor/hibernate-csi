<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:import url="menu.jsp" />

<form action="cria-usuario.html" method="post">
    <input type="hidden" name ="id" value="${usuario.id}" />
    <p>Nome:</p>
    <input type="text" name="nome" value="<c:out value="${usuario.nome}" />">
    <p>Login:</p>
    <input type="text" name="login" value="<c:out value="${usuario.login}" />">
    <p>Senha:</p>
    <input type="password" name="senha">
    <button type="submit"> Editar </button>
</form>

</body>
</html>
