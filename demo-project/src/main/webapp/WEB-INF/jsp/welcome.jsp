<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Welcome</title>
	</head>
	<body>
 	<%
	String user = null;
	if(session.getAttribute("username")==null){
		response.sendRedirect("/login");
	}
	%> 
<div style="background-color:white;margin:auto;padding:5px;width:400px;height:500px;text-align:center;border: 5px solid black;border-radius:10px;">
			<h1>Welcome ${user.username}</h1>
			<div style="background-color:lightblue;margin:auto;width:360px;height:70%;border: 2px solid black;border-radius:5px;">
				<div style="margin:auto;width:342px;height:30%;">
					<div style="border: 2px solid black;background-color:white;border-radius:100%;float:left;width:28%;height:90%;padding:auto;">
						
					</div>
					
					<div style="background-color:white;border: 2px solid black;border-radius:5px;float:right;width:65%;height:90%;">
						<p>
							<h2 style="padding:0;margin:0;">${user.firstName} ${user.lastName}</h2>
						</p>
					</div>
					
				</div>
				<div style="margin:auto;width:342px;height:65%;">
					<div style="background-color:white;border: 2px solid black;border-radius:5px;width:325px;height:95%;text-align:left;padding-left:1em;">
						<h3>Gender: ${user.gender}</h3>
						<h3>Extra Attribute...</h3>
						<h3>Extra Attribute...</h3>
						<h3>Extra Attribute...</h3>
						<h3>Extra Attribute...</h3>
					</div>
				</div>
			</div>
			<div style="margin:auto;width:90%;height:10%;">
				<div style="float:right;padding-top:8px;">
					<a href="/logout" style="border:1px solid white;border-radius:3px;background-color:blue;padding:5px;color:white;">logout</a>						
				</div>
			</div>
		</div>
	</body>
</html>