package fr.enib.cai.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enib.cai.model.BarBusiness;
import fr.enib.cai.pojo.Bar;

/**
 * Servlet implementation class HtmlServlet
 */
public class BarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final BarBusiness beerBusiness = new BarBusiness();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BarServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   RequestDispatcher dispatch = null;
	    
	    //serve ONLY /beer uri (no duplicate content for - google crawler - )
	    // uri = /servlet-alone/bar/
	    String[] uri = request.getRequestURI().split("/");
	    if( uri.length > 3 && uri[3].length() > 0  ) {
	      // OH where is something at the end of the URI 
	      //hum 404 page
	      response.sendError(404);
	      return;
	    } else {
	      // Call the business
	      Collection<Bar> bar = beerBusiness.getBar();
	  
	      // Adds the model response to the request
	      request.setAttribute("bar", bar);
	  
	      // Forward to the JSP
	      dispatch = request.getRequestDispatcher("/WEB-INF/views/bar.jsp");
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
    String[] uri = request.getRequestURI().split("/");
    String action = uri[3];

    if ("checkInBeer".equals(action)) {
      // Gets the id of the beer to delete in the request
      int beerId = Integer.parseInt(request.getParameter("id"));
      
      // Adds the POJO to the list of beers
      Bar bar = beerBusiness.checkinBeer(beerId, 1);

      // Adds the new beer to the request
      request.setAttribute("bar", bar);
      
      // Forward to the JSP (JSON FEED)
      dispatch = request.getRequestDispatcher("/WEB-INF/views/bar-json.jsp");  
    } else if("checkOutBeer".equals(action)) {
      // Gets the id of the beer to delete in the request
      int beerId = Integer.parseInt(request.getParameter("id"));
      
      // Adds the POJO to the list of beers
      Bar bar = beerBusiness.checkoutBeer(beerId, 1);

      // Adds the new beer to the request
      request.setAttribute("bar", bar);
      dispatch = request.getRequestDispatcher("/WEB-INF/views/bar-json.jsp");  
    }
    
    
    // Forward to the default JSP (idempotent request)
    if( dispatch == null) {
      doGet(request, response);
    } else {
      dispatch.forward(request, response);
    }    
	}
}
