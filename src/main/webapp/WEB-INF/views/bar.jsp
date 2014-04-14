<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>My Bar</title>
		<script>
		// Checkin beers in the bar
		function checkInBeer( beerId ) {
			$.post("bar/checkInBeer", { id: beerId },
				function(beerStockJson) {
				beerStockJson = JSON.parse(beerStockJson);
				if( beerStockJson.id != null) {
					updateRow(beerStockJson.id,beerStockJson.stock);
				} else {
					displayError();
				}
				}).error(function() { 
					displayError();
				});
		}
		
		// Checkout beers in the bar
		function checkOutBeer( beerId ) {
			$.post("bar/checkOutBeer", { id: beerId },
				function(beerStockJson) {
				beerStockJson = JSON.parse(beerStockJson);
				if( beerStockJson.id != null) {
					updateRow(beerStockJson.id,beerStockJson.stock);
				} else {
					displayError();
				}
				}).error(function() { 
					displayError();
				});
		}		
		
		function displayError() {
			$("#error-message").html('<div class="alert alert-error"><button type="button" class="close" data-dismiss="alert">×</button><strong>Oh snap!</strong> Something bad has happened, please refresh the page !</div>');
		}
		
		function updateRow( beerId, beerStock) {	
			var row = document.getElementById("beer_" + beerId);
			row.cells[4].innerHTML =  beerStock;
		}
				
		</script>
</head>
<body>
    <!-- nav bar -->
    <jsp:include page="includes/navbar.jsp"/>
    
	<div class="container" data-spy="scroll" data-target="#topnavbar" data-twttr-rendered="true" style="padding-top: 40px;">
		<!--  HEADER -->
		<div class="header">
			<div class="header-left">
				<ul class="nav">
					<li class="nav">My Favorite beers</li>
				</ul>
			</div>
		</div>
		<hr>
           
        <div id="error-message"></div>
        
		<table id="beerstable" class="table table-condensed">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Brewery</th>
                  <th>Country</th>
                  <th>Alcohol</th>                 
                  <th>Stock</th>
                 </tr>
              </thead>
              <tbody>
   
				<c:forEach items="${bar}" var="beerStock">
	                <tr id="beer_${beerStock.beer.id}">
	                  <td>${beerStock.beer.name}</td>
	                  <td>${beerStock.beer.brewery}</td>
	                  <td>${beerStock.beer.country}</td>
	                  <td>${beerStock.beer.alcohol}</td>
	                  <td>${beerStock.stock}</td>
	                  <td>
	                  <a href="javascript:checkInBeer(${beerStock.beer.id})"><i class="icon-plus"></i></a>
	                  <a href="javascript:checkOutBeer(${beerStock.beer.id})"><i class="icon-minus"></i></a>
	                  </td>
	                </tr>
				</c:forEach>
              </tbody>
         </table>
         <hr>
	</div>
	
	<jsp:include page="includes/footer.jsp" />    
</body>
</html>