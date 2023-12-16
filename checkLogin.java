package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.BEAN.Tour;
import Model.BO.checkLoginBO;
import Model.BO.functionBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ControllerCheckLogin")
public class checkLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public checkLogin() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String destination = null;
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    HttpSession session= request.getSession(true);
	    //
	    session.setAttribute("username", username);
	    //
	    checkLoginBO checkLoginBO = new checkLoginBO();
	    ArrayList<Tour> tourArray = null;
        functionBO func = new functionBO();
        tourArray = func.getAllTour(); 
        request.setAttribute("tourArray", tourArray);

	    if (checkLoginBO.isValidUser(username, password, "admin")) {
	    	session.setAttribute("username", username);
	        destination = "/home_admin.jsp";
	    } else if (checkLoginBO.isValidUser(username, password, "client")) {
	        destination = "/home.jsp";
	    } else {
	        destination = "/Login.jsp";
	    }

	    RequestDispatcher rd = request.getRequestDispatcher(destination);
	    rd.forward(request, response);
	}
}
