package controllers;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

import models.Categorie;
import models.Post;
import models.Tag;
import play.data.validation.Required;
import play.data.validation .Valid;
import play.mvc.Controller;
import services.DataLayer;

@With(Security.class)
public class Administration extends Controller {
	@Before
	static void setConnectedUser() {
		if (!Security.isConnected())
		Consultation.index();
	}

	public static void articles() {
		List<Post> list=DataLayer.getPosts(true);
		render(list);
	}
	public static void comments(String postid) {
		Post post=DataLayer.getPostById(postid);
		render(post);
	}
}
