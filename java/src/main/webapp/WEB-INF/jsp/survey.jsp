<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle">Survey</c:param>
	<c:param name="activeNav" value="survey"/>

</c:import>

<style>
.error {
	color:red;
}
</style>
<div class="container">
	<h2>Please take this short survey</h2>
	<p>In order to better serve the public, please fill out the fields below. Your feedback is important and will be used to make improvements to our national parks.</p>
	
	<form:form id="survey" action="/m3-java-capstone/survey" method="POST" modelAttribute="survey">
		<div class="form-group">
			<label for="parkCode"><strong>Favorite National Park</strong></label>
			<form:select class="form-control" id="parkCode" path="parkCode">
						
				<form:option value="">Choose A Park</form:option>
				<c:forEach var="park" items="${parkList}">
					<form:option value="${park.parkCode}">${park.parkName}</form:option>
				</c:forEach>
			</form:select>	
			<form:errors class="error" path="parkCode"/>
		</div>
		
		<div class="form-group">
			<label for="emailAddress"><strong>Your Email</strong></label>
			<form:input type="email" class="form-control" id="emailAddress" path="emailAddress" placeholder="Enter email"/>
			<form:errors class="error" path="emailAddress"/>		
		</div>
		
		<div class="form-group">
			<label for="state"><strong>State of residence</strong></label>
			<form:select class="form-control" id="state" path="state">
				<form:option value="">Choose a state</form:option>
				<form:option value="AL">Alabama</form:option>
				<form:option value="AK">Alaska</form:option>
				<form:option value="AZ">Arizona</form:option>
				<form:option value="AR">Arkansas</form:option>
				<form:option value="CA">California</form:option>
				<form:option value="CO">Colorado</form:option>
				<form:option value="CT">Connecticut</form:option>
				<form:option value="DE">Delaware</form:option>
				<form:option value="DC">District Of Columbia</form:option>
				<form:option value="FL">Florida</form:option>
				<form:option value="GA">Georgia</form:option>
				<form:option value="HI">Hawaii</form:option>
				<form:option value="ID">Idaho</form:option>
				<form:option value="IL">Illinois</form:option>
				<form:option value="IN">Indiana</form:option>
				<form:option value="IA">Iowa</form:option>
				<form:option value="KS">Kansas</form:option>
				<form:option value="KY">Kentucky</form:option>
				<form:option value="LA">Louisiana</form:option>
				<form:option value="ME">Maine</form:option>
				<form:option value="MD">Maryland</form:option>
				<form:option value="MA">Massachusetts</form:option>
				<form:option value="MI">Michigan</form:option>
				<form:option value="MN">Minnesota</form:option>
				<form:option value="MS">Mississippi</form:option>
				<form:option value="MO">Missouri</form:option>
				<form:option value="MT">Montana</form:option>
				<form:option value="NE">Nebraska</form:option>
				<form:option value="NV">Nevada</form:option>
				<form:option value="NH">New Hampshire</form:option>
				<form:option value="NJ">New Jersey</form:option>
				<form:option value="NM">New Mexico</form:option>
				<form:option value="NY">New York</form:option>
				<form:option value="NC">North Carolina</form:option>
				<form:option value="ND">North Dakota</form:option>
				<form:option value="OH">Ohio</form:option>
				<form:option value="OK">Oklahoma</form:option>
				<form:option value="OR">Oregon</form:option>
				<form:option value="PA">Pennsylvania</form:option>
				<form:option value="RI">Rhode Island</form:option>
				<form:option value="SC">South Carolina</form:option>
				<form:option value="SD">South Dakota</form:option>
				<form:option value="TN">Tennessee</form:option>
				<form:option value="TX">Texas</form:option>
				<form:option value="UT">Utah</form:option>
				<form:option value="VT">Vermont</form:option>
				<form:option value="VA">Virginia</form:option>
				<form:option value="WA">Washington</form:option>
				<form:option value="WV">West Virginia</form:option>
				<form:option value="WI">Wisconsin</form:option>
				<form:option value="WY">Wyoming</form:option>
			</form:select>
			<form:errors class="error" path="state"/>
		</div>
		
		<div class="form-group">
			<label for="activityLevel"><strong>Activity level</strong></label>
			<form:select class="form-control" id="activityLevel" path="activityLevel" placeholder="Enter activity level">
				<form:option value="">Choose an activity level</form:option>
				<form:option value="inactive">Inactive</form:option>
				<form:option value="sedentary">Sedentary</form:option>
				<form:option value="active">Active</form:option>
				<form:option value="extremely active">Extremely Active</form:option>
			</form:select>
			<form:errors class="error" path="activityLevel"/>		
		</div>
		
		<button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />