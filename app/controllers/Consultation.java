package controllers;

import java.util.List;

import models.Categorie;
import models.Post;
import models.Tag;
import play.data.validation.Required;
import play.mvc.Controller;
import services.DataLayer;

public class Consultation extends Controller {

	public static void index() {
		List<Tag> tags = DataLayer.getAllTags();
		List<Categorie> categories=DataLayer.getAllCategories();
		render(tags,categories);
	}

	public static void articles(int type, String value) {
		List<Post> list=null;
		switch(type){
		case 1: list= DataLayer.getPostsByCategorie(value);break;
		case 2: list= DataLayer.getPostsByTag(value);break;
		default: break;
		}
		if(list!=null)
			list=DataLayer.getAllPosts();
		List<Tag> tags = DataLayer.getAllTags();
		List<Categorie> categories=DataLayer.getAllCategories();
		render(list,tags,categories);
	}

	public static void article(String id) {
		Post post = DataLayer.getPostById(id);
		List<Tag> tags = DataLayer.getAllTags();
		List<Categorie> categories=DataLayer.getAllCategories();
		render(post,tags,categories);
	}
}