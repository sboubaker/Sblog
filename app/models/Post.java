package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="post")
public class Post extends Model {

	/** Field mapping. */
	@Required
    @Column(length = 65535)
	public String content;
	/** Field mapping. */
	public Date lastchange;
	/** Field mapping. */
	@Required
	public boolean status;
	/** Field mapping. */
	@Required
	public String title;
	@ManyToOne
	@JoinColumn(name = "id_user")
	public User user;
	/** Field mapping. */
	@ManyToOne
    @JoinColumn(name = "id_categorie")
	public Categorie categorie = new Categorie();
	/** Field mapping. */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "post_tag", 
		joinColumns = { 
			@JoinColumn(name = "id_post") 
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "id_tag") 
		}
	)
	public List<Tag> tags = new ArrayList<Tag>();
	/** Field mapping. */
	@OneToMany(cascade = CascadeType.REMOVE)
	public List<Comment> comments = new ArrayList<Comment>();
	/** number of show **/
	public int nshow;
    /** nuber of valid comments **/
    @Transient
    public int vcomments;
	public void init(){
        vcomments=0;
        for(Comment comment:comments){
              if(comment.status)
                  vcomments++;
        }
    }
}
