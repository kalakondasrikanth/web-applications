<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/sq.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Titillium+Web:400,300,300italic,400italic,600italic,600">
		<title>Search for a question</title>
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

		<div class="welcome"></div><br>
		<div>
			<table id="xyz">
				<th> Search for a question !!</th>
			</table><br>
			<form method="POST" action="<c:url value="/search-question-logged"/>">
				<p align="center"> <input name="userInput" type="text" placeholder="Enter your text here !!" class="search_bar">
					<input type="hidden"  id="setValue" name="value" value="">
					<input type="submit" value="Search" class="search_submit"/>
				</p>
				<p align="center"> <br> <button type="reset" class="search__reset">Reset</button></p>
			</form>
		</div>
		
		<script>var str = '${user.username}';</script>
		<script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
		<script src="/leoforfriends-1.00/javascript/search-question.js"></script>
	</body>
</html>
