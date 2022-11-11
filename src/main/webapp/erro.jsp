<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>tela de erros</title>
</head>
<body>

<h1>mensagem de erro emtre en contato com o suporte do sistema</h1>

<%

out.print(request.getAttribute("msg"));

%>

</body>
</html>