<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>New Contact</title>
</head>
<body>
<h1>
	New Contact  
</h1>

<form:form modelAttribute="contact" method="post" id="add-contact-form">
	<label>Name</label>
	<form:input path="name"/>
	<label>Last Name</label>
	<form:input path="lastName"/>
	<button type="submit">Add Contact</button>
</form:form>
</body>
</html>
