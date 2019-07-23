<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle">Login</c:param>
	<c:param name="activeNav" value="login"/>

</c:import>

<style>
.error {
	color:red;
}
</style>
<div class="container ml-4">
	<c:if test="${message != null}">
		<div class="alert alert-danger" role="alert">
	  		${message}
		</div>
	</c:if>
	<h2>Login</h2>
	
	<form action="/m3-java-capstone/login" method="POST">
		<div class="form-group">
			<label for="username"><strong>Username</strong></label>
			<input type="text" class="form-control" placeholder="Username" id="username" name="username" required="required"/>
		</div>
		<div class="form-group">
			<label for="password"><strong>Password</strong></label>
			<input type="password" class="form-control" id="password" name="password" required="required"/>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>

</div>


<c:import url="common/footer.jsp"/>