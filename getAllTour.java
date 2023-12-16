package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.BEAN.Tour;
import Model.BO.functionBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ControllerGetAllTour")
public class getAllTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public getAllTour() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				ArrayList<Tour> tourArray = null;
		        functionBO func = new functionBO();
		        tourArray = func.getAllTour(); 
		        request.setAttribute("tourArray", tourArray);
		        request.getRequestDispatcher("home_admin.jsp").forward(request, response);
		        
	}

}
