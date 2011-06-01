package models;

import java.util.HashSet;
import java.util.Set;

public class Categorie {

	/** Field mapping. */
	public Long id;
	/** Field mapping. */
	public String name;
	/** Field mapping. */
	public Integer parentId;
	/** Field mapping. */
	public Set<PostCategories> postCategories = new HashSet<PostCategories>();

	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Categorie() {
		// Default constructor
	}

}
