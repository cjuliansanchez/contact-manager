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
		<th>Last Name</th>
	</tr>
	<c:forEach var="contact" items="${contacts}">
			<tr>
				<td>${contact.name}</td>
				<td>${contact.lastName}</td>
				<c:url var="editUrl" value="editContact">
					<c:param name="name" value="${contact.name}" />
					<c:param name="lastName" value="${contact.lastName}" />
				</c:url>
				<td><a href="${editUrl}">Edit</a></td>
			</tr>
		</c:forEach>
</table>
</body>
</html>
