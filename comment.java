package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import Model.BEAN.Comment;
import Model.BEAN.User;
import Model.BO.CommentBO;
import Model.BO.functionBO;

@WebServlet("/comment")
public class comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public comment() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	    HttpSession session= request.getSession(true);
        CommentBO cmt = new CommentBO();
		String username = (String)session.getAttribute("username");
		
		User user = null;
		user = cmt.getFull_name(username);
		int tour_id = 1;
		ArrayList<Comment> list = null;

        list = cmt.getAllComment_of_tour(tour_id);
        request.setAttribute("commentArray", list);
        request.setAttribute("full_name", user);
        request.getRequestDispatcher("Comment.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession(true);
		CommentBO cmt = new CommentBO();
		String username = (String)session.getAttribute("username");
		int id =  Integer.parseInt((String)session.getAttribute("tourId"));
		System.out.println("input: "+id);
		int user_id = cmt.getIDUserByUsername(username);
		String content = request.getParameter("content");
		cmt.SendComment(id, user_id, content);
		request.getRequestDispatcher("Comment.jsp").forward(request, response);
	}

}