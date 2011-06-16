package controllers;

import jobs.Commentnotifyer;
import models.*;
import play.Logger;
import play.Play;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.io.File;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import play.data.validation.Required;
import play.data.validation .Valid;
import play.mvc.Controller;
import services.DataLayer;

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
	public static void getCommentaires(long postId) {
		Post post=DataLayer.getPostById(postId);
        post.init();
		renderTemplate("Administration/commentaires.html", post);
	}
	public static void validerCommentaire(long postId,int commentNumber) {
		Post post=DataLayer.getPostById(postId);
        post.comments.get(commentNumber).status= !post.comments.get(commentNumber).status;
		post.comments.get(commentNumber).save();
        if(post.comments.get(commentNumber).status){
            new Commentnotifyer(post.comments.get(commentNumber),postId).now();
        }
		post.save();
		getCommentaires(postId);
	}
    public static void supprimerCommentaire(long postId,int commentNumber) {
		Post post=DataLayer.getPostById(postId);
        Comment comment=post.comments.get(commentNumber);
        post.comments.set(commentNumber,null);
        post.save();
        comment.delete();
		getCommentaires(postId);
	}
    public static void validerArticle(long postId) {
		Post post=DataLayer.getPostById(postId);
		post.status= !post.status;
		post.save();
		articles();
	}
    public static void nouveauArticle(long postId){
        Post post;
        if(postId!=-1){
            post = DataLayer.getPostById(postId);
        }else{
            post=new Post();
            post.id=(long)-1;
        }
        List<Categorie> categories=DataLayer.getAllCategories();
        render(categories,post);
    }
    public static void nouvelleImage(){
        File images=Play.getFile("/public/images/blog");
        render(images);
    }
    public static void supprimerImage(String file){
        notFoundIfNull(file);
        File photo= Play.getFile("/public/images/blog/"+file);
        if(!photo.delete())
            Logger.info(file+" can't be deleted");
        nouvelleImage();
    }
    public static void ajouterImage(File photo){
        notFoundIfNull(photo);
        File newFile= Play.getFile("/public/images/blog/"+photo.getName());
        if(!photo.renameTo(newFile))
             Logger.info(photo.getName()+" can't be renamed");
        if(!photo.delete())
            Logger.info(photo.getName()+" can't be deleted");
        nouvelleImage();
    }
    public static void ajouterArticle(@Required String title,@Required String content,@Required String strtags,@Required long categorieId,@Required  long postId){
        if(validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            nouveauArticle(-1);
        }
        Post post;
        if(postId!=-1){
             post = DataLayer.getPostById(postId) ;
        }else{
            post = new Post();
        }
        //setting the date
        post.lastchange=new Date();
        post.title= title;
        post.content=content;
        post.status=false;
        String [] tags=strtags.split(";");
        for(String strtag:tags){
            Tag tag=DataLayer.getTagByName(strtag.trim());
            if(tag==null){
                   tag = new Tag();
                   tag.tag=strtag.trim();
                   tag.save();
              }
            tag.init();
            if(!post.tags.contains(tag)){
              post.tags.add(tag);
            }

        }
        Categorie categorie=DataLayer.getCategorieById(categorieId);
        if(categorie != null){
            //new Post
           if(post.categorie.getId()==null){
             post.categorie=categorie;
             post.save();
             categorie.posts.add(post);
             categorie.save();
          //old post
           }else{
           //not changed categorie
           if(categorie.getId().equals(post.categorie.getId())){
               post.save();
           }else{
              //categorie changed
              Categorie cat=post.categorie;
              int i=cat.posts.indexOf(post);
              cat.posts.remove(i);
              cat.save();
              post.categorie=categorie;
              post.save();
              categorie.posts.add(post);
              categorie.save();
           }
           }
        }
        articles();
    }
    public static void supprimerArticle(long postId){
        Post post=DataLayer.getPostById(postId);
        if(post!=null){
        Categorie categorie=post.categorie;
        if(categorie!=null){
        int i=categorie.posts.indexOf(post);
        categorie.posts.remove(i);
        categorie.save();
        }
        post.categorie=null;
        post.save();
        post.delete();
        }
        articles();
    }
    public static void ajouterCategorie(String name){
           Categorie categorie=new Categorie();
           categorie.name=name.trim();
           categorie.save();
           nouvelleCategorie();
    }
    public static void nouvelleCategorie(){
           List<Categorie> categories;
           categories=Categorie.findAll();
           render(categories);
    }
    public static void supprimerCategorie(long catId){
           Categorie categorie=DataLayer.getCategorieById(catId);
           if(categorie!=null){
              categorie.delete();
           }
           nouvelleCategorie();
    }
    public static void supprimerTag(String stag){
           Tag tag=DataLayer.getTagByName(stag);
           tag.delete();
            tags();
    }

    public static void tags() {
       List<Tag> tags=DataLayer.getAllTags();
        render(tags);
    }
    public static void clearCache() {
       Cache.clear();
        articles();
    }
}
