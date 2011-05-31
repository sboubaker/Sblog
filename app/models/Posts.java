package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Posts {

	/** Field mapping. */
	public String content;
	/** Field mapping. */
	public Long id;
	/** Field mapping. */
	public Date lastchange;
	/** Field mapping. */
	public Set<PostCategories> postCategories = new HashSet<PostCategories>();

	/** Field mapping. */
	public Set<PostTags> postTags = new HashSet<PostTags>();

	/** Field mapping. */
	public String status;
	/** Field mapping. */
	public String title;

	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Posts() {
		// Default constructor
	}

	/**
	 * Constructor taking a given ID.
	 * 
	 * @param id
	 *            to set
	 */
	public Posts(Long id) {
		this.id = id;
	}

	/**
	 * Constructor taking a given ID.
	 * 
	 * @param content
	 *            String object;
	 * @param id
	 *            Long object;
	 * @param lastchange
	 *            Date object;
	 * @param title
	 *            String object;
	 */
	public Posts(String content, Long id, Date lastchange, String title) {

		this.content = content;
		this.id = id;
		this.lastchange = lastchange;
		this.title = title;
	}
}
