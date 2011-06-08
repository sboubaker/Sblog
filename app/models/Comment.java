package models;

import java.util.Date;

import play.data.validation.Required;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Transient;

@Embedded
public class Comment{

	/** Field mapping. */
	@Required
	public String content;
	/** Field mapping. */
	@Required
	public Date date;
	/** Field mapping. */
	@Required
	public boolean status;
	/** Field mapping. */
	@Required
	public String usermail;
	/** Field mapping. */
	@Required
	public String username;
	
	public String usersite;
	
	public boolean subscribe;
	public Comment() {
		// Default constructor
	}

	public Comment(String content, Date date) {
		this.content = content;
		this.date = date;
	}
}
