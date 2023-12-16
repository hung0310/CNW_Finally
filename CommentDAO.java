package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.BEAN.Comment;
import Model.BEAN.User;

public class CommentDAO {
	 	private static final String DB_URL = "jdbc:mysql://localhost:3307/data";
	    private static final String DB_USERNAME = "root";
	    private static final String DB_PASSWORD = "26102003";
	    boolean isExist = false;
        private static Connection conn = null;
        private static PreparedStatement stmt = null;
        private static ResultSet rs = null;
	    
        public ArrayList<Comment> getAllComment_of_tour(int tour_id) {
            ArrayList<Comment> result = new ArrayList<Comment>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                String query = "SELECT c.content, u.full_name " +
                               "FROM comment c " +
                               "INNER JOIN user u ON c.user_id = u.user_id " +
                               "WHERE c.tour_id = ?";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, tour_id);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String name_user = rs.getString("full_name");
                    String content = rs.getString("content");
                    Comment comment = new Comment();
                    comment.set_nameuser(name_user);
                    comment.setComment(content);
                    result.add(comment);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        
	    public String getNameUser(int user_id) {
	    	String result = "";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				String query = "select full_name from user where user_id = ?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, user_id);
				rs = stmt.executeQuery();
				if(rs.next()) {
					result = rs.getString("full_name");
				}
			} catch (Exception e) {}
			return result;
	    }
	    
	    public User getFull_name(String username) {
	    	User user = null;
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				Statement stm = conn.createStatement();
				String query = "select user_id from user where username = '"+username+"'";
				ResultSet rs = stm.executeQuery(query);
				
				if (rs.next()) {
					int id = rs.getInt("user_id");
					String full_name = getNameUser(id);
					System.out.println(full_name);
					user = new User();
					user.set_fullname(full_name);
				}
			} catch (Exception e) {}
			return user;
		}
        
        public void SendComment(int tour_id, int user_id, String content) {
            Connection conn = null;
            PreparedStatement stmt = null;
            System.out.println("tour:"+tour_id);
            System.out.println("user:"+user_id);
            System.out.println("content:"+content);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                String insertQuery = "insert into comment(tour_id, user_id, content) values(?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);
                stmt.setInt(1, tour_id);
                stmt.setInt(2, user_id);
                stmt.setString(3, content);
    			stmt.executeUpdate();	
            } catch (Exception ex) {}
        }
        
	    public int getIDUserByUsername(String username) {
			int id = 0;
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				Statement stm = conn.createStatement();
				String query = "select user_id from user where username = '"+username+"'";
				ResultSet rs = stm.executeQuery(query);
				
				if (rs.next()) {
					id = rs.getInt("user_id");
				}
			} catch (Exception e) {}
			return id;
		}
}
