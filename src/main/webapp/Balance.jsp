<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance Failure Page</title>
</head>
<body>
<h3>
<%
session = request.getSession();
out.println("Saving Balance = "+session.getAttribute("Balance"));

%>


</h3>
<a href = "Home.jsp"></a>
</body>
</html>