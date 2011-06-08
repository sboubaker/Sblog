package controllers;

import java.util.List;

import models.Categorie;
import models.Post;
import models.Tag;
import models.UiObject;
import play.data.validation.Required;
import play.data.validation .Valid;
import play.mvc.Before;
import play.mvc.Controller;
import services.DataLayer;

import java.util.Date;

public class Consultation extends Controller {

	@Before
	static void setConnectedUser() {
		UiObject uiObject=new UiObject();
		uiObject.posts=DataLayer.getnewPosts(3);
		uiObject.tags=DataLayer.getAllTags();
		uiObject.categories=DataLayer.getAllCategories();
		renderArgs.put("uiObject", uiObject);
	} 
	public static void index() {
		List<Post> list=DataLayer.getPosts(false);
		renderTemplate("Consultation/articles.html",list);
	}
	public static void contact() {
		render();
	}
	public static void articles(int type, String value) {
		List<Post> list=null;
		switch(type){
		case 1: list= DataLayer.getPostsByCategorie(value);break;
		case 2: list= DataLayer.getPostsByTag(value);break;
		default: break;
		}
		if(list!=null)
			list=DataLayer.getPosts(false);
		render(list);
	}

	public static void article(String id) {
		Post post = DataLayer.getPostById(id);
		post.nshow++;
		post.save();
		render(post);
	}
	public static void addComment(@Valid models.Comment comment,String post_id) {
		Post post = DataLayer.getPostById(post_id);
		//TODO validation result
		comment.date=new Date();
		comment.status=false;
		post.comments.add(comment);
		post.save();
		String message="Votre commentaire sera publi� apr�s sa validation. Merci";
		article(post_id);
	}

	
}