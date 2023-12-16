package Controller;

import java.io.IOException;

import Model.BO.RegisterBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/ControllerRegister")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = null;
	    String fullname = request.getParameter("full_name");
	    String address = request.getParameter("address");
	    String phone_number = request.getParameter("phone");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String username = request.getParameter("username");
	    String role = request.getParameter("role");
	    RegisterBO user = new RegisterBO();
	    user.registerUser(fullname, address, phone_number, email, username, password,role);
	    destination = "/Login.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	    
	}

}
