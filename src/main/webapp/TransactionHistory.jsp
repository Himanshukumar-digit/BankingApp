<%@page import="java.sql.ResultSet"%>

<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.Connection"%>

<%@page import="java.sql.DriverManager"%>

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

	int ACC_No = (int) session.getAttribute("ACC_No");

	Class.forName("com.mysql.cj.jdbc.Driver");

	String url = "jdbc:mysql://localhost:3306/banking";

	String user = "root";

	String pwd = "root";

	Connection con = DriverManager.getConnection(url, user, pwd);

	String sql = "select * from transfers where sender_accno=? ";

	PreparedStatement psta = con.prepareStatement(sql);

	psta.setInt(1, ACC_No);

	ResultSet res5 = psta.executeQuery();

	while (res5.next() == true) {

		out.println("<br> Customer ID" + res5.getInt(1));

		out.println("<br> Bank Name " + res5.getString(2));

		out.println("<br>Ifsc" + res5.getString(3));

		out.println("<br> Sender Account Number" + res5.getInt(4));

		out.println(" <br>Reciever Ifsc" + res5.getString(5));

		out.println("<br> Reciever Account Number " + res5.getInt(6));
		out.println(" <br>Transfered Amount" + res5.getInt(7));

	}
	%>

</body>

</html>