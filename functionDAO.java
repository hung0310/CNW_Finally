package Model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBContext.DBContext;
import Model.BEAN.Booking;
import Model.BEAN.Tour;


public class functionDAO {
	    boolean isExist = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
	    public boolean addTour(String location, String description, String number_day, String filepath,String price,String detail_description,String video_link) { 
	        try {
	            conn = DBContext.getConnection();
	            String sql = "INSERT INTO tour(location, description, number_day, image,Price,detail_description,video_link) VALUES (?, ?, ?, ?,?,?,?)";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, location);
	            stmt.setString(2, description);
	            stmt.setString(3, number_day);
	            stmt.setString(4, filepath);
	            stmt.setString(5, price);
	            stmt.setString(6, detail_description);
	            stmt.setString(7, video_link);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                isExist = true;
	                
	            }
	        } catch (ClassNotFoundException ex) {
	            System.out.println("MySQL JDBC driver not found.");
	            ex.printStackTrace();
	        } catch (SQLException ex) {
	            System.out.println("An error occurred while connecting to the database.");
	            ex.printStackTrace();
	        } catch (Exception ex) {
	            System.out.println("An unexpected error occurred.");
	            ex.printStackTrace();
	        }

	        return isExist;
	    }
	    
	    public ArrayList<Tour> getAllTours() {
			ArrayList<Tour> result = new ArrayList<Tour>();
			
			try {
				conn = DBContext.getConnection();
				Statement stm = conn.createStatement();
				String query = "select * from tour";
				ResultSet rs = stm.executeQuery(query);
				
				while (rs.next()) {
					Tour tour = new Tour();
					tour.setId(rs.getInt(1));
					tour.setLocation(rs.getString(2));
					tour.setDescription(rs.getString(3));
					tour.setNumber_day(rs.getString(4));
					tour.setImage(rs.getString(5));
					tour.setPrice(rs.getString(6));
					tour.setDetail_description(rs.getString(7));
					tour.setVideo_link(rs.getString(8));
					result.add(tour);
				}
				
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
	
	        public Tour getTourbyId(int tourId) {
	        	
	            Tour tour = null;
	            
	            try  {
	            	 conn = DBContext.getConnection();
	                String query = "SELECT * FROM tour WHERE tour_id = ?";
	                try (PreparedStatement statement = conn.prepareStatement(query)) {
	                    statement.setInt(1, tourId);
	                    try (ResultSet resultSet = statement.executeQuery()) {
	                        if (resultSet.next()) {
	                            tour = new Tour();
	                            tour.setId(resultSet.getInt("tour_id"));
	                            tour.setLocation(resultSet.getString("location"));
	                            tour.setDescription(resultSet.getString("description"));
	                            tour.setNumber_day(resultSet.getString("number_day"));
	                            tour.setImage(resultSet.getString("image"));
	                            tour.setPrice(resultSet.getString("Price"));
	                            tour.setDetail_description(resultSet.getString("detail_description"));
	        					tour.setVideo_link(resultSet.getString("video_link"));
	                        }
	                    }
	                }
	            } catch (SQLException  | ClassNotFoundException e) {
	                e.printStackTrace();
	            }

	            return tour;
	        }
	        public boolean updateTourDAO( String location, String description, String numberOfDays, String image,String price,String detail_description,String video_link,int id) {
	            boolean success = false;
	            try {
	            	conn = DBContext.getConnection();
	                String query = "UPDATE tour SET location = ?, description = ?, number_day = ?, image = ? , Price = ? WHERE tour_id = ?";
	                PreparedStatement preparedStatement = conn.prepareStatement(query);
	                preparedStatement.setString(1, location);
	                preparedStatement.setString(2, description);
	                preparedStatement.setString(3, numberOfDays);
	                preparedStatement.setString(4, image);
	                preparedStatement.setString(5, price);
	                preparedStatement.setString(6, detail_description);
	                preparedStatement.setString(7, video_link);
	                preparedStatement.setInt(8,id);
	                int rowsUpdated = preparedStatement.executeUpdate();
	                if (rowsUpdated > 0) {
	                    success = true;
	                }
	            } catch (SQLException | ClassNotFoundException  e) {
	                e.printStackTrace();
	            }
	            return success;
	        }
	        public boolean deleteDAO(int id) {
	        	boolean success = false;
	        	 try {
	        		    conn = DBContext.getConnection();
		                String query = "DELETE FROM tour WHERE tour_id = ?";
		                PreparedStatement preparedStatement = conn.prepareStatement(query);
		              
		                preparedStatement.setInt(1,id);
		                int rowsUpdated = preparedStatement.executeUpdate();
		                if (rowsUpdated > 0) {
		                    success = true;
		                }
		            } catch (SQLException | ClassNotFoundException  e) {
		                e.printStackTrace();
		            }
	        	 return success;
	        }
	      
	        public ArrayList<Booking> getAllBookingDAO() {
	            ArrayList<Booking> bookings = new ArrayList<Booking>();

	            try {
	            	conn = DBContext.getConnection();
	                Statement stmt = conn.createStatement();
	                String query = "SELECT booking_id, full_name, location, phone_number, email, booking_date, num_participants " +
	                        "FROM booking " +
	                        "JOIN user ON booking.user_id = user.user_id " +
	                        "JOIN tour ON booking.tour_id = tour.tour_id";
	                ResultSet rs = stmt.executeQuery(query);

	                while (rs.next()) {
	                    int bookingId = rs.getInt("booking_id");
	                    String fullName = rs.getString("full_name");
	                    String location = rs.getString("location");
	                    String phoneNumber = rs.getString("phone_number");
	                    String email = rs.getString("email");
	                    Date bookingDate = rs.getDate("booking_date");
	                    int numParticipants = rs.getInt("num_participants");

	                    Booking booking = new Booking(bookingId, fullName, location, phoneNumber, email, bookingDate, numParticipants);
	                    bookings.add(booking);
	                }
	            } catch (SQLException | ClassNotFoundException e) {
	                e.printStackTrace();
	            }

	            return bookings;
	        }
	        
	    public int getIDUserByUsername(String username) {
			int id = 0;
			try {
	            conn = DBContext.getConnection();
				Statement stm = conn.createStatement();
				String query = "select user_id from user where username = '"+username+"'";
				ResultSet rs = stm.executeQuery(query);
				
				if (rs.next()) {
					id = rs.getInt("user_id");
				}
				
				stm.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return id;
		}
}

