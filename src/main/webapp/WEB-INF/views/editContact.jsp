<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>New Contact</title>
</head>
<body>
	<h1>Edit Contact</h1>

	<form:form modelAttribute="contactDTO" method="post"
		id="edit-contact-form">
		<div>
			<label>Name</label>
			<form:input path="newName" />
		</div>
		<div>
			<label>Last Name</label>
			<form:input path="newLastName" />
		</div>
		<form:hidden path="currentName" />
		<form:hidden path="currentLastName" />
		<button type="submit">Save Contact</button>
	</form:form>
</body>
</html>
