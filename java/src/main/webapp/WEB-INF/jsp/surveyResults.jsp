<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle">Survey Results</c:param>
	<c:param name="activeNav" value="surveyResults"/>
</c:import>

<style>
.parkImg {
	height:20vh;
	border: 4px solid white;
}

#resultList {
	margin-left:0px;
}

</style>

<div class="ml-3">
	<h2>Most Popular National Parks!</h2>
	<p>List below are the nation parks that you the public voted as their favorites.</p>
</div>
<div class="container" id="" style="margin:0px">
<ul class="list-group list-group-flush">
	<c:forEach var="result" items="${resultList}">
	  	<li class="list-group-item result-list survey-result-items">
	  		<div class="d-flex justify-content-between mb-3">
				<img class="parkImg" src="<c:url value="/img/parks/${result.parkCode.toLowerCase()}.jpg"/>" />
				<div class="align-self-center">
					<h4>${result.parkName}</h4>
				</div>
				<div class="align-self-center">
					<h4># votes: ${result.surveyCount}</h4>
				</div>
			</div>
	  	</li>		
	</c:forEach>
</ul>
</div>





<c:import url="/WEB-INF/jsp/common/footer.jsp" />