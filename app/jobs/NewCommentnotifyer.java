package jobs;

import models.Comment;
import models.Post;
import org.apache.commons.mail.SimpleEmail;
import play.jobs.Job;
import play.libs.Mail;
import services.DataLayer;

/**
 * User: boubaker
 * Date: 10/06/11
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class NewCommentnotifyer extends Job {
    private Comment comment;
    private long postId;

    public void doJob() {
        SimpleEmail email =null;
        Post post= DataLayer.getPostById(postId);
                try{
                email = new SimpleEmail();
                email.setFrom("boubaker.sabri@gmail.com");
                email.addTo("boubaker.sabri@gmail.com");
                email.setSubject("Nouveau commentaire sur: "+post.title);
                email.setMsg(comment.username+" a ajouté un nouveau commentaire le "+comment.date);
                Mail.send(email);
                }catch(Exception e){
                    e.printStackTrace();
                }


    }
    public NewCommentnotifyer(Comment comment, long postId){
              this.comment=comment;
              this.postId=postId;
    }
}
