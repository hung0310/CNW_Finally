package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.BEAN.Comment;
import Model.BEAN.Tour;
import Model.BEAN.User;
import Model.BO.CommentBO;
import Model.BO.functionBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/detailTourServlet")
public class detailTourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public detailTourServlet() {
        super();
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());

        HttpSession session = request.getSession(true);
        CommentBO cmt = new CommentBO();
        String username = (String) session.getAttribute("username");

        User user = null;
        user = cmt.getFull_name(username);

        String tourIdParam = request.getParameter("tourId");
        int tour_id = Integer.parseInt(tourIdParam);

        ArrayList<Comment> list = cmt.getAllComment_of_tour(tour_id);
        request.setAttribute("commentArray", list);
        request.setAttribute("full_name", user);

        functionBO fun = new functionBO();
        Tour tour = fun.getTourbyId(tour_id);
        request.setAttribute("tour_detail", tour);
        
        session.setAttribute("tourId", tour_id);

        request.getRequestDispatcher("detailTour.jsp").forward(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String destination = null;
        CommentBO cmt = new CommentBO();
        String username = (String) session.getAttribute("username");
        
        int id = (int) session.getAttribute("tourId");
        int user_id = cmt.getIDUserByUsername(username);
        String content = request.getParameter("content");
        cmt.SendComment(id, user_id, content);
        response.sendRedirect(request.getHeader("Referer"));
    }

}
