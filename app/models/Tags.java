package models;

import java.util.HashSet;
import java.util.Set;

public class Tags {

	
	public Long id;
	
	public Set<PostTags> postTags = new HashSet<PostTags>();

	
	public String tag;
	
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
	 * 
	 */
	public Tags(Long id) {
		this.id = id;
	}

}
