<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 align="centre">Welcome to the Bank</h1>
<%
      session = request.getSession();
      String s1 = (String) session.getAttribute("Customer_name");
      out.println(s1+"Welcome to your account.Please select an option");
%>
<br>
<br>
 <li><a href="CheckBalance">1.Check Balance</a></li><br><br>
 <li><a href="ChangePin.jsp">2.Change Password</a></li><br><br>
 <li><a href="Loan.jsp">3.Loan Details</a></li><br><br>
 <li><a href="logout">4.Logout.</a></li><br><br>
 <li><a href="Transfer.html">5.Transfer Amount.</a></li><br><br>
  <li><a href="TransactionHistory.jsp">6.View Transaction History.</a></li><br><br>
 

</body>
</html>