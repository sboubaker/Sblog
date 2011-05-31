package models;

import java.util.Date;

public class Comments {

	public String content;

	/** Field mapping. */

	public Date date;

	/** Field mapping. */

	public String status;

	/** Field mapping. */

	public String usermail;

	/** Field mapping. */

	public String username;

	public Comments() {
		// Default constructor
	}

	/**
	 * Constructor taking a given ID.
	 * 
	 * @param content
	 *            String object;
	 * @param date
	 *            Date object;
	 * @param id
	 *            CommentsPK object;
	 */
	public Comments(String content, Date date) {

		this.content = content;
		this.date = date;

	}
}
