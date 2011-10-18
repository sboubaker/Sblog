package controllers;

import java.util.List;

import jobs.NewCommentnotifyer;
import models.Post;
import models.Stat;
import org.apache.commons.mail.SimpleEmail;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.libs.Mail;
import play.mvc.Before;
import play.mvc.Http;
import services.DataLayer;

import java.util.Date;

public class Consultation extends GenericController {

    public static void stat(String stat){
       if (!Security.isConnected()){
           Stat st=new Stat();
           st.date=new Date();
           st.url=stat.substring(stat.indexOf("#")+1,stat.length()) ;
           st.ip=stat.substring(0,stat.indexOf("#")) ;
           st.save();
         }
    }
    public static void index() {
        List<Post> list = DataLayer.getPosts(false);
        if (list.size() >= 5) {
            list = list.subList(0, 4);
        }
        for (Post post : list)
            post.init();
        String title="Geek 2.0, Un blog sur Java, J2ee et Cloud";
        renderTemplate("Consultation/articles.html", list,title);
    }

    public static void contact(int i) {
        render(i);
    }


    public static void about() {
        render();
    }

    public static void articles(String type, String value) {
        List<Post> list = null;
        String title=null;
        if (type.equals("tag")) {
           list = DataLayer.getPostsByTag(value);
        } else {
            list = DataLayer.getPostsByCategorie(value);
        }
         title=value;
        if ((list == null) || (list.size() == 0))
            list = DataLayer.getPosts(false);

        render(list,title);
    }

    public static void article(String title, int i) {
        Post post = DataLayer.getPostByTitle(title);
        if (!Security.isConnected())
        {
            post.nshow += 1;
            post.save();
        }
        post.init();
        render(post, i,title);
    }

    public static void addComment(@Valid models.Comment comment, String title) {
        Post post = DataLayer.getPostByTitle(title);
        comment.date = new Date();
        comment.status = false;
        comment.save();
        post.comments.add(comment);
        post.save();
        new NewCommentnotifyer(comment, title).doJob();
        article(title, 1);
    }

    public static void sendMail(@Required String email, @Required String name, @Required String subject, @Required String message) {
        if (validation.hasErrors()) {
            params.flash();
            validation.keep();
            contact(0);
        }
        SimpleEmail mail = new SimpleEmail();
        try {
            mail.setFrom(email);
            mail.setSubject(subject);
            mail.addTo("boubaker.sabri@gmail.com");
            mail.setMsg("Name:" + name + "\nMessage from geek 2.0: " + message);
            Mail.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        contact(1);
    }

}