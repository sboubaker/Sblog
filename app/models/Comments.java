package models;

import java.util.Date;

public class Comments {

	public String content;

	

	public Date date;

	

	public String status;

	

	public String usermail;

	

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
