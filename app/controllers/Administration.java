package controllers;

import jobs.Commentnotifyer;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.io.File;
import java.io.PipedOutputStream;
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
public class Administration extends GenericController {
	@Before
	static void setConnectedUser() {
		if (!Security.isConnected())
		Consultation.index();
	}
	public static void articles() {
		List<Post> list=DataLayer.getPosts(true);
        for(Post post:list)
            post.init();
		render(list);
	}
	public static void commentaires(long postid) {
		Post post=DataLayer.getPostById(postid);
        post.init();
		render(post);
	}
	public static void validerCommentaire(long postid,int commentnumber) {
		Post post=DataLayer.getPostById(postid);
        post.comments.get(commentnumber).status= !post.comments.get(commentnumber).status;
		post.comments.get(commentnumber).save();
        if(post.comments.get(commentnumber).status){
            new Commentnotifyer(post.comments.get(commentnumber),postid).now();
        }
		post.save();
		commentaires(postid);
	}
    public static void validerPost(long postid) {
		Post post=DataLayer.getPostById(postid);
		post.status= !post.status;
		post.save();
		articles();
	}
    public static void nouveauArticle(){
        List<Categorie> categories=DataLayer.getAllCategories();
        render(categories);
    }
    public static void nouvelleImage(){
        File images=Play.getFile("/public/images/blog");
        render(images);
    }
    public static void supprimerImage(String file){
        notFoundIfNull(file);
        File photo= Play.getFile("/public/images/blog/"+file);
        photo.delete();
        nouvelleImage();

    }
    public static void ajouterImage(File photo){

        notFoundIfNull(photo);
        File newFile= Play.getFile("/public/images/blog/"+photo.getName());
        photo.renameTo(newFile);
        photo.delete();
        nouvelleImage();

    }
    public static void ajouterArticle(@Required String title,@Required String content,@Required String strtags,@Required String categorieid){
        if(validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            nouveauArticle();
        }
        Post post=new Post();
        Categorie categorie=DataLayer.getCategorieById(Long.parseLong(categorieid));
        if(categorie != null){
           post.categorie=categorie;
        }
        //setting the date
        post.lastchange=new Date();
        post.title= title;
        post.content=content;
        post.status=false;
        post.user=DataLayer.getUserByEmail(Security.connected());
        String [] tags=strtags.split(";");
        for(String strtag:tags){
            Tag tag=DataLayer.getTagByName(strtag.trim());
            if(tag==null){
                   tag = new Tag();
                   tag.tag=strtag.trim();
                   tag.save();
              }
            post.tags.add(tag);
        }
        post.save();
        categorie.posts.add(post);
        categorie.save();
        articles();
    }
    public static void supprimerArticle(long postid){
        Post post=DataLayer.getPostById(postid);
        Categorie categorie=post.categorie;
        int i=categorie.posts.indexOf(post);
        categorie.posts.remove(i);
        categorie.save();
        post.categorie=null;
        post.save();
        post.delete();
        articles();
    }
    public static void ajouterCategorie(String name){
           Categorie categorie=new Categorie();
           categorie.name=name.trim();
           categorie.save();
           nouvelleCategorie();
    }
    public static void nouvelleCategorie(){
           List<Categorie> categories=null;
           categories=Categorie.findAll();
           render(categories);
    }
}
