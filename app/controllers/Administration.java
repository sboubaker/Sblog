package controllers;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Security.class)
public class Administration extends Controller {
	@Before
	static void setConnectedUser() {
		if (!Security.isConnected())
		Consultation.index();
	}

	public static void index() {
		render();
	}
}
