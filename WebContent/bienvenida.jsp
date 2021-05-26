<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenido</title>
</head>
<body>

	<% String usuario=request.getParameter("usuario"); %>
	<% String iniciado=request.getParameter("iniciado"); %>
	<% String method=request.getParameter("method"); %>
	<% if(iniciado.equals("false")){ %>
		<h1>Conectado a la BD</h1>
	<% } %>
	<h1>Bienvenido Usuario: <%=usuario%> (Llamada: <%=method%>)</h1>
	<form method="GET" action="ConsultaLibrosServlet">
		Consulta Libro: <input type=text name="titulo"><br>
		<br> <input type=submit value="Consulta Libros" name="submit">
	</form>
</body>
</html>