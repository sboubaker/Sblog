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
import play.data.validation.Required;
import play.data.validation .Valid;
import play.mvc.Controller;
import services.DataLayer;

import models.User;

@With(Security.class)
public class Administration extends Controller {
	@Before
	static void setConnectedUser() {
		if (!Security.isConnected())
		Consultation.index();
	}

	public static void articles() {
		List<Post> list=DataLayer.getAllPosts();
		render(list);
	}
	public static void nouveauArticle(){
		List<Categorie> categories=DataLayer.getAllCategories();
		render(categories);
	}
	public static void addPost(@Valid Post post,String categorieid,String strtags){
		Categorie categorie=DataLayer.getCategorieById(categorieid);
		if(categorie==null){
		nouveauArticle();
		}
		if(strtags==null){
		nouveauArticle();
		}
		String tags[]=strtags.split(";");
		List<Tag> list=new ArrayList<Tag>();
		for(String stags:tags){
		Tag tag=DataLayer.getTagByName(stags.trim());
		if(tag==null){
		  tag=new Tag();
		  tag.tag=stags.trim();
		  tag.save();
		}
		list.add(tag);
		}
		post.tags=list;
		post.categories.add(categorie);
		post.lastchange=new Date();
		post.status="NOK";
		post.nshow=0;
		User user=DataLayer.getUserByEmail(Security.connected());
		post.user=user;
		post.save();
		articles();
	}
}
