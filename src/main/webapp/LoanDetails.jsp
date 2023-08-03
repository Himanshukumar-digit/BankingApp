<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>Insert title here</title>

</head>

<body>

	<%
	session = request.getSession();

	out.print("You select the " + session.getAttribute("l_type"));

	out.print("Tenure for your loan is " + session.getAttribute("ten"));

	out.print("rate of Interest is " + session.getAttribute("interest"));

	out.print("Some Information " + session.getAttribute("desc"));
	%>

	<h6>CLick here to return</h6>

	<a class="btn btn-primary" href="/Banking_Application/Home.jsp"
		role="button">REGISTER</a>



</body>

</html>