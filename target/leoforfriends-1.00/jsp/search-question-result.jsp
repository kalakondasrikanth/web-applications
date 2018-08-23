<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/sqr.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Titillium+Web:400,300,300italic,400italic,600italic,600">
		<title>Search question result</title>
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

		<div class="welcome"></div><br>
		<!-- display the message -->
		<table id="xyz">
			<th> Showing all your results based on matching tokens </th>
		</table><br>
		<!-- display the just found quesitons, if any and no errors -->
		<table id="results" align="center">
			<tbody>
				<tr>
					<th>USER</th>
					<th>TITLE</th>
				</tr>
				<c:if test='${not empty questions && !message.error}'>
					<c:forEach items="${questions}" var="question">
						<div>
							<tr>
								<td><c:out value="${question.actor}"/></td>
								<td>
									<div class="section">
										<div class="title">
											<c:out value="${question.title}"/>
										</div>
										<div class="answer" id="myDIV">
											<p class="italic"> This is the answer for your selection :</p> <br><c:out value="${question.content}"/>
											<br><br><a href="/leoforfriends-1.00/jsp/question-page.jsp?value=<c:out value="${question.id}"/>">See other details of the question</a>
										</div>
									</div>
								</td>
							</tr>
						</div>
					</tbody>
				</c:forEach>
			</table>
		</c:if>
				
		<script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
		<script src="/leoforfriends-1.00/javascript/sqr.js"></script>
	</body>
</html>
