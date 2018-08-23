<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/login-result.css">
	</head>
	<body>
		<c:choose>
			<c:when test="${message.error}">
				<p class="error_content">
					<b>AN ERROR OCCURRED</b></br></br>
					<b>DETAILS:</b></br> <c:out value="${message.message}"/></br>
					<b>CODE:</b> <c:out value="${message.errorCode}"/></br>
					<b>ERROR TYPE:</b> <c:out value="${message.errorDetails}"/></br> </br>
					Go back to our <a href="/leoforfriends-1.00/">Home</a>
				<p>
			</c:when>

			<c:otherwise>
				<p><c:out value="${message.message}"/></p>
			</c:otherwise>
		</c:choose>
	</body>
</html>