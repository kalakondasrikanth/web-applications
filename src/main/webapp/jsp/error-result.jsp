<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/login-result.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Titillium+Web:400,300,300italic,400italic,600italic,600">
	</head>

	<body>
		<nav>
			<ul>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="home"  href="/leoforfriends-1.00/">
						<i class="fa fa-home"></i> HOME
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="bitbucket repo"  href="https://bitbucket.org/upd-dei-stud-prj/w2018_leoforfriendsweb/" target="_blank">
						<i class="fa fa-bitbucket"></i> BitBucket
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="search a question" href="/leoforfriends-1.00/jsp/search-question.jsp">
						<i class="fa fa-search"></i> Search a question
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="Create a question" href="/leoforfriends-1.00/jsp/create-question-form.jsp">
						<i class="fa fa-file"></i> Create a question
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="List all the questions" href="/leoforfriends-1.00/jsp/list-all-question.jsp">
						<i class="fa fa-file"></i> List all the questions
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" id="logprof" title="Login" href="/leoforfriends-1.00/jsp/login.jsp">
						<i class="fa fa-sign-in"></i> Login
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" id="regout" title="registration" href="/leoforfriends-1.00/jsp/registration.jsp">
						<i class="fa fa-user"></i> Registration
					</a>
				</li>
			</ul>
		</nav>

		<div class="cont">
			<div class="int">
				<div class="login">
					<div class="login_row">
						<c:choose>
							<c:when test="${message.error}">
								<p class="error_content">
									<b>AN ERROR OCCURRED</b></br></br>
									<b>DETAILS:</b></br> <c:out value="${message.message}"/></br>
									<b>CODE:</b> <c:out value="${message.errorCode}"/></br>
									<b>ERROR TYPE:</b> <c:out value="${message.errorDetails}"/></br> </br>
									Go back to our <a href="/leoforfriends-1.00/">Home</a>
								</p>
							</c:when>
							<c:otherwise>
								<p><c:out value="${message.message}"/></p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

 