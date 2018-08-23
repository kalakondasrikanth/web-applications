<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/list-all-question.css">
      	<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css"> 
        <link rel="stylesheet" href="/leoforfriends-1.00/css/title.css"> 
		<title>List of all questions</title>
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
			<h1 align="center">Questions:</h1>
			<div id="results" class="title">
				
			</div>
		</div>

		<script>var str = '${user.username}';</script>
        <script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
        <script src="/leoforfriends-1.00/javascript/homepage.js"></script>
		<script src="/leoforfriends-1.00/javascript/list-all-question.js"></script>
	</body>
</html>