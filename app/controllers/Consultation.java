package controllers;

import java.util.List;

import jobs.NewCommentnotifyer;
import models.Post;
import org.apache.commons.mail.SimpleEmail;
import play.data.validation.Required;
import play.data.validation .Valid;
import play.libs.Mail;
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
    public static void about() {
		render();
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

	public static void article(long id,int i) {
		Post post = DataLayer.getPostById(id);
		post.nshow++;
        post.save();
        post.init();
        int succ=i;
		render(post,succ);
	}
	public static void addComment(@Valid models.Comment comment,long post_id) {
		Post post = DataLayer.getPostById(post_id);
		comment.date=new Date();
		comment.status=false;
		comment.save();
		post.comments.add(comment);
		post.save();
        new NewCommentnotifyer(comment,post_id).doJob();
        article(post_id, 1);
	}
    public static void sendMail(@Required String email,@Required String name,@Required String subject,@Required String message) {
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            contact(0);
        }
        SimpleEmail mail=new SimpleEmail();
        try{
        mail.setFrom(email);
        mail.setSubject(subject);
        mail.addTo("boubaker.sabri@gmail.com");
        mail.setMsg("Name:"+name+"\nMessage from geek 2.0: "+message);
        Mail.send(mail);
        }catch (Exception e){
            e.printStackTrace();
        }
        contact(1);
	}
	
}