<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Change Pin</h1>

	<form action="changePassword" method="post">

		<input type="text" name="prevPin" placeholder="Old pin"> <input
			type="text" name="newPin" placeholder="new Pin"> <input
			type="text" name="cnfrmPin" placeholder="confirm Pin"> <input
			type="submit">

	</form>

</body>
</html>