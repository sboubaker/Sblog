package jobs;

import models.Comment;
import models.Post;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import play.jobs.Job;
import services.DataLayer;
import play.libs.Mail;
/**
 * User: boubaker
 * Date: 10/06/11
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class Commentnotifyer extends Job {
    private Comment comment;
    private long postId;

    public void doJob() {
        SimpleEmail email =null;
        Post post= DataLayer.getPostById(postId);
        for(Comment cmt:post.comments){
               if(cmt.subscribe && !cmt.getId().equals(comment.getId())){
                try{
                email = new SimpleEmail();
                email.setFrom("Geek 2.0");
                email.addTo(cmt.usermail);
                email.setSubject("Nouveau commentaire sur: "+post.title);
                email.setMsg(comment.username+" a ajouté un nouveau commentaire");
                Mail.send(email);
                }catch(Exception e){
                    e.printStackTrace();
                }

               }
        }
        }
    public Commentnotifyer(Comment comment,long postId){
              this.comment=comment;
              this.postId=postId;
    }
}
