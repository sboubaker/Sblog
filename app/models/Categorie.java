package models;

import java.util.ArrayList;
import java.util.List;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity("categories")
public class Categorie extends Model {

	/** Field mapping. */
	public String name;
	/** Field mapping. */
	public Integer parentId;
	/** Field mapping. */
	@Reference
	public List<Post> posts = new ArrayList<Post>();

	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Categorie() {
		// Default constructor
	}
}
