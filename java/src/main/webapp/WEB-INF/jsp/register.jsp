<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle">Register</c:param>
	<c:param name="activeNav" value="register"/>

</c:import>

<style>
.error {
	color:red;
}
</style>
<div class="container ml-4">
	<h2>New User Registration</h2>
	
	<form:form id="registration" action="/m3-java-capstone/register" method="POST" modelAttribute="user">
	
	  <div class="form-group">
	    <label for="username"><strong>Username</strong></label>
	    <form:input type="text" class="form-control" placeholder="Username" path="username" required="required"/>
	  	<form:errors path="username" class="bg-danger"/>
	  </div>
	  
	  <div class="form-group">
	    <label for="password"><strong>Password</strong></label>
	    <form:input type="password" class="form-control" path="password" required="required"/>
	  	<form:errors path="password" class="bg-danger"/>
	  </div>
	  
	  <div class="form-group">
	    <label for="passwordConfirm"><strong>Confirm Password</strong></label>
	    <form:input type="password" class="form-control" path="confirmPassword"/>
	  	<form:errors path="passwordMatching" class="bg-danger"/>
	  </div>
	  	  	  	  
	  <button type="submit" class="btn btn-primary">Submit</button>
	
	
	</form:form>

</div>


<c:import url="common/footer.jsp"/>