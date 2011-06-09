package controllers;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import models.Categorie;
import models.Post;
import models.Tag;
import models.UiObject;
import play.data.validation.Required;
import play.data.validation .Valid;
import play.mvc.Controller;
import services.DataLayer;

import models.User;

@With(Security.class)
public class Administration extends Controller {
	@Before
	static void setConnectedUser() {
		UiObject uiObject=new UiObject();
		uiObject.posts=DataLayer.getnewPosts(3);
		uiObject.tags=DataLayer.getAllTags();
		uiObject.categories=DataLayer.getAllCategories();
		renderArgs.put("uiObject", uiObject);
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
	public static void valider(String postid,int commentnumber) {
		Post post=DataLayer.getPostById(postid);
		post.comments.get(commentnumber).status= !post.comments.get(commentnumber).status;
		post.save();
		comments(postid);
	}
}
