<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Create Account</title>
	</head>
	<body>
		<div style="margin:auto;padding:5px;width:400px;height:500px;text-align:center;border: 5px solid black;border-radius:10px;">
			<h1>CREATE ACCOUNT</h1>
			<form action="/create" method="post">
				<p>User Name: <input type="text" name="username" value="${username}"></p>
				<p>Password: <input type="password" name="password"/></p>
				<p>First Name: <input type="text" name="firstName" value="${firstName}"/></p>
				<p>Last Name: <input type="text" name="lastName" value="${lastName}"/></p>
				<p>
					<%
					boolean male =false, female =false, other =false;
					if(!(request.getParameter("gender")==null)){
						String check = request.getParameter("gender");
						switch(check){
						case "male":
							male = true;
							break;
						case "female":
							female = true;
							break;
						case "other":
							other = true;
							break;
						default:
							break;
						}
					}
					%>
					
					<span style="float:left;width:33%;"><input type="radio" id="male" name="gender" value="male" <% if(male){out.println("checked=\"checked\"");} %>>
					Male</span>
					
					<span style="float:left;margin:auto;width:33%;"><input type="radio" id="female" name="gender" value="female" <% if(female){out.println("checked=\"checked\"");} %>>
					Female</span>
					
					<span style="float:right;width:33%;"><input type="radio" id="other" name="gender" value="other" <% if(other){out.println("checked=\"checked\"");} %>>
					Other</span>
				</p>
				<p style="font-weight:bold;color:blue;clear:both;margin-top:5px;">
					<a href="login">Back to Login</a>
					<input type="submit" value="Create Account"/>
				</p>
			<c:forEach var="error" items="${errors}">
				<p style="width:99%;height:auto;font-weight:bold;">${error}</p>
			</c:forEach>
			</form>
		</div>
	</body>
</html>