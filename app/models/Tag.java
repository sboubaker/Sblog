package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
@Table(name="tag")
public class Tag extends Model {
	
	@ManyToMany
	@JoinTable(
		name = "post_tag", 
		joinColumns = { 
			@JoinColumn(name = "id_tag") 
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "id_post") 
		}
	)
	public List<Post> posts = new ArrayList<Post>();
	public String tag;
    @Transient
    public int vposts;

	/**
	 * Default constructor
	 */
	public Tag() {
		// Default constructor
	}
    public void init(){
         vposts=0;
        for(Post post:posts)
            vposts++;
    }
}
