<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>


<json:object>
	<c:if test="${not empty bar}">
		<json:property name="id" value="${bar.beer.id}"/>
		<json:property name="stock" value="${bar.stock}"/>
	</c:if>
</json:object>