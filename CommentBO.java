package Model.BO;

import java.util.ArrayList;

import Model.BEAN.Comment;
import Model.BEAN.User;
import Model.DAO.CommentDAO;

public class CommentBO {
	CommentDAO cmt = new CommentDAO();
	public ArrayList<Comment> getAllComment_of_tour(int tour_id){
		return cmt.getAllComment_of_tour(tour_id);
	}
	public void SendComment(int tour_id, int user_id, String content) {
		cmt.SendComment(tour_id, user_id, content);
	}
	public User getFull_name(String username) {
		return cmt.getFull_name(username);
	}
	public int getIDUserByUsername(String username) {
		return cmt.getIDUserByUsername(username);
	}
}
