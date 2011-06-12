package controllers;

import java.util.List;

import jobs.Commentnotifyer;
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
public class Consultation extends GenericController {

	public static void index() {
		List<Post> list=DataLayer.getPosts(false);
        for(Post post:list)
            post.init();
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
		if(list==null)
			list=DataLayer.getPosts(false);
        for(Post post:list)
            post.init();
		render(list);
	}

	public static void article(long id) {
		Post post = DataLayer.getPostById(id);
		post.nshow++;
        post.save();
        post.init();
		render(post);
	}
	public static void addComment(@Valid models.Comment comment,long post_id) {
		Post post = DataLayer.getPostById(post_id);
		//TODO validation result
		comment.date=new Date();
		comment.status=false;
		comment.number=post.comments.size();
		comment.save();
		post.comments.add(comment);
		post.save();
		article(post_id);
	}

	
}