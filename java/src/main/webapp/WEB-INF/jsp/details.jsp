<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<c:import url="/WEB-INF/jsp/common/header.jsp" >
	<c:param name="pageTitle">Park Details</c:param>
	<c:param name="activeNav" value="details"/>

</c:import>

<style>
.weatherImg {
 	width:100%;
 	
}


 .weatherMessage .tempMessage {
 display: block;
 }


</style>

<div class="container">

	<!-- image goes here
 -->
	<div class="image">
		<img
			src="<c:url value="/img/parks/${park.parkCode.toLowerCase()}.jpg"/>" />
	</div>

	<h1>${park.parkName}</h1>
	<p>${park.parkDescription}</p>
	
	<p>${park.inspirationalQuote}</p>
	<p>-${park.inspirationalQuoteSource}</p>
	
	<table class="table">
		<tr>
			<th scope="col">State</th>
			<th scope="col">Acreage</th>
			<th scope="col">Elevation in Feet</th>
			<th scope="col">Miles of Trail</th>
			<th scope="col">Number of Campsites</th>
			<th scope="col">Year Founded</th>
			<th scope="col">Annual Visitor Count</th>
			<th scope="col">Entry Fee</th>
			<th scope="col">Number of Animal Species</th>
		</tr>
	
	<tr>
		<td>${park.state}</td>
		<td>${park.acreage}</td>
		<td>${park.elevationInFeet}</td>
		<td>${park.milesOfTrail}</td>
		<td>${park.numberOfCampsites}</td>
		<td>${park.yearFounded}</td>
		<td>${park.annualVisitorCount}</td>
		<td>
			<c:choose>
				<c:when test="${park.entryFee == 0 }">Free!</c:when>
				<c:otherwise>$${park.entryFee}.00</c:otherwise>
			</c:choose>	
		</td>
		<td>${park.numberOfAnimalSpecies}</td>
	</tr>
	
	</table>
	

	<form method="POST" action="/m3-java-capstone/details">
		<div class="d-flex container pt-4">
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="tempChoice" id="radio1" value="F" checked>
				<label class="form-check-label" for="radio1">
					Fahrenheit
				</label>
			</div>
			<div class="form-check form-check-inline">
				<c:choose>
					<c:when test="${tempChoice=='C'}">
						  <input class="form-check-input" type="radio" name="tempChoice" id="radio2" value="C" checked>
					</c:when>
					<c:otherwise>
						  <input class="form-check-input" type="radio" name="tempChoice" id="radio2" value="C">
					</c:otherwise>
				</c:choose>
				<c:if test=""></c:if>
				<label class="form-check-label" for="radio2">
					Celsius
				</label>
			</div>
			<div class="mr-2">
				<button type="submit" class="btn btn-primary mb-2">Change Temp</button>
			</div>
		</div>
		
	</form>

	<c:choose>
		<c:when test="${tempChoice == 'F'}">
			<div class="alert alert-info" role="alert">
				  Temperature is displayed in fahrenheit
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-info" role="alert">
				Temperature is displayed in celsius
			</div>
		</c:otherwise>
	</c:choose>
	
	<div class="container d-flex">


		<c:forEach var="day" items="${weatherList}">
			<c:choose>
				<c:when test="${tempChoice == 'F'}">
					<c:set var= "high" value="${day.highInF}"/>
					<c:set var= "low" value="${day.lowInF}"/>
				</c:when>
				
				<c:otherwise>
					<c:set var= "high" value="${day.highInC}"/>
					<c:set var= "low" value="${day.lowInC}"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${day.fiveDayForecastValue == 1 }">
					<div class="container" id="todayWeather">
						<h3>Today</h3>
						<img class="weatherImg" src="<c:url value="/img/weather/${day.forecast}.png"/>" /> 
						<span class="high">High: ${high }</span> 
						<span class="low">Low: ${low}</span> 
						<div class=weatherMessage>${day.weatherMessage}</div>
						<div class=tempMessage>${day.tempMessage}</div>
					</div>
				</c:when>

				<c:otherwise>
					<div class="container" id="fututeWeather">
						<img class="weatherImg" src="<c:url value="/img/weather/${day.forecast}.png"/>" /> 
						<span class="high">High: ${high}</span> 
						<span class="low">Low: ${low}</span>

					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />