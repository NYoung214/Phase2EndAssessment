<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	session.invalidate();
%>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<div style="margin:auto;padding:5px;width:400px;height:500px;text-align:center;border: 5px solid black;border-radius:10px;">
			<h1>LOGIN</h1>
			<p style="width:290px;height:1em;font-weight:bold;">${success}</p>
			<form action="/login" method="post">
				<p>User Name: <input type="text" name="username"></p>
				<p>Password: <input type="password" name="password"/></p>
				<p style="font-weight:bold;color:blue;"><a href="create">Create Account</a></p>
				<input type="submit" value="Login"/>
			</form>
		</div>
	</body>
</html>