<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${param.pageTitle}"></c:out></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="<c:url value="/css/site.css"/>" rel="stylesheet" type="text/css" />
<style type="text/css">
	label {
	    display: block;
	}
	
	.form-control {
	    display: inline-block;
	}
	
	#homeLink:hover, #surveyLink:hover, #resultLink:hover{
		color: #8dae19
	}
</style>

</head>
<body>
	<div class="d-flex">
		<div>
			<c:url var="headImgUrl" value="img/logo.png"/>
			<img style="height: 10rem" src="${headImgUrl}">
		</div>
	</div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-2">
		<div class="collapse navbar-collapse justify-content-between" id="navbarNav">
			<ul class="navbar-nav mr-auto align-items-center">
				<li class="nav-item ${param.activeNav == 'home'? 'active':''}">
					<a id="homeLink" class="nav-link navbar-brand" href="<c:url value="/homePage"/>">Home</a>
				</li>
				<li class="nav-item ${param.activeNav == 'survey'? 'active':''}" style="align-items: center">
					<a id="surveyLink" class="nav-link"  style="align-items: center" href="<c:url value="/survey"/>">Survey</a>
				</li>
				<li class="nav-item ${param.activeNav == 'surveyResults'? 'active':''}">
					<a id="resultLink" class="nav-link" href="<c:url value="/surveyResults"/>">Survey Results</a>
				</li>
			</ul>
			<ul class="navbar-nav">
				<c:if test="${not empty appCurrentUser}">
					<li class="nav-item">
					    <span class="navbar-text mr-3"><c:out value="Hello, ${appCurrentUser.username}!" /></span>
					</li>
					<li class="nav-item">
						<c:url var="logoutUrl" value="/logout"/>
		            	<form action="${logoutUrl}" method="POST" class="navbar-form">
		                   <button type="submit" class="btn btn-secondary">Logout</button>
                		</form>
					</li>
				</c:if>
				<c:if test="${empty appCurrentUser}">
					<li class="nav-item ${param.activeNav == 'register'? 'active':''}">
						<a id="register" class="nav-link mr-2" href="<c:url value="/register"/>">Register</a>
					</li>
				
					<li class="nav-item ${param.activeNav == 'login'? 'active':''}">
						<a id="login" class="nav-link mr-2" href="<c:url value="/login"/>">Login</a>
					</li>
				</c:if>
			</ul>
		</div>
	</nav>
	
