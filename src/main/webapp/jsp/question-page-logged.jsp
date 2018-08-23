<!---->
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

	
	<head>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/question-page.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<title>Question page</title>
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
		
		<div class="background">
		</div>
		<div class="cont">
			<h1 align="center">Question:</h1>
			<div id="question">
				
			</div>
			<h1 align="center">Answers:</h1>
			<div id="results">
				
			</div>
			<button id="answerbtn" align="center">Insert your answer</button>
			<div id="answer">
				<form id="form" method="POST" action="<c:url value="/create-answer"/>">
						<input type="hidden" id="qid" class="zx" name="questionid" type="number"/><br/>
						<textarea id="qtext" name="content" class="zx" placeholder="Enter your answer..." type="text" cols="50" rows="10"></textarea>
						<button class="submit" type="submit">Submit</button><br/>
				</form>
			</div>

		</div>
		
		<script>var str = '${user.username}';</script>
		<script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
		<script src="/leoforfriends-1.00/javascript/homepage.js"></script>
		<script src="/leoforfriends-1.00/javascript/question-page-logged.js"></script>
	</body>
</html>