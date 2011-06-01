package models;

import java.util.HashSet;
import java.util.Set;

public class Tag {

	
	public Long id;
	
	public Set<PostTags> postTags = new HashSet<PostTags>();

	
	public String tag;
	
	public Long tagId;

	/**
	 * Default constructor
	 */
	public Tag() {
		// Default constructor
	}

	/**
	 * Constructor taking a given ID.
	 * 
	 * @param id
	 * 
	 */
	public Tag(Long id) {
		this.id = id;
	}

}
