<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New user created</title>
</head>
<body>
<h1>Let's create a new user</h1>

<p>
	First Name: <% String fName = request.getParameter("firstname"); out.print(fName);   %><br>
	Last Name:  <% String lName = request.getParameter("lastname");  out.print(lName);   %><br>
	Sex:    	<% String sex   = request.getParameter("sex"); 		 out.print(sex); 	 %><br>
</p>

	
</body>
</html>