<!---->
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
	<head>
		<meta charset="UTF-8">
		<title>Registration@leoforfriends</title>  
		<link rel="stylesheet" href="../css/registration.css">
		<link rel="stylesheet" href="../css/topnav.css">
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
				<div class="register">
					<div class="register__form">
						<form method="POST" id="regform" action="<c:url value="/registration"/>">
						    <p id="title">Registration</p>
							<div class="register__row">
								<input name="email" id="emailbox" type="text" class="register__input name" placeholder="Email" /><br/>
							</div>
							<div class="register__row">
								<input name="username" id="usernamebox" type="text" class="register__input name" placeholder="Username" /><br/>
							</div>
							<div class="register__row">
								<input name="name" id="namebox" type="text" class="register__input name" placeholder="Name" /><br/>
							</div>
							<div class="register__row">
								<input name="surname" id="surnamebox" type="text" class="register__input name" placeholder="Surname" /><br/>
							</div>
							<div class="register__row">
								<input name="hashedpw" id="pwbox" input type="password" type="text" class="register__input pass" placeholder="Password"/><br/>
							</div>
							<div class="register__row">
								<input name="hashedpw1" id="pw1box" input type="password" type="text" class="register__input pass" placeholder="Confirm Password"/><br/>
							</div>
							<button type="submit" class="register__submit">Register</button>
						</form>
						<p class="register__signup">Already have an account? &nbsp;<a href="/leoforfriends-1.00/jsp/login.jsp">Login</a></p>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="/leoforfriends-1.00/javascript/registration.js"></script>
	</body>
</html>