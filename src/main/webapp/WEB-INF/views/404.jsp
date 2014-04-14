<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Oups page not finded</title>
</head>
	<body>
	    <!-- nav bar -->
	    <jsp:include page="includes/navbar.jsp"/>
	    
		<div class="container" data-spy="scroll" data-target="#topnavbar" data-twttr-rendered="true" style="padding-top: 40px;">
			<!--  HEADER -->
			<div class="header">
				<div class="header-left">
					<ul class="nav">
						<li class="nav"></li>
					</ul>
				</div>
			</div>
			<hr>
	        Désolé, cette page n'existe pas !
	    </div>
		<jsp:include page="includes/footer.jsp" />    
	</body>
</html>