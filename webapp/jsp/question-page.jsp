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
		
		<div class="background">
  		</div>
		<div class="cont">
			<h1 align="center">Question:</h1>
			<div id="question">

			</div>
			<h1 align="center">Answers:</h1>
			<div id="results">
				
			</div>
			<div id="qlink">
				
			</div>
		</div>
		
		<script src="/leoforfriends-1.00/javascript/question-page.js"></script>
	</body>
</html>