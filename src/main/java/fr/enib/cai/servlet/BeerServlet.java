package fr.enib.cai.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enib.cai.model.BarBusiness;
import fr.enib.cai.pojo.Beer;

/**
 * Servlet implementation class HtmlServlet
 */
public class BeerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final BarBusiness beerBusiness = new BarBusiness();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BeerServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HTTP GET - model consultation - idempotent request
		RequestDispatcher dispatch = null;
		
		//serve ONLY /beer uri (no duplicate content for - google crawler - )
		// uri = /servlet-alone/beer/
		String[] uri = request.getRequestURI().split("/");
		if( uri.length > 3 && uri[3].length() > 0  ) {
			// OH where is something at the end of the URI 
			//hum 404 page
			response.sendError(404);
			return;
		} else {
			// Call the business
			Collection<Beer> beers = beerBusiness.getBeers();
	
			// Adds the model response to the request
			request.setAttribute("beers", beers);
	
			// Forward to the JSP
			dispatch = request.getRequestDispatcher("/WEB-INF/views/beer.jsp");
		}
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = null;
		
		// HTTP POST - model modification
		
		// find the action name ( /beer/add or /beer/delete )
		// very simple (not robust)
		String[] uri = request.getRequestURI().split("/");
		String action = uri[3];

		if ("add".equals(action)) {
			//  ADD Beer AJAX request - no idempotent request
			// Creation a POJO
			Beer newBeer = new Beer(request.getParameter("name"), 
									request.getParameter("brewery"), 
									request.getParameter("country"),
									Double.parseDouble(request.getParameter("alcohol")));
		
			// Adds the POJO to the list of beers
			Beer newbeer = beerBusiness.addBeer(newBeer);

			// Adds the new beer to the request
			if( newbeer != null) {
				request.setAttribute("beer", newbeer);
			}
			// Forward to the JSP (JSON FEED)
			dispatch = request.getRequestDispatcher("/WEB-INF/views/beer-json.jsp");		
		} else if ("delete".equals(action)) {
			// DELETE BEER AJAX Request - no idempotent request
			
			// Gets the id of the beer to delete in the request
			int beerId = Integer.parseInt(request.getParameter("id"));
			
			// Call the business 
			Beer beerDeleted = beerBusiness.removeBeer(beerId);
			
			// Adds the beer deleted to the request
			if( beerDeleted != null) {
				request.setAttribute("beer", beerDeleted);
			}			
			
			// Forward to the JSP (JSON FEED)			
			dispatch = request.getRequestDispatcher("/WEB-INF/views/beer-json.jsp");
		} else {
			//unknown action forward to error page
			dispatch = request.getRequestDispatcher("/WEB-INF/views/404.jsp");
		}


		// Forward to the default JSP (idempotent request)
		if( dispatch == null) {
			doGet(request, response);
		} else {
			dispatch.forward(request, response);
		}
	}

}
