<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>My Favorite beers</title>
		<script>
			$(document).ready( function() {
				$('#addbeerForm').ajaxForm( function(newBeerJson) {
					// add the new beer to the table
					newBeer = JSON.parse(newBeerJson);
					if( newBeer.name != null) {
						addRow( newBeer);
						// reset the form
						$('#addbeerForm')[0].reset();
						// set the focus
						$('#name').focus();
					}

				});
			});
	
			// Adds newBeer in the table beerstable
			function deleteBeer( beerId ) {
				$.post("beer/delete", { id: beerId },
					function(deletedBeerJson) {
					deletedBeer = JSON.parse(deletedBeerJson);
					if( deletedBeer.name != null) {
						deleteRow(deletedBeer.id);
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
			
			function deleteRow( beerId) {	
				var row = document.getElementById("beer_" + beerId);
				row.parentNode.removeChild(row);
			}
			
			// Adds newBeer in the table beerstable
			function addRow( newBeer) {
				var table=document.getElementById("beerstable");
				var newrow = table.insertRow(-1);
				newrow.setAttribute("id","beer_" + newBeer.id);
				
				var cellName = newrow.insertCell(0);
				cellName.innerHTML += newBeer.name;
				var cellBrewery = newrow.insertCell(1);
				cellBrewery.innerHTML += newBeer.brewery;
				var cellCountry = newrow.insertCell(2);
				cellCountry.innerHTML += newBeer.country;
				var cellAlcohol = newrow.insertCell(3);
				cellAlcohol.innerHTML += newBeer.alcohol;		
				var cellDelete  = newrow.insertCell(4);
				cellDelete.innerHTML = '<a href="javascript:deleteBeer(' + newBeer.id + ')"><i class="icon-remove-sign"></i></a>';				
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
                  <th>Delete</th>                  
                </tr>
              </thead>
              <tbody>
   
				<c:forEach items="${beers}" var="beer">
	                <tr id="beer_${beer.id}">
	                  <td>${beer.name}</td>
	                  <td>${beer.brewery}</td>
	                  <td>${beer.country}</td>
	                  <td>${beer.alcohol}</td>
	                  <td><a href="javascript:deleteBeer(${beer.id})"><i class="icon-remove-sign"></i></a></td>
	                </tr>
				</c:forEach>
              </tbody>
         </table>
         <hr>
	</div>
	
	<div id="addbeer" class="lightbox hide fade" tabindex="-1" role="dialog" aria-hidden="true">
		<div class='lightbox-header'>
			<button type="button" class="close" data-dismiss="lightbox" aria-hidden="true">&times;</button>
		</div>
		<div class='lightbox-content'>	
			<form id="addbeerForm" action="beer/add" method="post" class="form-horizontal">
				<div id="nameControlGroup" class="control-group">
					<label class="control-label" id="nameLabel" for="name">Name</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="Name">
					</div>
				</div>
	
				<div id="breweryControlGroup" class="control-group">
					<label class="control-label" id="breweryLabel" for="brewery">Brewery</label>
					<div class="controls">
						<input type="text" id="brewery" name="brewery" placeholder="Brewery">
					</div>
				</div>
	
				<div id="countryControlGroup" class="control-group">
					<label class="control-label" id="countryLabel" for="country">Country</label>
					<div class="controls">
						<input type="text" id="country" name="country" placeholder="Country">
					</div>
				</div>
	
				<div id="alcoholControlGroup" class="control-group">
					<label class="control-label" id="alcoholLabel" for="country">Alcohol</label>
					<div class="controls">
						<input type="text" id="alcohol" name="alcohol" placeholder="Alcohol">
					</div>
				</div>
	
				<div class="control-group">
					<div class="controls">
						<button  class="btn">Add</button>
					</div>
				</div>
			</form>
	
		</div>
	</div>	
	
	<jsp:include page="includes/footer.jsp" />    
</body>
</html>