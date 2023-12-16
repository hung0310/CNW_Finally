package Model.BO;

import java.util.ArrayList;

import Model.BEAN.Booking;
import Model.BEAN.Tour;
import Model.DAO.functionDAO;

public class functionBO {
	functionDAO funcDAO = new functionDAO();
	public boolean addTour(String location, String description, String number_day, String filepath,String price,String detail_description,String video_link)  {
		return funcDAO.addTour(location,  description,  number_day,  filepath,price,detail_description,video_link);
		
	}
	public ArrayList<Tour> getAllTour(){
		return funcDAO.getAllTours();
	}
	public Tour getTourbyId(int idtour) {
		return funcDAO.getTourbyId(idtour);
	}
	 public boolean updateTourBO(String location, String description, String number_day, String filepath,String price,String detail_description,String video_link,int id) { 
		 return funcDAO.updateTourDAO(location, description, number_day, filepath,price,detail_description,video_link,id);
	 }
	 public boolean deleteBO(int id) {
		 return funcDAO.deleteDAO(id);
	 }
	 public ArrayList<Booking> getAllBookingBO() {
		 return funcDAO.getAllBookingDAO();
	 }
}
