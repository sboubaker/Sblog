package controllers;


import play.Play;
import services.DataLayer;

public class Security extends Secure.Security {
	 
	/**
     * This method is called during the authentication process. This is where you check if
     * the user is allowed to log in into the system. This is the actual authentication process
     * against a third party system (most of the time a DB).
     *
     * @param username l'@ mail de l'utilisateur
     * @param password  le pwd ed l'utilisateur
     * @return true if the authentication process succeeded
     */
    static boolean authentify(String username, String password) {
        return Play.configuration.getProperty("application.admin").equals(username)
                && Play.configuration.getProperty("application.adminpwd").equals(password);
    }
    /**
     * This method checks that a profile is allowed to view this page/method. This method is called prior
     * to the method's controller annotated with the @Check method. 
     *
     * @param profile le profile de l'utilisateur connecté
     * @return true if you are allowed to execute this controller method.
     */
    static boolean check(String profile) {
    	return Play.configuration.getProperty("application.admin.role").equals(profile);
    }
}

