<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
           
           
<json:object>
	<c:if test="${not empty beer}">
	     <json:property name="name" value="${beer.name}"/>
	     <json:property name="brewery" value="${beer.brewery}"/>
	     <json:property name="country" value="${beer.country}"/>
	     <json:property name="alcohol" value="${beer.alcohol}"/>
	     <json:property name="id" value="${beer.id}"/>
	</c:if>
</json:object>