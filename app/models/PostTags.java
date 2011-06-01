package models;


public class PostTags {

	/** Field mapping. */
	public Long id;
	/** Field mapping. */
	public Post post;
	/** Field mapping. */
	public Tag tag;

	public PostTags() {
		// Default constructor
	}

	/**
	 * Constructor taking a given ID.
	 * 
	 * @param id
	 *            to set
	 */
	public PostTags(Long id) {
		this.id = id;
	}

}
