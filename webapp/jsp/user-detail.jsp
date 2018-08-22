<!--<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Profile</title>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/user-detail.css"/>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/title.css">
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
						<i class="fa fa-user"></i>
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
			<div class="container">
				<div class="row">
					<div id="blockOne">
						<div class="span6" id="title">
							<h1>Your Profile</h1>
						</div>
						<div class="span2">
							<figure>
								<form method="GET" id="figcontainer" action="<c:url value="/user-detail"/>">
									<image src="${url}" alt="Your profile picture" id="picture" width="500" height="600">
									<figcaption id="caption" class="figure-caption">Your profile picture.</figcaption>
								</form>
							</figure>
						</div>
					</div>

					<div id="blockTwo">
						<div class="span4" id="info">
							<form method="GET" action="<c:url value="/user-detail"/>">
								<b style="font-size: 25px">PROFILE INFORMATION</b>
								<p id="user"> Username: <c:out value="${user.username}"/></p>
								<p> Name: <c:out value="${user.name}"/></p>
								<p> Surname: <c:out value="${user.surname}"/></p>
								<p> Email: <c:out value="${user.email}"/></p>
								<p> Academic Title: <c:out value="${user.academicTitle}"/></p>
							</form>
							<div id="bottomdiv">
								<form action="/leoforfriends-1.00/jsp/user-modify.jsp">
									<button type="submit" id="modifybtn" class="btn btn-success">Modify</>
								</form>
								<form action="/leoforfriends-1.00/jsp/privacy.jsp">
									<button type="submit" id="gdprbtn" class="btn btn-warning">Notes on Privacy</>
								</form>
							</div>
						</div>
						<div id="questionbtn">
							<button type="submit" class="btn btn-secondary">Show all my questions</>
						</div>
					</div>
				</div>
			</div>
			<h1 align="center">My Questions</h1>
			<div id="results" class="title">
				<p id="resTable"></p>
			</div>
		</div>
		<script>var str = '${user.username}';</script>
		<script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
		<script src="/leoforfriends-1.00/javascript/homepage.js"></script>
		<script src="/leoforfriends-1.00/javascript/user-detail.js"></script>
	</body>
</html>

