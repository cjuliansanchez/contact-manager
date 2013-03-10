<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>List of Contacts</title>
</head>
<body>
<h1>
	Contacts  
</h1>
<a href="contacts/new">New Contact</a>
<table border="1">
	<tr>
		<th>Name</th>
	</tr>
	<c:forEach var="contact" items="${contacts}">
	<tr>
		<td>  ${contact.name} </td>
	</tr>
	</c:forEach>
</table>
</body>
</html>
