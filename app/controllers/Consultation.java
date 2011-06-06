package controllers;

import java.util.List;

import models.Categorie;
import models.Post;
import models.Tag;
import play.data.validation.Required;
import play.data.validation .Valid;
import play.mvc.Controller;
import services.DataLayer;

import java.util.Date;

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
		post.nshow++;
		post.save();
		List<Tag> tags = DataLayer.getAllTags();
		List<Categorie> categories=DataLayer.getAllCategories();
		render(post,tags,categories);
	}
	public static void addComment(@Valid models.Comment comment,String post_id) {
		Post post = DataLayer.getPostById(post_id);
		//TODO validation result
		comment.date=new Date();
		comment.status="NOK";
		post.comments.add(comment);
		post.save();
		String message="Votre commentaire sera publié aprés sa validation. Merci";
		article(post_id);
	}

	
}