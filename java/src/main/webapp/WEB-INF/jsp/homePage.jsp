<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle">Home</c:param>
	<c:param name="activeNav" value="home"/>

</c:import>

<div class="container flex-fill">
	<c:forEach var="park" items="${parkList}">
		<div class="row mb-5">
			<div class="col-5">
				<c:url var="detailsUrl" value="/details">
					<c:param name="parkCode">${park.parkCode}</c:param>
				</c:url>
				<a  href="${detailsUrl}">
					<img id="park-pics" src="<c:url value="/img/parks/${park.parkCode.toLowerCase()}.jpg"/>" class="img-fluid"/>
				</a>
				
			</div>
			<div class="col-7 d-flex flex-column">
			 
				<h3 class="mt-4">${park.parkName}</h3>
				<p>${park.parkDescription}</p>
			</div>
		</div>
	
	</c:forEach>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />