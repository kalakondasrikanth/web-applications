<!--<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/topnav.css">
		<link rel="stylesheet" href="/leoforfriends-1.00/css/user-detail.css"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Titillium+Web:400,300,300italic,400italic,600italic,600">
		<title>Modify User Account</title>
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
        					<h1>Modify User Account</h1>
        				</div>
        				<div class="span2">
        					<figure>
        						<form method="GET" id="figcontainer" action="<c:url value="/user-detail"/>">
        							<image src="${url}" alt="Your profile picture" id="picture" width="500" height="600">
        							<figcaption id="caption" class="figure-caption">A wonderful specimen of Tarsier.</figcaption>
        						</form>
        						<form method="POST" action="<c:url value="/modifypicture"/>" enctype = "multipart/form-data">
                                	<input type="file" name="image" id="imagebtn" class="btn btn-success" value="change picture"/>
                                	<input type="submit" id="btnsave" value="save">
                                </form>
                                <form method="POST" action="<c:url value="/remove-picture"/>">
                                    <input type="submit" id="remove" value="remove">
                                </form>
        					</figure>
        				</div>
        			</div>
        			<div id="blockTwo">
                        <div class="span4" id="info">
                            <form method="POST" action="<c:url value="/user-modify"/>">
                                <fieldset>
                                    <label for="username">Username:</label>
                                    <input name="username" type="text"/><br/>

                                    <label for="name">Name:</label>
                                    <input name="name" type="text"/><br/>

                                    <label for="surname">Surname:</label>
                                    <input name="surname" type="text"/><br/>

                                    <label for="academicTitle">Academic Title:</label>
                                    <input name="academicTitle" type="text"/><br/>

                                    <label for="hashedpw">Password:</label>
                                    <input name="hashedpw" input type="password" type="text"/><br/>

                                    <label for="hashedpw1">Confirm password:</label>
                                    <input name="hashedpw1" input type="password" type="text"/><br/>

                                    <button type="submit" id="subbtn">Submit</button><br/>
                                    <button type="reset" id="rembtn">Reset the form</button>
                                </fieldset>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>

		<script>var str = '${user.username}';</script>
		<script src="/leoforfriends-1.00/javascript/jquery-3.3.1.min.js"></script>
		<script src="/leoforfriends-1.00/javascript/homepage.js"></script>
	</body>
</html>