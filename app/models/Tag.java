package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

	/**
	 * Default constructor
	 */
	public Tag() {
		// Default constructor
	}
}
