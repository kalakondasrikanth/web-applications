<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="/leoforfriends-1.00/css/privacy.css">
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
			<div class="int">
				<div class="login">
					<div class="row">
                        <p id="para">
                            <h1 id="head">GDPR-related information</h1>
                            Europe is now covered by the world's strongest data protection rules. The mutually agreed General Data Protection Regulation (GDPR) came into force on May 25, 2018, and was designed to modernise laws that protect the personal information of individuals.
                            </br></br>
                            Before GDPR started to be enforced, the previous data protection rules across Europe were first created during the 1990s and had struggled to keep pace with rapid technological changes. GDPR alters how businesses and public sector organisations can handle the information of their customers. It also boosts the rights of individuals and gives them more control over their information.
                            </br></br>
                            But what is GDPR?</br>
                            The GDPR is Europe's new framework for data protection laws replacing the previous 1995 data protection directive. </br>
                            The EU's GDPR website says the legislation is designed to "harmonise" data privacy laws across Europe as well as give greater protection and rights to individuals. Within the GDPR there are large changes for the public as well as businesses and bodies that handle personal information, which we'll explain in more detail later.
                            </br></br>
                            After more than four years of discussion and negotiation, GDPR was adopted by both the European Parliament and the European Council in April 2016. The underpinning regulation and directive were published at the end of that month.
                            </br></br>
                            The key points of the GDPR as well as information on the impacts it will have on business can be found at the official
                            <u><a href="https://ec.europa.eu/info/law/law-topic/data-protection_en" target="blank">European Commision website</a></u>.
                        </p>
				    </div>
				</div>
			</div>
		</div>
		<script>var str = '${user.username}';</script>
        <script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
        <script src="/leoforfriends-1.00/javascript/homepage.js"></script>
	</body>
</html>