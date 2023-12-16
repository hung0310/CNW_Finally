package Model.BO;


import Model.DAO.checkLoginDAO;

public class checkLoginBO {
checkLoginDAO checkLoginDAO = new checkLoginDAO();

public boolean isValidUser(String username, String password, String role) {
	return checkLoginDAO.isExitUser(username,password,role);
	
}



}
