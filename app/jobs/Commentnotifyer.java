package jobs;

import models.Comment;
import models.Post;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import play.jobs.Job;
import services.DataLayer;
import play.libs.Mail;
/**
 * Created by IntelliJ IDEA.
 * User: boubaker
 * Date: 10/06/11
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class Commentnotifyer extends Job {
    Email email = new SimpleEmail();
    public void doJob() {
             Mail.send(email);
        }
    public Commentnotifyer(Comment comment,long postId){

        Post post= DataLayer.getPostById(postId);
        for(Comment cmt:post.comments){
               if(cmt.subscribe && !cmt.getId().equals(comment.getId())){
                try{
                email.setFrom("boubaker.sabri@gmail.com");
                email.addTo(cmt.usermail);
                email.setSubject("Nouveau commentaire sur: "+post.title);
                email.setMsg(comment.username+" a ajouté un nouveau commentaire");
                }catch(Exception e){
                }

               }
        }
    }
}
