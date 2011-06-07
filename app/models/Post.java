package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.data.validation.Required;
import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity("posts")
public class Post extends Model {

	/** Field mapping. */
	@Required
	public String content;
	/** Field mapping. */
	public Date lastchange;
	/** Field mapping. */
	@Required
	public boolean status;
	/** Field mapping. */
	@Required
	public String title;
	@Reference
	public User user;
	/** Field mapping. */
	@Reference
	public List<Categorie> categories = new ArrayList<Categorie>();
	/** Field mapping. */
	@Reference
	public List<Tag> tags = new ArrayList<Tag>();
	/** Field mapping. */
	@Embedded
	public List<Comment> comments = new ArrayList<Comment>();
	/** number of show **/
	public int nshow;
	/**
	 * Default constructor
	 */
	public Post() {
		// Default constructor
	}

	/**
	 * constructor
	 */
	public Post(String content, Date lastchange, String title) {
		this.content = content;
		this.lastchange = lastchange;
		this.title = title;
	}

}
