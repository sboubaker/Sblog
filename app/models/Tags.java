package models;

import java.util.HashSet;
import java.util.Set;

public class Tags {

	/** Field mapping. */
	public Long id;
	/** Field mapping. */
	public Set<PostTags> postTags = new HashSet<PostTags>();

	/** Field mapping. */
	public String tag;
	/** Field mapping. */
	public Long tagId;

	/**
	 * Default constructor
	 */
	public Tags() {
		// Default constructor
	}

	/**
	 * Constructor taking a given ID.
	 * 
	 * @param id
	 *            to set
	 */
	public Tags(Long id) {
		this.id = id;
	}

}
