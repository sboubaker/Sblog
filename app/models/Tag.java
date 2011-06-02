package models;

import java.util.ArrayList;
import java.util.List;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;

@Entity("tags")
public class Tag extends Model {

	public List<Post> posts = new ArrayList<Post>();
	public String tag;

	/**
	 * Default constructor
	 */
	public Tag() {
		// Default constructor
	}
}
