<!----><%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Create Answer</title>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/result.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Titillium+Web:400,300,300italic,400italic,600italic,600">
	</head>

	<body>
		<nav>
			<ul>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="home"  href="/leoforfriends-1.00/jsp/homepage.jsp">
						<i class="fa fa-home"></i> HOME
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="bitbucket repo"  href="https://bitbucket.org/upd-dei-stud-prj/w2018_leoforfriendsweb/" target="_blank">
						<i class="fa fa-bitbucket"></i> BitBucket
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="search a question" href="/leoforfriends-1.00/jsp/search-question-logged.jsp">
						<i class="fa fa-search"></i> Search a question
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="Create a question" href="/leoforfriends-1.00/jsp/create-question-form.jsp">
						<i class="fa fa-file"></i> Create a question
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" title="List all the questions" href="/leoforfriends-1.00/jsp/list-all-question-logged.jsp">
						<i class="fa fa-file"></i> List all the questions
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip" id="logprof" title="User" href="/leoforfriends-1.00/jsp/user-detail.jsp">
						<i class="fa fa-user"></i><c:out value=" ${user.username}"/>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" rel="tooltip"  title="Logout" href="/leoforfriends-1.00/logout">
						<i class="fa fa-sign-out"></i> Logout
					</a>
				</li>
			</ul>
		</nav>
		<div class="cont">
			<div class="int">
				<div id="res">
					

					<!-- display the just created answer, if any and no errors -->
					<c:if test='${not empty answer && !message.error}'>
						<h1>
							Answer created successfully!
						</h1>
						<ul>
							<li>question id: <c:out value="${answer.questionid}"/></li>
							<li>answer content: <c:out value="${answer.content}"/></li>
						</ul>
						<p>
							<a href="/leoforfriends-1.00/jsp/question-page-logged.jsp?value=<c:out value="${answer.questionid}"/>">Return to the question page</a>
						</p>
					</c:if>
					<c:if test='${message.error}'>
						<p class="error_content">
							<b>AN ERROR OCCURRED</b></br></br>
							<b>DETAILS:</b></br> <c:out value="${message.message}"/></br>
							<b>CODE:</b> <c:out value="${message.errorCode}"/></br>
							<b>ERROR TYPE:</b> <c:out value="${message.errorDetails}"/></br> </br>
							<a href="/leoforfriends-1.00/jsp/question-page-logged.jsp?value=<c:out value="${answer.questionid}"/>">Return to the question page</a>
						</p>
					</c:if>
				</div>	
			</div>	
		</div>
		
		<script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
	</body>
</html>