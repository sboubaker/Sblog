package models;

public class PostCategories {

	/** Field mapping. */
	public Categorie category;
	/** Field mapping. */
	public Long id;
	/** Field mapping. */
	public Post post;

	public PostCategories() {
		// Default constructor
	}

	/**
	 * Constructor taking a given ID.
	 * 
	 * @param id
	 *            to set
	 */
	public PostCategories(Long id) {
		this.id = id;
	}

}
