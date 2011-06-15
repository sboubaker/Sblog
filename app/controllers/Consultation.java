package controllers;

import java.util.List;

import jobs.Commentnotifyer;
import models.Categorie;
import models.Post;
import models.Tag;
import models.UiObject;
import org.apache.commons.mail.SimpleEmail;
import play.data.validation.Required;
import play.data.validation .Valid;
import play.libs.Mail;
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
	public static void contact(int i) {
		int succ=i;
        render(succ);
	}
	public static void articles(int type, String value) {
		List<Post> list=null;
		switch(type){
		case 1: list= DataLayer.getPostsByCategorie(value);break;
		case 2: list= DataLayer.getPostsByTag(value);break;
		default: break;
		}
		if((list==null) || (list.size()==0))
			list=DataLayer.getPosts(false);
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
    public static void sendMail(@Required String email,@Required String name,@Required String subject,@Required String message) {
        if(validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            contact(0);
        }
        SimpleEmail mail=new SimpleEmail();
        try{
        mail.setFrom(email);
        mail.setSubject(subject);
        mail.addTo("boubaker.sabri@gmail.com");
        mail.setMsg("Name:"+name+"\n"+message);
        Mail.send(mail);
        }catch (Exception e){
            e.printStackTrace();
        }
        contact(1);
	}
	
}