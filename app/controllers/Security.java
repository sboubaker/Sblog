package controllers;



import models.User;
import services.DataLayer;
import util.Constantes;

public class Security extends Secure.Security {
	 
	/**
     * This method is called during the authentication process. This is where you check if
     * the user is allowed to log in into the system. This is the actual authentication process
     * against a third party system (most of the time a DB).
     *
     * @param username
     * @param password
     * @return true if the authentication process succeeded
     */
    static boolean authentify(String username, String password) {
        User user=DataLayer.getUserByEmail(username);
        if(user!=null){
        	return user.userpwd.equals(password);
        }
        return false;
    }
    /**
     * This method checks that a profile is allowed to view this page/method. This method is called prior
     * to the method's controller annotated with the @Check method. 
     *
     * @param profile
     * @return true if you are allowed to execute this controller method.
     */
    static boolean check(String profile) {
    	User user=DataLayer.getUserByEmail(connected());
        if(user!=null){
        	return user.role.equals(profile);
        }
        return false;
        	
    }
}
